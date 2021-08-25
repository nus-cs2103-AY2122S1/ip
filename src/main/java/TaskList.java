import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        Ui.addTaskMessage(this, task);
    }

    public void removeTask(int index) throws DukeException {
        if (index == 0) {
            throw new IndexMismatchException();
        }
        if (index > this.size()) {
            throw new IndexOutOfBoundException();
        }
        Ui.removeTaskMessage(this, index);
        this.taskList.remove(index - 1);
    }

    public void completeTask(int item) throws DukeException {
        if (item == 0) {
            throw new IndexMismatchException();
        }
        if (item > this.taskList.size()) {
            throw new IndexOutOfBoundException();
        }
        if (this.taskList.get(item - 1).isDone) {
            throw new DukeException("☹ OOPS!!! The task is already done!");
        }
        Ui.taskDoneMessage(this, item);
        this.taskList.get(item - 1).setDone(true);
    }

    private void buildList(StringBuilder builder, ArrayList<Task> taskList, String emptyMessage) {
        if (taskList.isEmpty()) {
            builder.append(emptyMessage);
        } else {
            int listSize = taskList.size();
            for (int i = 0; i < listSize; i++) {
                builder.append("\t\t").append(i + 1).append(". ").append(taskList.get(i));
                if (i < listSize - 1) {
                    builder.append("\n");
                }
            }
        }
    }

    public String printList() {
        StringBuilder itemList = new StringBuilder("\tHere are the tasks in your list:\n");
        buildList(itemList, this.taskList, "\tNothing here yet...");

        return itemList.toString();
    }

    public String printList(LocalDate date) {

        ArrayList<Task> targetTasks = this.taskList.stream()
                .filter(x -> x.getDate() != null && x.getDate().equals(date))
                .collect(Collectors.toCollection(ArrayList::new));

        StringBuilder itemList = new StringBuilder("\tHere is the result:\n");
        buildList(itemList, targetTasks, "\tNothing special will happen on this day");

        return itemList.toString();
    }
}
