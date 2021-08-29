package duke;

import duke.commands.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Command parse(String inputString) throws DukeException {
        String command = inputString.split(" ")[0];

        if (inputString.equals("bye")) {
            return new ExitCommand();
        } else if (inputString.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")) {
            try {
                String[] inputStringArr = inputString.split(" ");

                if (inputStringArr.length != 2) {
                    throw new DukeException("OOPS!!! Invalid input.\n");
                }

                return new DoneCommand(Integer.parseInt(inputStringArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! Please input a number after done.\n");
            }
        } else if (command.equals("todo")) {
            String[] inputStringArr = inputString.split(" ");

            if (inputStringArr.length < 2) {
                throw new DukeException("OOPS!!! Please input description.\n");
            }

            String todoDescr = inputString.substring(5);
            return new TodoCommand(todoDescr);
        } else if (command.equals("deadline")) {
            String[] inputStringArr = inputString.split(" /by ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            if (inputStringArr[0].equals("deadline")) {
                throw new DukeException("OOPS!!! Please input a description\n");
            }

            if (inputStringArr.length != 2) {
                throw new DukeException("OOPS!!! Please input a valid date.\n");
            }

            String deadlineDescr = inputStringArr[0].substring(9);
            LocalDateTime dateTime = LocalDateTime.parse(inputStringArr[1], formatter);

            return new DeadlineCommand(deadlineDescr, dateTime);
        } else if (command.equals("event")) {
            String[] inputStringArr = inputString.split(" /at ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            if (inputStringArr[0].equals("event")) {
                throw new DukeException("OOPS!!! Please input a description\n");
            }

            if (inputStringArr.length != 2) {
                throw new DukeException("OOPS!!! Please input a valid date.\n");
            }

            String eventDescr = inputStringArr[0].substring(6);
            LocalDateTime dateTime = LocalDateTime.parse(inputStringArr[1], formatter);

            return new EventCommand(eventDescr, dateTime);
        } else if (command.equals("delete")) {
            try {
                String[] inputStringArr = inputString.split(" ");

                if (inputStringArr.length != 2) {
                    throw new DukeException("OOPS!!! Invalid Input.\n");
                }

                return new DeleteCommand(Integer.parseInt(inputStringArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! Please input a number after delete.\n");
            }
        } else if (command.equals("find")) {
            String[] inputStringArr = inputString.split(" ");

            if (inputStringArr.length < 2) {
                throw new DukeException("OOPS!!! Please input a string after find.\n");
            }

            return new FindCommand(inputString.substring(5));
        } else {
            throw new DukeException("Invalid input. Please try again\n");
        }
    }
}
