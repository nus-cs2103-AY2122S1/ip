public class Virushade {
    private Storage storageFile;
    private TaskList tasks;

    public Virushade(String fileName) {
        storageFile = new Storage(fileName);
        try {
            tasks = new TaskList(storageFile);
        } catch (VirushadeException e) {
            UI.handleVirushadeException(e);
        }
    }

    /**
     * The running sequence for Virushade.
     */
    public void run() {
        UI.interact();
    }

    public static void main(String[] args) {
        new Virushade("data/Virushade.txt").run();
    }
}
