package gnosis;
import gnosis.controller.GnosisController;

import java.util.Scanner;

/**
 *
 * Gnosis class is the main programme to execute chat-bot assistant.
 * Commands Gnosis can provide a task tracker to user:
 *
 * "t0do (task name)" - creates t0do task
 * "deadline (task name) /by (datetime)" - creates deadline task
 * "event (task name) /at (datetime)" - creates event task
 * "find (keyword)" - displays all task matching keyword
 * "list" - displays all tasks
 * "done (task number)" - marks specified task as done
 * "bye" - exits program
 *
 * @author Pawandeep Singh

 * */
public class Gnosis {

    /** Handles Gnosis View */
    private GnosisUI gnosisView;

    /** Handles Gnosis Logic */
    private GnosisController gnosisController;

    /**
     * Gnosis constructor to initialise view and controller.
     */
    public Gnosis() {
        gnosisView = new GnosisUI();
        this.gnosisController = new GnosisController(gnosisView);
    }

    /**
     * Starts running Gnosis programme by retrieving user input.
     *
     * @param sc user input
     */
    private void run(Scanner sc) {
        gnosisController.startGnosis(sc);
    }


    public static void main(String[] args) {
            // initialise gnosis program
            Gnosis gnosis = new Gnosis();

            // runs program and waits for user input
            Scanner sc = new Scanner(System.in);
            gnosis.run(sc);

            sc.close();
    }
}
