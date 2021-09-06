import ui.Launcher;

/** This class implements the Duke memo assistant.
 * @author damithc
 * edited by Wanyu
 */
public class Duke {

    /**
     * Starts the program and execute commands
     * detected by parser as per user input.
     */
    public void run() {
        Launcher.run();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
