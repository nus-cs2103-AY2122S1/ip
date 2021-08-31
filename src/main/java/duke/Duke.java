package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Duke class
 * Uses storage class to gather data from text file
 * When run, parses input into parser class which utilizes ui class to give an output
 * After each command it saves to the storage text file
 */
public class Duke extends Application{



    public Duke() {}



//    public static void startDuke() throws IOException {
//        new Duke().run();
//
//    }
//
//    /**
//     * Starts the duke bot
//     *
//     * @throws IOException
//     */
//    private void run() throws IOException {
//        ui.showWelcome();
//        Parser duke = new Parser(this.tasks);
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLine()) {
//            ui.showLine();
//            String str = sc.nextLine();
//            duke.parse(str);
//            ui.showLine();
//            storage.save(tasks);
//        }
//
//
//    }



    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Duke.class.getResource("/duke.fxml"));
        Scene scene = new Scene(root); // Setting the scene to be our Label

        stage.setTitle("Duke Bot");
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
