package daifugo;

import java.util.ArrayList;
import java.util.List;

import daifugo.Card.Suit;

public class AIClass
{
  private final int NO_PAIR         = 0;
  private final int PAIR            = 1;
  private final int THREE_OF_A_KIND = 2;
  private final int FOUR_OF_A_KIND  = 3;

  /**
   * Constructor for initial move
   */
  public List<Card> chooseNextHand(Game.NumberOrder order, List<Card> cards)
  {
    Card[] chosenCards;
    List<List<Card>> handList1 = new ArrayList<List<Card>>();  // list of no-pair hands
    List<List<Card>> handList2 = new ArrayList<List<Card>>();  // list of pair hands
    List<List<Card>> handList3 = new ArrayList<List<Card>>();  // list of three-of-a-kind hands
    List<List<Card>> handList4 = new ArrayList<List<Card>>();  // list of four-of-a-kind hands

    List<Card> rankCards = new ArrayList<Card>();
    int numJokers = 0;
    boolean hasStrongHand = true;
    int i, j;

    int meanVal = 0;
    int totalVal = 0;
    for (i = 0; i < cards.size(); i++) {
      Card c = cards.get(i);
      if (c.getSuit() == Suit.JOKERS)
        numJokers++;
      else {
        rankCards.add(c);
        totalVal += c.getValue();
      }
    }
    meanVal = totalVal / (cards.size() - numJokers);

    // Create a list of all the hands for each category
    List<Card> tmpCards = new ArrayList<Card>();
    while ((i = 0) < rankCards.size()) {
      tmpCards.add(rankCards.get(i));
      j = i + 1;
      while (rankCards.get(i).getValue() == rankCards.get(j).getValue() &&
          j < rankCards.size()) {
        tmpCards.add(rankCards.get(j));
        j++;
      }
      switch (tmpCards.size()) {
      case 1:  handList1.add(tmpCards); break;
      case 2:  handList2.add(tmpCards); break;
      case 3:  handList3.add(tmpCards); break;
      default: handList4.add(tmpCards);
      }
      tmpCards.clear();
      i = j;
    }

    List<Card> tmpCards = new ArrayList<Card>();
    while ((i = 0) < rankCards.size()) {
      tmpCards.add(rankCards.get(i));
      for (j = i + 1; j < rankCards.size(); j++) {
        if (rankCards.get(i).getValue() == rankCards.get(j).getValue())
          tmpCards.add(rankCards.get(j));
        else {
          handList.get(tmpCards.size()-1).add(tmpCards.toArray(new Card[tmpCards.size()]));
          tmpCards.clear();
          i = j;
          break;
        }
      }
    }

    /*
     *  Pattern to make a revolution
     */
    // Return four of a kind hand
    if (order == Game.NumberOrder.ASCENDING && meanVal < 6
        && handList.get(FOUR_OF_A_KIND).size() > 0)
      return handList.get(FOUR_OF_A_KIND).get(0);
    else if (order == Game.NumberOrder.DESCENDING && meanVal > 6
        && handList.get(FOUR_OF_A_KIND).size() > 0)
      return handList.get(FOUR_OF_A_KIND).get(handList.get(FOUR_OF_A_KIND).size()-1);
    // Return three of a kind hand with a wild card
    if (order == Game.NumberOrder.ASCENDING && meanVal < 6
        && handList.get(THREE_OF_A_KIND).size() > 0 && numJokers > 0) {
      return handList.get(THREE_OF_A_KIND).get(0);
    }

    else if (order == Game.NumberOrder.DESCENDING && meanVal > 6
        && handList.get(FOUR_OF_A_KIND).size() > 0)
      return handList.get(FOUR_OF_A_KIND).get(handList.get(FOUR_OF_A_KIND).size()-1);


    // Call the weakest value
    int calledValue = 0;
    if (order == Game.NumberOrder.ASCENDING) {
      tmpVal = 0;
      for (i = 0; i < rankCards.size(); i++) {

      }
    }

    return chosenCards;
  }

  private Card[] aaa(int handcategory, int order) {

  }

}
