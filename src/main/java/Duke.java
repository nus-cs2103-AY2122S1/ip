import duke.Ui;
import javafx.application.Application;

/**
 * Duke class is run as a program via main() method
 *
 * @author Lee Jae Ho
 * @since 0.1
 */
public class Duke {

    /**
     * Our main method. This xecutes the Duke program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }
}