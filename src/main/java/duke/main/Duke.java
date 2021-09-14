package duke.main;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.executions.Execution;
import duke.logics.Parser;
import duke.saveloadmanager.Storage;
import duke.task.TaskList;
import duke.uimanager.TextUi;

/**
 * @@author Hang Zelin
 * Main Programme to execute the Duke Project
 * Duke will allow users to add three types of tasks: "todo" "deadline" "event".
 * Duke also allow users to list all tasks, mark a task to be done if it is finished, and
 * delete the task if the task is finished.
 * You can also search a specific task by its date, keyword.
 */
public class Duke {
    private static final String FILEPATH = "tasks.txt";
    private final Storage storage;
    private final TextUi textUi;
    private TaskList tasks;

    /**
     * Initializes Ui, storage and load TaskLists from specific filePath for Duke.
     */
    public Duke() {
        textUi = new TextUi();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
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
     * Runs the programme of Duke. It will firstly say Hello to users. Then it will repeatedly accept input from
     * users and filter out key commands, then call OperationForDuke to execute a task by commands.
     * The process will not stop until users enter "goodbye".
     * Noted: Every time an execution is done, the saveData will be updated.
     *
     * @param input Input user take in.
     * @return Response Duke gives.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);
        Execution execution = new Execution(tasks, textUi, parser);
        String dukeResponse;

        dukeResponse = execution.executionResponse();
        dukeResponse += updateSaveData(); //Update the SaveData every time a round of operation is done.

        return dukeResponse;
    }
}
