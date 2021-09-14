package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses different strings and acts as a helper for other classes.
 */
public class Parser {

    /**
     * Returns the type of command given a command from a user.
     *
     * @param str The original command from the user.
     * @return Type of command.
     */
    public String parseCommand(String str) {
        if (str.equals("bye")) {
            return "bye";
        } else if (str.equals("list")) {
            return "list";
        } else if (str.startsWith("done")) {
            return "done";
        } else if (str.startsWith("delete")) {
            return "delete";
        } else if (str.startsWith("todo") || str.startsWith("event") || str.startsWith("deadline")) {
            return "addTask";
        } else if (str.startsWith("find")) {
            return "find";
        } else if (str.equals("\n")){
            return "empty";
        } else {
            return "errorInput";
        }
    }

    /**
     * Returns the correct Task object given a command to add a task from the user.
     *
     * @param str A command to add a task.
     * @return A Task object that should be added to the user's TaskList.
     * @throws DukeException If the format of the add task command is incorrect.
     */
    public Task parseAddTask(String str) throws DukeException{
        if (str.startsWith("todo")) {
            checkFormatOfToDoCommand(str);
            int indexForToDoDescription = "todo ".length();
            return new ToDo(str.substring(indexForToDoDescription));
        } else if (str.startsWith("event")) {
            checkFormatOfEventCommand(str);
            int indexOfAt = str.indexOf("/at ");
            int indexForEventDescription = "event ".length();
            int indexOfTimeDescription = indexOfAt + "/at ".length();
            String description = str.substring(indexForEventDescription, indexOfAt - 1);
            String time = str.substring(indexOfTimeDescription);
            assert(description != "");
            assert(time != "");
            return new Event(description, time);
        } else {
            checkFormatOfDeadlineCommand(str);
            int indexOfBy = str.indexOf("/by ");
            int indexForEventDescription = "deadline ".length();
            int indexOfTimeDescription = indexOfBy + "/by ".length();
            String description = str.substring(indexForEventDescription, indexOfBy - 1);
            String time = str.substring(indexOfTimeDescription);
            assert(description != null);
            assert(time != null);
            return new Deadline(description, time);
        }
    }

    private void checkFormatOfDeadlineCommand(String str) throws DukeException {
        int minimumDeadlineCommandLength = "deadline _ /by _".length();
        boolean deadlineCommandFormatIsWrong = str.indexOf(" ") == -1;
        if (deadlineCommandFormatIsWrong || str.length() < minimumDeadlineCommandLength) {
            throw new DukeException("Wrong input for adding a deadline-task.");
        }
        int indexOfBy = str.indexOf("/by ");
        if (indexOfBy == -1) {
            throw new DukeException("You must specify the deadline.");
        }
        String time = str.substring(indexOfBy + "/by ".length());
        try {
            LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Deadline should be specified in the format of YYYY-MM-DD.");
        }
    }

    private void checkFormatOfEventCommand(String str) throws DukeException {
        int minimumEventCommandLength = "event _ /at _".length();
        boolean eventCommandFormatIsWrong = str.indexOf(" ") == -1;
        if (eventCommandFormatIsWrong || str.length() < minimumEventCommandLength) {
            throw new DukeException("Wrong input for adding an event-task.");
        }
        int indexOfAt = str.indexOf("/at ");
        if (indexOfAt == -1) {
            throw new DukeException("You must specify the time for an event-task.");
        }
    }

    private void checkFormatOfToDoCommand(String str) throws DukeException {
        int minimumToDoCommandLength = "todo _".length();
        boolean todoCommandFormatIsWrong = str.indexOf(" ") == -1;
        if (todoCommandFormatIsWrong || str.length() < minimumToDoCommandLength) {
            throw new DukeException("The description of todo cannot be empty");
        }
    }

    /**
     * Returns the task number of the task that should be marked as done given a command
     * to mark a task as done from the user.
     *
     * @param str A command to mark a task as done.
     * @return The task number of the task that should be marked as done.
     * @throws DukeException If the format of the mark task as done command is incorrect.
     */
    public int parseDoneCommand(String str) throws DukeException {
        try {
            int minimumLengthOfDoneCommand = "done _".length();
            boolean doneCommandFormatIsWrong = str.indexOf(" ") == -1;
            if (doneCommandFormatIsWrong || str.length() < minimumLengthOfDoneCommand) {
                //check if str follows the mark as done command format: done_taskNumber
                throw new DukeException("Wrong input for marking task as done.");
            }
            int taskNumberIndex = str.indexOf(" ") + 1;
            int taskNumber = Integer.parseInt(str.substring(taskNumberIndex));
            return taskNumber;
        } catch  (NumberFormatException ex) {
           throw new DukeException("Task must be an integer!");
        }
    }

    /**
     * Returns the task number of the task that should be deleted given a command
     * to delete a task from the user.
     *
     * @param str A command to delete a task.
     * @return The task number of the task that should be deleted.
     * @throws DukeException If the format of the delete command is incorrect.
     */
    public int parseDeleteCommand(String str) throws DukeException {
        try {
            int minimumLengthOfDeleteCommand = "delete _".length();
            boolean deleteCommandFormatIsWrong = str.indexOf(" ") == -1;
            int index = str.indexOf(" ");
            if (deleteCommandFormatIsWrong || str.length() < minimumLengthOfDeleteCommand) {
                //check if str follows the delete command format: delete_taskNumber
                throw new DukeException("Wrong input for deleting task.");
            }
            int taskNumber = Integer.parseInt(str.substring(index + 1));
            return taskNumber;
        } catch  (NumberFormatException ex) {
            throw new DukeException("Task must be an integer!");
        }
    }

    /**
     * Returns String use as the keyword for finding matching tasks.
     *
     * @param str A command to find matching tasks.
     * @return The string that is to be used as the keyword.
     * @throws DukeException If the format of the find command is incorrect.
     */
    public String parseFindCommand(String str) throws DukeException {
        int minimumLengthOfFindCommand = "find _".length();
        boolean findCommandFormatIsWrong = str.indexOf(" ") == -1;
        if (str.length() < minimumLengthOfFindCommand || findCommandFormatIsWrong) {
            //check if str follows the find command format: find_keyword
            throw new DukeException("Wrong input for finding task.");
        } else {
            int indexForStartOfKeyword = 5;
            return str.substring(indexForStartOfKeyword);
        }
    }

}
