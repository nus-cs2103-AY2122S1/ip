import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {

    TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public String[] checkInput(String input) throws DukeException {

        String command = input.split(" ")[0];
        String[] output;
        String parsedInput;

        switch (command) {
            case "done":
                parsedInput = doneCheck(input);
                output = new String[]{command, parsedInput};
                return output;

            case "delete":
                parsedInput = deleteCheck(input);
                output = new String[]{command, parsedInput};
                return output;

            case "todo":
                parsedInput = todoCheck(input);
                output = new String[]{command, parsedInput};
                return output;

            case "event":
                parsedInput = eventCheck(input);
                output = new String[]{command, parsedInput.split(" \\| ")[0], parsedInput.split(" \\| ")[1]};
                return output;

            case "deadline":
                parsedInput = deadlineCheck(input);
                output = new String[] {command, parsedInput.split(" \\| ")[0],
                        parsedInput.split(" \\| ")[1], parsedInput.split(" \\| ")[2]};
                return output;

            case "bye":

            case "list":
                return new String[]{command};


            default:
                throw new DukeException("Please enter a valid command");
        }
    }

    public String doneCheck(String input) throws DukeException {
        if (input.length() == 4) {
            throw new DukeException("Please use this format: 'done (task number)'");
        } else if (Integer.parseInt(input.split(" ")[1]) < 0 ||
                Integer.parseInt(input.split(" ")[1]) > taskList.getLength()) {
            throw new DukeException("Invalid task number!");
        }
        return input.split(" ")[1];
    }

    public String deleteCheck(String input) throws DukeException {
        if (input.length() == 6) {
            throw new DukeException("Please use this format: 'delete (task number)'");
        } else if (Integer.parseInt(input.split(" ")[1]) < 0 ||
                Integer.parseInt(input.split(" ")[1]) > taskList.getLength()) {
            throw new DukeException("Invalid task number!");
        }
        return input.split(" ")[1];
    }

    public String todoCheck(String input) throws DukeException {
        if (input.length() == 4) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String todoDesc = input.substring(5);
        return todoDesc;
    }

    public String eventCheck(String input) throws DukeException {
        // need to check that for event they use the /at properly else reject
        if (!input.contains("/at ")) {
            throw new DukeException("Please use this format: 'event <task> /at <date and time>' to specify the date and time!");
        }
        int eventDateIndex = input.indexOf("/at ") + 4;
        String eventDesc = input.substring(6, eventDateIndex - 4);
        String eventDate = input.substring(eventDateIndex);
        return eventDesc + " | " + eventDate;
    }

    public String deadlineCheck(String input) throws DukeException {
        // need to check that for deadline they use the /by properly else reject
        if (!input.contains("/by ")) {
            throw new DukeException("Please use this format: 'deadline <task> /by <date and time>' to specify the date and time!");
        }
        int deadlineDateIndex = input.indexOf("/by ") + 4;
        int deadlineTimeIndex = deadlineDateIndex + 11;

        String date = input.substring(deadlineDateIndex, deadlineDateIndex + 10);
        String time = input.substring(deadlineTimeIndex);
        String deadlineDesc = input.substring(9, deadlineDateIndex - 4); //skip the "deadline "
        return deadlineDesc + " | " + date + " | " + time;

    }


}
