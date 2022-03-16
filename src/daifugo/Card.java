package daifugo;

public class Card
{
  public static final boolean PRINT_IN_COLOR = false;

  public enum Suit { SPADES, HEARTS, DIAMONDS, CLUBS, JOKERS }
  public enum Rank { THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
                     TEN, JACK, QUEEN, KING, ACE, DEUCE, WILD }

  private Suit suit;
  private Rank rank;
  private int value;

  public Card(int number)
  {
    int findSuit = number / 13;
    switch(findSuit) {
    case 0:  suit = Suit.SPADES;   break;
    case 1:  suit = Suit.HEARTS;   break;
    case 2:  suit = Suit.DIAMONDS; break;
    case 3:  suit = Suit.CLUBS;    break;
    default:
      suit = Suit.JOKERS;
      rank = Rank.WILD;
      value = 13;
      return;
    }
    int findRank = value = number % 13;
    switch (findRank) {
    case 0:  rank = Rank.THREE; break;
    case 1:  rank = Rank.FOUR;  break;
    case 2:  rank = Rank.FIVE;  break;
    case 3:  rank = Rank.SIX;   break;
    case 4:  rank = Rank.SEVEN; break;
    case 5:  rank = Rank.EIGHT; break;
    case 6:  rank = Rank.NINE;  break;
    case 7:  rank = Rank.TEN;   break;
    case 8:  rank = Rank.JACK;  break;
    case 9:  rank = Rank.QUEEN; break;
    case 10: rank = Rank.KING;  break;
    case 11: rank = Rank.ACE;   break;
    default: rank = Rank.DEUCE;
    }
  }

  public Suit getSuit() { return suit; }
  public Rank getRank() { return rank; }
  public int getValue() { return value; }

  public String toString()
  {
    final String ANSI_RESET = "\u001b[00m";
    final String ANSI_BLACK = "\u001b[00;30m";
    final String ANSI_RED   = "\u001b[00;31m";
    final String ANSI_WHITE_BACKGROUND = "\u001b[47m";

    String str = "";
    if (PRINT_IN_COLOR) {
      str += ANSI_WHITE_BACKGROUND;
      switch (suit) {
      case SPADES:
      case CLUBS:
      case JOKERS:
        str += ANSI_BLACK;
        break;
      default:
        str += ANSI_RED;
      }
    }
    switch (suit) {
    case SPADES:   str += "♠"; break;
    case HEARTS:   str += "♥"; break;
    case DIAMONDS: str += "◆"; break;
    case CLUBS:    str += "♣"; break;
    default:       str += "JOKER";
    }
    switch (rank) {
    case THREE: str += "3";  break;
    case FOUR:  str += "4";  break;
    case FIVE:  str += "5";  break;
    case SIX:   str += "6";  break;
    case SEVEN: str += "7";  break;
    case EIGHT: str += "8";  break;
    case NINE:  str += "9";  break;
    case TEN:   str += "10"; break;
    case JACK:  str += "J";  break;
    case QUEEN: str += "Q";  break;
    case KING:  str += "K";  break;
    case ACE:   str += "A";  break;
    case DEUCE: str += "2";  break;
    default:    str += "";
    }
    if(PRINT_IN_COLOR)
      str += ANSI_RESET;
    return str;
  }

}
