package duke;

import duke.task.Task;
import exception.DukeException;

public class Ui {
    public void greet() {
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?\n");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again!");
    }

    public void showErrorMessage(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void showNoTaskMessage() {
        System.out.println("There are no tasks!");
    }

    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.printf("  %d.%s%n", i, taskList.get(i));
        }
    }

    public void showDeleteMessage(Task deletedTask, int taskListSize) {
        System.out.printf("Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.%n",
                deletedTask, taskListSize);
    }

    public void showAddTaskMessage(Task newTask, int taskListSize) {
        System.out.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.%n",
                newTask, taskListSize);
    }

    public void showDoneMessage(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:\n  " + doneTask);
    }
}
