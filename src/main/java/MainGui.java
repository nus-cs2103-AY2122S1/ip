import chatbot.Dude;
import dao.TaskDao;
import dao.TaskDaoImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.CommandLogicUnitImpl;
import logic.ICommandLogicUnit;
import ui.GuiUiImpl;
import ui.IUi;
import ui.view.MainWindow;

import java.io.IOException;

public class MainGui extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            MainWindow mainWindow = fxmlLoader.getController();
            
            // this is circular dependency, due to my bad design :(
            TaskDao taskDao = new TaskDaoImpl();
            
            IUi ui = new GuiUiImpl(mainWindow);
            ICommandLogicUnit commandLogicUnit = new CommandLogicUnitImpl(taskDao, ui);
            
            Dude dude = new Dude(commandLogicUnit, ui);
            mainWindow.setChatbot(dude);
            dude.initialize();
            
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        stage.show();
    }
}
