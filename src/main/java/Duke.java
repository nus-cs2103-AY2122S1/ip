package main.java;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person
 * keep track of various things.
 *
 * @author Zhen Xuan (Tutorial Group 04)
 * @version CS2103T AY21/22 S2
 */
public class Duke {
    //Duke initialisation
    protected static final String LINE = "\t____________________________________________________________";
    private static final String INTRO = "Hello! I'm Duke\n\t What can I do for you?";
    private static final String BYE = "Bye. Hope to see you again soon!";

    /**
     * Provides horizontal lines with indentation.
     *
     * @param str
     * @return formatted reply
     */
    protected static void reply(String str) {
         System.out.println(LINE);
         System.out.println("\t " + str);
         System.out.println(LINE + "\n");
    }

    public static void main(String[] args) {
        reply(INTRO);
        Scanner sc = new Scanner(System.in);
        boolean on = true;
        while (on) {
            String input = sc.nextLine();
            String[] commandPair = input.split(" ", 2);
            switch (commandPair[0]) {
                case "bye":
                    on = false;
                    reply(BYE);
                    sc.close();
                    break;
                case "list":
                    Task.printList();
                    break;
                case "done":
                    Task.setDone(Integer.valueOf(commandPair[1]) - 1);
                    break;
                case "todo":
                    new ToDo(commandPair[1]);
                    break;
                case "deadline":
                    String[] deadlinePair = commandPair[1].split("/by", 2);
                    new Deadline(deadlinePair[0], deadlinePair[1]);
                    break;
                case "event":
                    String[] eventPair = commandPair[1].split("/at", 2);
                    new Event(eventPair[0], eventPair[1]);
                    break;
                default:
                    reply("Invalid command");
            }
        }
    }
}
