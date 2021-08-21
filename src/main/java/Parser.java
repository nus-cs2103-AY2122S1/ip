import java.util.ArrayList;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException, Exception {
        String[] commandSplit = fullCommand.split(" ", 2);
        String commandType = "";
        if (commandSplit.length >= 1) {
            commandType = commandSplit[0];
        }
        Command command;
        switch (commandType) {
        case "bye":
            command = new Command((taskList, ui, storage) -> {
                ui.showBye();
            }, true);
            break;
        case "list":
            command = new Command((taskList, ui, storage) -> {
                ArrayList<Task> tasks = taskList.getTasks();
                ui.showln("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    ui.showln(String.format("%d. %s", i + 1, tasks.get(i)));
                }
            }, false);
            break;
        case "done": {
            command = new Command((taskList, ui, storage) -> {
                int i = Integer.parseInt(commandSplit[1].trim());
                Task task = taskList.markTaskAsDone(i - 1);
                ui.showln("Nice! I've marked this task as done:");
                ui.showTask(task);
                storage.save(taskList.getTasks());
            }, false);
            break;
        }
        case "todo": {
            if (commandSplit.length < 2) {
                throw new DukeException(String.format("The description of a %s cannot be empty.", commandType));
            }
            command = new Command((taskList, ui, storage) -> {
                String taskName = commandSplit[1].trim();
                Task newTask = new Todo(taskName);
                taskList.addTask((newTask));
                storage.save(taskList.getTasks());
                ui.showln("Got it. I've added this task: ");
                ui.showTask(newTask);
                ui.showTaskCount(taskList.getSize());
            }, false);
            break;
        }
        case "deadline": {
            if (commandSplit.length < 2) {
                throw new DukeException(String.format("The description of a %s cannot be empty.", commandType));
            }
            String[] nameAndTime = commandSplit[1].split("/by", 2);
            if (nameAndTime.length < 2) {
                throw new DukeException(String.format("The dateTime of a %s cannot be empty.", commandType));
            }
            command = new Command((taskList, ui, storage) -> {
                String taskName = nameAndTime[0].trim();
                String dateTime = nameAndTime[1].trim();
                Task newTask = new Deadline(taskName, dateTime);
                taskList.addTask((newTask));
                storage.save(taskList.getTasks());
                ui.showln("Got it. I've added this task: ");
                ui.showTask(newTask);
                ui.showTaskCount(taskList.getSize());
            }, false);
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
            command = new Command((taskList, ui, storage) -> {
                String taskName = nameAndTime[0].trim();
                String dateTime = nameAndTime[1].trim();
                Task newTask = new Event(taskName, dateTime);
                taskList.addTask((newTask));
                storage.save(taskList.getTasks());
                ui.showln("Got it. I've added this task: ");
                ui.showTask(newTask);
                ui.showTaskCount(taskList.getSize());
            }, false);
            break;
        }
        case "delete": {
            command = new Command((taskList, ui, storage) -> {
                int i = Integer.parseInt(commandSplit[1].trim());
                Task task = taskList.removeTask(i - 1);
                storage.save(taskList.getTasks());
                ui.showln("Noted. I've removed this task:");
                ui.showTask(task);
                ui.showTaskCount(taskList.getSize());
            }, false);
            break;
        }
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
