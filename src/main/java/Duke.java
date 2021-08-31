import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import Storage.*;
import Task.TaskList;
import Task.Task;
import Task.DeadlineException;
import Task.TodoException;
import Task.EventsException;

public class Duke {
    private final Ui userInterface = new Ui();

    public static void main(String[] args) {
        Duke user = new Duke();
        boolean end = true;
        user.userInterface.greet();
        user.userInterface.getDataInputList();
        while (end) {
            end = user.userInterface.echo();
            System.out.println("__________________________________");
        }
    }
}


//deals with interactions with the user
class Ui {
    private final Storage store = new Storage();
    private TaskList taskList = new TaskList();
    /**
     * Generate the initiate message.
     */
    public void greet() {
        System.out.println("__________________________________");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________");
    }

    /**
     * generate the end message.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        store.saveListInFile("Duke.txt", taskList);
    }
    /**
     * The original method to simply generate an echo message.
     * Now only meant to decide if the user has decided to end
     * the program.
     * @return boolean
     */
    public boolean echo() {
        Scanner user = new Scanner(System.in);
        String input = user.nextLine();
        return choiceOfAction(input);
    }

    /**
     * To decide base on the input what is the next action.
     * The decision on whether to show list, set existing task to
     * done, or to create and record new task.
     * @param input by the user
     * @return boolean
     */
    public boolean choiceOfAction(String input) {
        if (input.equals("bye")) {
            bye();
            return false;
        } else if (input.equals("list")) {
            taskList.showList();
            return true;
        } else {
            String[] newInput = input.split(" ");
            String instruction = newInput[0];
            if (instruction.equals("done")) {
                if (newInput.length < 2) {
                    System.out.println("Please input the index of the Task to be done!");
                } else {
                    int index = Integer.parseInt(newInput[1]);
                    if (index <= taskList.getTaskList().size() && index > 0) {
                        taskList.getTaskList().get(index - 1).done();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("    " + this.taskList.getTaskList().get(index - 1));
                    } else {
                        System.out.println("invalid index");
                    }
                }
            } else if (instruction.equals("delete")) {
                try {
                    taskList.deleteTask(newInput);
                }catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }else {
                try {
                    Task newTask = store.createTask(input);
                    taskList.addTask(newTask);
                } catch (TodoException tx) {
                    System.out.println(tx.getLocalizedMessage());
                } catch (DeadlineException dx) {
                    System.out.println(dx.getLocalizedMessage());
                } catch (EventsException ex) {
                    System.out.println(ex.getLocalizedMessage());
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            return true;
        }
    }

    /**
     * Encapsulation up the class structure.
     */
    public void getDataInputList() {
        store.getDataInputList(this.taskList);
    }
}
///////////////////////////////////////////////////////////////////


