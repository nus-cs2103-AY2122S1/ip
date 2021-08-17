/**
 * The Duke chatbot app
 */

import java.util.Scanner;

public class Duke {
    /**
     * Global Variables
     */
    public static final String SPACE = "    ";
    public static final String LOGO =
            SPACE + "██████   ██████  ██████   █████  ████████ \n" +
            SPACE + "██   ██ ██    ██ ██   ██ ██   ██    ██    \n" +
            SPACE + "██████  ██    ██ ██████  ███████    ██    \n" +
            SPACE + "██   ██ ██    ██ ██   ██ ██   ██    ██    \n" +
            SPACE + "██████   ██████  ██   ██ ██   ██    ██";
    public static final String BOT_LINE = "============================================================";
    public static final String USER_LINE = "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _";

    /**
     * The main function of Borat
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        /**
         * The Scanner to scan user input
         */
        Scanner sc = new Scanner(System.in);

        /**
         * The list of the Borat app
         */
        Items list = new Items();

        /**
         * Borat's Greetings
         */
        System.out.println(SPACE + BOT_LINE);
        System.out.println(LOGO);
        System.out.println(SPACE + BOT_LINE);
        System.out.println(SPACE + "Jak się masz? My name-a Borat. I like you.");
        System.out.println(SPACE + "What I do for you?");
        System.out.println(SPACE + BOT_LINE);

        String rawInput = getInput(sc);
        String output = "";
        label:
        while (true) {

            try {
                String[] input = parseInput(rawInput);
                String command = input[0];

                switch (command) {
                    case "bye":
                        // Quit program
                        break label;
                    case "list":
                        // Displays the tasks in the list
                        output = list.toString();

                        break;
                    case "done":
                        // Marks a task as done.
                        output = list.markDone(Integer.parseInt(input[1]));

                        break;
                    case "todo":
                        // Add a todo task in the list.
                        output = list.addItem(new Todo(input[1]));

                        break;
                    case "deadline":
                        // Add a deadline task in the list.
                        output = list.addItem(new Deadline(input[1], input[2]));

                        break;
                    case "event":
                        // Add an event task in the list.
                        output = list.addItem(new Event(input[1], input[2]));

                        break;
                    case "delete":
                        // Delete an event from the list.
                        output = list.removeItem(Integer.parseInt(input[1]));
                }
                showMessage(output);
            } catch (DukeException e) {
                showMessage(e.getMessage());
            }

            rawInput = getInput(sc);
        }

        /**
         * Good bye message from Borat
         */
        showMessage("Bye. Have a good time!");
    }

    /**
     * Parse the user input string into a meaningful String array.
     * @param rawInput The user input
     * @return Parsed input ready to be processed
     * @throws DukeException An invalid input will produce this exception
     */
    private static String[] parseInput(String rawInput) throws DukeException {
        String[] input = rawInput.split("\\s+");
        if (input.length < 1) {
            throw new DukeException("What you mean?");
        }
        switch (input[0]) {
            case "list":
                if (input.length != 1) {
                    throw new DukeException("Do you mean `list`?");
                }
                return new String[] {input[0]};

            case "done":
                if (input.length != 2) {
                    throw new DukeException("'done' command needs exactly 1 argument. (example: 'done 5')");
                }
                try {
                    // Check if the argument is a number
                    Integer.parseInt(input[1]);
                } catch (Exception e) {
                    throw new DukeException("'done' command needs an integer as a number. (example: 'done 5')");
                }
                return new String[] {input[0], input[1]};

            case "todo":
                if (input.length < 2) {
                    throw new DukeException("'todo' command needs a description. (example: 'todo watch Borat')");
                }
                String description = combineStringArray(input, 1, input.length);
                return new String[] {input[0], description};

            case "deadline":
                if (input.length < 2) {
                    throw new DukeException("'deadline' command needs a description. (example: 'deadline watch Borat /by tonight')");
                }
                String arguments = combineStringArray(input, 1, input.length);
                String[] divided = arguments.split(" /by ");
                if (divided.length < 2) {
                    throw new DukeException("'deadline' command needs a deadline. use '/by'. (example: 'deadline watch Borat /by tonight')");
                } else if (divided.length > 2) {
                    throw new DukeException("'deadline' command has too many deadline. use 1 '/by'. (example: 'deadline watch Borat /by tonight')");
                }
                return new String[] {input[0], divided[0], divided[1]};

            case "event":
                if (input.length < 2) {
                    throw new DukeException("'event' command needs a description. (example: 'event Borat concert /at Aug 6th 2-4pm')");
                }
                String args = combineStringArray(input, 1, input.length);
                String[] div = args.split(" /at ");
                if (div.length < 2) {
                    throw new DukeException("'event' command needs a deadline. use '/by'. (example: 'event Borat concert /at Aug 6th 2-4pm')");
                } else if (div.length > 2) {
                    throw new DukeException("'event' command has too many deadline. use 1 '/by'. (example: 'event Borat concert /at Aug 6th 2-4pm')");
                }
                return new String[] {input[0], div[0], div[1]};

            case "bye":
                if (input.length != 1) {
                    throw new DukeException("Do you mean `bye`?");
                }
                return new String[] {input[0]};

            case "delete":
                if (input.length != 2) {
                    throw new DukeException("'delete' command needs exactly 1 argument. (example: 'delete 5')");
                }
                try {
                    // Check if the argument is a number
                    Integer.parseInt(input[1]);
                } catch (Exception e) {
                    throw new DukeException("'delete' command needs an integer as a number. (example: 'delete 5')");
                }
                return new String[] {input[0], input[1]};

            default:
                throw new DukeException("What you mean?");
        }
    }

    /**
     * Get the user input
     * @param sc The scanner to get the input
     * @return The string representation of the user input
     */
    private static String getInput(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Displays Borat's message to the user
     * @param message The message content to be displayed
     */
    private static void showMessage(String message) {
        message = SPACE + message.replace("\n", "\n" + SPACE);
        System.out.println(SPACE + USER_LINE);
        System.out.println(message);
        System.out.println(" ");
        System.out.println(SPACE + BOT_LINE);
    }

    /**
     * Combine an array of strings into a space separated sentence.
     * @param arr The string array
     * @param start The starting index to be combined (inclusive)
     * @param exclusiveEnd The ending index (exclusive)
     * @return The sentence.
     */
    private static String combineStringArray(String[] arr, int start, int exclusiveEnd) {
        StringBuilder tmp = new StringBuilder();
        if (exclusiveEnd > arr.length) {
            exclusiveEnd = arr.length;
        }
        for (int i = start; i < exclusiveEnd; ++i) {
            if (i + 1 >= exclusiveEnd) {
                tmp.append(arr[i]);
            } else {
                tmp.append(arr[i]).append(" ");
            }
        }
        return tmp.toString();
    }
}
