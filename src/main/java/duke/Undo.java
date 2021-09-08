package duke;

import java.io.IOException;
import java.util.List;

public class Undo implements GeneralCommand {
    private List<Ui> uiList;
    private Ui ui;
    private List<Storage> storageList;
    private Storage storage;
    private List<TaskList> tasksList;
    private TaskList tasks;
    private boolean shouldUndo = true;
    private Duke duke;

    public Undo(List<Storage> storageList, List<Ui> uiList, List<TaskList> tasksList, Duke duke) throws UndoException {
        this.duke = duke;
        if (storageList.isEmpty() || tasksList.isEmpty()) {
            shouldUndo = false;
        } else {
            this.uiList = uiList;
            this.storageList = storageList;
            this.tasksList = tasksList;
            try {
                this.ui = uiList.get(uiList.size() - 1);
                this.storage = storageList.get(storageList.size() - 1);
                this.tasks = tasksList.get(tasksList.size() - 1);
            } catch (Exception e) {
                throw new UndoException("Something went wrong");
            }
        }
    }

    public String execute() throws IOException, UndoException {
        if (!shouldUndo) {
            throw new UndoException();
        }
        storage.save(tasks);
        uiList.remove(ui);
        tasksList.remove(tasks);
        storageList.remove(storage);
        duke.setElements(storage, tasks, ui, storageList, uiList,  tasksList);
        return ui.undoMessageToString(String.format("You have %s undo commands left available", tasksList.size()));
    }
}
