package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private static final String line = "____________________________________________________________";

    /**
     * Send a greeting to the user.
     */
    public static String greet() {
        String greet = "Hello I'm Duke\n"
                + "What can I do for you?";
        System.out.println(line);
        System.out.println(greet);
        System.out.println(line);
        return greet;
    }

    /**
     * Say goodbye to the user.
     */
    public static String bye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);
        return bye;
    }

    /**
     * Tell user the task info of tasks in the given TaskList
     *
     * @param tasks List of tasks.
     */
    public static String list(TaskList tasks) {
        String list = "Here are tasks in your list: \n";

        for (int index = 1; index <= tasks.size(); index++) {
            list += index + "." + tasks.get(index - 1).toString() + "\n";
        }
        System.out.println(line);
        System.out.println(list);
        System.out.println(line);
        return list;
    }

    /**
     * Tell user the task info of the completed task.
     *
     * @param task A Task be done by user.
     */
    public static String done(Task task) {
        String done = "done:\n" + task.toString();
        System.out.println(line);
        System.out.println(done);
        System.out.println(line);
        return done;
    }

    /**
     * Tell user the task info of the deleted task.
     *
     * @param task A Task be deleted by user.
     */
    public static String delete(TaskList tasks, Task task) {
        String delete = "removed:\n" + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        System.out.println(line);
        System.out.println(delete);
        System.out.println(line);
        return delete;
    }

    /**
     * Tell user the task info of the newly added task.
     * Tell user the size of TaskList after adding a new task.
     *
     * @param tasks A list of task.
     * @param task A Task be added by user.
     */
    public static String add(TaskList tasks, Task task) {
        String add = "added: " + task
                + "\nNow you have " + tasks.size() + ""
                + " tasks in the list.";
        System.out.println(line);
        System.out.println(add);
        System.out.println(line);
        return add;
    }

    /**
     * Print out the list of task info searched by user using a keyword
     *
     * @param taskList A list of task.
     * @param keyword keyword user want to search by.
     */
    public static String find(TaskList taskList, String keyword) {
        String find = "Here are the matching tasks in your list:\n"
                + taskList.find(keyword);
        System.out.println(line);
        System.out.println(find);
        System.out.println(line);
        return find;
    }

    /**
     * Tell user a error occur while loading local data
     */
    public String showLoadingError() {
        String showLoadingError = "Cannot Load From Data.";
        System.out.println(line);
        System.out.println(showLoadingError);
        System.out.println(line);
        return showLoadingError;
    }

}
