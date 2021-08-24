package gnosis;
import java.util.Scanner;
/**
 *
 * gnosis.Gnosis class is the main programme to execute chat-bot assistant.
 * Commands gnosis.Gnosis can provide a task tracker to user:
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

    private GnosisUI gnosisView;
    private GnosisController gnosisController;

    public Gnosis() {
        gnosisView = new GnosisUI();
        this.gnosisController = new GnosisController(gnosisView);
    }

    private void run(Scanner sc) {
        gnosisController.startGnosis(sc);
    }

    public static void main(String[] args) {
            Gnosis gnosis = new Gnosis();
            Scanner sc = new Scanner(System.in);
            gnosis.run(sc);
            sc.close();
    }
}
