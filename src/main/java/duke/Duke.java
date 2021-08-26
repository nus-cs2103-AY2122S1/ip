/**
 * @author Hang Zelin
 * The main Programme to execute the Duke Project
 * <p>
 * Duke will allow users to add three types of tasks: "todo" "deadline" "event".
 * <p>
 * Duke also allow users to list all tasks, mark a task to be done if it is finished, and
 * delete the task if the task is finished.
 * <p>
 * You can also search a specific task by its date, keyword.
 */
package duke;

import duke.excpetions.DukeException;
import duke.saveloadmanager.Storage;
import duke.task.TaskList;
import duke.uimanager.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * @param filePath
     * @author Hang Zelin
     * @description initialize Ui, storage and load tasklists from specific filePath for Duke
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * @param taskType
     * @param task
     * @param time
     * @param index
     * @return void
     * @auther Hang Zelin
     * @description Choose a specific task to execute via tasks type and add to the tasklists.
     * Every time an execution is done, the task will be stored to the local file called tasks.txt
     * via Storage.
     */
    public void OperationForDuke(String taskType, String task, String time, int index) {
        switch (taskType) {
        case "bye": {
            ui.GoodbyeMessage();
            break;
        }
        case "list": {
            ui.PrintList(tasks);
            break;
        }
        case "done": {
            try {
                tasks.detectIndex(index);
                tasks.MarkDone(index);
                ui.MarkDone(tasks.get(index).getTaskInfo());
            } catch (DukeException e) {
                e.PrintErrorMessage();
            }
            break;
        }
        case "delete": {
            try {
                tasks.detectIndex(index);
                ui.Delete(tasks.get(index).getTaskInfo(), tasks.size() - 1);
                tasks.Delete(index);
            } catch (DukeException e) {
                e.PrintErrorMessage();
            }
            break;
        }
        case "tell": {
            ui.getSpecificDateEvent();
            tasks.getSpecificDateEvent(time);
            break;
        }
        case "find": {
            ui.FindTask();
            tasks.FindTask(task);
            break;
        }
        default: {
            try {
                tasks.add(taskType, task, time);
                ui.add(tasks.get(tasks.size() - 1).getTaskInfo(), tasks.size());
            } catch (DukeException e) {
                e.PrintErrorMessage();
            }
            break;
        }
        }
    }

    /**
     * @param
     * @return void
     * @author Hang Zelin
     * @Description Update a save data every time a round of execution is done.
     */
    public void UpdateSaveData() {
        try {
            storage.SaveListDataToFile(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

    /**
     * @param
     * @return void
     * @author Hang Zelin
     * @description Run the programme of Duke. It will firstly say Hello to users. Then it will repeatedly accept input from
     * users and filter out key commands, then call OperationForDuke to execute a task by commands. The process will not stop
     * until users enter "goodbye".
     * <p>
     * Noted: Every time an execution is done, the savedata will be updated.
     */
    public void run() {
        //Say Hello to the User
        ArrayList<String> Messages;
        String taskType = "";
        String task = "";
        String time = "";
        int index = 0;
        ui.HelloMessage();

        while (true) {
            try {
                Messages = ui.ARoundOfInput();
            } catch (DukeException e) {
                ui.PrintAline();
                e.PrintErrorMessage();
                ui.PrintAline();
                continue;
            }

            if (Messages.size() < 4) {
                continue;
            }

            taskType = Messages.get(0);
            task = Messages.get(1);
            time = Messages.get(2);
            index = Integer.parseInt(Messages.get(3));

            ui.PrintAline();
            OperationForDuke(taskType, task, time, index);
            ui.PrintAline();

            UpdateSaveData(); //Update the SaveData every time a round of operation is done.
            if (taskType.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
