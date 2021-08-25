package duke;

import java.time.LocalDate;

import commands.*;
import tasks.*;
import exceptions.*;

/**
 * This class handles the parsing of the user input into duke.Duke
 *
 * Generally, it parses the command and returns a Command object.
 *
 */


public class DukeParser {

    /**
     * Parses the input String given by the user and returns a Command object for
     * Duke to execute according to the user input.
     *
     * @param input The input line given by the user in Duke.
     * @return A Command that represents the user's input.
     */
    public Command parse(String input) {
        try {
            String[] parsedInput = input.split(" ", 2);
            String command = parsedInput[0];
            if (command.equals("list")) {
                return new DisplayTasksCommand();
            } else if (command.equals("bye")) {
                return new QuitCommand();
            } else if (command.equals("help")) {
                return new HelpCommand();
            } else if (command.equals("done")) {
                // assume command is in the form "done x"
                Integer index = Integer.parseInt(parsedInput[1].trim());

                // subtract 1 as task list is 0-indexed but on display it is 1-indexed.
                return new MarkDoneCommand(index - 1);
            } else if (command.equals("delete")) {
                // assume command is in the form "delete x"
                Integer index = Integer.parseInt(parsedInput[1].trim());

                // subtract 1 as task list is 0-indexed but on display it is 1-indexed.
                return new DeleteTaskCommand(index - 1);
            } else if (command.equals("todo")) {
                String desc = parsedInput[1];
                return new AddTodoCommand(desc, false);
            } else if (command.equals("event")) {
                String[] details = parsedInput[1].split(" /at ");
                String desc = details[0];
                String time = details[1];
                return new AddEventCommand(desc, false, time);
            } else if (command.equals("deadline")) {
                String[] details = parsedInput[1].split(" /by ");
                String desc = details[0];
                LocalDate time = LocalDate.parse(details[1]);
                return new AddDeadlineCommand(desc, false, time);
            } else if (command.equals("find")) {
                String query = parsedInput[1];
                return new FindTaskCommand(query);
            }
            return new UnknownCommand();
        } catch (NumberFormatException e) {
            System.out.println("It seems like you have entered an invalid task number");
            System.out.println("Please enter the task number as shown in the list.");
            return new ErrorCommand("An exception occurred! See details above.");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("Missing fields in command. Type 'help' for more info.");
        } catch (Exception e) { //TODO DANGEROUS! please change
            return new ErrorCommand("An exception occurred!");
        }
    }
}
