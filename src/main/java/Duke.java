import java.util.Scanner;

public class Duke {
    private Command commands;

    /**
     * Constructor for Duke.
     *
     * @param name name of bot.
     */
    public Duke(String name) {
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
                "\n\t%1$s--------------------- ", input);
    }

    /**
     * Starts the bot.
     */
    public void start() {
        System.out.println(format(commands.greeting()));

        Scanner scanner = new Scanner(System.in);
        String input;
        while(!((input = scanner.next().toLowerCase()).equals("bye"))) {
            Keyword keyword = null;
            try {
                keyword = Keyword.checkKeyword(input);
            } catch (DukeException e) {
                System.out.println(format(e.toString()));
            }
            if (keyword != null) {
                switch (keyword) {
                    case LIST:
                        System.out.println(format(commands.list()));
                        break;
                    case TODO:
                        try {
                            System.out.println(format(commands.todo(scanner.nextLine().trim())));
                        } catch (DukeException e) {
                            System.out.println(format(e.toString()));
                        }
                        break;
                    case EVENT:
                        String[] text = scanner.nextLine().split("/at ");
                        try {
                            System.out.println(format(commands.event(text[0].trim(), text[1])));
                        } catch (DukeException e) {
                            System.out.println(format(e.toString()));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(format("OOPS!!! No date for event! " +
                                    "Use format of event description /at date \n\t"));
                        }
                        break;
                    case DEADLINE:
                        String[] txt = scanner.nextLine().split("/by ");
                        try {
                            System.out.println(format(commands.deadline(txt[0].trim(), txt[1])));
                        } catch (DukeException e) {
                            System.out.println(format(e.toString()));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(format("OOPS!!! No date for deadline! " +
                                    "Use format of deadline description /by date \n\t"));
                        }
                        break;
                    case DONE:
                        try {
                            System.out.println(format(commands.done((scanner.nextInt()) - 1)));
                        } catch (DukeException e) {
                            System.out.println(format(e.toString()));
                        }
                        break;
                    case DELETE:
                        try {
                            System.out.println(format(commands.delete((scanner.nextInt()) - 1)));
                        } catch (DukeException e) {
                            System.out.println(format(e.toString()));
                        }
                        break;
                }
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