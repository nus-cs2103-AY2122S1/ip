package duke;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * The main class for the bot
 *
 * @author Houten Teo
 * @version CS2103T week 6
 */
public class Duke extends Application {
    private static String format = "yyyy-MM-dd"; //default date format
    private MyList list;
    private Storage storage;

    /**
     * Constructor for the duke class.
     * Initialises the list and storage.
     */
    public Duke() {
        this.list = new MyList();
        this.storage = new Storage(this.list, "Data.txt");
    }

    /**
     * Returns the current date format setting.
     * @return the DateTimeFormatter
     */
    public static String getFormat() {
        return format;
    }

    /**
     * Changes the date format.
     * @param newFormat The desired format
     * @throws IllegalArgumentException Thrown if the format is not accepted by
     *                                  DateTimeFormatter
     */
    public static void setFormat(String newFormat) throws IllegalArgumentException {
        DateTimeFormatter.ofPattern(newFormat);
        format = newFormat;
    }


    /**
     * Starts up the duke GUI.
     * @param stage The stage for the GUI
     */
    @Override
    public void start(Stage stage) throws Exception {
        //URL url = new File("src/main/resources/MainWindow.fxml").toURI().toURL();
        //Parent root = FXMLLoader.load(url);
        //System.out.println(getClass().getResource("MainWindow.fxml"));
        //System.out.println(getClass().getResource("userIcon.png"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        Parent root2 = loader.load();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Duke");
        stage.show();
    }

    /**
     * Starts the CLI version of the duke.
     */
    public void startCli() {

        System.out.println(Ui.getWelcomeMessage());

        this.storage.load();

        Parser p = new Parser(this.list, this.storage);

        while (p.isRunning()) {
            Scanner s = new Scanner(System.in);
            System.out.println(p.getDukeResponse(s.nextLine()));
        }
    }
}
