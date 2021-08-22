package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.Date;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person
 * keep track of various things.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Duke {
    //Duke initialisation
    protected static final String LINE = "\t____________________________________________________________";
    private static final String INTRO = "Hello! I'm Duke\n\t What can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LOADING = "Initialising Duke... Loading data...";
    private static final String LOADED = "Loading done.";
    private static final String FILEPATH = "data/TaskList.txt";


    /**
     * Provides horizontal lines with indentation.
     *
     * @param str the String to be formatted
     */
    protected static void reply(String str) {
        System.out.println(LINE);
        System.out.println("\t " + str);
        System.out.println(LINE + "\n");
    }

    /**
     * Throws exceptions from invalid commands (String[]s).
     *
     * @param command the command to be checked
     * @param greater change the condition of the check
     * @param len     the number of segments in the command
     * @param message the message to be shown in the error
     * @throws IllegalArgumentException an error
     */
    protected static void checkLen(String[] command, boolean greater, int len, String message) throws IllegalArgumentException {
        if (greater) {
            if (command.length > len)
                throw new IllegalArgumentException("There are too many arguments that I do not understand. Sorry.");
        } else {
            if (command.length < len) throw new IllegalArgumentException(message);
        }
    }

    /**
     * Check if the string is numeric.
     *
     * @param str the string to be checked
     */
    protected static int isNumeric(String str) throws IllegalArgumentException {
        try {
            int i = Integer.parseInt(str);
            if (i < 1)
                throw new IllegalArgumentException("The list is numbered 1, 2, 3, .... There are no items numbered 0 nor with a negative number.");
            return i;
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("You did not provide a valid number.");
        }
    }

    public static void main(String[] args) {
        try {
            reply(LOADING);
            Storage storage = new Storage(FILEPATH);
            TaskList tl = new TaskList(storage.load());
            reply(LOADED);
            reply(INTRO);
            boolean on = true;
            Scanner sc = new Scanner(System.in);
            while (on) {
                String input = sc.nextLine();
                String[] commandPair = input.split(" ", 2);
                Command command = StringToCommand.convert(commandPair[0]);
                try {
                    switch (command) {
                        case BYE:
                            checkLen(commandPair, true, 1, "");
                            on = false;
                            reply(BYE_MESSAGE);
                            sc.close();
                            break;
                        case LIST:
                            checkLen(commandPair, true, 2, "");
                            if (commandPair.length == 1) {
                                tl.printList();
                            } else {
                                tl.printListDate(commandPair[1]);
                            }
                            break;
                        case DONE:
                            checkLen(commandPair, false, 2, "You did not indicate the task to be marked done. Use 'done <task's number>' to mark the task as done.");
                            int toBeDone = isNumeric(commandPair[1]);
                            if (toBeDone > tl.count())
                                throw new IllegalArgumentException("You do not have that much tasks. Try adding more tasks.");
                            tl.setDone(toBeDone - 1);
                            storage.setDone(toBeDone - 1);
                            break;
                        case DELETE:
                            checkLen(commandPair, false, 2, "You did not indicate the task to be deleted. Use 'delete <task's number>' to delete the task.");
                            int toBeDeleted = isNumeric(commandPair[1]);
                            if (toBeDeleted > tl.count())
                                throw new IllegalArgumentException("You do not have that much tasks. Try adding more tasks.");
                            tl.delete(toBeDeleted - 1);
                            storage.delete(toBeDeleted - 1);
                            break;
                        case TODO:
                            checkLen(commandPair, false, 2, "The description of a todo cannot be empty. Use 'todo <description>' to add a todo.");
                            tl.addTask(new ToDo(false, commandPair[1]));
                            storage.add("T", commandPair[1], " ");
                            break;
                        case DEADLINE:
                            checkLen(commandPair, false, 2, "The description of a deadline cannot be empty. Use 'deadline <description> /at <date>' to add a deadline.");
                            String[] deadlinePair = commandPair[1].split("/by", 2);
                            checkLen(deadlinePair, false, 2, "The description / date of a deadline cannot be empty. Use 'deadline <description> /at <date>' to add a deadline.");
                            tl.addTask(new Deadline(false, deadlinePair[0], deadlinePair[1]));
                            storage.add("D", deadlinePair[0], deadlinePair[1]);
                            break;
                        case EVENT:
                            checkLen(commandPair, false, 2, "The description of an event cannot be empty. Use 'event <description> /at <date>' to add an event.");
                            String[] eventPair = commandPair[1].split("/at", 2);
                            checkLen(eventPair, false, 2, "The description / date of an event cannot be empty. Use 'event <description> /at <date>' to add an event.");
                            tl.addTask(new Event(false, eventPair[0], eventPair[1]));
                            storage.add("E", eventPair[0], eventPair[1]);
                            break;
                        default:
                            throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
                    }
                } catch (IllegalArgumentException e) {
                    reply("☹ OOPS!!! " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            reply("☹ OOPS!!! File not found!");
        } catch (IOException e) {
            reply("☹ OOPS!!! Cannot create file!");
        } catch (DateTimeException e) {
            reply("☹ OOPS!!! Your date (YYYY-MM-DD) / date & time (YYYY-MM-DD HHMM) (24h) is given in the wrong format!");
        }
    }
}
