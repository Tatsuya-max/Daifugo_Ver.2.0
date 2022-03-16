package daifugo;

public class Scoreboard
{

  private Player playerList[];
  private int scores[];

  public Scoreboard(Player playerList[])
  {
    scores = new int[playerList.length];
    for (int i = 0; i < playerList.length; i++) {
      scores[i] = 0;
    }
    this.playerList = playerList;
  }

  public void addToScore(int player, int points) {
    scores[player] += points;
  }

  public int getRoundPoints(int numWinners) {
    return playerList.length / 2 - numWinners;
  }

  public int getScore(int player) {
    return scores[player];
  }

  public Player[] getPlayerList() { return playerList; }

  public int getNumPlayers() { return playerList.length; }

  public int getFinalWinner() {
    int winner = 0;
    int bestScore = scores[0];
    for (int i=1; i<scores.length; i++) {
      if (scores[i]>bestScore) {
        bestScore = scores[i];
        winner = i;
      }
    }
    return winner;
  }

  public String toString() {
    String retVal = "";
    for (int i=0; i<scores.length; i++) {
      retVal += "playerList[i]" + ":" + "scores[i]" + "\n";
    }
    return retVal;
  }

}
