package duke;

public class Parser {

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
        } else if (str.equals("\n")){
            return "empty";
        } else {
            return "errorInput";
        }
    }

    public Task parseAddTask(String str) throws DukeException{
        if (str.startsWith("todo")) {
            //todo format: todo_description
            if ((str.charAt(4) != ' ') || str.length() < 6) {
                throw new DukeException("The description of todo cannot be empty");
            }
            return new ToDo(str.substring(5));
        } else if (str.startsWith("event")) {
            //event format: event_description_/at_time
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
            //deadline format: deadline_description_/by_time
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

    public String parseDoneCommand(String str) throws DukeException {
        int index = str.indexOf(" ");
        if (index == -1 || str.length() < 6) {
            throw new DukeException("Wrong input for marking task as done.");
        }
        return str.substring(index + 1);
    }

    public String parseDeleteCommand(String str) throws DukeException {
        int index = str.indexOf(" ");
        if (index == -1 || str.length() < 8) {
            throw new DukeException("Wrong input for deleting task.");
        }
        return str.substring(index + 1);
    }

}
