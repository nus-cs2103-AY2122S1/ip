package duke;

import java.util.ArrayList;

/**
 * Class includes methods for performing different actions to the list of tasks.
 */
public class ListOfTasks {
    private static int count;
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Creates a list of tasks.
     */
    public ListOfTasks() {
        ui = new Ui();
        this.tasks = new ArrayList<>();
        this.count = 0;
    }

    /**
     * Returns the list of tasks.
     *
     * @return tasks
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Adds a task to list of tasks.
     *
     * @param newTask represents the new task to be added
     */
    public void includeAdditionalTask(Task newTask) {
        tasks.add(this.count, newTask);
        count++;
    }

    /**
     * Adds a task to list of tasks.
     *
     * @param information represents the information regarding task to be added.
     * @return the text for when the task is added.
     */
    public String addTask(String information){
        int oldCount = count;
        information = removeVal(information, "todo");
        tasks.add(count, new ToDo(information, "TODO"));
        CompilationOfFiles.updateSavedFile(this.tasks.get(count), "TODO");
        count++;
        return ui.addTaskMessage() + "\n" + ui.produceCurrentTask(tasks.get(oldCount))
                + "\n" + ui.produceNumberOfTasks(count);
    }

    /**
     * Lists all tasks in the list.
     *
     * @return list of tasks in string format
     */
    public String listOut() {
        return ui.listTaskMessage() + "\n" + ui.listAllTasks(tasks,count);
    }

    /**
     * Marks a completed task as done.
     *
     * @param command represents command given by user to mark a task as done
     * @return done task in string format
     */
    public String markDone(String command) {
        try {
            command = removeVal(command, "done");
            int a;
            a = Integer.parseInt(command);
            a = a - 1;
            if (a < count && a >= 0) {
                this.tasks.get(a).markDone();
                CompilationOfFiles.updateFile(this.tasks);
                return ui.produceDoneMessage() + "\n" + ui.produceCurrentTask(this.tasks.get(a));
            } else {
                return ui.produceInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            return ui.produceInvalidTaskNumber();
        }
    }

    /**
     * Deletes a task from list of tasks.
     *
     * @param command represents command given by user to delete a task.
     * @return deleted task in string format
     */
    public String delete(String command) {
        try {
            command = removeVal(command, "delete");
            int a;
            a = Integer.parseInt(command);
            a = a - 1;

            if (a < count && a >= 0) {
                Task deletedVal = this.tasks.remove(a);
                CompilationOfFiles.updateFile(this.tasks);
                count--;
                return ui.produceDeletedMessage() + "\n" + deletedVal.toString()
                        + "\n" + ui.produceNumberOfTasks(count);
            } else {
                return ui.produceInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            return ui.produceInvalidTaskNumber();
        }
    }

    /**
     * Removes redundant value from command.
     *
     * @param command represents command given to remove value
     * @param val represents value to be removed
     * @return value after changes made
     */
    private String removeVal(String val, String command) {
        int len = command.length();
        val = val.strip();
        val = val.substring(len);
        val = val.strip();
        return val;
    }

    /**
     * Adds an event to list of tasks.
     *
     * @param information represents information pertaining event.
     * @return added Event in string format
     */
    public String addEvent(String information) {
        int oldCount = count;
        if (!information.contains("/at")) {
            return ui.produceInvalidInput();
        }

        information = removeVal(information, "event");
        String[] moreInformation = information.split("/at", 2);

        this.tasks.add(count, new Event(moreInformation[0],
                moreInformation[1].strip(), "EVENT"));
        CompilationOfFiles.updateSavedFile(this.tasks.get(count), "EVENT");
        count = count + 1;
        return ui.addTaskMessage()
                + "\n" + ui.produceCurrentTask(this.tasks.get(oldCount))
                + "\n" + ui.produceNumberOfTasks(count);
    }

    /**
     * Adds a deadline to list of tasks.
     *
     * @param information represents information pertaining deadline.
     * @return added deadline in string format
     */
    public String addDeadline(String information) {
        int oldCount = count;
        if (!information.contains("/by")) {
            return ui.produceInvalidInput();
        }

        information = removeVal(information, "deadline");
        String[] moreInformation2 = information.split("/by", 2);

        this.tasks.add(count, new Deadline(moreInformation2[0],
                moreInformation2[1].strip(), "DEADLINE"));
        CompilationOfFiles.updateSavedFile(this.tasks.get(count), "DEADLINE");
        count = count + 1;
        return ui.addTaskMessage()
                + "\n" + ui.produceCurrentTask(this.tasks.get(oldCount))
                + "\n" + ui.produceNumberOfTasks(count);
    }

    /**
     * Lists out similar tasks from list of tasks.
     *
     * @param searchString represents information we
     *                     are using to find similar tasks.
     * @return list of similar tasks in string format
     */
    public String findSimilarTasks(String searchString) {
        searchString = removeVal(searchString, "find");
        ArrayList<String> similarTasks = new ArrayList<String>();
        similarTasks.add("\t Here are the matching tasks in your list:");

        int index = 1;
        for (Task task : this.tasks) {
            if (task.isContainingSimilarInformation(searchString)) {
                similarTasks.add("\t" + index + "." + task);
                index++;
            }
        }
        return ui.produceSimilarTasks(similarTasks);
    }


}

