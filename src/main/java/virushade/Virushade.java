package virushade;

import virushade.tasks.TaskList;

/**
 * The Main class of the application.
 */
public class Virushade {
    private Storage storageFile;
    private TaskList tasks;

    /**
     * The constructor for Virushade.
     * @param fileName The file path of the file where data is written to.
     */
    public Virushade(String fileName) {
        storageFile = new Storage(fileName);
        try {
            TaskList tasks = new TaskList(storageFile);
        } catch (VirushadeException e) {
            Ui.handleVirushadeException(e);
        }
    }

    /**
     * The running sequence for Virushade.
     */
    public void run() {
        Ui.interact();
    }

    /**
     * Runs our program under "data/Virushade.txt".
     * @param args Not used.
     */
    public static void main(String[] args) {
        new Virushade("data/Virushade.txt").run();
    }
}
