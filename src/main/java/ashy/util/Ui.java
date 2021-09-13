package ashy.util;

import java.sql.Array;
import java.util.ArrayList;

import ashy.task.Task;

public class Ui {
    /**
     * Returns the greeting message
     * @return the greeting message
     */
    public String greetingMessage() {
        return "Hello! I'm Ashy! What can I do for you?";
    }
    /**
     * Returns the exit message for the chatbot
     * @return the output to be displayed
     */
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
    /**
     * Returns the output for a find command
     * @param matchingTasks the tasks that match the keyword
     * @param numMatches number of matching tasks
     * @return the output to be displayed
     */
    public String findOutput(ArrayList<Task> matchingTasks, int numMatches) {
        String output = "Here are " + numMatches + " matching tasks in your list: \n";
        for (int i = 0; i < numMatches; i++) {
            output += (i + 1) + ". " + matchingTasks.get(i) + "\n";
        }
        return output;
    }
    /**
     * Returns the output of a update command
     * @param task the task that has been updated
     * @return teh output to be displayed
     */
    public String updateOutput(Task task) {
        String output = "I have updated the task description for you! \n" + task;
        return output;
    }
    /**
     * Returns the output for the help command
     * @return the output to display
     */
    public String helpOutput() {
        String output = "Here is what I can do for you!\n"
                + "------------Task Manager------------\n"
                + "To create new tasks: \n"
                + "1. todo (task description)\n"
                + "2. deadline (task description) /by dd-MM-yyyy\n"
                + "3. event (task description) /at dd-MM-yyyy HHmm\n"
                + "------------Other Commands------------\n"
                + "4. done (task number)\n"
                + "5. delete (task number)\n"
                + "6. update (task number) : (new task description)\n"
                + "7. list\n"
                + "8. help\n";
        return output;
    }
}
