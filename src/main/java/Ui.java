public class Ui {
    public static final String DIVIDER = "=================================";

    public static void showWelcomeMessage() {
        System.out.println("Hello from Duke!");
        System.out.println("What do you need to do today?");
        System.out.println(DIVIDER);
    }

    public static void showExitMessage() {
        System.out.println(DIVIDER);
        System.out.println("See you! Have a nice day!");
        System.out.println(DIVIDER);
    }

    public static void showTaskList(TaskList list) {
        System.out.println(DIVIDER);
        System.out.println(list.toDisplay());
        System.out.println(DIVIDER);

    }

    public static void showAddedTask(TaskList list) {
        System.out.println(DIVIDER);
        System.out.printf("added: %s\n", list.getTask(list.count()));
    }

    public static void showDeletedTask(Task task) {
        System.out.println(DIVIDER);
        System.out.println("Got it! I've removed this task:");
        System.out.println(task);
    }

    public static void showDoneTask(Task task) {
        System.out.println(DIVIDER);
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(task);
        System.out.println(DIVIDER);
    }

    public static void showTaskCount(TaskList tasks) {
        System.out.printf("Now you have %d tasks in your list.%n%n", tasks.count());
        System.out.println(DIVIDER);

    }

    public static void showErrorMessage(DukeException e) {
        System.out.println(DIVIDER);
        System.out.println(e.getMessage());
        System.out.println(DIVIDER);
    }


}
