package bubbles;

public class Parser {
    // deals with making sense of the user command
    public Object[] formatTask(String task) {
        String input = "";
        String[] arr = task.split(" \\| ");
        int n = arr.length;

        String taskType = arr[0];

        String completed = arr[1];
        boolean isDone = false;

        if (completed.equals("0")) {
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
