package GameService;

import DataBase.sql.DataSource;
import DataBase.sql.DatabaseManager;
import Models.Game;
import Models.Move;
import Server.ClientConnection;
import Shared.Packet;
import Shared.UserInformation;
import app.Server;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameHandler implements Runnable {
    private final AtomicBoolean running = new AtomicBoolean(false);
    private Packet packet;
    private Thread worker;
    private DataSource ds = DatabaseManager.getInstance();
    private Server server = new Server();
    private GameService service;
    private ClientConnection clientConnection;


    public GameHandler(ClientConnection clientConnection, Packet packet, GameService service) {
        this.service = service;
        this.packet = packet;
        this.clientConnection = clientConnection;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        running.set(false);
        System.out.println("GameHandler has stopped!");
    }

    @Override
    public void run() {
        running.set(true);
        System.out.println("GameHandler has started!");

        String request = packet.getRequest();
        UserInformation userInformation = packet.getInformation();
        Serializable data = packet.getData();
        HashMap<String, GameThread> gameThreadList = service.getGameThreadList();

        System.out.println("Request: " + request);

        switch (request) {
            case Packet.GET_GAMES:
                try {
                    clientConnection.getOutputStream().writeObject(new Packet(Packet.GET_GAMES, userInformation, service.getGames())); // list of current games
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Packet.NEW_GAME_CREATED:
                try {
                    Game game = new Game(clientConnection);
                    service.addGame(game); //pull game name from data
                    Packet packet = new Packet(Packet.NEW_GAME_CREATED, clientConnection.getInformation(), "SUCCESS");
                    clientConnection.getOutputStream().writeObject(packet);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case Packet.JOIN_GAME:
                Game game = service.getGame(data.toString());
                game.join(clientConnection);
                System.out.println("Opponent joined game!");

                GameThread gameThread = new GameThread(game, game.getPlayer1ClientConnection(), clientConnection);
                gameThreadList.put(game.getId(), gameThread);
                gameThread.start();
                System.out.println("starting game thread!");
                break;

            case Packet.OBSERVE_GAME:
                Game ObserverGame = service.getGame(data.toString());
                break;

            case Packet.GAME_MOVE:
                try {
                    Move newMove = (Move) data;

                    if (gameThreadList.containsKey(newMove.getGameName())) {
                        GameThread gameThreadForMove = gameThreadList.get(newMove.getGameName());
                        gameThreadForMove.addMove(newMove);
                    } else {
                        // else statement is for when opponent has not been found yet.
                        Packet errorPacket = new Packet(Packet.NO_OPPONENT_FOUND, clientConnection.getInformation(), "No Opponent Found");
                        try {
                            clientConnection.getOutputStream().writeObject(errorPacket);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

        }

        stop();
    }

    public boolean getRunning() {
        return running.get();
    }
}