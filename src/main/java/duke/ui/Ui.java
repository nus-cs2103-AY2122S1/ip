package duke.ui;

import duke.commands.Command.CommandType;
import duke.errors.DukeError;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static String pluralOrNo(int cap) {
        return cap <= 1 ? "" : "s";
    }

    public void showWelcome() {
        System.out.println("Henlo, Duke here! How can I be of assistance?");
    }

    public void showError(DukeError e) {
        System.out.println(e);
    }

    public void showAddTaskMessage(Task task, TaskList taskList) {
        int size = taskList.getCapacity();
        System.out.printf("Got it. I've added this task:\n    %s\n" +
                "Now you have %d task%s in the list\n", task , size, pluralOrNo(size));
    }

    public void showEditTaskMessage(Task task, CommandType commandType, TaskList taskList) {
        switch(commandType) {
        case DELETE:
            int size = taskList.getCapacity();
            System.out.printf("Noted. Ive removed this task:\n    %s\n" +
                    "Now you have %d task%s in the list\n", task, size, pluralOrNo(size));
            break;
        case DONE:
            System.out.printf("Nice! I've marked this task as done:\n    %s\n", task);
            break;
        case UNDO:
            System.out.printf("I've marked this task as undone:\n    %s\n", task);
        default:
            break;
        }
    }

    public void showTaskList(TaskList taskList) {
        ArrayList<Task> arrayList = taskList.getArrayList();
        int currentCapacity = arrayList.size();
        if (currentCapacity > 0) {
            String toPrint = "Here are the tasks in your list:\n";
            for (int i = 0; i < currentCapacity; i++) {
                Task task = arrayList.get(i);
                toPrint += String.format("%d. %s\n", i + 1, task);
            }
            System.out.print(toPrint);
        } else {
            System.out.println("You have no tasks!");
        }
    }

    public void showExitMessage() {
        System.out.println("Good bye!");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


}
