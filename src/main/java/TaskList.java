import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> TaskList;

    public TaskList(ArrayList<Task> list) {
        this.TaskList = list;
    }

    public ArrayList<Task> getList() {
        return TaskList;
    }

    public void addTask(taskType type, String[] details) {
        switch (type) {
        case TODO:
            TaskList.add(new ToDo(details[0]));
            break;
        case DEADLINE:
            TaskList.add(new Deadline(details[0], details[1]));
            break;
        case EVENT:
            TaskList.add(new Event(details[0], details[1]));
            break;
        }

    }

    public Task deleteTask(int taskNo) throws DukeException {
        int index = taskNo - 1;
        try {
            Task toDelete = TaskList.get(taskNo - 1);
            TaskList.remove(index);
            return toDelete;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I cannot find that task no. please enter a valid number :)");
        }

    }

    public Task doneTask(int taskNo) throws DukeException {
        int index = taskNo - 1;
        try {
            TaskList.get(index).markDone();
            return TaskList.get(taskNo - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I cannot find that task no. please enter a valid number :)");
        }
    }

    public Task getTask(int taskNo) {
        Task toPrint = TaskList.get(taskNo - 1);
        return toPrint;
    }

    public int count() {
        return TaskList.size();
    }

    public String toDisplay() {
        String str = "";
        int i = 1;
        for (Task item:TaskList) {
            str = str + String.format("%s. %s\n", i, item);
            i += 1;
        }
        return str;
    }

}
