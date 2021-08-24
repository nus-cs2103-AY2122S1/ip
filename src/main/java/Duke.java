import java.util.ArrayList;
import java.util.Scanner;

/**
 * Allows for the main initialization of the Duke Program
 *
 * @author: Wei Yangken
 */
public class Duke {

    private Storage storage;
    private Tasklist tasklist;

    public Duke(String filepath) {
        storage = new Storage(filepath);
        tasklist = storage.load();
    }

    public void run() {
        String cmd;
        Scanner scanner = new Scanner(System.in);
        Ui.start();

        while (true) {
            cmd = scanner.next();
            String input = scanner.nextLine();
            Task task;

            switch (cmd) {
                case "bye":
                    storage.save();
                    Ui.exit();
                    return;
                case "list":
                    tasklist.list();
                    break;
                case "done":
                    int idx = Integer.parseInt(input.trim()) - 1;
                    tasklist.getTask(idx).setToCompleted();
                    break;
                case "todo":
                case "deadline":
                case "event":
                    task = Parser.parseStringIntoTask(input, cmd, false);
                    tasklist.add(task);
                    break;
                case "delete":
                    int removedIdx = Integer.parseInt(input.trim());
                    tasklist.delete(removedIdx);
                    break;
                default:
                    try {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        System.out.println(Ui.breakline);
                    }
            }
        }
    }

    public static void main(String[] args) {
        String filepath = "src\\main\\java\\data\\tasklist.txt";
        new Duke(filepath).run();
    }
}

