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
     * Constructor for creating a list of tasks.
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
     * This method manages adding a task.
     *
     * @param newTask represents the new task to be added
     */
    public void includeAdditionalTask(Task newTask) {
        tasks.add(this.count, newTask);
        count++;
    }

    /**
     * This method manages adding a task.
     *
     * @param information represents the information regarding task to be added
     */
    public void addTask(String information) {
        ui.addTaskMessage();
        information = removeVal(information, "todo");
        tasks.add(count, new ToDo(information, "TODO"));
        CompilationOfFiles.updateSavedFile(this.tasks.get(count), "TODO");
        ui.printCurrentTask(tasks.get(count));
        count++;
        ui.printNumberOfTasks(count);
    }

    /**
     * This method manages listing out the tasks.
     */
    public void listOut() {
        ui.listTaskMessage();
        int a = 0;
        while (a < count) {
            ui.listEachTask(tasks, a);
            a = a + 1;
        }
    }

    /**
     * This method manages marking a task as done.
     *
     * @param command represents command given by user to mark a task as done
     */
    public void markDone(String command) {
        try {
            command = removeVal(command, "done");
            int a;
            a = Integer.parseInt(command);
            a = a - 1;
            if (a < count && a >= 0) {
                this.tasks.get(a).markDone();
                CompilationOfFiles.updateFile(this.tasks);
                ui.printDoneMessage();
                ui.printCurrentTask(this.tasks.get(a));
            } else {
                ui.printInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            ui.printInvalidTaskNumber();
        }
    }

    /**
     * This method manages deleting task.
     *
     * @param command represents command given by user to delete a task.
     */
    public void delete(String command) {
        try {
            command = removeVal(command, "delete");
            int a;
            a = Integer.parseInt(command);
            a = a - 1;

            if (a < count && a >= 0) {
                Task deletedVal = this.tasks.remove(a);
                CompilationOfFiles.updateFile(this.tasks);
                ui.printDeletedMessage();
                System.out.println("       " + deletedVal.toString());
                count--;
                ui.printNumberOfTasks(count);
            } else {
                ui.printInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            ui.printInvalidTaskNumber();
        }
    }

    /**
     * This method manages removing a value.
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
     * This method manages adding an event to list of tasks.
     *
     * @param information represents information pertaining event.
     */
    public void addEvent(String information) {

        if (!information.contains("/at")) {
            ui.printInvalidInput();
            return;
        }
        ui.addTaskMessage();

        information = removeVal(information, "event");
        String[] moreInformation = information.split("/at", 2);

        this.tasks.add(count, new Event(moreInformation[0],
                moreInformation[1].strip(), "EVENT"));
        CompilationOfFiles.updateSavedFile(this.tasks.get(count), "EVENT");
        ui.printCurrentTask(this.tasks.get(count));
        count = count + 1;
        ui.printNumberOfTasks(count);
    }

    /**
     * This method manages adding a deadline to list of tasks.
     *
     * @param information represents information pertaining deadline.
     */
    public void addDeadline(String information) {

        if (!information.contains("/by")) {
            ui.printInvalidInput();
            return;
        }

        information = removeVal(information, "deadline");
        String[] moreInformation2 = information.split("/by", 2);

        this.tasks.add(count, new Deadline(moreInformation2[0],
                moreInformation2[1].strip(), "DEADLINE"));
        CompilationOfFiles.updateSavedFile(this.tasks.get(count), "DEADLINE");
        System.out.println("     Got it. I've added this task:");
        ui.printCurrentTask(this.tasks.get(count));
        count = count + 1;
        ui.printNumberOfTasks(count);
    }
    /**
     * This method manages listing out similar tasks.
     *
     * @param searchString represents information we
     *                     are using to find similar tasks.
     */
    public void findSimilarTasks(String searchString) {
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
        System.out.println(searchString);
        ui.printSimilarTasks(similarTasks);
    }


}




