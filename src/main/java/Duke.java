/**
 * Main class for Duke, scans for inputs and responds to user.
 */
public class Duke {

    private Ui ui;

    public Duke() {
        ui = new Ui();

        try {
            Task.readCache();
        } catch (DukeException e){
            Ui.printMsg(new String[] {e.getMessage()});
        }

        try {
            Task.initializeCache();
        } catch (DukeException e){
            Ui.printMsg(new String[] {e.getMessage()});
        }
    }    

    public void run() {
        ui.run();
    }

    /**
     * Main execution when Duke is run.
     *
     * @param args Will not be used
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
