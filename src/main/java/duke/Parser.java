package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Parser that receives inputs from the user and executes the correct commands.
 */
public class Parser {

    TaskList tasks;
    String userInput;
    int index;

    /**
     * Constructor for the Parser object
     *
     * @param tasks
     * @param userInput
     * @param index
     */
    public Parser(TaskList tasks, String userInput, int index) {
        this.tasks = tasks;
        this.userInput = userInput;
        this.index = index;
    }

    /**
     * Executes when Duke receives a bye command
     * Sends a goodbye message to the user
     */
    public void bye_execute() {
        String byeMsg = "    ----------------------------\n"
                + "    okay :<, bye!" + "\n"
                + "    ----------------------------";
        System.out.println(byeMsg);
    }

    /**
     * Executes when Duke receives a list command
     * Outputs the list of current tasks to the user
     */
    public void list_execute() {
        String message = "    ----------------------------\n"
                + "    " + "Here are the tasks in your list:\n";
        int i = 0;
        while (i < tasks.getNumberOfTasks()) {
            message += "    " + (i+1) + ". " + tasks.get(i).toString() + "\n";
            i++;
        }
        message += "    ----------------------------";
        System.out.println(message);
    }

    /**
     * Executes when Duke receives a done command
     * Marks the relevant task as done
     */
    public void done_execute() {
        String userIndex = userInput.substring(5);
        int i = Integer.valueOf(userIndex);
        if (tasks.get(i-1) == null) {
            System.out.println("no task found!");
        } else {
            tasks.get(i-1).markAsDone();
            String message = "    ----------------------------\n"
                    +"Nice! I have marked this task as done:\n"
                    +"[X] " + tasks.get(i-1).getDescription() + "\n" + "    ----------------------------";
            System.out.println(message);
        }
    }

    /**
     * Executes when Duke receives a toDo command
     * Adds a todo task to the list of tasks
     */
    public void todo_execute() {
        try {
            Task A = new ToDo(userInput.substring(5));
            tasks.addTask(A);
            Duke.index++;
            String message = "    ----------------------------\n"+
                    "Got it, I've added this task: \n"
                    + A.toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    +"    ----------------------------";
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! The description of a todo cannot be empty!");
        }
    }

    /**
     * Executes when Duke receives an event command
     * Adds an event task to the list of tasks
     */
    public void event_execute() {
        try {
            int i = userInput.indexOf("/");
            String description = userInput.substring(6,i-1);
            //String time = userInput.substring(i+1);
            String[] split = userInput.split("at");
            String date = split[1].substring(1);
            LocalDate d = LocalDate.parse(date);
            String formattedTime = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Task A = new Event(description, formattedTime);
            tasks.addTask(A);
            Duke.index++;
            String message = "    ----------------------------\n"
                    +"Got it, I've added this task: \n"
                    + A.toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    +"    ----------------------------";
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! The description of an event cannot be empty and must contain a time!");
        }
    }

    /**
     * Executes when Duke receives a deadline command
     * Adds a deadline task to the list of tasks
     */
    public void deadline_execute() {
        try {
            int i = userInput.indexOf("/");
            String description = userInput.substring(9,i-1);
            //String time = userInput.substring(i+1);
            String[] split = userInput.split("by");
            String date = split[1].substring(1);
            LocalDate d = LocalDate.parse(date);
            String formattedTime = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Task A = new Deadlines(description, formattedTime);
            tasks.addTask(A);
            Duke.index++;
            String message = "    ----------------------------\n"
                    + "Got it, I've added this task: \n"
                    + A.toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    + "    ----------------------------";
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! The description of a deadline cannot be empty and must contain a time!");
        }
    }

    /**
     * Executes when Duke receives a delete command
     * Deletes the relevant task from the list of tasks
     */
    public void delete_execute() {
        String userIndex = userInput.substring(7);
        int i = Integer.valueOf(userIndex);
        if (tasks.get(i-1) == null) {
            System.out.println("no task found!");
        } else {
            Duke.index--;
            String message = "    ----------------------------\n"
                    + "Got it, I've removed this task: \n"
                    + tasks.get(i-1).toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    + "    ----------------------------";
            System.out.println(message);
            tasks.removeTask(i-1);
        }
    }

    /**
     * Executes when Duke receives a find command
     * Finds tasks in the list that contain the keyword specified by the user
     */
    public void find_execute() {
        String keyword = userInput.split(" ")[1];
        String taskString = "";
        boolean hasTask = false;
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription();
            if (description.contains(keyword)) {
                taskString += tasks.get(i).toString() + "\n";
                hasTask = true;
            }
        }
        if (hasTask) {
            String messageTrue = "    ----------------------------\n"
                    + "Here are your tasks: \n"
                    + taskString
                    + "    ----------------------------";
            System.out.println(messageTrue);
        } else {
            String messageFalse = "    ----------------------------\n"
                    + "There are no tasks with this keyword!\n"
                    + "    ----------------------------";
            System.out.println(messageFalse);
        }

    }


    /**
     * Generates a formatted string of tasks to be outputted to the user and to be saved into hard disk.
     * @return Formatted string of tasks
     */

    public String generateTasks() {
        String taskString = "";
        int i = 0;
        while (i < tasks.getNumberOfTasks()) {
            taskString += tasks.get(i).toString() + "\n";
            i++;
        }
        return taskString;
    }
}
