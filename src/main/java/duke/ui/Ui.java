package duke.ui;

import duke.task.Task;
import duke.task.TaskList;
import java.util.List;

public class Ui {

    public static String DIVIDER_LINE = "\t____________________________";

    /**
     * Method to generate greet string when Duke is first run
     */
    public String greet() {
        return "Hello from Duke\n";
    }

    /**
     * Method to generate string corresponding to Command.EXIT
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Method to generate string corresponding to Command.UNKNOWN
     */
    public String unknownCommand() {
        return "Sorry, I do not know this command!";
    }

    /**
     * Method for listing tasks given a tasklist
     * Loops through the task prepending it with a tab space and its index number
     * before calling that task's toString method
     *
     * @param tasks TaskList object containing the tasks
     */
    public String list(TaskList tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return list;
    }

    /**
     * Method for listing through tasks in a given ArrayList, and printing them
     * Used during find commands
     *
     * @param tasks ArrayList of Task objects to loop through
     */
    public String list(List<Task> tasks) {
        String toReturn = "Here are the matching tasks your list\n";
        for (int i = 0; i < tasks.size(); i++) {
            toReturn += (i + 1) + ". " + tasks.get(i).toString() +"\n";
        }
        return toReturn;
    }

    
    /**
     * Method for generating the string corresponding to Command.INDEXCOMMAND
     *
     * @param size length of the tasklist
     * @param task task operated on
     * @param taskArray String[] of the user input split once by " "
     */
    public String indexCommand(int size, Task task, String[] taskArray) {
        String toReturn = "";
        if (taskArray[0].equals("done")) {
            toReturn += "Nice! I\'ve marked this task as done:\n";
            toReturn +=  "\t" + task.toString() + "\n";
        } else {
            toReturn += "Noted. I've removed this task: \n";
            toReturn += "\t" + task.toString() + "\n";
            toReturn += "Now you have " + size + " tasks in the list";
        }
        return toReturn;
    }

    /**
     * Method for generating string corresponding to Command.ADDCOMMAND
     *
     * @param task Task added
     * @param size length of tasklist
     */
    public String addTask(Task task, int size) {
        String toReturn = "Got it. I\'ve added this task:\n";
        toReturn += "\t" + task.toString() + "\n";
        toReturn += "Now you have " + size +
                           " tasks in the list.";
        return toReturn;
    }

    public String updateTask(Task task) {
        String toReturn = "Okay I\'ve update this task:\n";
		return toReturn += "\t" + task.toString();
    }
}
