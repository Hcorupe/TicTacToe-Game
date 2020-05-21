package Shared;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GameInformation implements Serializable {

    private String player1Username;
    private String player2Username;
    private String player1Id;
    private String player2Id;
    private String id;
    private Timestamp startTime;
    private Timestamp endTime;
    private String startingPlayerId;
    private String startingPlayerUserName;
    private String winningPlayerId;
    private String winningPlayerUserName;
    private int wins = 0;
    private int lose = 0;
    private int tie= 0;

    public String getStartingPlayerUserName() {
        return startingPlayerUserName;
    }

    public void setStartingPlayerUserName(String startingPlayerUserName) {
        this.startingPlayerUserName = startingPlayerUserName;
    }

    public String getWinningPlayerUserName() {
        return winningPlayerUserName;
    }

    public void setWinningPlayerUserName(String winningPlayerUserName) {
        this.winningPlayerUserName = winningPlayerUserName;
    }

    public String getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(String player1Id) {
        this.player1Id = player1Id;
    }

    public String getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(String player2Id) {
        this.player2Id = player2Id;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getTie() {
        return tie;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }


    private List<String> spectators = new ArrayList<>();

    public GameInformation() {
    }

    public String getPlayer1Username() {
        return player1Username;
    }

    public void setPlayer1Username(String player1Username) {
        this.player1Username = player1Username;
    }

    public String getPlayer2Username() {
        return player2Username;
    }

    public void setPlayer2Username(String player2Username) {
        this.player2Username = player2Username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getStartingPlayerId() {
        return startingPlayerId;
    }

    public void setStartingPlayerId(String startingPlayerId) {
        this.startingPlayerId = startingPlayerId;
    }

    public String getWinningPlayerId() {
        return winningPlayerId;
    }

    public void setWinningPlayerId(String winningPlayerId) {
        this.winningPlayerId = winningPlayerId;
    }

    public List<String> getSpectators() {
        return spectators;
    }

    public void setSpectators(List<String> spectators) {
        this.spectators.addAll(spectators);
    }




}
