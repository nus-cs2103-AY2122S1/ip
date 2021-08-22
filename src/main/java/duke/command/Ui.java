package duke.command;

public class Ui {
    private static String DOTTED_LINES = "-".repeat(80);
    public static void showWelcomeMessage() {
        System.out.println(DOTTED_LINES);
        System.out.println("Hello I'm LOTTERY-A");
        System.out.println("Also known as the List Of Tasks That Eventually Require Your Attention");
        System.out.println("What can I do for you?");
        System.out.println(DOTTED_LINES);
    }

    public static void showLoadingError() {
        System.out.println(DOTTED_LINES);
        System.out.println("File cannot be created");
        System.out.println(DOTTED_LINES);
    }

    public static void showSavingError() {
        System.out.println(DOTTED_LINES);
        System.out.println("File cannot be saved");
        System.out.println(DOTTED_LINES);
    }

    public static void showByeMessage() {
        System.out.println(DOTTED_LINES);
        System.out.println("Bye. Don't forget, these tasks will still require your attention when you return!");
        System.out.println(DOTTED_LINES);
    }

    public static void showAddTaskMessage(String taskDesc, int size) {
        System.out.println(DOTTED_LINES);
        System.out.println("Got it. I've added this task:\n" + taskDesc);
        System.out.println("Now you have " + size + " tasks in the list");
        System.out.println(DOTTED_LINES);
    }

    public static void showDeleteTaskMessage(String taskDesc, int size) {
        System.out.println(DOTTED_LINES);
        System.out.println("Noted. I've removed this task:\n" + taskDesc);
        System.out.println("Now you have " + size + " tasks in the list");
        System.out.println(DOTTED_LINES);
    }

    public static void showMarkAsDoneMessage(String taskDesc) {
        System.out.println(DOTTED_LINES);
        System.out.println("Nice! I've marked this task as done:\n" + taskDesc);
        System.out.println(DOTTED_LINES);
    }

}
