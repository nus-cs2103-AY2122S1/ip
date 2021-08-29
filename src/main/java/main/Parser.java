package main;


import task.deadline.Deadline;
import task.event.Event;
import task.Task;
import task.Todo.Todo;
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
     */
    public void readCommand(Ui ui, String nextLine, TaskList task) {
        int firstTime = 0;


        while (true) {
            if (firstTime == 1) {
                nextLine = ui.scanNextLine();
            }

            // exit if bye
            if (nextLine.equals("bye")) {
                this.bye();
                break;
            }

            // outputting the list
            else if (nextLine.equals("list")) {
                task.outputList();
                firstTime = 1;
            }

            //marking task as done
            else if (nextLine.length() > 4 && nextLine.substring(0, 4).equals("done")) {
                task.markTaskAsDone(nextLine);
                firstTime = 1;
            }

            //deleting task from the list
            else if (nextLine.length() > 6 && nextLine.substring(0, 6).equals("delete")) {
                task.deleteTask(nextLine);
                firstTime = 1;
            }

            // adding todo to the list
            else if (nextLine.length() > 4 && nextLine.substring(0, 4).equals("todo")) {
                Task todo = new Todo(nextLine);
                task.addTodo(nextLine, todo);
                firstTime = 1;
            }

            // finding words in the list
            else if (nextLine.substring(0, 4).equals("find")) {
                System.out.println("Found these matching tasks!");
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
                        System.out.println(count
                                + ".["
                                    + taskList.get(i).getType()
                                        + "]["
                                            + completeList.get(i)
                                                + "]"
                                                    + taskList.get(i).getTask());
                        count = count + 1;
                    }
                }
                if (found == 0) {
                    System.out.println(nextLine.substring(5) + " not found");
                }
            }

            //adding deadline to the list
            else if (nextLine.length() > 8 && nextLine.substring(0, 8).equals("deadline")) {
                Task deadline = new Deadline(nextLine);
                task.addDeadline(nextLine, deadline);
                firstTime = 1;
            }

            //adding event to the list
            else if (nextLine.length() > 5 && nextLine.substring(0, 5).equals("event")) {
                Task event = new Event(nextLine);
                task.addEvent(nextLine, event);
                firstTime = 1;
            }

            else {
                firstTime = 1;
                ui.randomDescriptionException(nextLine);
            }
        }
    }

    /**
     * Prints bye statement when user quit the bot
     */
    public void bye() {
        System.out.println("Bye bye! Hope to see you again soon!");
    }

}
