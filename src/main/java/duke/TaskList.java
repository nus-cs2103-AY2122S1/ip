package duke;

import task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private final Storage storage;
    private final Ui ui;
    private final ArrayList<Task> list;
    
    public TaskList(Storage storage, Ui ui, ArrayList<Task> list) {
        this.storage = storage;
        this.ui = ui;
        this.list = list;
    }
    
    public boolean isInvalidIndex(int index) {
        return index > list.size() || index < 1;
    }
    
    public void showList() {
        ui.showList(list);
    }
    
    public void setTaskDone(int index) {
        Task toSetDone = list.get(index);
        storage.setDBEntryDone(toSetDone.databaseEntry());
        toSetDone.setDone();
        ui.showDone(toSetDone);
    }
    
    public void deleteTask(int index) {
        Task deleted = list.remove(index);
        storage.deleteDBEntry(deleted.databaseEntry());
        ui.showDelete(deleted, list.size());
    }
    
    public void findTasksByDate(LocalDate desiredDate) {
        ui.showDateFind(list, list.size(), desiredDate);
    }

    public void findTasksByKeyword(String keyword) {
        ui.showKeywordFind(list, list.size(), keyword);
    }
    
    public void addTask(Task newTask) {
        list.add(newTask);
        storage.addDBEntry(newTask.databaseEntry());
        ui.showAdd(newTask, list.size());
    }
}
