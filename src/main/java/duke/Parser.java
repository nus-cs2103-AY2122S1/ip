package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;

public class Parser {

    /**
     * Takes in user's input and convert it into a command obj.
     *
     * @param input User's raw input
     * @return A command object
     * @throws IncompleteCommandException if command is incomplete
     */
    public static Command parse(String input) throws IncompleteCommandException{
        if (input.toUpperCase().equals(CommandList.BYE.toString())) {
            return new ByeCommand(input);
        } else if (input.toUpperCase().equals(CommandList.LIST.toString())) {
            return new ListCommand(input);
        } else if (input.toUpperCase().contains(CommandList.DELETE.toString())) {
            return new DeleteCommand(input);
        } else if (isDone(input)) {
            return new DoneCommand(input);
        } else {
            //ADD duke.command.Command
            Task newTask = null;
            if (input.toUpperCase().contains(CommandList.TODO.toString())) {
                if (input.length() > 5) {
                    String taskMessage = input.substring(5);
                    newTask = new Todo(taskMessage.strip());
                } else {
                    throw new IncompleteCommandException("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (input.toUpperCase().contains(CommandList.DEADLINE.toString())) {
                if (input.length() > 8) {
                    if (input.contains("/by")) {
                        String[] stringArr = input.substring(9).split("/by");
                        LocalDate date;
                        try {
                            date = LocalDate.parse(stringArr[1].strip());
                            newTask = new Deadline(stringArr[0], date);
                        } catch (Exception e) {
                            System.out.println("Incorrect date format! Please follow YYYY-MM-DD for the date");
                        }

                    } else {
                        System.out.println("Your deadline is missing a /by (date)");
                    }
                } else {
                    throw new IncompleteCommandException("OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (input.toUpperCase().contains(CommandList.EVENT.toString())) {
                if (input.length() > 5) {
                    if (input.contains("/at")) {
                        String[] stringArr = input.substring(6).split("/at");
                        newTask = new Event(stringArr[0], LocalDate.parse(stringArr[1].strip()));
                    } else {
                        System.out.println("Your duke.task.Event is missing a /at (date)");
                    }
                } else {
                    throw new IncompleteCommandException("OOPS!!! The description of a event cannot be empty.");
                }
            }
            if (newTask != null) {
                return new AddCommand(input, newTask);
            }
        }

        return new InvalidCommand("");
    }

    /**
     * Check if a given input has done command.
     *
     * @param input a string that is the input of the user.
     * @return a boolean if done command is found.
     */
    public static boolean isDone(String input) {
        if (input.length() >= 4) {
            return input.toUpperCase().startsWith(CommandList.DONE.toString());
        } else {
            return false;
        }
    }

    /**
     * Check if input string is numeric or not.
     *
     * @param input a string input from user.
     * @return a boolean if input is numeric.
     */
    public static boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
