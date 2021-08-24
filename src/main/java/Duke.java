import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project Duke
 *
 * @author Willy Angga Prawira
 */

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    Duke(String filepath) {
        taskList = new TaskList(new ArrayList<Task>());
        storage = new Storage(filepath);
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        parser = new Parser(scan, storage, taskList);
        System.out.println(
                "Hello! I'm Amped :) \n"
                        + "Type: \n"
                        + "1. A task (todo/deadline/event) followed by description to add tasks \n"
                        + "   e.g \"deadline submit homework /by Sunday 12 pm\" \n"
                        + "2. \"list\" to see the list of tasks \n"
                        + "3. \"done [number]\" to mark a particular task as done \n"
                        + "4. \"delete [number]\" to delete a particular task \n"
                        + "5. \"bye\" to exit"
        );
        parser.parse();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
