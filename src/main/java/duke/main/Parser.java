package duke.main;

import duke.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * Parses user inputs.
     *
     * @param taskList list of user Tasks.
     * @param storage  Storage liked to current session.
     * @param input    String from user.
     * @param ui       Ui for Duke.
     * @return true if input is not "bye", else false.
     */
    public static boolean parse(TaskList taskList, Storage storage, String input, Ui ui) {
        String[] commandAndDesc = input.split(" ", 2);
        String command = commandAndDesc[0];
        String description = commandAndDesc.length == 2 ? commandAndDesc[1] : "";

        switch (command) {
            case "bye":
                return false;
            case "clear":
                storage.resetTasks();
                taskList.clearTasks();
                break;
            case "list":
                ui.displayTaskList(taskList);
                break;
            case "deadline":
                taskList.addTask(new Deadline(description));
                break;
            case "event":
                taskList.addTask(new Event(description));
                break;
            case "todo":
                taskList.addTask(new ToDo(description));
                break;
            case "done":
            case "delete":
                try {
                    int taskNum = Integer.parseInt(description);
                    Task inFocus = taskList.getTask(taskNum);
                    if (inFocus == null) {
                        ui.showInvalidSelectionError();
                    } else {
                        if (command.equals("done")) {
                            inFocus.markAsDone();
                        } else {
                            taskList.deleteTask(inFocus);
                        }
                    }
                } catch (NumberFormatException e) {
                    ui.showNumberFormatException();
                }
                break;
            default:
                if (command.equals("")) {
                    ui.showEmptyInputException();
                } else {
                    ui.showUnknownCommandException();
                }
                break;
        }
        storage.write(taskList);
        return true;
    }

    /**
     * Parses the time input.
     *
     * @param time input String.
     * @return LocalDate generated from Parsing
     * @throws DateTimeParseException if the input String is incorrectly formatted.
     */
    public static LocalDate parseTime(String time) throws DateTimeParseException {
        try {
            return LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("\t☹ OOPS!!! Please specify the time in the yyyy-mm-dd format.\n");
        }
    }
}
