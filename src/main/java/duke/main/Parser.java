package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class Parser {
    public static boolean parse(TaskList taskList, Storage storage, String input, Ui ui) {
        String[] commandAndDesc = input.split(" ", 2);
        String command = commandAndDesc[0];
        String description = commandAndDesc.length == 2 ? commandAndDesc[1] : "";

        switch (command) {
            case "bye":
                return false;
            case "clear":
                storage.resetTasks();
                taskList.clearTasks();
                break;
            case "list":
                ui.printTaskList(taskList);
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
            case "delete":
                try {
                    int taskNum = Integer.parseInt(description);
                    Task inFocus = taskList.getTask(taskNum);
                    if (inFocus == null) {
                        ui.showInvalidSelectionError();
                    } else {
                        if (command.equals("done")) {
                            inFocus.markAsDone();
                        } else {
                            taskList.deleteTask(inFocus);
                        }
                    }
                } catch (NumberFormatException e) {
                    ui.showNumberFormatException();
                }
                break;
            default:
                if (command.equals("")) {
                    ui.showEmptyInputException();
                } else {
                    ui.showUnknownCommandException();
                }
                break;
        }
        storage.updateStorage(taskList);
        return true;
    }

    private void onDelete() {

    }
}
