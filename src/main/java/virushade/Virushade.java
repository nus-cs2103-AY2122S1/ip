package virushade;

import virushade.tasks.TaskList;

public class Virushade {
    private Storage storageFile;
    private TaskList taskList;

    public Virushade(String fileName) {
        storageFile = new Storage(fileName);
        try {
            TaskList taskList = new TaskList(storageFile);
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

    public static void main(String[] args) {
        new Virushade("data/Virushade.txt").run();
    }
}
