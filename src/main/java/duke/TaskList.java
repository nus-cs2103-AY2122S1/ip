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
     * @param fileContents Content of the file used to save data.
     */
    public TaskList(ArrayList<Task> fileContents) {
        assert fileContents != null : "Tasks are not null but in an arraylist.";
        contents = fileContents;
    }

    /**
     * Adds a task to the TaskList depending on what kind of task was added,
     * checking the user input and acting accordingly.
     * If input is valid, it is added; otherwise a reminder message is sent to the
     * user to show what kind of input is valid.
     *
     * @param userInput Line of user input which contains the task.
     */
    public static String addTask(String userInput) {
        boolean isDeadLine = userInput.startsWith("deadline") || userInput.startsWith("d ");
        boolean isEvent = userInput.startsWith("event") || userInput.startsWith("e ");
        boolean isToDo = userInput.startsWith("todo") || userInput.startsWith("t ");
        boolean isCorrectDeadlineFormat = userInput.contains("by");
        boolean isCorrectEventFormat = userInput.contains("at");

        if (isDeadLine || isEvent || isToDo) {
            Task newTask;
            if (isDeadLine && isCorrectDeadlineFormat) {
                newTask = new Deadline(userInput);
            } else if (isEvent && isCorrectEventFormat) {
                newTask = new Event(userInput);
            } else {
                newTask = new ToDo(userInput);
            }
            contents.add(newTask);
            Storage.writeFile();
            assert contents.contains(newTask) : "Task List should contain newly added Task";
            return Ui.addTaskMessage(newTask, contents.size());
        } else {
            return Ui.invalidTaskMessage();
        }
    }

    /**
     * Obtains the list of the current tasks.
     *
     * @return List of current tasks.
     */
    public static ArrayList<Task> getList() {
        return contents;
    }

    /**
     * Prints out the list of current tasks. Displays a different
     * message if list is empty.
     */
    public static String printList() {
        if (contents.size() == 0) {
            return Ui.emptyListMessage();
        } else {
            return Ui.getCurrentTasks(contents);
        }
    }

    /**
     * Marks the current task as done and changes its UI appearance when
     * printed out.
     *
     * @param userInput UserInput which indicates which task is done.
     */
    public static String markTaskDone(String userInput) {
        try {
            String index = userInput.substring(5);
            int x = Integer.parseInt(index);
            Task temp = contents.get(x - 1);
            temp.markDone();
            return Ui.markTaskMessage(temp);
        } catch (StringIndexOutOfBoundsException e) {
            return Ui.invalidDoneInput();
        } catch (ArrayIndexOutOfBoundsException f) {
            return Ui.numberOutsideOfArrayBounds();
        }
    }

    /**
     * Removes the task completely from the list.
     *
     * @param userInput UserInput which indicates which task is to be removed.
     */
    public static String removeTask(String userInput) {
        try {
            String index;

            // Users may use delete, del or rm to remove tasks.
            if (userInput.startsWith("delete")) {
                index = userInput.substring(7);
            } else if (userInput.startsWith("del")) {
                index = userInput.substring(4);
            } else {
                index = userInput.substring(3);
            }

            int x = Integer.parseInt(index);
            Task temp = contents.get(x - 1);
            contents.remove(temp);
            assert !contents.contains(temp) : "Task List should not contain current Task";
            return Ui.removeTaskMessage(temp);
        } catch (StringIndexOutOfBoundsException e) {
            return Ui.invalidDeleteInput();
        } catch (ArrayIndexOutOfBoundsException f) {
            return Ui.numberOutsideOfArrayBounds();
        }
    }

    /**
     * Marks all tasks as saved to the file.
     */
    public static void markTasksSaved() {
        for (Task x: contents) {
            x.markSaved();
        }
    }

    /**
     * Finds and returns all tasks containing the userInput.
     *
     * @param userInput Search request by the user.
     */
    public static String findTask(String userInput) {
        try {
            String keyword;
            if (userInput.startsWith("f")) {
                keyword = userInput.substring(2);
            } else {
                keyword = userInput.substring(5);
            }

            assert keyword != null : "Keyword should not be empty";

            ArrayList<Task> matchingList = new ArrayList<>();
            int counter = 0;
            for (Task x : contents) {
                if (x.getDescription().contains(keyword)) {
                    matchingList.add(x);
                    counter++;
                }
            }
            if (counter == 0) {
                return Ui.searchFoundNothing();
            } else {
                return Ui.searchList(matchingList);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return Ui.invalidFindInput();
        } catch (ArrayIndexOutOfBoundsException f) {
            return Ui.numberOutsideOfArrayBounds();
        }
    }
}
