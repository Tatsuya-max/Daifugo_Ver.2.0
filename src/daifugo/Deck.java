package daifugo;

import java.util.ArrayList;
import java.util.Random;

public class Deck
{
  private ArrayList<Card> cards;

  public Deck(int numberOfPlayers)
  {
    cards = new ArrayList<Card>();
    // fill up the deck with number cards
    for (int i = 0; i < 52; i++)
      cards.add(new Card(i));

    shuffle();

    // burn cards for rounding based on number of players
    int burnCards = 54 % numberOfPlayers;
    for (int i = 0; i < burnCards; i++)
      nextCard();

    // add two jokers
    cards.add(new Card(52));
    cards.add(new Card(53));

    shuffle();
  }

  public Card nextCard()
  {
    if (cards.size() <= 0) {
      System.err.println("The deck is Empty");
      return null;
    }
    return cards.remove(cards.size() - 1);
  }

  public boolean isEmpty()
  {
    return cards.size() <= 0;
  }

  private void shuffle()
  {
    for (int i = 1; i <= 3; i++) {
      riffleShuffle();
      if (i == 2) stripCut();
    }
  }

  private void riffleShuffle()
  {
    ArrayList<Card> newCards = new ArrayList<>();
    int j = 0;
    int k = cards.size() / 2;
    for (int i = 0; i < cards.size(); i++) {
      if (i % 2 == 0) {
        newCards.add(cards.get(j));
        j++;
      } else {
        newCards.add(cards.get(k));
        k++;
      }
    }
    cards = newCards;
  }

  private void stripCut()
  {
    ArrayList<Card> newCards = new ArrayList<>();
    Random random = new Random();
    while (cards.size() > 0) {
      int j = random.nextInt(10) + 9;
      if (j > cards.size())
        j = cards.size();
      for (int i = 0; i < j; i++) {
        Card card = cards.remove(cards.size() - j + i);
        newCards.add(card);
      }
    }
    cards = newCards;
  }

  public static void main(String args[])
  {
    System.out.println("test Deck.");
    final int totalPlayers = 4;
    Deck d = new Deck(totalPlayers);
    int i = 1;
    while (!d.isEmpty()) {
      System.out.print(i + ":");
      System.out.println(d.nextCard());
      i++;
    }
  }

}
