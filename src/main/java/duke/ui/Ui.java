package duke.ui;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Text UI of the application.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Constructs a message containing all tasks in the ArrayList.
     *
     * @param list list of tasks
     * @return ArrayList in String format
     */
    public static String tasklistToString(ArrayList<Task> list) {
        assert(list.size() > 0);
        String answer = "";
        int counter = 1;
        for (Task item : list) {
            answer += String.format("%d: %s\n", counter, item.toString());
            counter++;
        }
        return answer;
    }
}
