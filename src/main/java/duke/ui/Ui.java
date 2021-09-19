package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private static final String line = "____________________________________________________________";

    /**
     * Send a greeting to the user.
     */
    public static String greet() {
        String greet = line + "\n"
                + "Hello I'm Duke\n"
                + "What can I do for you?\n"
                + line;
        System.out.println(greet);
        return greet;
    }

    /**
     * Say goodbye to the user.
     */
    public static String bye() {
        String bye = line + "\nBye. Hope to see you again soon!\n" + line;
        System.out.println(bye);
        return bye;
    }

    /**
     * Tell user the task info of tasks in the given TaskList
     *
     * @param tasks List of tasks.
     */
    public static String list(TaskList tasks) {
        String list = line + "\n" + "Here are tasks in your list:\n";

        for (int index = 1; index <= tasks.size(); index++) {
            list += index + "." + tasks.get(index - 1).toString() + "\n";
        }
        list += line + "\n";
        System.out.println(list);
        return list;
    }

    /**
     * Tell user the task info of the completed task.
     *
     * @param task A Task be done by user.
     */
    public static String done(Task task) {
        String done = line + "\n" + "done:\n" + task.toString() + line + "\n";
        System.out.println(done);
        return done;
    }

    /**
     * Tell user the task info of the deleted task.
     *
     * @param task A Task be deleted by user.
     */
    public static String delete(TaskList tasks, Task task) {
        String delete = line + "\n" + "removed:\n" + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n"
                + line + "\n";
        System.out.println(delete);
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
        String add = line + "\n" + "added: " + task
                + "\nNow you have " + tasks.size() + ""
                + " tasks in the list.\n"
                + line + "\n";
      System.out.println(add);
      return add;
    }

    /**
     * Print out the list of task info searched by user using a keyword
     *
     * @param taskList A list of task.
     * @param keyword keyword user want to search by.
     */
    public static String find(TaskList taskList, String keyword) {
        String find = line + "\n" + "Here are the matching tasks in your list:\n"
                + taskList.find(keyword)
                + line + "\n";
        System.out.println(find);
        return find;
    }

    /**
     * Tell user a error occur while loading local data
     */
    public String showLoadingError() {
        String showLoadingError = line + "\n"
                + "Cannot Load From Data.\n"
                + line + "\n";
        System.out.println(showLoadingError);
        return showLoadingError;
    }

}
