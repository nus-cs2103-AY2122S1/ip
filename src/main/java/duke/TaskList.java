package duke;

import java.util.ArrayList;

/**
 * This class contains the task list with operations that can
 * be used on the list.
 */
public class TaskList {
    /** The contents found in the TaskList. **/
    private static ArrayList<Task> contents;

    /**
     * Constructor for TaskList.
     *
     * @param fileContents the content of the file used to save data
     */
    public TaskList(ArrayList<Task> fileContents) {
        contents = fileContents;
    }

    /**
     * Adds a task to the TaskList depending on what kind of task was added,
     * checking the user input and acting accordingly.
     * If input is valid, it is added; otherwise a reminder message is sent to the
     * user to show what kind of input is valid.
     *
     * @param userInput the line of user input which contains the task
     */
    public static void addTask(String userInput) {
        if (userInput.startsWith("deadline") || userInput.startsWith("event") ||
                userInput.startsWith("todo")) {
            Task newTask;
            if (userInput.startsWith("deadline") && userInput.contains("by")) {
                newTask = new Deadline(userInput);
            } else if (userInput.startsWith("event") && userInput.contains("at")) {
                newTask = new Event(userInput);
            } else {
                newTask = new ToDos(userInput);
            }
            contents.add(newTask);
            Ui.addTaskMessage(newTask, contents.size());
        } else {
            Ui.invalidTaskMessage();
        }
    }

    /**
     * Obtains the list of the current tasks.
     *
     * @return the list of current tasks.
     */
    public static ArrayList<Task> getList() {
        return contents;
    }

    /**
     * Prints out the list of current tasks. Displays a different
     * message if list is empty.
     */
    public static void printList() {
        if (contents.size() == 0) {
            Ui.emptyListMessage();
        } else {
            Ui.getCurrentTasks(contents);
        }
    }

    /**
     * Marks the current task as done and changes its UI appearance when
     * printed out.
     *
     * @param userInput the userInput which indicates which task is done.
     */
    public static void markTaskDone(String userInput) {
        String index = userInput.substring(5);
        int x = Integer.parseInt(index);
        Task temp = contents.get(x - 1);
        temp.markDone();
        Ui.markTaskMessage(temp);
    }

    /**
     * Removes the task completely from the list.
     *
     * @param userInput the userInput which indicates which task is to be removed.
     */
    public static void removeTask(String userInput) {
        String index = userInput.substring(7);
        int x = Integer.parseInt(index);
        Task temp = contents.get(x - 1);
        contents.remove(temp);
        Ui.removeTaskMessage(temp);
    }

    /**
     * Marks all tasks as saved to the file.
     */
    public void markTasksSaved() {
        for (Task x: contents) {
            x.markSaved();
        }
    }

    /**
     * Finds and returns all tasks containing the userInput.
     *
     * @param userInput the search request by the user
     */
    public static void findTask(String userInput) {
        String keyword = userInput.substring(5);
        ArrayList<Task> matchingList = new ArrayList<>();
        int counter = 0;
        for (Task x : contents) {
            if (x.getDescription().contains(keyword)) {
                matchingList.add(x);
                counter++;
            }
        }
        if (counter == 0) {
            Ui.searchFoundNothing();
        } else {
            Ui.searchList(matchingList);
        }
    }
}
