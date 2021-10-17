import duke.Ui;
import javafx.application.Application;

/**
 * Duke class is run as a program via main() method.
 *
 * @author Lee Jae Ho
 * @since 0.2
 */
public class Duke {

    /**
     * Executes the Duke program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }
}