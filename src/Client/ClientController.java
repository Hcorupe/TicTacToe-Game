package Client;

import Shared.UserInformation;
import UI.Client.*;

import UI.Client.GameBoardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientController
{
    private Client client;
    private Stage stage;

    // Controllers to be initialized
    private SignInController signInController;
    private MainMenuController mainMenuController;
    private UI.Client.SignUpController signUpController;
    private UI.Client.Options options;
    private GameBoardController gameBoardController;

    // Scenes
    private Pane signInPane;
    private Pane signUpPane;
    private Pane mainMenuPain;
    private Pane optionsPane;
    private Pane gameBoardPane;

    private ReadMessageBus readMessageBus;


    public ClientController(Stage stage) {
        this.stage = stage;
        client = new Client("localhost", 8000, new UserInformation("NA", "NA", "Anonymous", "NA", "NA"));
        initialize();
        setUpClientToUI();
    }

    // initializes controllers
    private void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/Client/SignIn.fxml"));
            signInPane = loader.load();
            signInController = loader.getController();
            signInController.setClientController(this);

            loader = new FXMLLoader(getClass().getResource("../UI/Client/MainMenu.fxml"));
            mainMenuPain = loader.load();
            mainMenuController = loader.getController();
            mainMenuController.setClientController(this);

            loader = new FXMLLoader(getClass().getResource("../UI/Client/SignUp.fxml"));
            signUpPane = loader.load();
            signUpController = loader.getController();
            signUpController.setClientController(this);
            signUpController.setSignInController(signInController);

            loader = new FXMLLoader(getClass().getResource("../UI/Client/Options.fxml"));
            optionsPane = loader.load();
            options = loader.getController();
            options.setClientController(this);
            Scene optionsScene = new Scene(optionsPane);

            loader = new FXMLLoader(getClass().getResource("../UI/Client/GameBoard.fxml"));
            gameBoardPane = loader.load();
            gameBoardController = loader.getController();
            gameBoardController.setClientController(this);
            Scene gameBoardScene = new Scene(gameBoardPane);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setUpClientToUI()
    {
        try
        {
            Scene scene = new Scene(signInPane);
            stage.setScene(scene);
            stage.show();
            readMessageBus = new ReadMessageBus(this);
            readMessageBus.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void run() {
        client.execute();
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Pane getSignInPane() {
        return signInPane;
    }

    public Pane getSignUpPane() {
        return signUpPane;
    }

    public Pane getMainMenuPain() {
        return mainMenuPain;
    }

    public Pane getOptionsPane() {
        return optionsPane;
    }

    public SignInController getSignInController()
    {
        return signInController;
    }

    public SignUpController getSignUpController()
    {
        return signUpController;
    }

    public Options getOptions() {
        return options;
    }

    public Pane getGameBoardPane() {
        return gameBoardPane;
    }

    public GameBoardController getGameBoardController() {
        return gameBoardController;
    }
}
