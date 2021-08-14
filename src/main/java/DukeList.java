import java.util.ArrayList;

/**
 * A wrapper for the list of Task stored by Duke
 *
 * @author Wong Yun Rui Chris
 */
public class DukeList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * A private method to add the new Task into list and return the corresponding String reply.
     *
     * @param task The Task that will be added into the list
     * @return The String of the reply after adding a task
     */
    private String addTask(Task task) {
        this.list.add(task);
        return "\tGot it. I've added this task:\n\t\t " + task.toString()
                + "\n\tNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * A private method to return the list of all the Tasks.
     *
     * @return The String of the list of all the tasks formatted
     */
    private String displayTask() {
        StringBuilder output = new StringBuilder("\tHere are the tasks in your list:\n");
        int i = 1;
        for (Task task: list) {
            String temp = "\t" + i + "." + task.toString() + "\n";
            output.append(temp);
            i++;
        }
        return output.toString();
    }

    /**
     * A private method to mark the Task at the index of the list to be done.
     *
     * @param index The index of the Task in the list that is to be mark as done
     * @return The corresponding String reply after marking a task done
     */
    private String markTask(int index) {
        return "\tNice! I've marked this task as done:\n\t\t" + list.get(index - 1).markDone();
    }

    /**
     * The method to handle all the text input to call the correct corresponding method
     *
     * @param input The text input from the user to Duke
     * @return The corresponding String reply to the user's input
     */
    public String handleInput(String input) {
        String[] inputArray = input.split(" ",2);
        String reply = "";
        String[] tempArray;

        switch (inputArray[0]) {
            case "list":
                reply = this.displayTask();
                break;
            case "done":
                reply = this.markTask(Integer.parseInt(inputArray[1]));
                break;
            case "todo":
                reply = this.addTask(new Todo(inputArray[1]));
                break;
            case "deadline":
                tempArray = inputArray[1].split(" /by ");
                reply = this.addTask(new Deadline(tempArray[0], tempArray[1]));
                break;
            case "event":
                tempArray = inputArray[1].split(" /at ");
                reply = this.addTask(new Event(tempArray[0], tempArray[1]));
                break;
            default:
                break;
        }
        return reply;
    }
}
