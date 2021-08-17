/**
 * Response class contains the logic for processing the commands from Duke.
 * At level-3, it supports (i) the list command, (ii) the bye command,
 * (iii) adding items to the list, (iv) and marking the tasks as done
 */

public class Response {
    private Task[] lstOfTasks = new Task[100];
    private int itemCount = 0;

    /**
     * Returns a farewell statement to the user.
     * @return A string to bids farewell to the user
     */
    String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a numbered list with the Tasks that were added.
     * @return A numbered list with the Tasks that were added
     */
    String list() {
        String res = "";
        for (int i = 0; i < itemCount; i++) {
            res += (i + 1) + ". [" + lstOfTasks[i].getStatusIcon() + "] " +
                    lstOfTasks[i].description + "\n";
        }
        return res;
    }

    /**
     * Returns a string that shows the Task that has been completed.
     * @param taskNumber the Task number on the list of Tasks
     * @return A string that shows the Task that has been completed
     */
    String done(int taskNumber) {
        String res = "Nice! I've marked this task as done:\n";
        Task currTask = lstOfTasks[taskNumber];
        currTask.markAsDone();
        res += " [" + currTask.getStatusIcon() + "] " + currTask.description;
        return res;
    }

    /**
     * Handles the input commands from Duke.
     * @param string the command input from Duke
     * @return a numbered list, a farewell, or an added message 
     */
    String output(String string) {
        if (string.equals("list")) {
            return list();
        } else if (string.equals("bye")) {
            return bye();
        } else if (string.contains("done")) {
            // getting the task number
            int taskNum = Integer.parseInt(
                    String.valueOf(
                            string.toCharArray()[string.length() - 1])) - 1;
            return done(taskNum);
        }
        lstOfTasks[itemCount] = new Task(string);
        itemCount++;
        return "added: " + string;
    }
}
