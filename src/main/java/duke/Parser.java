package duke;

import duke.command.*;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser which parses the input given by users of Duke.
 */
public class Parser {
    /**
     * Checks if dateString is in a valid date form depicted by dateFormatter.
     *
     * @param dateString String which contains a date.
     * @param dateFormatter Format which you want to check if dateString is in.
     * @return true if dateString is in valid form, else otherwise.
     */
    public static boolean isValidDate(String dateString, DateTimeFormatter dateFormatter) {
        try {
            LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if timeString is in a valid date form depicted by timeFormatter.
     *
     * @param timeString String which contains a time.
     * @param timeFormatter Format which you want to check if timeString is in.
     * @return true if timeString is in valid form, else otherwise.
     */
    public static boolean isValidTime(String timeString, DateTimeFormatter timeFormatter) {
        try {
            LocalTime.parse(timeString, timeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if input is a remove command.
     *
     * @param input Input which is being checked.
     * @return true if input is a remove command.
     */
    public static boolean isRemove(String input){
        return input.startsWith("remove ");
    }

    /**
     * Checks if input is a done command.
     *
     * @param input Input which is being checked.
     * @return true if input is a done command.
     */
    public static boolean isDone(String input) {
//        String[] splited = phrase.split(" ");
////        System.out.println(splited);
//        if (splited.length != 2) {
//            return false;
//        } else {
//            return splited[0].equals("done") && splited[1]
//                  .matches("\\d+") && Integer.valueOf(splited[1]) <= listLength;
//        }
        return input.startsWith("done ");
    }

    /**
     * Checks if user input is a find command.
     *
     * @param input User input to check if it is a find command.
     * @return true if it is a find command, else false.
     */
    public static boolean isFind(String input) {
        return input.startsWith("find ");
    }

    /**
     * Splits string which contains a description and possibly a deadline of Task Objects.
     *
     * @param input Input which contains a description and possibly a deadline.
     * @param type Type of Task information the input contains.
     * @return String[] containing the description at index 0 and the deadline (if any) at index 1.
     * @throws DukeException If no deadline or description for respective Task objects.
     */
    public static String[] splitInput(String input, String type) throws DukeException {
        if (type.equals("deadline") || type.equals("event")) {
            String[] str = input.split("/");

            if (str.length == 1) {
                if (type.equals("deadline")) {
                    throw new IncompleteDeadlineException();
                } else {
                    throw new IncompleteEventException();
                }
            } else {
                String[] first = str[0].split(" ");
                String[] second = str[1].split(" ");

                String description = "";
                String deadline = "";

                for (int i = 1; i < first.length; i++) {
                    description += first[i];
                    if (i != first.length - 1) {
                        description += " ";
                    }
                }

                for (int i = 1; i < second.length; i++) {
                    if (i == 1 && (second[i].equals("by") || second[i].equals("at"))) {
                        // handle the case where user formatted command wrongly (include a space after "/")
                        continue;
                    }
                    if (Parser.isValidDate(second[i], DateTimeFormatter.ISO_LOCAL_DATE)) {
                        deadline += LocalDate.parse(second[i], DateTimeFormatter.ISO_LOCAL_DATE)
                                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    } else if (Parser.isValidTime(second[i], DateTimeFormatter.ISO_LOCAL_TIME)) {
                        deadline += LocalTime.parse(second[i], DateTimeFormatter.ISO_LOCAL_TIME)
                                .format(DateTimeFormatter.ofPattern("hh:mm a"));
                    } else {
                        deadline += second[i];
                    }

                    if (i != second.length - 1) {
                        deadline += " ";
                    }
                }

                return new String[]{description, deadline};
            }
        } else {
            String[] str = input.split(" ");

            if (str.length == 1) {
                throw new IncompleteToDoException();
            } else {
                String description = "";
                for (int i = 1; i < str.length; i++) {
                    description += str[i];
                    if (i != str.length - 1) {
                        description += " ";
                    }
                }
                return new String[]{description};
            }
        }
    }

    /**
     * Parses the input string and returns a command for Duke to execute.
     *
     * @param userInput Input which needs to be parsed.
     * @param ui Ui object from Duke class.
     * @param tasks TaskList object from Duke class.
     * @return Command to execute.
     * @throws DukeException If incorrect values are passed for remove or done commands.
     */
    public static Command parse(String userInput, Ui ui, TaskList tasks) throws DukeException{
        if (userInput.equals("list")) {
            return new ListCommand();
        } else if (Parser.isDone(userInput)) {
            String[] splited = userInput.split(" ");

            if (splited.length < 2 || !splited[1].matches("\\d+")
                    || Integer.valueOf(splited[1]) > tasks.getSize()) {
                throw new DukeException(ui.buildMessage("Please key in valid number to mark as done."));
            } else {
                int index = Integer.valueOf(splited[1]) - 1;
                return new DoneCommand(index);
            }
        } else if (Parser.isRemove(userInput)) {
            String[] str = userInput.split(" ");

            if (str.length < 2 || !str[1].matches("\\d+") || Integer.valueOf(str[1]) > tasks.getSize()) {
                throw new DukeException(ui.buildMessage("Please key in valid number to remove."));
            } else {
                return new RemoveCommand(Integer.valueOf(str[1]) - 1);
            }
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (Parser.isFind(userInput)) {
            String[] splitInput = userInput.split(" ");

            if (splitInput.length == 1) {
                throw new IncompleteFindException();
            }

            String keyword = splitInput[1];

            return new FindCommand(keyword);
        } else {
            String[] splited = userInput.split(" ");

            if (splited[0].equals("todo") || splited[0].equals("deadline") || splited[0].equals("event")) {
                String[] str = Parser.splitInput(userInput, splited[0]);

                if (splited[0].equals("todo")) {
                    ToDo add = new ToDo(str[0]);
                    return new AddCommand(add);
                } else if (splited[0].equals("deadline")) {
                    Deadline add = new Deadline(str[0], str[1]);
                    return new AddCommand(add);
                } else {
                    Event add = new Event(str[0], str[1]);
                    return new AddCommand(add);
                }
            } else {
                throw new InvalidCommandException();
            }
        }
    }
}
