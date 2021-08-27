package virushade;

import virushade.tasks.TaskList;

import java.util.Scanner;

public class Ui {
    /**
     * Prints a line break. Used after every interaction with Virushade.
     */
    private static void lineBreak() {
        System.out.println("_____________________________________________________\n");
    }

    /**
     * Prints the greeting.
     */
    private static void greet() {
        System.out.println( "Hello from\n"
                + "__      __ ( )                ____\n"
                + "\\ \\    / /  _   ____  _   _  / __/\n"
                + " \\ \\  / /  | | | ,__|| | | | \\ \\\n"
                + "  \\ \\/ /   | | | |   | |_| | _) \\\n"
                + "   \\__/    |_| |_|    \\__,_||___/\n"
                + "What can <<Virushade>> do for you?\n");
        lineBreak();
    }

    /**
     * The echo function that Virushade uses. Echoes whatever words that Virushade is given.
     * @param wordToEcho The word provided by the user for Virushade to echo.
     */
    public static void echo(String wordToEcho) {
        System.out.println(wordToEcho);
    }

    /**
     * Virushade first says goodbye before exiting.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
        System.exit(0);
    }

    /**
     * Handles unexpected inputs to Virushade.
     */
    private static void handleUnexpectedInputs(String str) throws VirushadeException {
        if (str.startsWith("todo")) {
            throw new VirushadeException("OOPS!!! The description of a todo task cannot be empty.");
        } else if (str.startsWith("deadline")) {
            throw new VirushadeException("OOPS!!! The description of a deadline task cannot be empty.");
        } else if (str.startsWith("event")) {
            throw new VirushadeException("OOPS!!! The description of an event task cannot be empty.");
        } else if (str.startsWith("done")) {
            throw new VirushadeException("OOPS!!! Please enter an integer after 'done'.");
        } else if (str.startsWith("delete")) {
            throw new VirushadeException("OOPS!!! Please enter an integer after 'delete'.");
        } else if (str.startsWith("find")) {
            throw new VirushadeException("OOPS!!! Please tell me what to find.");
        } else {
            throw new VirushadeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Determines what Virushade should do with given string str.
     * Note: str is assumed to not be "bye".
     *
     * @param str The input instructional string.
     */
    private static void assignTask(String str) {
        try {
            if (str.equals("list")) {
                TaskList.list();
            } else if (str.startsWith("find ")) {
                TaskList.find(str.substring(5));
            } else if (str.startsWith("done ")) {
                TaskList.completeTask(str.substring(5));
            } else if (str.startsWith("todo ")) {
                TaskList.add(str.substring(5), "TODO");
            } else if (str.startsWith("deadline ")) {
                TaskList.add(str.substring(9), "DEADLINE");
            } else if (str.startsWith("event ")) {
                TaskList.add(str.substring(6), "EVENT");
            } else if (str.startsWith("delete ")) {
                TaskList.delete(str.substring(7));
            } else {
                handleUnexpectedInputs(str);
            }
        } catch (VirushadeException e) {
            handleVirushadeException(e);
        }
    }

    private static void scanInput() {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String str = sc.nextLine();

            if (str.equals("bye")) {
                break;
            } else {
                assignTask(str);
            }

            lineBreak();
        }
    }

    public static void interact() {
        greet();
        scanInput();
        exit();
    }

    public static void showCreatingFiles() {
        System.out.println("Creating data files...");
    }

    public static void showCreatingDirectory() {
        System.out.println("Creating data directory...");
    }

    public static void handleVirushadeException(VirushadeException e) {
        System.out.println(e.getMessage());
    }
}
