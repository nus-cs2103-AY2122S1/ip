package duke;
import java.util.ArrayList;

/**
 * Stores the list of tasks in an array list. This list can then be altered based on the user's inputs
 */
public class TaskList {
    public ArrayList<Task> tasks;
    public int count;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.count = 0;
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.count = tasks.size();
    }

    /**
     * Marks a task in the tasklist as done
     * @param i Takes in an integer to find the correct string placement
     */
    public String doTask(int i) {
        if (i > tasks.size()) {
            return "Invalid Input for done command";
        } else if (!tasks.get(i - 1).isDone) {
            String output = "Nice! I've marked this task as done:\n";
            tasks.get(i - 1).markAsDone();
            output += tasks.get(i - 1);
            return output;
        } else {
            return "This task is already marked as done";
        }
    }

    /**
     * Adds an event task to the tasklist
     * @param in Provides the string input for the event task
     * @param i Takes in an integer to find the correct string placement in the string input
     */
    public String addEventTask(String in, int i) {
        String output = "Got it. I've added this task: \n";
        tasks.add(new EventTask(in.substring(6, i), in.substring(i + 1)));
        output += tasks.get(tasks.size()-1) + "\n";
        if (tasks.size() == 1) {
            output += "Now you have " + tasks.size() + " task in the list.";
        } else {
            output += "Now you have " + tasks.size() + " tasks in the list.";
        }
        return output;
    }

    /**
     * Adds a deadline task to the task list
     * @param in Provides the string input for the deadline task
     * @param i Takes in an integer to find the correct string placement in the string input
     */
    public String addDeadlineTask(String in, int i) {
        String output = "Got it. I've added this task: \n";
        tasks.add(new DeadlineTask(in.substring(9, i), in.substring(i + 1)));
        output += tasks.get(tasks.size()-1) + "\n";
        if (tasks.size() == 1) {
            output += "Now you have " + tasks.size() + " task in the list.";
        } else {
            output += "Now you have " + tasks.size() + " tasks in the list.";
        }
        return output;
    }

    /**
     * Adds a todo task to the task list
     * @param in Provides the string input for the todo task
     */
    public String addToDoTask(String in) {
        String output = "Got it. I've added this task: \n";
        tasks.add(new ToDoTask(in.substring(5)));
        output += tasks.get(tasks.size()-1) + "\n";
        if (tasks.size() == 1) {
            output += "Now you have " + tasks.size() + " task in the list.";
        } else {
            output += "Now you have " + tasks.size() + " tasks in the list.";
        }
        return output;
    }

    /**
     * Displays the tasklist
     */
    public String showList() {
        int counter = 1;
        String output = new String();
        output += " Here are the tasks in your list: \n";
        for (Task item: tasks) {
            if(item != null) {
                output += counter + ". " + item.toString() + "\n";
                counter++;
            }
        }
        return output;
    }

    /**
     * Delets a task from the tasklist
     * @param i
     */
    public String deleteTask(int i) {
        if (tasks.isEmpty()) {
            return "The list is empty.";
        } else {
            if (i > 100) {
                return "Invalid Input for delete command";
            } else if (i > tasks.size()) {
                return "Invalid Input for delete command";
            } else {
                String output = "Noted. I've removed this task: \n";
                output += tasks.get(i - 1);
                tasks.remove(i - 1);
                return output;
            }
        }
    }

    /**
     * Finds all tasks containing a keyword
     * @param in keyword to be found
     */
    public String findTask(String in) {
        String output = "Here are the matching tasks in your list: \n";
        for (Task item : tasks) {
            if (item.toString().contains(in)) {
                output += item.toString() + "\n";
            }
        }
        return output;
    }

    public String toString() {
        if (tasks.isEmpty()) {
            return "No tasks";
        } else {
            String result = "";
            for (Task item : tasks) {
                result += item.toString();
                result += " ";
            }
            return result;
        }
    }
}
