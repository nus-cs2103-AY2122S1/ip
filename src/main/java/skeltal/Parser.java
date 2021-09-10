package skeltal;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * A Parser class that handles the parsing of userInputs, to determine Skeltal's response.
 */
public class Parser {
    /**
     * This method parses the user's input and determines skeltal's response accordingly.
     * userInput should be in the form "command arg1 arg2".
     * @param userInput A String of the userInput.
     */
    public static String parse(String userInput) {
        String reply = "";
        try {
            String[] commandDescriptionArr = parseInput(userInput);
            isUserInputValid(commandDescriptionArr);
            String command = commandDescriptionArr[0];
            String description = commandDescriptionArr[1];

            switch(command) {
                case "list":
                    reply = TaskList.listReply();
                    break;
                case "done":
                    reply = TaskList.done(description) + "\n";
                    reply += Storage.store();
                    break;
                case "deadline":
                    Deadline dead = new Deadline(description);
                    reply = addThenStore(dead);
                    break;
                case "event":
                    Event event = new Event(description);
                    reply = addThenStore(event);
                    break;
                case "todo":
                    ToDo todo = new ToDo(description);
                    reply = addThenStore(todo);
                    break;
                case "delete":
                    deleteThenStore(description);
                    break;
                case "store":
                    reply += Storage.store();
                    break;
                case "find":
                    reply += TaskList.findMatchingTasks(description);
                    break;
                default:
                    throw new SkeltalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (SkeltalException e) {
            reply = e.getMessage();
        } finally {
            assert reply != "" : "Skeltal's reply cannot be empty";
            return reply;
        }
    }

    public static String parseDescription(String description, String taskType) throws SkeltalException {
        String[] taskAndTime = description.split("/", 2);

        if (taskAndTime.length == 1) {
            throw new SkeltalException("OOPS! The description of an " + taskType + " cannot be empty!");
        }

        String time = taskAndTime[1];
        assert time != null : "Time field cannot be empty";

        if (time.length() > 3) {
            String[] atOrBy = time.split(" ", 2);
            if (atOrBy[0].equals("by") || atOrBy[0].equals("at")) {
                time = time.substring(3);
            }
        }

        try {
            LocalDate date = LocalDate.parse(time, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            time = date.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        } catch (DateTimeParseException e) {
        }

        return time;
    }

    private static String[] parseInput(String userInput) {
        String[] commandDescriptionArr = userInput.split(" ", 2);
        assert commandDescriptionArr[2] != null : "Description cannot be empty";

        return commandDescriptionArr;
    }

    private static String addThenStore(Task inputTask) throws SkeltalException {
        String reply = "";
        reply += TaskList.addTask(inputTask) + "\n";
        reply += Storage.store();
        return reply;
    }

    private static String deleteThenStore(String description) throws SkeltalException {
        String reply = "";
        reply += "Noted. I have removed this task\n";
        reply += TaskList.delete(description);
        reply += Storage.store();
        return reply;
    }

    private static void isUserInputValid(String[] isValid) throws SkeltalException {
        if (isValid.length == 1) {
            if (isValid[0].equals("todo")) {
                throw new SkeltalException("OOPS! The description of todo cannot be empty!");
            } else {
                throw new SkeltalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
