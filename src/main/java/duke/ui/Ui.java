package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public void formatMessages(String message) {
        String output = "     --------------------------------------\n"
                + "      " + message + "\n"
                + "\n     --------------------------------------";
        System.out.println(output);
    }

    public void introduceDuke() {
        String introduction = "Hello, I am Ah Seng, the foodcourt uncle. Come chitchat with me.";
        formatMessages(introduction);
    }

    public void showLoadingError() {
        String loadingError = "No previous .txt File detected. Creating a new one for you.";
        formatMessages(loadingError);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showExitMessage() {
        String endingMessage = "Ah ok bye. Next time treat uncle kopi ok?";
        formatMessages(endingMessage);
    }

    public void showNonsenseMessage(String input) {
        String endingMessage = input + "? What talking you...?";
        formatMessages(endingMessage);
    }

    public void showError(String errorMessage) {
        formatMessages(errorMessage);
    }

    public void showTaskAdded(Task task, TaskList tasklist) {
        String msg = "OK uncle added a task for you liao.\n" + "      " + task.toString()
                + "\n      You now have " + tasklist.getSize() + " tasks remaining.";
        formatMessages(msg);
    }

    public void showTaskDeleted(Task task, TaskList taskList) {
        String msg = "OK, uncle removed a task for you liao.\n" + "      " + task.toString()
                + "\n      You now have " + taskList.getSize() + " tasks remaining.";
        formatMessages(msg);
    }

    public void showTaskCompleted(Task task, TaskList taskList) {
        String msg = "Sweechai Butterfly la, you finished this task:\n" + "      " + task.toString()
                + "\n      You now have " + taskList.getSize() + " tasks remaining.";
        formatMessages(msg);
    }

    public void showTasklist(TaskList tasklist) {

        StringBuilder output = new StringBuilder("Wahseh, these are all the tasks you haven't do ley!\n      ");
        int i = 1;
        for (Task task : tasklist.getTaskList()) {
            if (i != 1) {
                output.append("      ");
            }
            output.append(i).append(". ").append(task.toString()).append("\n");
            i += 1;
        }

        formatMessages(output.toString());
    }

    public void showTasklistDeleted() {
        String msg = "Ok la, Uncle get rid of all your tasks already.";
        formatMessages(msg);
    }

    public void showTaskSnoozed(Task task) {
        String msg = "Uncle help you extend one week liao. Don't forget to do it ah!\n" + "      " + task.toString();
        formatMessages(msg);
    }

    public void showSearches(ArrayList<Task> searches) {

        StringBuilder output = new StringBuilder("Ah boy, here is the list you wanted.\n      ");
        int i = 1;
        for (Task task : searches) {
            if (i != 1) {
                output.append("      ");
            }
            output.append(i).append(". ").append(task.toString()).append("\n");
            i += 1;
        }

        formatMessages(output.toString());
    }
}
