public class TextUi {
    public static final String DIVIDER = "==========================================";

    public static void showWelcomeMessage() {
        System.out.println("Hi, Nat. Type out your task right away! :D");
        System.out.println(DIVIDER);
    }

    public static void showTaskNumbered(int i, Task t) {
        System.out.printf("%s. %s\n", i + 1, t);
    }

    public static void showTaskAdded(TaskList tl) {
        System.out.println("added: " + tl.getTaskList().get(tl.getLength() - 1));
    }

    public static void showTaskRemoved(Task t) {
        System.out.printf("Noted. I've removed this task:\n%s\n", t);
    }

    public static void showTaskDone(Task t) {
        System.out.printf("%s\nThe task is marked as done! Good job :D\n", t);
    }

    public static void showUpdatedNumberOfTasks(TaskList tl) {
        int len = tl.getLength();
        if (len == 1) {
            System.out.printf("Now you have %d task in the list.\n", tl.getLength());
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", tl.getLength());
        }
    }

    public static void showErrorMessage(String s) {
        System.out.println(s);
    }

    public static void showGoodbyeMessage() {
        System.out.println("See you! :)");
    }

}
