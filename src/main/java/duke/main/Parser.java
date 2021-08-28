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
     * Parses user inputs.
     *
     * @param input    String from user.
     * @return true if input is not "bye", else false.
     */
    public boolean parse(String input) {
        String[] commandAndDesc = input.split(" ", 2);
        String command = commandAndDesc[0];
        String description = commandAndDesc.length == 2 ? commandAndDesc[1] : "";

        switch (command) {
            case "bye":
                return false;
            case "find":
                List<Task> matches = taskList.find(description);
                ui.showMatchingTasks(matches);
                break;
            case "clear":
                storage.resetTasks();
                taskList.clearTasks();
                break;
            case "list":
                ui.displayTaskList(taskList);
                break;
            case "deadline":
                taskList.addTask(new Deadline(description));
                break;
            case "event":
                taskList.addTask(new Event(description));
                break;
            case "todo":
                taskList.addTask(new ToDo(description));
                break;
            case "done":
                Task selectedTask = selectTask(description);
                selectedTask.markAsDone();
                break;
            case "delete":
                Task toDelete = selectTask(description);
                taskList.deleteTask(toDelete);
                break;
            default:
                if (command.equals("")) {
                    ui.showEmptyInputException();
                } else {
                    ui.showUnknownCommandException(command);
                }
                break;
        }
        storage.write(taskList);
        return true;
    }

    private Task selectTask(String taskNumString) {
        try {
            int taskNum = Integer.parseInt(taskNumString);
            Task inFocus = taskList.getTask(taskNum);
            if (inFocus == null) {
                throw new DukeException("\tSorry, I can't seem to find that task\n");
            }
            return inFocus;
        } catch (NumberFormatException e) {
            throw new DukeException("\tI'm Sorry, WHAT?!?!\n");
        }
    }
}
