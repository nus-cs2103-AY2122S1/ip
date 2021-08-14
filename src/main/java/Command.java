public class Command {

    public static void greet() {
        String logo = " ____ \n"
                + "|  _ \\ _   _\n"
                + "| | | | | | | \n"
                + "| |_| | |_| | \n"
                + "|____/ \\__,_|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Du, your personal assistant chatbot!:)\n"
                + "What can I do for you?");
    }

    public static void echo(String command) {
        System.out.println("____________________________________________________________\n"
                + command + "\n"
                + "____________________________________________________________");
    }

    public static void close_programme() {
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!" + "\n"
                + "____________________________________________________________");
    }

}

