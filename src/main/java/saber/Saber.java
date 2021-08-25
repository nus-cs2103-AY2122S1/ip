package saber;

import saber.commands.SaberCommand;
import saber.exceptions.SaberCommandNotFoundException;
import saber.exceptions.SaberStorageLoadException;
import saber.exceptions.SaberStorageStoreException;
import saber.task.Deadline;
import saber.task.Event;
import saber.task.Todo;
import saber.ui.SaberUI;

import java.util.ArrayList;

public class Saber {
    private final TaskStorage storage;
    private final SaberUI saberGeneralUI;
    private TaskList taskList;

    enum InputCommand {
        add,
        bye,
        done,
        deadline,
        delete,
        event,
        todo,
        list,
        find,
    }

    enum TaskType {
        deadline,
        event,
        normalTask,
        todo,
    }

    public Saber(String filePath) {
        saberGeneralUI = new SaberUI();
        storage = new TaskStorage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (SaberStorageLoadException e) {
            saberGeneralUI.showStorageLoadingError();
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    public void storeExistingTaskList() {
        TaskType[] taskTypeArray = new TaskType[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) instanceof Deadline) {
                taskTypeArray[i] = TaskType.deadline;
            } else if (taskList.get(i) instanceof Event) {
                taskTypeArray[i] = TaskType.event;
            } else if (taskList.get(i) instanceof Todo) {
                taskTypeArray[i] = TaskType.todo;
            } else {
                taskTypeArray[i] = TaskType.normalTask;
            }
        }
        try {
            storage.store(taskList, taskTypeArray);
        } catch (SaberStorageStoreException e) {
            saberGeneralUI.showStorageStoringError();
        }
    }

    public void run() {
        boolean isEnd = false;
        saberGeneralUI.showLogo();
        saberGeneralUI.showGreeting();
        while (!isEnd) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> storeExistingTaskList()));
            try {
                String input = saberGeneralUI.readInput();
                SaberParser saberParser = new SaberParser(input);
                saberGeneralUI.showLineBreak();
                SaberCommand command = saberParser.parse();
                command.execute(taskList);
                isEnd = command.isExit();
            } catch (SaberCommandNotFoundException e) {
                saberGeneralUI.showCommandNotFoundError();
            } catch (NullPointerException e) {
                saberGeneralUI.showGenericError();
            } finally {
                saberGeneralUI.showLineBreak();
            }
        }
        storeExistingTaskList();
    }

    public static void main(String[] args) {
        new Saber(System.getProperty("user.dir")).run();
    }
}
