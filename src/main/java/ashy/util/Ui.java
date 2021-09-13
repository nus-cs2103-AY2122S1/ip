package ashy.util;

import java.util.ArrayList;

import ashy.task.Task;

public class Ui {

    public String greetingMessage() {
        return "Hello! I'm Ashy! What can I do for you?";
    }

    public String exitMessage() {
        return "ByeBye! Hope to see you again!";
    }

    /**
     * Returns the output for a list command
     * @param commands the commands to be displayed
     * @return the output to be displayed
     */
    public String listTasksOutput(ArrayList<Task> commands) {
        String output = "Here are the tasks in your list: \n";
        for (int i = 0; i < commands.size(); i++) {
            output += (i + 1) + ". " + commands.get(i) + "\n";
        }
        return output;
    }
    /**
     * Returns the output for a done command
     * @param task the task to be marked as done
     * @param tasksRemaining number of tasks to be done remaining
     * @return the output to be displayed
     */
    public String doneOutput(Task task, int tasksRemaining) {
        String output = "Good job! I've marked this task as completed: \n" + task
                + "\n You now have " + tasksRemaining + " tasks to complete";
        return output;
    }
    /**
     * Returns the output for a delete command
     * @param task the task to be deleted
     * @param tasksRemaining the number of tasks remaining in the list
     * @return the output to be displayed
     */
    public String removeOutput(Task task, int tasksRemaining) {
        String output = "Okay! I have removed this task for you: \n" + task
                + "\n You now have " + tasksRemaining + " tasks in your list.";
        return output;
    }
}
