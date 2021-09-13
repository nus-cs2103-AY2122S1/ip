import java.util.Scanner;
import Storage.*;
import Task.TaskList;
import Task.Task;
import Task.DeadlineException;
import Task.TodoException;
import Task.EventsException;

public class Duke{
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

    public String getResponse(String input) {
        return userInterface.choiceOfAction(input);
    }

    /**
     * Function returns the logo of the program.
     * @return string containing the logo.
     */
    public final static String greet() {
        return Ui.greet();
    }
}


//deals with interactions with the user
class Ui {
    private final Storage store = new Storage();
    private TaskList taskList = new TaskList();

    /**
     * Function returns the logo of the program.
     * @return string containing the logo.
     */
    public final static String greet() {
        String logo = "__________________________________\n"
                +" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                +"__________________________________";

        return logo;
    }

    /**
     * generate the end message.
     */
    public String bye() {
        store.saveListInFile("src/main/java/Duke.txt", taskList);
        return "Bye. Hope to see you again soon!";
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
            if (choiceOfAction(input).equals("Bye. Hope to see you again soon!")) {
                return false;
            }
        }
        return true;
    }

    /**
     * To decide base on the input what is the next action.
     * The decision on whether to show list, set existing task to
     * done, or to create and record new task.
     * @param input by the user
     * @return String message to be senr
     */
    public String choiceOfAction(String input) {
        if (input.equals("bye")) {
            return bye();
        } else if (input.equals("list")) {
            return taskList.toString();
        } else {
            String[] newInput = input.split(" ");
            String instruction = newInput[0];
            if (instruction.equals("done")) {
                return doneAction(newInput);
            } else if (instruction.equals("delete")) {
                return deleteAction(newInput);
            }else if (instruction.equals("find")) {
                return findAction(newInput);
            } else {
                try {
                    if(store.isTaskDuplicate(input, taskList)) {
                        return "Input task has already been created!";
                    } else {
                        Task newTask = store.createTask(input);
                        return taskList.addTask(newTask);
                    }
                } catch (TodoException | DeadlineException | EventsException tx) {
                    return tx.getLocalizedMessage();
                } catch (Exception e) {
                    return e.getLocalizedMessage();
                }
            }
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
    private String doneAction(String[] newInput) {
        if (newInput.length < 2) {
            return "Please input the index of the Task to be done!";
        } else {
            int index = Integer.parseInt(newInput[1]);
            if (index <= taskList.getTaskList().size() && index > 0) {
                taskList.getTaskList().get(index - 1).done();
                return "Nice! I've marked this task as done: "
                        + "    " + this.taskList.getTaskList().get(index - 1) + "\n";
            } else {
                return "invalid index";
            }
        }
    }

    /**
     * Function perform the action of deleting a task when requested. It also catches
     * the Exception throw.
     * @param newInput is the String Array containing the index of the Task.
     */
    private String deleteAction(String[] newInput) {
        try {
            return taskList.deleteTask(newInput);
        }catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    /**
     * Action find keywords in main TaskList the fits the request.
     * @param newInput is a String Array that contains the keyword needed for the search.
     * @return TaskList Object containing the searched Tasks.
     */
    private String findAction(String[] newInput) {
        StringBuilder resultList = new StringBuilder("Here are the matching tasks in your list:\n");
        if (newInput.length >= 2) {
            String keyword = newInput[1];
            for (Task curTask : this.taskList.getTaskList()) {
                for (String information : curTask.getFinalAction().split(" ")) {
                    if (information.equals(keyword)) {
                        resultList.append(curTask)
                                .append("\n");
                    }
                }
            }
        }
        return resultList.toString();
    }
}
