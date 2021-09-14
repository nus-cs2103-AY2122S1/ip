package duke;

import java.util.ArrayList;

/**
 * The TaskList class that represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for a TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructor for a TaskList object.
     *
     * @param taskList List of tasks as an ArrayList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * A method to add a new task to the task list.
     *
     * @param newTask The new task to be added.
     */
    public void add(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * A method to get the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * A method to delete a task or mark a task as done.
     *
     * @param input Array containing the command and the index of the task to be edited.
     * @return The response to be displayed in the GUI.
     * @throws DukeException If an invalid index is given as input.
     */
    public String editTask(String[] input) throws DukeException {
        String num = input[1];
        int index = Integer.parseInt(num) - 1;
        if (index >= taskList.size() || index == -1) {
            assert index < 0 || index >= getLength() : "task index should be out of range";
            throw new DukeException("There is no such task in your list D:");
        }
        String command = input[0];
        switch (command) {
        case "done":
            Task toMark = taskList.get(index);
            toMark.markAsDone();
            return "Nice! I've marked this task as done:\n" + toMark.toString();
        case "delete":
            String deletedTask = taskList.get(index).toString();
            taskList.remove(index);
            return "Poof!\n" + deletedTask + "\nis gone"
                    + "\nNow you have " + taskList.size() + " tasks in the list";
        default:
            assert false : command;
            return "Didn't understand that :(";
        }
    }

    /** A method that finds tasks that contain the specified keyword.
     *
     * @param keyword Keyword to be searched for.
     * @return A TaskList of the matching tasks.
     * @throws DukeException If there are no matching tasks found.
     */
    public TaskList find(String keyword) throws DukeException {
        ArrayList<Task> newList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.contains(keyword)) {
                newList.add(currTask);
            }
        }
        if (newList.size() == 0) {
            throw new DukeException("There are no matching tasks :(");
        } else {
            return new TaskList(newList);
        }
    }

    /**
     * A method that sorts the task list by task.
     *
     * @return The response to be displayed in the GUI.
     */
    public String sortByTask() {
        ArrayList<Task> sortedList = new ArrayList<>();
        for (Task task : taskList) {
            sortedList.add(task);
        }
        sortedList.sort((o1, o2) -> Character.compare(o1.getTaskIcon(), o2.getTaskIcon()));
        this.taskList = sortedList;
        return "I've sorted your list by task:\n" + toString();
    }

    /**
     * A method that converts the task list to a suitable format to be saved in a save file.
     *
     * @return The formatted data as a string.
     */
    public String convertToData() {
        StringBuilder data = new StringBuilder();
        for (Task task : taskList) {
            data.append(task.toData() + "\n");
        }
        return data.toString();
    }

    /**
     * A method that returns the list as a string to be displayed in the GUI.
     *
     * @return String representation of the list.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            String curr = taskList.get(i).toString();
            result += String.format(" %s. %s\n", i + 1, curr);
        }
        return result;
    }
}
