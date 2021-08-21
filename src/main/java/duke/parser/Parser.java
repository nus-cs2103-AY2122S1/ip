package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.EmptyTaskDescriptionException;
import duke.exception.MissingTaskIndexException;
import duke.exception.TimeNotSpecifiedException;
import duke.exception.UnrecognisedCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public Command parse(String command) throws UnrecognisedCommandException, MissingTaskIndexException,
            EmptyTaskDescriptionException, TimeNotSpecifiedException, DateTimeParseException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else {
            if (command.equals("list")) {
                return new ListCommand();
            } else {
                // Splits the string on spaces, and acts accordingly based on the first word
                String[] wordsArr = command.split(" ");
                if (wordsArr.length == 0) {
                    throw new UnrecognisedCommandException();
                } else if (wordsArr[0].equals("done")) {
                    if (wordsArr.length != 2) {
                        throw new MissingTaskIndexException();
                    }
                    return new DoneCommand(Integer.valueOf(wordsArr[1]) - 1);
                } else if (wordsArr[0].equals("delete")) {
                    if (wordsArr.length != 2) {
                        throw new MissingTaskIndexException();
                    }
                    return new DeleteCommand(Integer.valueOf(wordsArr[1]) - 1);
                } else if (wordsArr[0].equals("todo") || wordsArr[0].equals("event")
                        || wordsArr[0].equals("deadline")) {
                    String taskType;
                    String task;
                    LocalDate date;

                    taskType = wordsArr[0];
                    int indexOfSpaceChar = command.indexOf(" ");
                    if (indexOfSpaceChar == -1) {
                        throw new EmptyTaskDescriptionException(taskType);
                    }
                    String taskDescription = command.substring(indexOfSpaceChar + 1);
                    if (taskDescription.trim().isEmpty()) {
                        throw new EmptyTaskDescriptionException(taskType);
                    }

                    if (taskType.equals("deadline")) {
                        if (!taskDescription.contains(" /by ")) {
                            throw new TimeNotSpecifiedException(" /by ");
                        }
                        String[] descriptionArr = taskDescription.split(" /by ");
                        task = descriptionArr[0];
                        String deadline = descriptionArr[1];
                        if (deadline.trim().isEmpty()) {
                            throw new TimeNotSpecifiedException(" /by ");
                        }
                        date = LocalDate.parse(deadline);
                        return new AddCommand(taskType, task, date);
                    } else if (taskType.equals("event")) {
                        if (!taskDescription.contains(" /at ")) {
                            throw new TimeNotSpecifiedException(" /at ");
                        }
                        String[] descriptionArr = taskDescription.split(" /at ");
                        task = descriptionArr[0];
                        String timeFrame = descriptionArr[1];
                        if (timeFrame.trim().isEmpty()) {
                            throw new TimeNotSpecifiedException(" /at ");
                        }
                        date = LocalDate.parse(timeFrame);
                        return new AddCommand(taskType, task, date);
                    } else {
                        task = taskDescription;
                        return new AddCommand(taskType, task);
                    }
                } else {
                    throw new UnrecognisedCommandException();
                }
            }
        }
    }
}
