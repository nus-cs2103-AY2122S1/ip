package duke.ui;

import java.time.LocalDate;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.utils.Constants;

/**
 * Deals with all the interactions with the user. Most of the methods prefixed with 'display' return
 * the output obtained from their respective Command functions as strings
 */
public class Ui {

    public Ui() {
    }

    public String displayEdit(Task oTask, Task eTask) {
        return "This task has been successfully edited:\n" +
                "Here is the previous version\n" + oTask.toString() + "\n\n" +
                "Here is the edited version\n"+ eTask.toString();
    }

    public String displayBye() {
        return Constants.BYE;
    }

    public String displayList(TaskList list) {
        return "Here are the tasks in your list:" + list.print();
    }

    public String displayDone(String taskDetails) {
        return "Nice! I've marked this task as done:\n" + taskDetails;
    }

    public String displayDelete(String taskDetails, TaskList list) {
        return "Noted. I've removed this task:\n"
                + taskDetails + "\n" + list.printRemainingTasks();
    }

    public String displayTasksOn(LocalDate date, TaskList list) {
        if (!list.isEmpty()) {
            return "Here are the tasks due on " + date.toString() + list.print();
        } else {
            return "No tasks are due on " + date + "!";
        }
    }

    public String displayFind(String word, TaskList list) {
        if (!list.isEmpty()) {
            return "Here are the matching tasks in your list:" + list.print();
        } else {
            return String.format("Sorry there are no tasks containing '%s' in the list\n", word);
        }
    }

    public String displayLoadingError(Exception ex) {
        return ex.toString();
    }

    public String displayError(String message) {
        return message;
    }

    public String displayAdd(Task task, TaskList taskList) {
       return "Got it. I've added this task:\n" + task.toString() + "\n" + taskList.printRemainingTasks();
    }
}
