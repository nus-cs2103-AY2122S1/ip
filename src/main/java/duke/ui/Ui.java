package duke.ui;

import java.time.LocalDate;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.utils.Constants;


/**
 * The Ui class deals with all the interactions with the user. Most of the methods prefixed with 'display' print out
 * the output obtained from their respective Command functions
 */
public class Ui {

    private final Scanner sc;

    /**
     * Public constructor which initialises the scanner
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void init() {
        System.out.println(Constants.LOGO + Constants.HELLO + Constants.LINE);
    }

    /**
     * Returns a string with the contents of the scanner. Throws an exception if the input is missing.
     * @return string with contents of the scanner.
     */
    public String read() {
        try {
            if (sc.hasNext()) {
                return sc.nextLine();
            } else {
                return "bye";
            }
        } catch (DukeException ex) {
            throw new DukeException("Missing Input!");
        }
    }


    public String displayBye() {
        return Constants.BYE;
    }

    public String displayList(TaskList list) {
        return list.print();
    }

    public String displayDone(String taskDetails) {
        return "Nice! I've marked this task as done:\n" + taskDetails;
    }


    public String displayDelete(String taskDetails, TaskList list) {
        return "Noted. I've removed this task:\n"
                + taskDetails + "\n" + list.printRemainingTasks();
    }

    public String displayTasksOn(LocalDate date, TaskList list) {
        if (!list.isEmpty()) {
            return list.print();
        } else {
            return "No tasks are due on " + date + "!";
        }
    }

    public String displayFind(String word, TaskList list) {
        if (!list.isEmpty()) {
            return "Here are the matching tasks in your list:" + list.print();
        } else {
            return "Sorry there are no tasks containing '%s' in the list\n";
        }
    }

    public String displayLoadingError(Exception ex) {
        return ex.toString();
    }

    public String displayError(String message) {
        return message;
    }

    public String displayLine() {
        return Constants.LINE;
    }

    public String displayAdd(Task task, TaskList taskList) {
       return "Got it. I've added this task:\n" + task.toString() + "\n" + taskList.printRemainingTasks();
    }

}
