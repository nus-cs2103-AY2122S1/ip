import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * Gnosis class is the main programme to execute chat-bot assistant.
 * Commands Gnosis can provide a task tracker to user:
 * "list" - displays all tasks
 * "done (task number)" - marks specified task as done
 * "bye" - exits program
 * default - adds user input as task
 *
 * @author Pawandeep Singh
 * @version A-Enums
 *
 * */
public class Gnosis {

    private static List<Task> tasks;

    private GnosisUI gnosisView;
    private GnosisController gnosisController;


    public Gnosis() {
        gnosisView = new GnosisUI();
        this.gnosisController = new GnosisController(gnosisView);
    }

    public void run(Scanner sc) {
        gnosisController.startGnosis(sc);
    }

    public static void main(String[] args) {
            Gnosis gnosis = new Gnosis();
            Scanner sc = new Scanner(System.in);
            gnosis.run(sc);
            sc.close();
    }
}
