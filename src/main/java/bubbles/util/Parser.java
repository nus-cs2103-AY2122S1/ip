package bubbles.util;

/**
 * A class that deals with making sense of the
 * user command.
 */
public class Parser {

    /**
     * Breaks down the line of input read from the file saved on the local hard disk.
     * Creates and returns an array that contains the necessary information for the
     * Bubbles bot to easily update its task list with what is being read from
     * the file.
     *
     * @param task A line of input read from the file saved on the local hard disk.
     * @return Object array which the first argument refers to the task category (ToDo,
     *          Deadline, Event), the second argument refers to the task description
     *          (and date), and the last argument refers to whether the task had been
     *          completed.
     */
    public Object[] formatTask(String task) {
        String input = "";

        String[] arr = task.split(" \\| ");
        int n = arr.length;

        String taskType = arr[0];

        String completed = arr[1];
        boolean isDone = false;
        if (completed.equals("1")) {
            isDone = true;
        }

        input += arr[2];

        if (n > 3) {
            String[] date = arr[3].split(" ", 2);

            input = input + " /" + date[0] + " " + date[1];
        }

        Object[] inputs = {taskType, input, isDone};
        return inputs;
    }
}
