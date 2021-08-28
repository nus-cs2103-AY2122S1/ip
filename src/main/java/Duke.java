import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    
    // ...

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        helloWorld.setFont(new Font("Arial", 24));
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        stage.setWidth(800);
        stage.setHeight(500);
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}