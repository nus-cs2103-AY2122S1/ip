package duke;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class represents the list of all tasks.
 *  It also houses some list operations.
 *
 * @author Ryan Tian Jun.
 */
public class TaskList {
    private static List<Task> taskList = new ArrayList<Task>();

    public TaskList() {

    }

    /**
     * Adds a Task to the List.
    */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Searches for Tasks containing a search query.
     * Features: case insensitive, partial string, by time,
     * by tasks not done, by Type.
     *
     * @return returns List of all Tasks that contain the Query.
     */
    public String search(String query) {
        String result = "Here's what I found!: \n";
        List<Task> results = processQuery(query);

        // Concatenates tasks into specified format
        if (results.size() == 0) {
            return "No matches found!";
        } else {
            assert results.size() > 0 : "The results list should not be empty";
            int counter = 1;
            for (Task resultFound : results) {
                String printResult = counter + "." + resultFound + "\n";
                counter++;
                result += printResult;
            }
        }
        return result;
    }

    private List<Task> processQuery(String query) {
        List<Task> results = new ArrayList<>();

        for (Task task : taskList) {
            String taskLowerCase = task.getDescription().toLowerCase();
            String queryLowerCase = query.toLowerCase();
            String queryTag = query.length() >= 2 ? queryLowerCase.substring(0, 2) : "";

            Task.Type taskType = task.getType();
            boolean hasTime = (taskType == Task.Type.E || taskType == Task.Type.D);


            // Searches based on whether task has been done
            if (isTaskDone(task, queryTag)) {
                results.add(task);
                // Searches based on task type
            } else if (isTaskType(task, queryTag, queryLowerCase)) {
                results.add(task);
                // Searches based on time
            } else if (hasTime && isTime(task, queryLowerCase)) {
                results.add(task);
                // Search based on task description
            } else if (taskLowerCase.contains(queryLowerCase)) {
                results.add(task);
            }
        }
        return results;
    }

    private boolean isTaskDone(Task task, String queryTag) {
        boolean isTaskDone = task.isDone();
        if (queryTag.contains("-d")) {
            return !isTaskDone;
        }
        return false;
    }

    private boolean isTaskType(Task task, String queryTag, String queryLowerCase) {
        Task.Type taskType = task.getType();
        if (queryTag.contains("-t")) {
            String queryTaskType = queryLowerCase.length() >= 2 ?queryLowerCase.substring(2).trim() : "";
            if (taskType == Task.Type.T && queryTaskType.equals("t")) {
                return true;
            } else if (taskType == Task.Type.D && queryTaskType.equals("d")) {
                return true;
            } else if (taskType == Task.Type.E && queryTaskType.equals("e")) {
                return true;
            }
        }
        return false;
    }

    private boolean isTime(Task task, String queryLowerCase) {
        String timeLowerCase = task.getTime().toLowerCase();
        return timeLowerCase.contains(queryLowerCase);
    }

    /**
     * Prints past commands.
     *
     * @return returns a printout of all the past user commands.
     */
    public static String listCommand() {
        int count = 1;
        String result = "";

        if (taskList.size() == 0) {
            return "List is Empty!";
        }
        for (Task item : taskList) {
            result += count + ". " + item + "\n";
            count++;
        }
        assert taskList.size() > 0 : "The task list should not be empty";
        return result;
    }

    /**
     * Marks tasks as done.
     *
     * @return returns a String message.
     * @throws DukeException Handles out of range errors.
     */
    public String markDone(int taskNumber) throws DukeException {
        if (taskNumber <= 0) {
            throw new DukeException("Mark Done:", 2);
        } else {
            if (taskNumber <= taskList.size()) {
                Task taskToChange = taskList.get(taskNumber - 1);
                taskToChange.markAsDone();

                return "Nice! I've marked this task as done: \n" + "   " + taskToChange;
            } else {
                throw new DukeException("Task does not exist");
            }
        }
    }

    /**
     * Deletes Tasks from the list.
     *
     * @return returns a String message.
     * @throws DukeException Handles out of range errors.
     */
    public String deleteTask(int taskNumber) throws DukeException {
        if (taskNumber <= 0) {
            throw new DukeException("Delete Task:", 2);
        } else {
            if (taskNumber <= taskList.size()) {
                assert taskList.size() > 0: "Task list should at least contain something to delete";
                String result = "Noted. I've removed this task: \n";
                Task taskToDelete = taskList.get(taskNumber - 1);
                taskList.remove(taskNumber - 1);

                result += "   " + taskToDelete + "\n";
                result += "Now you have " + taskList.size() + " tasks in the list.";
                return result;
            } else {
                throw new DukeException("Unable to Delete: Task does not exist");
            }
        }
    }


    /**
     * Saves list of commands to hard drive upon program exit.
     */
    public static void saveList() {
        Storage.wipeOldSave();
        for (Task task : taskList) {
            Storage.writeTask(task);
        }
    }


    /**
     * Returns the size of the List.
     *
     * @return The String representation of the size of the list.
     */
    @Override
    public String toString() {
        return Integer.toString(taskList.size());
    }

}
