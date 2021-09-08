package duke.main;

import duke.exceptions.DukeException;
import duke.saveloadmanager.Storage;
import duke.task.TaskList;
import duke.uimanager.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @@author Hang Zelin
 * Main Programme to execute the Duke Project
 * Duke will allow users to add three types of tasks: "todo" "deadline" "event".
 * Duke also allow users to list all tasks, mark a task to be done if it is finished, and
 * delete the task if the task is finished.
 * You can also search a specific task by its date, keyword.
 */
public class Duke {

    private final static String FILEPATH = "data/tasks.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * initialize Ui, storage and load TaskLists from specific filePath for Duke
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private String goodbye() {
        return ui.goodbyeMessage();
    }

    private String printList() {
        return tasks.printListUi();
    }

    private String markDone(int index) {
        String text;
        try {
            tasks.detectIndex(index);
            text = ui.markDoneUi(tasks.get(index).getTaskStatus());
            tasks.markDone(index);
        } catch (DukeException e) {
            text = e.getMessage();
        }
        return text;
    }

    private String delete(int index) {
        String text;
        try {
            tasks.detectIndex(index);
            text = ui.deleteUi(tasks.get(index).getTaskStatus(), tasks.size() - 1);
            tasks.delete(index);
        } catch (DukeException e) {
            text = e.getMessage();
        }
        return text;
    }

    private String tell(String time) {
        return ui.getSpecificDateEventUi() + tasks.getSpecificDateEvent(time);
    }

    private String find(String task) {
        return ui.findTasksUi() + tasks.findTasks(task);
    }

    private String add(String ... commands) {
        String text;
        try {
            tasks.add(commands[0], commands[1], commands[2]);
            text = ui.addUi(tasks.get(tasks.size() - 1).getTaskStatus(), tasks.size());
        } catch (DukeException e) {
            text = e.getMessage();
        }
        return text;
    }

    /**
     * Chooses a specific task to execute via tasks type and add to the tasklists.
     * Every time an execution is done, the task will be stored to the local file called tasks.txt
     * via Storage.
     *
     * @param index Index of the task users input.
     * @param commands JavaVarargs Commands users input.
     */
    public String operationForDuke(int index, String... commands) {
        String text;
        String operationType = commands[0], task = commands[1], time = commands[2];

        text = switch (operationType) {
               case "bye" -> goodbye();
               case "list" -> printList();
               case "done" -> markDone(index);
               case "delete" -> delete(index);
               case "tell" -> tell(time);
               case "find" -> find(task);
               default -> add(operationType, task, time);
        };
        assert text.equals("") : "OOPS, Duke stops responding!";
        return text;
    }

    /**
     * Updates a save data every time a round of execution is done.
     */
    public void updateSaveData() {
        try {
            storage.saveListDataToFile(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

    private String dealWithInput(String input) {
        ArrayList<String> messages;
        String operationType, task, time;
        String dukeResponse;
        int index;

        try {
            messages = ui.returnSplitComponent(input);
        } catch (DukeException e) {
            return e.getMessage();
        }

        assert messages.size() == 4 : "Error in Parser, should produce 4 key value for duke to execute!!";

        operationType = messages.get(0);
        task = messages.get(1);
        time = messages.get(2);
        index = Integer.parseInt(messages.get(3));
        dukeResponse = operationForDuke(index, operationType, task, time);
        return dukeResponse;
    }

    /**
     * Runs the programme of Duke. It will firstly say Hello to users. Then it will repeatedly accept input from
     * users and filter out key commands, then call OperationForDuke to execute a task by commands.
     * The process will not stop until users enter "goodbye".
     * Noted: Every time an execution is done, the savedata will be updated.
     *
     * @param input Input user take in.
     * @return Response Duke gives.
     */
    public String getResponse(String input) {
        String dukeResponse = dealWithInput(input);

        updateSaveData(); //Update the SaveData every time a round of operation is done.

        return dukeResponse;
    }
}
