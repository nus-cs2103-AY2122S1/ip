package duke.ui;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Text UI of the application.
 */
public class Ui {
    public Ui() {
    }

    /**
     * Constructs a message containing all tasks in the ArrayList.
     *
     * @param list List of tasks to convert to string.
     * @return ArrayList in String format.
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
