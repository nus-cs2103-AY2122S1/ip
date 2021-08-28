import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private final Storage storage;

    public TaskList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }
    public void addTask(Task task) {
        list.add(task);
        Ui.printAddMsg(task.toString(), getTotalTasks());
        this.writeFile(task);
    }

    public void removeTask(int taskNum) throws DukeException {
        if (taskNum < 1) {
            throw new DukeException("negative item");
        } else if (list.size() == 0) {
            throw new DukeException("no task found");
        } else if (taskNum > list.size()) {
            throw new DukeException("no such task");
        }

        Task item = list.remove(taskNum - 1);
        Ui.printRemoveMsg(item.toString(), getTotalTasks());
        this.updateFile();
    }

    public void markTaskAsDone(int taskNum) throws DukeException {
        if (taskNum < 1) {
            throw new DukeException("negative item");
        } else if (list.size() == 0) {
            throw new DukeException("no task found");
        } else if (taskNum > list.size()) {
            throw new DukeException("no such task");
        }

        Task item = list.get(taskNum - 1);
        item.markAsDone();
        this.updateFile();
        Ui.printTaskDone(item.toString());
    }

    private int getUndoneTasks() {
        int count = 0;
        for (Task task : list) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    private int getTotalTasks() {
        return list.size();
    }





    public void listTasks() throws DukeException {
        String msg = "    Here are the tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < list.size(); i++) {
            msg += String.format("    %d.%s", (i + 1), list.get(i).toString()) + System.lineSeparator();
        }
        Ui.prettify(msg);
    }

    public void updateFile() throws DukeException {
        storage.updateFile(list);
    }

    public void writeFile(Task t) throws DukeException {
        storage.writeFile(t);
    }
}
