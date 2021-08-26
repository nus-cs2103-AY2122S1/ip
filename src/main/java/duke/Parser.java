package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Class to deal with making sense of the user command
 */
public class Parser {

    /**
     * Constructor.
     */
    public Parser() {

    }

    /**
     * Scans using a provided scanner and adds the tasks to a given taskList.
     *
     * @param scan the scanner to be used
     * @param taskList the taskList to be used
     * @param printMsg prints confirmation messages if true, does not if false
     * @return false if previous input was to terminate ("bye"), else returns true
     */
    public boolean scanInputs(Scanner scan, TaskList taskList, boolean printMsg) {
        String data = scan.next();
        switch (data.toLowerCase()) {
        case "bye":
            scan.close();
            Ui.goodbyeMessage();
            return false;
        case "list":
            if (taskList.getSize() == 0) {
                Ui.noTasksMessage();
            } else {
                taskList.printTaskList();
            }
            return true;
        case "done":
            taskList.markAsDone(scan.nextInt() - 1, printMsg);
            return true;
        case "delete":
            taskList.deleteTask(scan.nextInt() - 1);
            return true;
        case "todo":
            try {
                taskList.addTask(new Todo(scan.nextLine()), printMsg);
            } catch (DukeException e) {
                e.printMsg();
            }
            return true;
        case "deadline":
            try {
                taskList.addTask(new Deadline(scan.nextLine()), printMsg);
            } catch (DukeException e) {
                e.printMsg();
            } catch (DateTimeParseException e) {
                System.out.println("Wrong date time format! Please use DD-MM-YYYY HHHH");
            }
            return true;

        case "event":
            try {
                taskList.addTask(new Event(scan.nextLine()), printMsg);
            } catch (DukeException e) {
                e.printMsg();
            }
            return true;
        default:
            System.out.println("What are you even typing omg get it right...");
            scan.nextLine(); // Remove all other input to the scanner
            return true;
        }

    }



}
