package duke;

import java.util.ArrayList;

/**
 * Static class that parses input lines into Commands.
 */
public class Parser {
    /**
     * Parses a line of input to a Command object.
     *
     * @param fullCommand string containing exactly one line of input.
     * @return Command object represented by fullCommmand.
     * @throws DukeException If fullCommand has wrong number of symbols.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commandSplit = fullCommand.split(" ", 2);
        String commandType = "";
        if (commandSplit.length >= 1) {
            commandType = commandSplit[0];
        }
        Command command;
        switch (commandType) {
        case "bye":
            command = new Command((taskList, ui, storage, history) -> {
                ui.showBye();
            }, true);
            break;
        case "list":
            command = new Command((taskList, ui, storage, history) -> {
                ArrayList<Task> tasks = taskList.getTasks();
                ui.showLine("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    ui.showLine(String.format("%d. %s", i + 1, tasks.get(i)));
                }
            });
            break;
        case "done":
            command = new Command((taskList, ui, storage, history) -> {
                int i = Integer.parseInt(commandSplit[1].trim());
                Task task = taskList.markTaskAsDone(i - 1);
                ui.showLine("Nice! I've marked this task as done:");
                ui.showTask(task);
                history.commit(taskList);
                storage.save(taskList.getTasks());
            });
            break;
        case "todo":
            if (commandSplit.length < 2) {
                throw new DukeException(String.format("The description of a %s cannot be empty.", commandType));
            }
            command = new Command((taskList, ui, storage, history) -> {
                String taskName = commandSplit[1].trim();
                Task newTask = new Todo(taskName);
                taskList.addTask((newTask));
                history.commit(taskList);
                storage.save(taskList.getTasks());
                ui.showLine("Got it. I've added this task: ");
                ui.showTask(newTask);
                ui.showTaskCount(taskList.getSize());
            });
            break;
        case "deadline": {
            if (commandSplit.length < 2) {
                throw new DukeException(String.format("The description of a %s cannot be empty.", commandType));
            }
            String[] nameAndTime = commandSplit[1].split("/by", 2);
            if (nameAndTime.length < 2) {
                throw new DukeException(String.format("The dateTime of a %s cannot be empty.", commandType));
            }
            command = new Command((taskList, ui, storage, history) -> {
                String taskName = nameAndTime[0].trim();
                String dateTime = nameAndTime[1].trim();
                Task newTask = new Deadline(taskName, dateTime);
                taskList.addTask((newTask));
                history.commit(taskList);
                storage.save(taskList.getTasks());
                ui.showLine("Got it. I've added this task: ");
                ui.showTask(newTask);
                ui.showTaskCount(taskList.getSize());
            });
            break;
        }
        case "event": {
            if (commandSplit.length < 2) {
                throw new DukeException(String.format("The description of a %s cannot be empty.", commandType));
            }
            String[] nameAndTime = commandSplit[1].split("/at", 2);
            if (nameAndTime.length < 2) {
                throw new DukeException(String.format("The dateTime of a %s cannot be empty.", commandType));
            }
            command = new Command((taskList, ui, storage, history) -> {
                String taskName = nameAndTime[0].trim();
                String dateTime = nameAndTime[1].trim();
                Task newTask = new Event(taskName, dateTime);
                taskList.addTask((newTask));
                history.commit(taskList);
                storage.save(taskList.getTasks());
                ui.showLine("Got it. I've added this task: ");
                ui.showTask(newTask);
                ui.showTaskCount(taskList.getSize());
            });
            break;
        }
        case "delete":
            command = new Command((taskList, ui, storage, history) -> {
                int i = Integer.parseInt(commandSplit[1].trim());
                Task task = taskList.removeTask(i - 1);
                history.commit(taskList);
                storage.save(taskList.getTasks());
                ui.showLine("Noted. I've removed this task:");
                ui.showTask(task);
                ui.showTaskCount(taskList.getSize());
            });
            break;
        case "find":
            if (commandSplit.length < 2) {
                throw new DukeException("The key of find cannot be empty.");
            }
            command = new Command((taskList, ui, storage, history) -> {
                String key = commandSplit[1].trim();
                ArrayList<Task> tasks = taskList.findTasks(key);
                ui.showLine("Here are the matching tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    ui.showLine(String.format("%d. %s", i + 1, tasks.get(i)));
                }
            });
            break;
        case "undo":
            command = new Command((taskList, ui, storage, history) -> {
                taskList.moveTasks(history.undo());
                ArrayList<Task> tasks = taskList.getTasks();
                ui.showLine("I've undo'ed one command. Here's your list of tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    ui.showLine(String.format("%d. %s", i + 1, tasks.get(i)));
                }
                storage.save(taskList.getTasks());
            });
            break;
        case "redo":
            command = new Command((taskList, ui, storage, history) -> {
                taskList.moveTasks(history.redo());
                ArrayList<Task> tasks = taskList.getTasks();
                ui.showLine("I've redo'ed one command. Here's your list of tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    ui.showLine(String.format("%d. %s", i + 1, tasks.get(i)));
                }
                storage.save(taskList.getTasks());
            });
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
