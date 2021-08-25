import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    TaskList(List<Task> tasksFromStorage) {
        listOfTasks = new ArrayList<>();
        if (!tasksFromStorage.isEmpty()) {
            listOfTasks.addAll(tasksFromStorage);
        }
    }

    TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public int getNumTasks() {
        return listOfTasks.size();
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public void addTask(Task t) {
        listOfTasks.add(t);
    }

    public String deleteTask(int deleteIndex) {
        String deletedTask = listOfTasks.get(deleteIndex - 1).toString();
        if (this.listOfTasks.size() == 1) { // if there is only one task in the list
            this.listOfTasks.clear();
        } else if (deleteIndex == 1){ // deleting leftmost element
            this.listOfTasks = new ArrayList<>(listOfTasks.subList(1, listOfTasks.size()));
        } else if (deleteIndex == listOfTasks.size()) { // deleting rightmost element
            this.listOfTasks = new ArrayList<>(listOfTasks.subList(0, listOfTasks.size() - 1));
        } else {
            ArrayList<Task> newList = new ArrayList<>(this.listOfTasks.subList(0, deleteIndex - 1));
            for (int i = deleteIndex; i < listOfTasks.size(); i++) {
                newList.add(listOfTasks.get(i));
            }
            this.listOfTasks = newList;
        }
        return deletedTask;
    }

    public String completeTask(int taskIndex) {
        String taskInfo;
        if (this.listOfTasks.get(taskIndex - 1) instanceof Todo) {
            Todo completedTodo = ((Todo) this.listOfTasks.get(taskIndex - 1)).markAsDone();
            this.listOfTasks.set(taskIndex - 1, completedTodo);
            taskInfo = completedTodo.toString();
        } else if (this.listOfTasks.get(taskIndex - 1) instanceof Deadline) {
            Deadline completedDeadline = ((Deadline) this.listOfTasks.get(taskIndex - 1)).markAsDone();
            this.listOfTasks.set(taskIndex - 1, completedDeadline);
            taskInfo = completedDeadline.toString();
        } else {
            Event completedEvent = ((Event) this.listOfTasks.get(taskIndex - 1)).markAsDone();
            this.listOfTasks.set(taskIndex - 1, completedEvent);
            taskInfo = completedEvent.toString();
        }
        return taskInfo;
    }

    public boolean isEmptyTaskList() {
        return listOfTasks.isEmpty();
    }

}
