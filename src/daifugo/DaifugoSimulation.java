package daifugo;

import java.util.ArrayList;
import java.util.List;

/**
 * 4 matches 1 set, 3 sets 1 tournament
 * points each one gets -> 1st: +2, 2nd: +1, 3rd: 0, 4th: -1
 * 1st place player in the 4th game gets +1 bonus
 * reset all positions at the end of every set
 */

/**
 * Run an Uno simulation of some number of games pitting some set of
 * opponents against each other. The mandatory command-line argument
 * (totalHumans) should contain an integer specifying how many games
 * to play in the match. The optional second command-line argument
 * should be either the word "verbose" or "quiet" and controls the
 * magnitude of output.
 */
public class DaifugoSimulation
{
  private static final int TOTAL_PLAYERS = 4;
  private static final int TOTAL_SETS = 1;
  private static final int TOTAL_MATCHES = 4;
  private static int totalHumans;
  private static int totalBots;


  Game game;
  List<Player> players;

  public static void main(String args[])
  {
    DaifugoSimulation simulation = new DaifugoSimulation(args);
    try {
      simulation.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public DaifugoSimulation(String args[])
  {
    totalHumans = 0;
    /*
    if (args.length != 1) {
      System.out.println("Usage: DaifugoSimulation totalHumans");
      System.exit(1);
    }
    totalHumans = Integer.valueOf(args[0]);
    */
    totalBots = TOTAL_PLAYERS - totalHumans;
    players = new ArrayList<Player>();
  }

  private void start()
  {
    int i;
    for (i = 0; i < totalHumans; i++)
      players.add(new Player("player" + (i + 1), false));
    for (i = 0; i < totalBots; i++)
      players.add(new Player("bot" + (i + 1), true));

    game = new Game(players);
    game.play(TOTAL_SETS, TOTAL_MATCHES);
  }

}
