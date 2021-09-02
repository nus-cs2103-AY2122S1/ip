package main;


import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.util.ArrayList;

/**
 * Handle flow of user command
 */
public class Parser {

    public Parser() {}


    /**
     * Read the user inputs and respond accordingly
     *
     * @param ui Ui to scan in lines
     * @param nextLine the next line being read
     * @param task TaskList the task used to access ArrayList of task n done_check
     * @return the output to be given to the user
     */
    public String readCommand(Ui ui, String nextLine, TaskList task) {
        String returned = "";

        // exit if bye
        if (nextLine.equals("bye")) {
            returned = "Bye bye! Hope to see you again soon!";
            Storage.writingToFile(task.getList(),
                    task.getDoneCheck());
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
            String error = ui.emptyDescriptionException(nextLine);
            if (error.equals("")) {
                Task todo = new Todo(nextLine);
                returned = task.addTodo(nextLine, todo);
            } else {
                returned = error;
            }
        }

        // finding words in the list
        else if (nextLine.length() > 4 && nextLine.substring(0, 4).equals("find")) {
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
                            + ".[" + taskList.get(i).getType()
                            + "][" + completeList.get(i) + "]"
                            + taskList.get(i).getTask();
                    count = count + 1;
                }
            }
            if (found == 0) {
                returned = nextLine.substring(5) + " not found";
            }
        }

        //adding deadline to the list
        else if (nextLine.length() >= 8 && nextLine.substring(0, 8).equals("deadline")) {
            String error = ui.emptyDescriptionException(nextLine);
            if (error.equals("")) {
                Task deadline = new Deadline(nextLine);
                returned = task.addDeadline(nextLine, deadline);
            } else {
                returned = error;
            }
        }

        //adding event to the list
        else if (nextLine.length() >= 5 && nextLine.substring(0, 5).equals("event")) {
            String error = ui.emptyDescriptionException(nextLine);
            if (error.equals("")) {
                Task event = new Event(nextLine);
                returned = task.addEvent(nextLine, event);
            } else {
                returned = error;
            }
        }

        //Random Description error
        else {
            returned = ui.randomDescriptionException(nextLine);
        }

        return returned;
    }

}
