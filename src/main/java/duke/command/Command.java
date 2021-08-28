package duke.command;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Command(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public void addNewTodo(String task) throws Exception {
        Task todoTask = Parser.isValidTodoTask(task);
        taskList.add(todoTask);
        ui.showAddNewTask();
        storage.saveNewTask(todoTask);
    }

    public void addNewDeadline(String task) throws Exception {
        Task deadlineTask = Parser.isValidDeadlineTask(task);
        taskList.add(deadlineTask);
        ui.showAddNewTask();
        storage.saveNewTask(deadlineTask);
    }

    public void addNewEvent(String task) throws Exception {
        Task eventTask = Parser.isValidEventTask(task);
        taskList.add(eventTask);
        ui.showAddNewTask();
        storage.saveNewTask(eventTask);
    }

    public void setTaskDone(String task) throws Exception {
        int itemDone = Parser.findFinishedItem(task);
        taskList.get(itemDone - 1).finished();
        ui.showMarkTaskDone(itemDone);
        storage.modifyTasks();
    }

    public void deleteTask(String task) throws Exception {
        int itemDeleted = Parser.findDeleteItem(task);
        Task deletedTask = taskList.remove(itemDeleted - 1);
        ui.showDeleteMessage(deletedTask);
        storage.modifyTasks();
    }

    public void findTask(String task) {
        String target = task.substring(5);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task cur = taskList.get(i);
            if (cur.getTaskName().contains(target)) {
                result.add(i);
            }
        }
        ui.showFindingTasks(result);
    }

    public void loadSavedTasks() {
        storage.loadSavedTasks();
    }

    public void showLogo() {
        ui.showLogo();
    }

    public void showList() {
        ui.showList();
    }

    public void showGoodBye() {
        ui.showGoodBye();
    }
}
