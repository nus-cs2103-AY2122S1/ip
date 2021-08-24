public class Ui {

    public static String breakline = "____________________________________________________________";


    /**
     * Provides the initialization message for the Duke Program
     */
    public static void start() {
        String greetingMsg = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetingMsg);
        System.out.println(breakline);
    }

    /**
     * Provides the exit message for the Duke Program
     */
    public static void exit() {
        String byeMsg = "Bye. Hope to see you again soon!";
        System.out.println(byeMsg);
        System.out.println(breakline);
    }
}
