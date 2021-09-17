package duke;

public class Ui {
    public static void printAll(String[] results) {
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
    }

    public static void bye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public static void showLoadingError() {
        System.out.println("\tError loading! Try restarting the program!");
    }
    public static void welcome() {
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
    }
}
