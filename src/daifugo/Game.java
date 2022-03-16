package daifugo;

import java.util.List;
import java.util.Random;

public class Game
{
  public enum NumberOrder { ASCENDING, DESCENDING };
  public enum SuitRestriction { UNRESTRICTED, RESTRICTED };

  private int setNumber;
  private int matchNumber;
  private List<Player> players;
  private int numberOfPlayers;
  private Deck deck;
  private NumberOrder order;
  private SuitRestriction restriction;
  private List<Card> tableCards;
  private Hand playerHands[];

  private int activeSeat;
  private int buttonSeat;
  private int lastToActSeat;

  private boolean[] isInvolvedInRound;
  private boolean[] isInvolvedInMatch;
  private int[] finishPosition;
  private int finishedPlayers;
  private boolean[] hasFouled;
  private int fouledPlayers;

  public Game(List<Player> playerList)
  {
    setNumber = 1;
    matchNumber = 1;
    players = playerList;
    numberOfPlayers = playerList.size();
    deck = new Deck(numberOfPlayers);
    playerHands = new Hand[numberOfPlayers];
    for (int i = 0; i < numberOfPlayers; i++)
      playerHands[i] = new Hand(players.get(i));
    order = NumberOrder.ASCENDING;
    restriction = SuitRestriction.UNRESTRICTED;
    tableCards = null;

    activeSeat = -1;
    buttonSeat = -1;
    lastToActSeat = -1;

    isInvolvedInRound = new boolean[numberOfPlayers];
    isInvolvedInMatch = new boolean[numberOfPlayers];
    finishPosition = new int[numberOfPlayers];
    finishedPlayers = 0;
    fouledPlayers = 0;
    for (int i = 0; i < numberOfPlayers; i++) {
      isInvolvedInRound[i] = true;
      isInvolvedInMatch[i] = true;
      finishPosition[i] = 0;
      hasFouled[i] = false;
    }
  }

  public void play(int totalSetCount, int totalMatchCount)
  {
    boolean gameFinished = false;
    while (!gameFinished) {
      startMatch();
      if (setNumber == totalSetCount && matchNumber == totalMatchCount)
        gameFinished = true;
      else if (setNumber != totalSetCount && matchNumber == totalMatchCount) {
          setNumber++;
          matchNumber = 1;
      }
    }

  }

  private void startMatch()
  {
    setupNextMatch();
    dealHandCards();

    activeSeat = buttonSeat;

    boolean matchFinished = false;
    while (!matchFinished) {
      boolean roundFinished = false;

      if(!playInitialCards())
        roundFinished = true;
      nextPlayerActive();

      while (!roundFinished) {
        askPlayerAction();
        if (lastToActSeat == activeSeat || numberOfRemainingPlayers(1) <= 1)
          roundFinished = true;
        else
          nextPlayerActive();
        storeFinishPositions();
      }
      if (numberOfRemainingPlayers(0) <= 1)
        matchFinished = true;
      else
        setupNextRound();
    }

  }

  private void setupNextRound()
  {
    tableCards = null;
    restriction = SuitRestriction.UNRESTRICTED;
    for (int i = 0; i < numberOfPlayers; i++)
      if (isInvolvedInMatch[i] && playerHands[i].size() > 0)
        isInvolvedInRound[i] = true;
  }

  private void setupNextMatch()
  {
    matchNumber++;
    deck = new Deck(numberOfPlayers);
    playerHands = new Hand[numberOfPlayers];
    for (int i = 0; i < numberOfPlayers; i++)
      playerHands[i] = new Hand(players.get(i));
    tableCards = null;
    order = NumberOrder.ASCENDING;
    restriction = SuitRestriction.UNRESTRICTED;

    setupNextButtonPosition();

    for (int i = 0; i < numberOfPlayers; i++) {
      isInvolvedInMatch[i] = true;
      isInvolvedInRound[i] = true;
      finishPosition[i] = 0;
      hasFouled[i] = false;
    }
    finishedPlayers = 0;
    fouledPlayers = 0;
  }

  private void storeFinishPositions()
  {
    for (int i = 0; i < numberOfPlayers; i++) {
      if (isInvolvedInMatch[i] && playerHands[i].size() <= 0) {
        isInvolvedInMatch[i] = false;
        isInvolvedInRound[i] = false;

        if (finishPosition[i] == 0) {
          if (!hasFouled[i])
            finishPosition[i] = finishedPlayers++ - fouledPlayers;
          else {
            finishPosition[i] = numberOfPlayers - fouledPlayers;
            finishedPlayers++;
            fouledPlayers++;
          }
        }

      }
    }
    if (numberOfPlayers - finishedPlayers == 1)
      for (int i = 0; i < numberOfPlayers; i++)
        if (finishPosition[i] == 0)
          finishPosition[i] = finishedPlayers++;
  }

  private void setupNextButtonPosition()
  {
    if (setNumber == 1 && matchNumber == 1) {
      Random random = new Random();
      buttonSeat = random.nextInt(numberOfPlayers);
    } else {
      for (int i = 0; i < numberOfPlayers; i++)
        if (finishPosition[i] == 1)
          buttonSeat = i;
    }
  }

  private void dealHandCards()
  {
    while (!deck.isEmpty()) {
      nextPlayerActive();
      playerHands[activeSeat].addCard(deck.nextCard());
    }
    for (Hand h : playerHands)
      h.sort();
  }

  /**
   * @param type : 0 = still involved in the Match, 1 = still involved in the Round
   */
  private int numberOfRemainingPlayers(int type)
  {
    int numberOfPlayersInvolved = 0;
    if (type == 1) {
      for (int i = 0; i < numberOfPlayers; i++)
        if (isInvolvedInRound[i])
          numberOfPlayersInvolved++;
    } else {
      for (int i = 0; i < numberOfPlayers; i++)
        if (isInvolvedInMatch[i])
          numberOfPlayersInvolved++;
    }
    return numberOfPlayersInvolved;
  }

  private boolean playInitialCards()
  {
    if (numberOfRemainingPlayers(0) <= 1)
      return false;

    PlayerMove initialMove = new PlayerMove(this);
    tableCards = initialMove.getCardsToPlay();
    nextPlayerActive();
    return true;
  }

  private void askPlayerAction()
  {
    if (isInvolvedInRound[activeSeat] && playerHands[activeSeat].size() > 0) {
      PlayerMove nextMove = new PlayerMove(this);
      String playerAction = nextMove.getAction();

      if (playerAction.equals("play")) {
        tableCards = nextMove.getCardsToPlay();
        updateLastSeatToAct();
      } else if (playerAction.equals("pass")) {
        isInvolvedInRound[activeSeat] = false;
      }
    }
  }

  private void reverseOrder()
  {
    if (order == NumberOrder.ASCENDING)
      order =  NumberOrder.DESCENDING;
    else
      order = NumberOrder.ASCENDING;
  }

  private void updateLastSeatToAct()
  {
    lastToActSeat = activeSeat - 1;
    if(lastToActSeat < 0)
      lastToActSeat = numberOfPlayers - 1;
  }

  private void nextPlayerActive()
  {
    activeSeat++;
    if (activeSeat == numberOfPlayers)
      activeSeat = 0;
  }

  public NumberOrder getOrder() { return order; }
  public SuitRestriction getRestriction() { return restriction; }
  public Player getPlayer() { return players.get(activeSeat); }
  public List<Card> getTableCards() { return tableCards; }
  public List<Card> getActiveHand() { return playerHands[activeSeat].getCards(); }

}
