package duke.ui;

import duke.commands.Task;
import duke.data.TaskList;

public class Ui {
    TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }
    public void greeting() {
        System.out.println("Aloha! I'm Sophia.\nWhat can I do for you?");
    }
    public void farewellMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void addTaskMsg(Task text) {
        System.out.println("Sure thing! I've added this task:\n  " + text);
    }

    public void completeTaskMsg(Task text) {
        System.out.println("Nice! I've marked this task as done:\n  " + text);
    }

    public void deleteTaskMsg(Task text) {
        System.out.println("Noted! I've removed this task:\n  " + text);
    }

    public void listCountMsg() {
        int len = this.tasks.count();
        if (len == 1) {
            System.out.println(String.format("Now you have %d task in the list.", len));
        } else {
            System.out.println(String.format("Now you have %d tasks in the list.", len));
        }
    }
}
