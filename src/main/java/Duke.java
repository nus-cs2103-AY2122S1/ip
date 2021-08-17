import java.util.Scanner;

public class Duke {
    final private String name;
    private Command commands;

    /**
     * Constructor for Duke.
     *
     * @param name name of bot.
     */
    public Duke(String name) {
        this.name = name;
        commands = new Command(name);
    }

    /**
     * Returns formatted output string for bot message.
     *
     * @param input An input string.
     * @return a formatted string for bot message.
     */
    public String format(String input) {
        return String.format("\t--------------------- " +
                "\n\t%1$s\n\t--------------------- ", input);
    }

    /**
     * Starts the bot.
     */
    public void start() {
        System.out.println(format(commands.greeting()));

        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(!((input = scanner.nextLine().toLowerCase()).equals("bye"))) {
            if (input.equals("list")) {
                System.out.println(format(commands.list()));
            } else {
                System.out.println(format(commands.add(input)));
            }
        }
        scanner.close();
        System.out.println(format(commands.end()));
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke bot = new Duke("halp");
        bot.start();
    }
}