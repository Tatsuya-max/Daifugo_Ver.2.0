package daifugo;

import java.util.ArrayList;
import java.util.List;

import daifugo.Card.Suit;

public final class HandEvaluator
{

  private HandEvaluator() {}

  public enum HandCategory { SINGLETON, PAIR, TRIPLET, QUADRUPLET,
                             QUINTUPLET, SEXTUPLET, SEQUENCE }

  /**
   * Method for initial move
   */
  public static List<Card[]> allTheHands(Game.NumberOrder theOrder, List<Card> theCards)
  {
    List<Card[]> theList = new ArrayList<>();
    int i;

    // List of singleton hands
    List<Card[]> highCardHands = new ArrayList<>();
    Card[] oneCard = new Card[1];
    for (i = 0; i < theCards.size(); i++) {
      oneCard[1] = theCards.get(i);
      highCardHands.add(oneCard);
    }

    // List of pair hands
    List<Card[]> aPairHands = new ArrayList<>();
    Card[] twoCards = new Card[2];
    for (i = 0; i < theCards.size(); i++) {
      oneCard[1] = theCards.get(i);
      highCardHands.add(oneCard);
    })



    TRIPLET, QUADRUPLET,
    QUINTUPLET, SEXTUPLET, SEQUENCE;


    return theList;
  }

  public static boolean allowToPlay(Card[] theHand, Card[] theGame.NumberOrder theOrder, Game.SuitRestriction theRestriction)
  {
    boolean isAllowed = false;
    theValue =
      if (theOrder == Game.NumberOrder.ASCENDING) {

      }

    return isAllowed;
  }

  public String computeStrengths(Card[] theHand) {
    int numOfCards = theHand.length;
    Card.Suit[] theSuits = new Card.Suit[numOfCards];
    int[] theValues = new int[numOfCards];
    int numOfJokers = 0;
    HandCategory category;

    for (int i = 0; i < numOfCards ; i++) {
      theSuits[i] = theHand[i].getSuit();
      theValues[i] = theHand[i].getValue();
      if (theSuits[i] == Suit.JOKERS)
        numOfJokers++;
    }

    if (numOfJokers == 0 && theValues[0] != theValues[1]) {
      category = HandCategory.SEQUENCE;
    } else {
      switch (numOfCards) {
        case 1:  category = HandCategory.SINGLETON;  break;
        case 2:  category = HandCategory.PAIR;       break;
        case 3:  category = HandCategory.TRIPLET;    break;
        case 4:  category = HandCategory.QUADRUPLET; break;
        case 5:  category = HandCategory.QUINTUPLET; break;
        default: category = HandCategory.SEXTUPLET;  break;
      }
    }
  }

}
