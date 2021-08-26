package duke;

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
            //checks if str follows the todo format: todo_description
            if ((str.charAt(4) != ' ') || str.length() < 6) {
                throw new DukeException("The description of todo cannot be empty");
            }
            return new ToDo(str.substring(5));
        } else if (str.startsWith("event")) {
            //checks if str follows the event format: event_description_/at_time
            if (str.charAt(5) != ' ' || str.length() < 12) {
                throw new DukeException("Wrong input for adding an event-task.");
            }
            int index = str.indexOf("/at ");
            if (index == -1) {
                throw new DukeException("You must specify the time for an event-task.");
            }
            String description = str.substring(6, index - 1);
            String time = str.substring(index + 4);
            return new Event(description, time);
        } else {
            //checks if str follows the deadline format: deadline_description_/by_time
            if (str.charAt(8) != ' ' || str.length() < 15) {
                throw new DukeException("Wrong input for adding a deadline-task.");
            }
            int index = str.indexOf("/by ");
            if (index == -1) {
                throw new DukeException("You must specify the deadline.");
            }
            String description = str.substring(9, index - 1);
            String time = str.substring(index + 4);
            return new Deadline(description, time);
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
            int index = str.indexOf(" ");
            if (index == -1 || str.length() < 6) {
                //check if str follows the mark as done command format: done_taskNumber
                throw new DukeException("Wrong input for marking task as done.");
            }
            int taskNumber = Integer.parseInt(str.substring(index + 1));
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
            int index = str.indexOf(" ");
            if (index == -1 || str.length() < 8) {
                //check if str follows the delete command format: delete_taskNumber
                throw new DukeException("Wrong input for deleting task.");
            }
            int taskNumber = Integer.parseInt(str.substring(index + 1));
            return taskNumber;
        } catch  (NumberFormatException ex) {
            throw new DukeException("Task must be an integer!");
        }
    }

    public String parseFindKeyWord(String str) throws DukeException {
        if (str.length() < 6 || str.indexOf(" ") == -1) {
            //check if str follows the find command format: find_keyword
            throw new DukeException("Wrong input for finding task.");
        } else {
            return str.substring(5);
        }
    }

}
