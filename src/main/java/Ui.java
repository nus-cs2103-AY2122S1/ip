public class Ui {

    public static void Logo() {
        Ui.Border();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void Border() {
        System.out.println("──────────────────────────────────────────");
    }

    public static void WelcomeMessage() {
        System.out.println("What can i do for you?");
        Ui.Border();
    }

    public static void UnknownCommand() {
        Ui.Border();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        Ui.Border();
    }

    public static void Goodbye() {
        Ui.Border();
        System.out.println("Bye, hope to see you again soon!");
    }


}
