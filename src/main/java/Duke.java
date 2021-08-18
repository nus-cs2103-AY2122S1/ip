/**
 * The Bhutu chatbot app
 */

import java.util.Scanner;

public class Duke {

    /**
     * Global variables
     */
    private static final String SPACE = "    ";
    private static final String LINE = SPACE + "______________________________________________________________________";
    private static final String END_LINE = SPACE + "======================================================================\n";

    private Items items;
    public Duke() {
        items = new Items();
    }


    /**
     * method to greet the user
     */
    private void greet() {
        System.out.println(LINE);
        System.out.println(SPACE + "Hello! I'm Bhutu, your personal chatbot!");
        System.out.println(SPACE + "What can I do for you?");
        System.out.println(END_LINE);
    }


    /**
     * interact with the user
     */
    private void interact() {
        String[] input = {""};
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            String output = "";
            input = getInput(sc).split("\\s+");
            String command = input[0];
            String[] task = compileInput(input);
            if (task[0].equals("error")) {
                continue;
            }
            switch (command) {
                case "list":
                    printMessage(items.printList());
                    break;
                case "done":
                    if (input.length < 2) {
                        printMessage("Enter the task number you have completed");
                        break;
                    }
                    printMessage(items.markDone(Integer.parseInt(input[1])));
                    break;
                case "bye":
                    flag = false;
                    break;
                case "todo":
                    printMessage(items.addItem(new Todo(task[0])));
                    break;
                case "event":
                    printMessage(items.addItem(new Event(task[0], task[1])));
                    break;
                case "deadline":
                    printMessage(items.addItem(new Deadline(task[0], task[1])));
                    break;
                default:
                    printMessage("I don't recognise this command\n" +
                            "Try 'list', 'todo', 'event', 'deadline', 'done' or 'bye'");
                    break;
            }
        }
        printMessage("Going so soon? Hope to see you again soon!");
    }

    /**
     * combine an array of strings into a space seperated sentence.
     * @param input the string array.
     * @return the sentence.
     */
    private StringBuilder combineInputArray(String[] input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            if (i < input.length - 1) {
                result.append(input[i]).append(" ");
            } else {
                result.append(input[i]);
            }
        }
        return result;
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
     * print all bot messages in a specific format.
     * @param message message from the bot.
     */
    private static void printMessage(String message) {
        message = SPACE + message.replace("\n", "\n" + SPACE);
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(END_LINE);
    }


    /**
     * Convert the user input string into meaningful commands.
     * @param input the user input string.
     * @return the meaningful commands.
     */
    private String[] compileInput(String[] input) {
        StringBuilder result = combineInputArray(input);
        switch (input[0]) {
            case "deadline":
                String[] output = result.toString().split(" /by ");
                if (output.length < 2) {
                    printMessage("Please provide both description and time. Use '/by'. (eg. deadline fix hair /by 1pm tomorrow)");
                } else {
                    return output;
                }
                break;
            case "event":
                String[] output1 = result.toString().split(" /at ");
                if (output1.length < 2) {
                    printMessage("Please provide both description and time. Use '/at'. (eg. event fix hair /at 1pm tomorrow)");
                } else {
                    return output1;
                }
                break;
            case "todo":
                if (input.length < 2) {
                    printMessage("Please specify the task you want to do");
                } else {
                    return new String[] {result.toString()};
                }
                break;
            case "done":
                if (input.length < 2) {
                    printMessage("Please specify which task you have done");
                    break;
                } else if (input.length != 2) {
                    printMessage("'done' command requires exactly 1 argument. (eg. done 12)");
                    break;
                }

                try {
                    Integer.parseInt(input[1]);
                } catch (Exception e) {
                    printMessage("'done' command requires an integer as number. (eg. done 12)");
                    break;
                }

                return new String[] {input[1]};
            case "list":
                if (input.length != 1) {
                    printMessage("'list' command doesn't require any arguments.");
                } else {
                    return new String[] {input[0]};
                }
                break;
            case "bye":
                if (input.length != 1) {
                    printMessage("'bye' command doesn't require any arguments.");
                    break;
                } else {
                    return new String[] {input[0]};
                }
            default:
                printMessage("I don't recognise this command\n" +
                        "Try 'list', 'todo', 'event', 'deadline', 'done' or 'bye'");
                break;
        }
        return new String[] {"error"};
    }


    /**
     * The main function of Bhutu
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        String logo = "\n" +
                "███████████████████████████████\n" +
                "█▄─▄─▀█─█─█▄─██─▄█─▄─▄─█▄─██─▄█\n" +
                "██─▄─▀█─▄─██─██─████─████─██─██\n" +
                "▀▄▄▄▄▀▀▄▀▄▀▀▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▄▀▀";

        System.out.println(logo);

        Duke duke = new Duke();
        duke.greet();
        duke.interact();
    }
}
