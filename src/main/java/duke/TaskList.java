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
        System.out.println("Just added:\n" + newTask.toString());
        System.out.println("You currently have " + taskList.size() + " tasks in the list.");
    }

    /**
     * A method to delete a task or mark a task as done.
     *
     * @param input Array containing the command and the index of the task to be edited.
     * @throws DukeException If an invalid index is given as input.
     */
    public void editTask(String[] input) throws DukeException {
        String num = input[1];
        int index = Integer.parseInt(num) - 1;
        if (index >= taskList.size() || index == -1) {
            throw new DukeException("There is no such task in your list D:");
        }
        String command = input[0];
        switch (command) {
        case "done":
            Task toMark = taskList.get(index);
            toMark.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n"
                    + toMark.toString());
            break;
        case "delete":
            System.out.println("Poof!\n" + taskList.get(index).toString() + "\nis gone");
            taskList.remove(index);
            System.out.println("Now you have " + taskList.size() + " tasks in the list");
            break;
        default:
            System.out.println("Didn't understand that :(");
            break;
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

    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "Your list is empty :(";
        }
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            String curr = taskList.get(i).toString();
            result += String.format(" %s. %s\n", i + 1, curr);
        }
        return result;
    }
}
