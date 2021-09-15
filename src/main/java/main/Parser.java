package main;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Handle flow of user command.
 */
public class Parser {

    public Parser() {}

    /**
     * Read the user inputs and respond accordingly.
     *
     * @param ui Ui to scan in lines.
     * @param nextLine the next line being read.
     * @param task TaskList the task used to access ArrayList of task n done_check.
     * @return the output to be given to the user.
     */
    public String readCommand(Ui ui, String nextLine, TaskList task) {
        String returned = "";

        // exit if bye
        if (nextLine.equals("bye")) {
           returned = bye(task);
        }

        //random expression error
        else if (nextLine.length() < 3) {
            returned = ui.randomDescriptionException(nextLine);
        }

        // outputting the list
        else if (nextLine.equals("list")) {
            returned = task.outputList();
        }

        //marking task as done
        else if (nextLine.length() > 4 && nextLine.substring(0, 4).equals("done")) {
            returned = task.markTaskAsDone(nextLine);
        }

        //deleting task from the list
        else if (nextLine.length() > 6 && nextLine.substring(0, 6).equals("delete")) {
            returned = task.deleteTask(nextLine);
        }

        // adding todo to the list
        else if (nextLine.length() >= 4 && nextLine.substring(0, 4).equals("todo")) {
            returned = addTodo(ui, nextLine, task);
        }

        // finding words in the list
        // Solution below adapted from https://www.tutorialspoint.com/javaexamples/string_search.htm
        else if (nextLine.length() > 4 && nextLine.substring(0, 4).equals("find")) {
            returned = find(task, nextLine);
        }

        //adding deadline to the list
        else if (nextLine.length() >= 8 && nextLine.substring(0, 8).equals("deadline")) {
            returned = addDeadline(ui, nextLine, task);
        }

        //adding event to the list
        else if (nextLine.length() >= 5 && nextLine.substring(0, 5).equals("event")) {
            returned = addEvent(ui, nextLine, task);
        }

        //Random Description error
        else {
            returned = ui.randomDescriptionException(nextLine);
        }

        return returned;
    }

    /**
     * Execute the bye command and save files
     *
     * @param task TaskList the task used to access ArrayList of task n done_check.
     * @return the output to be given to the user.
     */
    public String bye(TaskList task) {
        String returned = "";
        returned = "Bye bye! Hope to see you again soon!";
        Storage.writingToFile(task.getList(),
                task.getDoneCheck());
        return returned;
    }

    /**
     * Add todo task
     *
     * @param task TaskList the task used to access ArrayList of task n done_check.
     * @param ui Ui to scan in lines.
     * @param nextLine the next line being read.
     * @return the output to be given to the user.
     */
    public String addTodo(Ui ui, String nextLine, TaskList task) {
        String returned = "";
        String error = ui.emptyDescriptionException(nextLine);
        if (error.equals("")) {
            Task todo = new Todo(nextLine);
            returned = task.addTodo(todo);
        } else {
            returned = error;
        }
        return returned;
    }

    /**
     * Add deadline task
     *
     * @param task TaskList the task used to access ArrayList of task n done_check.
     * @param ui Ui to scan in lines.
     * @param nextLine the next line being read.
     * @return the output to be given to the user.
     */
    public String addDeadline(Ui ui, String nextLine, TaskList task) {
        String returned = "";
        String error = ui.emptyDescriptionException(nextLine);
        if (error.equals("")) {
            Task deadline = new Deadline(nextLine);
            returned = task.addDeadline(deadline);
        } else {
            returned = error;
        }
        return returned;
    }

    /**
     * Add event task
     *
     * @param task TaskList the task used to access ArrayList of task n done_check.
     * @param ui Ui to scan in lines.
     * @param nextLine the next line being read.
     * @return the output to be given to the user.
     */
    public String addEvent(Ui ui, String nextLine, TaskList task) {
        String returned = "";
        String error = ui.emptyDescriptionException(nextLine);
        if (error.equals("")) {
            Task event = new Event(nextLine);
            returned = task.addEvent(event);
        } else {
            returned = error;
        }
        return returned;
    }

    /**
     * Find the tasks that match user input
     *
     * @param task TaskList the task used to access ArrayList of task n done_check.
     * @param nextLine the next line being read.
     * @return the list of tasks that match the input
     */
    public String find(TaskList task, String nextLine) {
        String returned = "";
        returned = ("Found these matching tasks!") + "\n";
        ArrayList<Task> taskList = task.getList();
        ArrayList<String> completeList = task.getDoneCheck();
        int count = 1;
        int found = 0;

        for (int i = 0; i < taskList.size(); i++) {
            String current_line = taskList.get(i).getTask();
            int intIndex = current_line.indexOf(nextLine.substring(5));

            if (intIndex == -1) {
            } else {
                found = 1;
                returned = returned + count
                        + ". [" + taskList.get(i).getType()
                        + "][" + completeList.get(i) + "]"
                        + taskList.get(i).getTask() + "\n";
                count = count + 1;
            }
        }

        if (found == 0) {
            returned = nextLine.substring(5) + " not found";
        }
        return returned;
    }


}
