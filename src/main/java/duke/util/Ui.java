package duke.util;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    /**
     * Displays the greeting message.
     */
    public String showGreeting() {
        String output = getDashes()
                + "     Hello! I'm duke.Duke\n"
                + "     What can I do for you?\n"
                + getDashes();
        return output;
    }

    private String getDashes() {
        return "____________________________________________________________\n";
    }

    public void showLoadingError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays all the tasks in the taskList.
     * @param taskList the TaskList to be printed
     */
    public String printTasks(TaskList taskList) {
        ArrayList<Task> lst = taskList.getTasks();
        StringBuilder s = new StringBuilder();
        s.append(getDashes());
        for (int i = 0; i < lst.size(); i++) {
            s.append(String.format("     %d. %s\n", i + 1, lst.get(i).toString()));
        }
        s.append(getDashes());
        return s.toString();
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        String output = getDashes()
                + "Bye. Hope to see you again soon!\n"
                + getDashes();
        System.out.println(output);
    }

    /** Shows messages */
    public void showMessage(String message) {
        System.out.println(getDashes() + message + "\n" + getDashes());
    }
}
