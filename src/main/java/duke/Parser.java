package duke;

import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
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
     * @return A String based on what message needs to be returned
     */
    public String scanInputs(Scanner scan, TaskList taskList) {
        String data = scan.next();
        switch (data.toLowerCase()) {
        case "bye":
            return Ui.getGoodbyeMessage();
        case "list":
            if (taskList.getSize() == 0) {
                return Ui.getNoTasksMessage();
            } else {
                return taskList.getTaskList();
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
            } catch (NoSuchElementException e) {
                new DukeException("Todo cannot be empty :(").printMsg();
            }
            break;
        case "deadline":
            try {
                return taskList.addTask(new Deadline(scan.nextLine()));
            } catch (DukeException e) {
                e.printMsg();
            } catch (DateTimeParseException e) {
                System.out.println("Wrong date time format! Please use DD-MM-YYYY HHHH");
            } catch (NoSuchElementException e) {
                new DukeException("Deadline cannot be empty :(").printMsg();
            }
            break;
        case "event":
            try {
                return taskList.addTask(new Event(scan.nextLine()));
            } catch (DukeException e) {
                e.printMsg();
            } catch (NoSuchElementException e) {
                new DukeException("Event cannot be empty :(").printMsg();
            }
            break;
        case "find":
            try {
                return taskList.findTask(scan.nextLine());
            } catch (DukeException e) {
                e.printMsg();
            }
            break;
        case "tag":
            int index = scan.nextInt();
            String tag = scan.next();
            return taskList.addTag(index - 1, tag);
        default:
            return "What are you even typing omg get it right...";
        }
        return "What are you even typing omg get it right...";
    }
}
