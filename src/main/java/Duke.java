<<<<<<< Updated upstream
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
=======
import java.util.Scanner;
import Storage.*;
import Task.TaskList;
import Task.Task;
import Task.DeadlineException;
import Task.TodoException;
import Task.EventsException;

public class Duke{
    private final Ui userInterface = new Ui();

    public static void main(String[] args) {
        Duke user = new Duke();
        boolean end = true;
        user.userInterface.greet();
        user.userInterface.getDataInputList();
        while (end) {
            end = user.userInterface.echo();
            System.out.println("__________________________________");
        }
    }

    public String getResponse(String input) {
            return userInterface.choiceOfAction(input);
    }

    /**
     * Function returns the logo of the program.
     * @return string containing the logo.
     */
    public final static String greet() {
        return Ui.greet();
    }
}


//deals with interactions with the user
class Ui {
    private final Storage store = new Storage();
    private TaskList taskList = new TaskList();

    /**
     * Function returns the logo of the program.
     * @return string containing the logo.
     */
    public final static String greet() {
        String logo = "__________________________________\n"
                +" ____        _        \n"
>>>>>>> Stashed changes
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
