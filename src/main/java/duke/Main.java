package duke;
import java.util.Scanner;

/**
 * Main class for execution of the duke.
 */
class Main {
    public static void main(String[] args) {
        run();
    }

    /**
     * Creates an interactive duke.
     */
    public static void run() {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        while (duke.isLive()) {
            String command = sc.nextLine();
            try {
                duke.processCommand(command);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}
