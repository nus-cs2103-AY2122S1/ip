package gnosis.main;
import gnosis.controller.GnosisController;
import gnosis.ui.GnosisUI;

import java.util.Scanner;
/**
 *
 * gnosis.main.Gnosis class is the gnosis.main programme to execute chat-bot assistant.
 * Commands gnosis.main.Gnosis can provide a gnosis.task tracker to user:
 * "list" - displays all tasks
 * "done (gnosis.task number)" - marks specified gnosis.task as done
 * "bye" - exits program
 * default - adds user input as gnosis.task
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
