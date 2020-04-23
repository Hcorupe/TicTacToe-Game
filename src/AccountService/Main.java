package AccountService;
import Shared.Packet;
import Shared.UserInformation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application
{
   public static void main(String[] args) {
       launch(args);
   }

    @Override
    public void start(Stage stage) throws Exception
    {
        AccountService service = new AccountService();
        service.start();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/ServerUI/ServerDisplay.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
