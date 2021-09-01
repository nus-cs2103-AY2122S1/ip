package Duke;


import java.util.Scanner;
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
        store.saveListInFile("src/main/java/Duke/Duke.txt", taskList);
    }
    /**
     * The original method to simply generate an echo message.
     * Now only meant to decide if the user has decided to end
     * the program.
     * @return boolean
     */
    public boolean echo() {
        Scanner user = new Scanner(System.in);
        while(user.hasNextLine()) {
            String input = user.nextLine();
            return choiceOfAction(input);
        }
        return false;
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
                doneAction(newInput);
            } else if (instruction.equals("delete")) {
                deleteAction(newInput);
            }else if (instruction.equals("find")) {
                findAction(newInput);
            } else {
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

    /**
     * Function performs task when Done instruction is provided.
     * @param newInput is a String Array containing the Done and index of the Task.
     */
    private void doneAction(String[] newInput) {
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
    }

    /**
     * Function perform the action of deleting a task when requested. It also catches
     * the Exception throw.
     * @param newInput is the String Array containing the index of the Task.
     */
    private void deleteAction(String[] newInput) {
        try {
            taskList.deleteTask(newInput);
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Action find keywords in main TaskList the fits the request.
     * @param newInput is a String Array that contains the keyword needed for the search.
     * @return TaskList Object containing the searched Tasks.
     */
    private void findAction(String[] newInput) {
        System.out.println("Here are the matching tasks in your list:");
        if (newInput.length >= 2) {
            String keyword = newInput[1];
            //TaskList tempList = new TaskList();
            for (Task curTask : this.taskList.getTaskList()) {
                for (String information : curTask.getFinalAction().split(" ")) {
                    if (information.equals(keyword)) {
                        //tempList.addTaskWithoutMessage(curTask);
                        System.out.println(curTask);
                    }
                }
            }
            //tempList.showList();
        }
    }
}
