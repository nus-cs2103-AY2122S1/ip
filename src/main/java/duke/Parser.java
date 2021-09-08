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
     * @return false if previous input was to terminate ("bye"), else returns true
     */
    public String scanInputs(Scanner scan, TaskList taskList) {
        String data = scan.next();
        switch (data.toLowerCase()) {
        case "bye":
            scan.close();
            return Ui.getGoodbyeMessage();
        case "list":
            if (taskList.getSize() == 0) {
                return Ui.getNoTasksMessage();
            } else {
                return taskList.printTaskList();
            }
        case "done":
            return taskList.markAsDone(scan.nextInt() - 1);
        case "delete":
            return taskList.deleteTask(scan.nextInt() - 1);
        case "todo":
            try {
                return taskList.addTask(new Todo(scan.nextLine()));
            } catch (DukeException e) {
                e.printMsg();
            }
            break;
        case "deadline":
            try {
                return taskList.addTask(new Deadline(scan.nextLine()));
            } catch (DukeException e) {
                e.printMsg();
            } catch (DateTimeParseException e) {
                System.out.println("Wrong date time format! Please use DD-MM-YYYY HHHH");
            }
            break;
        case "event":
            try {
                return taskList.addTask(new Event(scan.nextLine()));
            } catch (DukeException e) {
                e.printMsg();
            }
            break;
        case "find":
            try {
                return taskList.findTask(scan.nextLine());
            } catch (DukeException e) {
                e.printMsg();
            }
            break;
        default:
            return "What are you even typing omg get it right...";
        }
        return "What are you even typing omg get it right...";
    }
}
