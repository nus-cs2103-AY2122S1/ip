package hyddd.main;

import java.io.IOException;

import hyddd.exceptions.HydddException;
import hyddd.executions.Execution;
import hyddd.logics.Parser;
import hyddd.saveloadmanager.Storage;
import hyddd.task.TaskList;
import hyddd.uimanager.TextUi;

/**
 * @@author Hang Zelin
 * Main Programme to execute the hyddd Project
 * hyddd will allow users to add three types of tasks: "todo" "deadline" "event".
 * hyddd also allow users to list all tasks, mark a task to be done if it is finished, and
 * delete the task if the task is finished.
 * You can also search a specific task by its date, keyword.
 */
public class Hyddd {
    private static final String FILEPATH = "tasks.txt";
    private final Storage storage;
    private final TextUi textUi;
    private TaskList tasks;

    /**
     * Initializes Ui, storage and load TaskLists from specific filePath for hyddd.
     */
    public Hyddd() {
        textUi = new TextUi();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (HydddException e) {
            tasks = new TaskList();
            e.getErrorMessage();
        }
    }

    /**
     * Updates a save data every time a round of execution is done.
     */
    public String updateSaveData() {
        String text = "";
        try {
            storage.saveListDataToFile(tasks);
        } catch (IOException e) {
            text = textUi.showSavingError();
        }
        return text;
    }

    /**
     * Runs the programme of hyddd. It will firstly say Hello to users. Then it will repeatedly accept input from
     * users and filter out key commands, then call OperationForhyddd to execute a task by commands.
     * The process will not stop until users enter "goodbye".
     * Noted: Every time an execution is done, the saveData will be updated.
     *
     * @param input Input user take in.
     * @return Response hyddd gives.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);
        Execution execution = new Execution(tasks, textUi, parser);
        String hydddResponse;

        hydddResponse = execution.executionResponse();
        hydddResponse += updateSaveData(); //Update the SaveData every time a round of operation is done.

        return hydddResponse;
    }
}
