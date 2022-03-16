package daifugo;

public class Player
{
  private String name;
  private boolean isBot;

  public Player(String name, boolean isBot)
  {
    this.name = name;
    this.isBot = isBot;
  }

  public String getName() { return name; }

  public boolean isBot() { return isBot; }
}
