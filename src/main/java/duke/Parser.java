package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.RemoveCommand;
import duke.exception.DukeException;
import duke.exception.IncompleteDeadlineException;
import duke.exception.IncompleteEventException;
import duke.exception.IncompleteToDoException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static boolean isValidDate(String dateString, DateTimeFormatter dateFormatter) {
        try {
            LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidTime(String timeString, DateTimeFormatter timeFormatter) {
        try {
            LocalTime.parse(timeString, timeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    public static boolean isRemove(String input){
        return input.startsWith("remove ");
    }

    public static boolean isDone(String input) {
//        String[] splited = phrase.split(" ");
////        System.out.println(splited);
//        if (splited.length != 2) {
//            return false;
//        } else {
//            return splited[0].equals("done") && splited[1].matches("\\d+") && Integer.valueOf(splited[1]) <= listLength;
//        }
        return input.startsWith("done ");
    }

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
                        deadline += LocalDate.parse(second[i], DateTimeFormatter.ISO_LOCAL_DATE).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    } else if (Parser.isValidTime(second[i], DateTimeFormatter.ISO_LOCAL_TIME)) {
                        deadline += LocalTime.parse(second[i], DateTimeFormatter.ISO_LOCAL_TIME).format(DateTimeFormatter.ofPattern("hh:mm a"));
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

    //parses the given userInput and returns a string for the UI to show the user
    public static Command parse(String userInput, Ui ui, TaskList taskList) throws DukeException{
        if (userInput.equals("list")) {
            return new ListCommand();
        } else if (Parser.isDone(userInput)) {
            String[] splited = userInput.split(" ");
            if (splited.length < 2 || !splited[1].matches("\\d+") || Integer.valueOf(splited[1]) > taskList.getSize()) {
                throw new DukeException(ui.buildMessage("Please key in valid number to mark as done."));
            } else {
                int index = Integer.valueOf(splited[1]) - 1;
                return new DoneCommand(index);
            }
        } else if (Parser.isRemove(userInput)) {
            String[] str = userInput.split(" ");
            if (str.length < 2 || !str[1].matches("\\d+") || Integer.valueOf(str[1]) > taskList.getSize()) {
                throw new DukeException(ui.buildMessage("Please key in valid number to remove."));
            } else {
                return new RemoveCommand(Integer.valueOf(str[1]) - 1);
            }
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
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
