package skeltal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Parser class that handles the parsing of userInputs, to determine Skeltal's response.
 */
public class Parser {

    /**
     * This method parses the user's input and determines skeltal's response accordingly.
     * userInput should be in the form "command arg1 arg2".
     * @param userInput A String of the userInput.
     */
    public static void parse(String userInput) {
        try {
            String[] commandDescriptionArr = parseInput(userInput);
            System.out.println(Ui.line());
            switch(commandDescriptionArr[0]) {
                case "list":
                    TaskList.listReply();
                    break;
                case "done":
                    TaskList.done(commandDescriptionArr[1]);
                    Storage.store();
                    break;
                case "deadline":
                    Deadline dead = new Deadline(commandDescriptionArr[1]);
                    TaskList.addTask(dead);
                    Storage.store();
                    break;
                case "event":
                    Event event = new Event(commandDescriptionArr[1]);
                    TaskList.addTask(event);
                    Storage.store();
                    break;
                case "todo":
                    if (commandDescriptionArr.length == 1) {
                        throw new SkeltalException("OOPS! The description of todo cannot be empty!");
                    }
                    ToDo todo = new ToDo(commandDescriptionArr[1]);
                    TaskList.addTask(todo);
                    Storage.store();
                    break;
                case "delete":
                    System.out.println("Noted. I have removed this task");
                    TaskList.delete(commandDescriptionArr[1]);
                    Storage.store();
                    break;
                case "store":
                    Storage.store();
                    break;
                case "find":
                    TaskList.findMatchingTasks(commandDescriptionArr[1]);
                    break;
                default:
                    throw new SkeltalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (SkeltalException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Ui.line());
    }

    public static String parseDescription(String description, String taskType) throws SkeltalException {
        String[] taskAndTime = description.split("/", 2);

        if (taskAndTime.length == 1) {
            throw new SkeltalException("OOPS! The description of an " + taskType + " cannot be empty!");
        }

        String time = taskAndTime[1];
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
        return commandDescriptionArr;
    }
}
