package duke;

import duke.task.Task;

public class Ui {
    // welcome
    public void welcome() {
//                String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    // bye
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // show loading error
    public void showLoadingError() {
        System.out.println("idk man im sorry");
    }

    // add task msg
    public void showTaskAdded(Task task, int size) {
        String t = size == 1 ? "task" : "tasks";
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d %s in the list.\n", task, size, t);
    }

    // complete task msg
    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    // delete task msg
    public void showTaskDeleted(Task task, int size) {
        String t = size == 1 ? "task" : "tasks";
        System.out.printf("Noted.I've removed this task:\n%s\nNow you have %d %s in the list\n", task, size, t);
    }

    // list task
    public void showTaskList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.getSize(); i++) {
            Task s = list.getTask(i);
            System.out.printf("%d.%s%n", i + 1, s);
        }
    }
}
