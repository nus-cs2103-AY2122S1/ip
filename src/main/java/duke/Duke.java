package duke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    static final String LOGO = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";;
    static final String GOODBYE = "Bye. Hope to see you again soon!";
    LinkedList<Item> itemList = new LinkedList<>();
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        System.out.println("Hello from\n" + LOGO);
        Scanner sc = new Scanner(System.in);
        String[] currLine;
        scanner: while (true) {
            try {
                currLine = sc.nextLine().split(" ");
                switch (currLine[0]) {
                    case "bye":
                        System.out.println(styleResponse(GOODBYE));
                        break scanner;
                    case "list":
                        System.out.println(this.list());
                        break;
                    case "done":
                        int idx = Integer.valueOf(currLine[1]);
                        System.out.println(this.markIdxAsDone(idx));
                        break;
                    default:
                        System.out.println(this.add(currLine));
                }
            } catch (DukeException e) {
                System.out.println(styleResponse(e.toString()));
            }
        }
        sc.close();
    }
    /**
     * Adds item to to-do list.
     * @param inputString
     * @return Returns and echoes string that was added.
     */
    public String add(String[] inputStrings) {
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Got it. I've added this task:");
        Item toAdd = null;
        switch (inputStrings[0]) {
            case "todo":
                toAdd = new ToDo(inputStrings);
                break;
            case "deadline":
                toAdd = new Deadline(inputStrings);
                break;
            case "event":
                toAdd = new Event(inputStrings);
                break;
            default:
                throw new DukeException("I'm sorry I don't know what that means.");
        }
        this.itemList.add(toAdd);
        printBuffer.add("  " + toAdd.toString());
        printBuffer.add(String.format("Now you have %d tasks in the list.", this.itemList.size()));
        return styleResponse(printBuffer);
    }
    /**
     * Prints all items in list.
     * @return String of tally.
     */
    public String list() {
        ListIterator<Item> iterator = this.itemList.listIterator();
        if (!iterator.hasNext()) {
            return "Empty!";
        } else {
            ArrayList<String> printBuffer = new ArrayList<>();
            printBuffer.add("Here are the tasks in your list:");
            Integer currIdx = 1;
            while (iterator.hasNext()) {
                printBuffer.add(currIdx.toString() + ". " + iterator.next().toString());
                currIdx++;
            }
            return styleResponse(printBuffer);
        }
    }

    /**
     * Marks to-do list at index i (0-count).
     * @param i 0 is the first item in the list.
     */
    public String markIdxAsDone(int i) {
        Item x = this.itemList.get(i - 1);
        ArrayList<String> printBuffer = new ArrayList<>();
        printBuffer.add("Nice! I've marked this task as done:");
        x.markAsDone();
        printBuffer.add("  " + x.toString());
        return styleResponse(printBuffer);
    }

    public static String echo(String inputString) {
        return inputString;
    }

    public static String styleResponse(String inputString) {
        return "     --------------------\n     " + inputString + "\n     --------------------";
    }

    /**
     * Uses a line-wise input array of Strings and formats it for output.
     * @param inputStrings
     * @return String of output.
     */
    public static String styleResponse(ArrayList<String> inputStrings) {
        String returnBuffer = "     --------------------\n";
        for (String line : inputStrings) {
            returnBuffer = returnBuffer.concat("     " + line + "\n");
        }
        return returnBuffer + "     --------------------";
    }
}
