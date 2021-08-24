package duke.storage;

import java.util.ArrayList;

import duke.commands.Ui;
import duke.tasks.Task;

public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Primary Constructor.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Retrieves the list of tasks that is being stored in this class.
     * 
     * @return ArrayList<Task> of tasks which is being stored.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Loads previous saved Tasks from a txt file. Save it in this current TaskList
     * in an ArrayList<Task> object.
     * 
     * @param savedTasks this is the ArrayList<Task> of Task coming from the txt
     *                   file.
     */
    public void loadFromStorage(ArrayList<Task> savedTasks) {
        savedTasks.forEach(task -> {
            this.taskList.add(task);
        });
    }

    /**
     * Helps to mark the task in the list of Tasks as done.
     * 
     * @param number is the Task id.
     */
    public void doneItem(String number) {
        int index = Integer.parseInt(number) - 1;
        Task curr = this.taskList.get(index);
        this.taskList.set(index, curr.markAsDone());
        curr = this.taskList.get(index);

        Ui.printMessage(Ui.DONE_MESSAGE + "\n" + Ui.INDENT + "  " + curr.toString());
    }

    /**
     * Adds a particular task given by the user into the TaskList.
     * 
     * @param task is the task that is to be added into the TaskList.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        Ui.printMessage("Got it. I've added this task:\n" + Ui.INDENT + "  " + task.toString() + "\n" + Ui.INDENT
                + "Now you have " + this.taskList.size() + " tasks in the list.");
    }

    /**
     * Deletes a particular task that is present in the TaskList.
     * 
     * @param number is the Task id.
     */
    public void deleteItem(String number) {
        int index = Integer.parseInt(number) - 1;
        Task task = this.taskList.get(index);
        this.taskList.remove(index);
        Ui.printMessage("Noted. I've removed this task:\n" + Ui.INDENT + "  " + task.toString() + "\n" + Ui.INDENT
                + "Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public void find(String find) {
        ArrayList<Task> tempArr = new ArrayList<Task>();

        this.taskList.forEach(task -> {
            if (task.getDescription().contains(find)) {
                tempArr.add(task);
            }
        });

        Ui.printList(tempArr, Ui.FIND_ZERO_SIZE, Ui.FIND_LIST_MESSAGE);
    }
}
