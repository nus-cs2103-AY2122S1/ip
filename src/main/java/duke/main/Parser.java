package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.util.List;

public class Parser {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Parser(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Checks if user has entered the terminating command.
     *
     * @param command String user input.
     * @return true if input is terminating command else false.
     */
    public static boolean isTerminateCommand(String command) {
        return command == "bye";
    }

    /**
     * Parses user inputs.
     *
     * @param input String from user.
     * @return String response from the parser.
     */
    public String parse(String input) {
        String[] commandAndDesc = input.split(" ", 2);
        String command = commandAndDesc[0];
        String description = commandAndDesc.length == 2 ? commandAndDesc[1] : "";
        String response;
        switch (command) {
            case "find":
                List<Task> matches = taskList.find(description);
                response = ui.getMatchingTasksSummary(matches);
                break;
            case "clear":
                String resetTaskMessage = storage.resetTasks();
                String clearTaskMessage = taskList.clearTasks();
                response = resetTaskMessage + clearTaskMessage;
                break;
            case "list":
                response = ui.getTaskListSummary(taskList);
                break;
            case "deadline":
                response = taskList.addTask(new Deadline(description));
                break;
            case "event":
                response = taskList.addTask(new Event(description));
                break;
            case "todo":
                response = taskList.addTask(new ToDo(description));
                break;
            case "done":
                Task selectedTask = selectTask(description);
                response = selectedTask.markAsDone();
                break;
            case "delete":
                Task toDelete = selectTask(description);
                response = taskList.deleteTask(toDelete);
                break;
            default:
                if (command.equals("")) {
                    response = ui.getEmptyInputMessage();
                } else {
                    response = ui.getUnknownCommandMessage(command);
                }
        }
        storage.write(taskList);
        return response;
    }

    private Task selectTask(String taskNumString) {
        try {
            int taskNum = Integer.parseInt(taskNumString);
            Task taskInFocus = taskList.getTask(taskNum);
            if (taskInFocus == null) {
                throw new DukeException("\tSorry, I can't seem to find that task\n");
            }
            return taskInFocus;
        } catch (NumberFormatException e) {
            throw new DukeException("\tI'm Sorry, WHAT?!?!\n");
        }
    }
}
