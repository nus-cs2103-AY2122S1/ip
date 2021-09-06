import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(Ui ui) {
        this.ui = ui;
        this.tasks = new ArrayList<>();
    }

    public TaskList(Ui ui, ArrayList<Task> tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        ui.addTask(task, this);
    }

    public void completeTask(String input) throws DukeException {
        int n = parseTaskNumber(input);
        Task task = tasks.get(n - 1);
        task.setDone();
        ui.completeTask(task);
    }

    public void deleteTask(String input) throws DukeException {
        int n = parseTaskNumber(input);
        Task task = tasks.get(n - 1);
        tasks.remove(n - 1);
        ui.deleteTask(task, this);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void listTasks() {
        ui.listTasks(this);
    }

    public int size() {
        return tasks.size();
    }

    private int parseTaskNumber(String input) throws DukeException {
        try {
            if (input.length() < 1) {
                throw new DukeException(DukeException.UNSPECIFIED_TASK);
            } else {
                int i = Integer.parseInt(input.substring(1));
                if (i > tasks.size()) {
                    throw new DukeException(DukeException.NOT_ENOUGH_TASKS);
                } else if (i <= 0) {
                    throw new DukeException(DukeException.INVALID_TASK_NUMBER);
                } else {
                    return i;
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.DEFAULT);
        }
    }
}
