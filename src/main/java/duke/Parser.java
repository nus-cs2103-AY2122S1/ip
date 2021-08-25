package duke;

import duke.exception.DukeException;
import duke.exception.IndexMismatchException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Task arrayCommandToTask(String[] data) throws DukeException {
        if (data.length < 3
                || !(data[1].equals("0") || data[1].equals("1"))
                || data[2].trim().isEmpty()) {
            throw new InvalidCommandException();
        }
        String proposedType = data[0];
        boolean isDone = data[1].equals("1");
        String description = data[2];

        switch (proposedType) {
            case "T":
                if (data.length == 3) {
                    return new ToDo(description, isDone);
                } else {
                    throw new InvalidCommandException();
                }
            case "D":
                if (data.length != 4) {
                    throw new InvalidCommandException();
                }
                try {
                    return new Deadline(description, isDone, LocalDate.parse(data[3]));
                } catch (DateTimeParseException e) {
                    throw new DukeException("\tPlease use the YYYY-MM-DD format for the time!");
                }
            case "E":
                if (data.length != 4) {
                    throw new InvalidCommandException();
                }
                try {
                    return new Event(description, isDone, LocalDate.parse(data[3]));
                } catch (DateTimeParseException e) {
                    throw new DukeException("\tPlease use the YYYY-MM-DD format for the time!");
                }
            default:
                throw new InvalidCommandException();
        }
    }

    public static Task descriptionToTask(Task.TaskType type, String description) throws DukeException {
        Task task;
        switch (type) {
            case TODO: {
                if (!description.trim().isEmpty()) {
                    task = new ToDo(description);
                } else {
                    throw new InvalidCommandException();
                }
                break;
            }
            case DEADLINE: {
                if (description.contains("/by")) {
                    String[] information = description.split("/by ", 2);
                    if (information[0].isEmpty()) {
                        throw new InvalidCommandException();
                    }
                    try {
                        task = new Deadline(information[0], LocalDate.parse(information[1]));
                    } catch (DateTimeParseException e) {
                        throw new DukeException("\tPlease use the YYYY-MM-DD format for the time!");
                    }
                } else {
                    throw new InvalidCommandException();
                }
                break;
            }
            case EVENT: {
                if (description.contains("/at")) {
                    String[] information = description.split("/at ", 2);
                    if (information[0].isEmpty()) {
                        throw new InvalidCommandException();
                    }
                    try {
                        task = new Deadline(information[0], LocalDate.parse(information[1]));
                    } catch (DateTimeParseException e) {
                        throw new DukeException("\tPlease use the YYYY-MM-DD format for the time!");
                    }
                } else {
                    throw new InvalidCommandException();
                }
                break;
            }
            default:
                throw new InvalidCommandException();
        }
        return task;
    }

    public static void parseCommand(String command, TaskList taskList, Storage storage) throws DukeException {
        command = command.trim();
        if (command.contains(" ")) {
            String commandType = command.split(" ", 2)[0];
            String description = command.split(" ", 2)[1].trim();

            switch (commandType) {
                case "todo": {
                    Task task = descriptionToTask(Task.TaskType.TODO, description);
                    taskList.addTask(task);
                    storage.addTask(task);
                    return;
                }
                case "deadline": {
                    Task task = descriptionToTask(Task.TaskType.DEADLINE, description);
                    taskList.addTask(task);
                    storage.addTask(task);
                    return;
                }
                case "event": {
                    Task task = descriptionToTask(Task.TaskType.EVENT, description);
                    taskList.addTask(task);
                    storage.addTask(task);
                    return;
                }
                case "done": {
                    if (description.matches("\\d+")) {
                        int item = Integer.parseInt(description);
                        taskList.completeTask(item);
                        storage.refreshTask(taskList);
                    } else {
                        throw new IndexMismatchException();
                    }
                    return;
                }
                case "delete": {
                    if (description.matches("\\d+")) {
                        int item = Integer.parseInt(description);
                        taskList.removeTask(item);
                        storage.refreshTask(taskList);
                    } else {
                        throw new IndexMismatchException();
                    }
                    return;
                }
                case "query": {
                    try {
                        Ui.printMessage(taskList.printList(LocalDate.parse(description)));
                    } catch (DateTimeParseException e) {
                        throw new InvalidCommandException();
                    }
                    return;
                }
                default: {
                    throw new InvalidCommandException();
                }
            }
        }

        switch (command) {
            case "list":
                Ui.printMessage(taskList.printList());
                break;
            case "bye":
                break;
            default:
                throw new InvalidCommandException();
        }
    }
}
