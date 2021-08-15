/**
 * collections of methods that the bot can call
 */
public class Command {

    /**
     * called when the user first start the programme
     */
    public static void greet() {
        String logo = " ____ \n"
                + "|  _ \\ _   _\n"
                + "| | | | | | | \n"
                + "| |_| | |_| | \n"
                + "|____/ \\__,_|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Du, your personal assistant chatbot!:)\n"
                + "Please input the tasks you want to add to your list");
    }

    /**
     * repeat the command
     * used in level 1
     * @param command command passed to bot
     */
    public static void echo(String command) {
        System.out.println("____________________________________________________________\n"
                + command + "\n"
                + "____________________________________________________________");
    }

    /**
     * end programme when user inputs "bye"
     */
    public static void close_programme() {
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to not see you again:P" + "\n"
                + "____________________________________________________________");
    }

}

