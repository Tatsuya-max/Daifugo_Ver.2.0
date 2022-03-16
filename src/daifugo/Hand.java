package daifugo;

import java.util.ArrayList;
import java.util.List;

public class Hand
{

  private List<Card> cards;
  private Player player;

  public Hand(Player player)
  {
    this.player = player;
    cards = new ArrayList<Card>();
  }

  public void addCard(Card c) { cards.add(c); }

  public void removeCard(Card c) { cards.remove(c); }

  public int size() { return cards.size(); }

  public boolean isEmpty() { return cards.size() <= 0; }

  public List<Card> getCards() { return cards; }

  public Player getPlayer() { return player; }

  public void removeCards(List<Card> cardList)
  {
    cards.removeAll(cardList);
  }

  public void sort()
  {
    sortBySuit();
    sortByRank();
  }

  private void sortBySuit()
  {
    int i;
    ArrayList<Card> newCards = new ArrayList<>();
    for (i = 0; i < cards.size(); i++) {
      Card c = cards.get(i);
      if (c.getSuit() == Card.Suit.SPADES)
        newCards.add(c);
    }
    for (i = 0; i < cards.size(); i++) {
      Card c = cards.get(i);
      if (c.getSuit() == Card.Suit.HEARTS)
        newCards.add(c);
    }
    for (i = 0; i < cards.size(); i++) {
      Card c = cards.get(i);
      if (c.getSuit() == Card.Suit.DIAMONDS)
        newCards.add(c);
    }
    for (i = 0; i < cards.size(); i++) {
      Card c = cards.get(i);
      if (c.getSuit() == Card.Suit.CLUBS)
        newCards.add(c);
    }
    for (i = 0; i < cards.size(); i++) {
      Card c = cards.get(i);
      if (c.getSuit() == Card.Suit.JOKERS)
        newCards.add(c);
    }
    cards = newCards;
  }

  private void sortByRank()
  {
    ArrayList<Card> newCards = new ArrayList<>();
    while (cards.size() > 0) {
      Card c = cards.get(0);
      for (int i = 1; i < cards.size(); i++) {
        Card c1 = cards.get(i);
        if (c1.getValue() < c.getValue())
          c = c1;
      }
      cards.remove(c);
      newCards.add(c);
    }
    cards = newCards;
  }

  public String toString()
  {
    String str = "[";
    for (int i = 0; i < cards.size()-1; i++)
      str += cards.get(i) + ", ";
    str += cards.get(cards.size()-1) + "]";
    return str;
  }

}
