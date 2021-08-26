package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] splitWord = input.split(" ", 2);
        String firstWord = splitWord[0];

        if (input.equals("bye")) {
            return new ExitCommand();
            
        } else if (input.equals("list")) {
            return new PrintCommand();
            
        } else if (firstWord.equals("done")) {
            String[] splitCommand = input.split(" ");
            if (splitCommand.length != 2) {
                throw new IllegalFormatException("☹ OOPS!!! Please specify task to be mark done in the correct format.");
            }

            String secondWord = splitCommand[1];
            int secondWordLength = secondWord.length();
            for (int i = 0; i < secondWordLength; i++) {
                if (!Character.isDigit(secondWord.charAt(i))) {
                    throw new IllegalFormatException("☹ OOPS!!! Please specify the task to be mark done as a number.");
                }
            }
            int taskToBeMarkDone = Integer.parseInt(secondWord) - 1;
            return new MarkDoneCommand(taskToBeMarkDone);
        } else if (firstWord.equals("todo")) {
            String[] splitCommand = input.split(" ",2);
            if (splitCommand.length == 1) {
                throw new IllegalFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
            }

            String toDoDescription = input.split(" ",2)[1];
            if (toDoDescription.length() == 0) {
                throw new IllegalFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Task taskToBeAdded = new ToDo(toDoDescription);
            return new AddCommand(taskToBeAdded);
        } else if (firstWord.equals("deadline")) {
            String[] splitCommand = input.split(" ",2);
            if (splitCommand.length == 1) {
                throw new IllegalFormatException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }

            String[] splitTask = splitCommand[1].split(" /by ", 2);
            String deadlineDescription = splitTask[0];
            if (deadlineDescription.length() == 0) {
                throw new IllegalFormatException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (splitTask.length == 1) {
                throw new IllegalFormatException("☹ OOPS!!! Please specify the deadline date in the format yyyy-mm-dd.");
            }

            LocalDate deadlineDateTime;
            try {
                deadlineDateTime = LocalDate.parse(splitTask[1]);
            } catch (DateTimeParseException e) {
                throw new IllegalFormatException("☹ OOPS!!! Please specify the deadline date in the format yyyy-mm-dd.");
            }

            Task taskToBeAdded = new Deadline(deadlineDescription, deadlineDateTime);
            return new AddCommand(taskToBeAdded);
        } else if (firstWord.equals("event")) {
            String[] splitCommand = input.split(" ",2);
            if (splitCommand.length == 1) {
                throw new IllegalFormatException("☹ OOPS!!! The description of a event cannot be empty.");
            }

            String[] splitTask = splitCommand[1].split(" /at ", 2);
            String eventDescription = splitTask[0];
            if (eventDescription.length() == 0) {
                throw new IllegalFormatException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            if (splitTask.length == 1) {
                throw new IllegalFormatException("☹ OOPS!!! Please specify the event date in the format yyyy-mm-dd.");
            }

            LocalDate eventDateTime;
            try {
                eventDateTime = LocalDate.parse(splitTask[1]);
            } catch (DateTimeParseException e) {
                throw new IllegalFormatException("☹ OOPS!!! Please specify the event date in the format yyyy-mm-dd.");
            }

            Task taskToBeAdded = new Event(eventDescription, eventDateTime);
            return new AddCommand(taskToBeAdded);
        } else if (firstWord.equals("delete")) {
            String[] splitCommand = input.split(" ");

            if (splitCommand.length != 2) {
                throw new IllegalFormatException("☹ OOPS!!! Please specify task to be removed in the correct format.");
            }

            String secondWord = splitCommand[1];
            int secondWordLength = secondWord.length();
            for (int i = 0; i < secondWordLength; i++) {
                if (!Character.isDigit(secondWord.charAt(i))) {
                    throw new IllegalFormatException("☹ OOPS!!! Please specify the task to be mark done as a number.");
                }
            }
            int taskIndexToBeRemoved = Integer.parseInt(secondWord) - 1;
            return new DeleteCommand(taskIndexToBeRemoved);
        } else if (firstWord.equals("find")) {
            String[] splitCommand = input.split(" ");

            if (splitCommand.length != 2) {
                throw new IllegalFormatException("☹ OOPS!!! Please specify one keyword to be searched.");
            }

            String keyword = splitCommand[1];
            
            return new FindCommand(keyword);
        } else {
            throw new UnknownCommandException();
        }
    }
}
