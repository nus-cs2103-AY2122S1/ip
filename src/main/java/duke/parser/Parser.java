package duke.parser;

import duke.commands.*;
import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasklist.TaskList;
import duke.utils.Constants;
import java.time.LocalDate;


public class Parser {

    public static Command parse(String input, TaskList taskList) {
        String[] parsedInput = input.trim().split("\\s", 2);
        String command = parsedInput[0];

        switch(command.toUpperCase()) {
            case "BYE":
                return parseBye();
            case "LIST":
                return parseList(taskList);
            case "DONE":
                return parseDone(input, taskList);
            case "DELETE":
                return parseDelete(input, taskList);
            case "TASKS_ON":
                return parseTasksOn(input, taskList);
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                return parseTask(input, taskList);
            default:
                throw new InvalidKeywordException();

        }
    }


    private static ByeCommand parseBye() {
        return new ByeCommand();
    }

    private static ListCommand parseList(TaskList taskList) {
        return new ListCommand();
    }

    private static AddCommand parseTask(String input, TaskList taskList) {
        Task task;
        String[] splitTasks = input.split("\\s", 2);
        String taskType = splitTasks[0].toLowerCase();
        try {
            switch (taskType) {
                case "todo":
                    if (hasEmptyDesc(splitTasks)) {
                        throw new EmptyDescriptionException(
                                "Sorry, the description of a todo cannot be empty" + Constants.TODO_FORMAT
                        );
                    } else {
                        String desc = splitTasks[1].trim();
                        task = new ToDo(desc);
                    }
                    break;
                case "deadline":
                    if (hasEmptyDesc(splitTasks)) {
                        throw new EmptyDescriptionException(
                                "Sorry, the description of a deadline cannot be empty" + Constants.DEADLINE_FORMAT
                        );
                    } else {
                        String[] parsedDeadline = splitTasks[1].split("/by");
                        if (hasDateButEmptyDesc(parsedDeadline)) {
                            throw new EmptyDescriptionException(
                                    "Sorry, the description of a deadline cannot be empty" + Constants.DEADLINE_FORMAT
                            );
                        } else if (hasEmptyDesc(parsedDeadline)) {
                            throw new IncorrectFormatException(
                                    "Please add a date for your deadline!" + Constants.DEADLINE_FORMAT);
                        } else {
                            String desc = parsedDeadline[0].trim();
                            String date = parsedDeadline[1].trim();
                            task = new Deadline(desc, DateTimeParser.deadlineDateParse(date));
                        }
                    }
                    break;
                case "event":
                    if (hasEmptyDesc(splitTasks)) {
                        throw new EmptyDescriptionException(
                                "Sorry the description of an event cannot be empty" + Constants.EVENT_FORMAT
                        );
                    } else {
                        String[] parsedEvent = splitTasks[1].split("/at");
                        if (hasDateButEmptyDesc(parsedEvent)) {
                            throw new EmptyDescriptionException(
                                    "Sorry the description of an event cannot be empty" + Constants.EVENT_FORMAT
                            );
                        } else if (hasEmptyDesc(parsedEvent)) {
                            throw new IncorrectFormatException(
                                    "Please add a date and time for your event!" + Constants.EVENT_FORMAT);
                        } else {
                            String details = parsedEvent[0].trim();
                            String at = parsedEvent[1].trim();
                            task = new Event(details, DateTimeParser.eventDateTimeParse(at));
                        }
                    }
                    break;
                default:
                    throw new InvalidKeywordException();
            }
            return new AddCommand(task);
        } catch (ArrayIndexOutOfBoundsException ex) {
            switch (taskType) {
                case "deadline": throw new IncorrectFormatException(
                        "Please specify a description and date for your deadline!" + Constants.DEADLINE_FORMAT);
                case "event": throw new IncorrectFormatException(
                        "Please specify a description, date and time for your event!" + Constants.EVENT_FORMAT);
                default: throw new IncorrectFormatException(
                        "Please specify a description and date/time for your task!");
            }
        }
    }

    private static DoneCommand parseDone(String input, TaskList taskList) {
        try {
            String[] parsedTask = input.split("\\s", 2);
            String indexOfTask = parsedTask[1].trim();
            int index = Integer.parseInt(indexOfTask);
            if (index > 0 && index > taskList.size()) {
                throw new NonExistentTaskException();
            } else {
                return new DoneCommand(index);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            throw new InvalidNumberInputException();
        }
    }

    private static DeleteCommand parseDelete(String input, TaskList taskList) {
        try {
            String[] parsedTask = input.split("\\s", 2);
            String indexOfTask = parsedTask[1].trim();
            int index = Integer.parseInt(indexOfTask);
            if (index > 0 && index > taskList.size()) {
                throw new NonExistentTaskException();
            } else {
                return new DeleteCommand(index);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            throw new InvalidNumberInputException();
        }
    }

    private static TasksOnCommand parseTasksOn(String input, TaskList taskList) {
        try {
            String[] parsedString = input.split("\\s", 2);
            LocalDate date = DateTimeParser.deadlineDateParse(parsedString[1].trim());
            return new TasksOnCommand(date);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new EmptyDescriptionException("Please enter a date to view all the tasks due");
        }
    }


    private static boolean hasEmptyDesc(String[] taskArray) {
        return taskArray.length == 1 || taskArray[1].isBlank() || taskArray[1].isEmpty();
    }

    private static boolean hasDateButEmptyDesc(String[] taskArray) {
        return (taskArray[0].isBlank() || taskArray[0].isEmpty()) && (!taskArray[1].isBlank());
    }

}
