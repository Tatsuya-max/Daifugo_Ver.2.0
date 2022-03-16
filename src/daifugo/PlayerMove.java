package daifugo;

import java.util.List;

public class PlayerMove
{
  private AIClass AI = new AIClass();
  private String action = null;
  private List<Card> cardsToPlay = null;

  /**
   * Constructor
   */
  PlayerMove(Game theGame)
  {
    /** for initial move */
    if (theGame.getTableCards() == null) {
      if (theGame.getPlayer().isBot()) {
        cardsToPlay = AI.chooseNextHand(theGame.getOrder(), theGame.getActiveHand());
      }
    } else {
      /** for second move and later */
      if (theGame.getPlayer().isBot()) {
        cardsToPlay = AI.chooseNextHand(
            theGame.getOrder(), theGame.getRestriction(), theGame.getTableCards(), theGame.getActiveHand());
        action = (cardsToPlay != null) ? "play" : "pass";
      }
    }
  }

  public String getAction() { return action; }

  public List<Card> getCardsToPlay() { return cardsToPlay; }

}
