import java.util.Scanner;

public class Duke {

    private final static String LOGO =  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String TERMINATION_COMMAND = "bye";

    private final static String LIST_ENTRIES_COMMAND = "list";

    private final static String MARK_ENTRY_DONE_COMMAND = "done";

    private final static String TODO_COMMAND = "todo";
    private final static String EVENT_COMMAND = "event";
    private final static String DEADLINE_COMMAND = "deadline";

    private static Entry[] entries;

    private static int numberOfEntries;

    private static void addEntry(Entry entry) {
        entries[numberOfEntries++] = entry;
        System.out.println("Awesome, Duke remembers this event:"+ entry);
        if (numberOfEntries < 2) {
            System.out.println("We now have " + numberOfEntries + " task in our plan!");
        } else {
            System.out.println("We now have " + numberOfEntries + " tasks in our plan!");
        }
    }

    private static void initialiseDuke() {
        entries = new Entry[100];
        numberOfEntries = 0;
    }

    private static void listEntries() {
        if (numberOfEntries > 0) {
            for (int i = 0; i < numberOfEntries; i++) {
                System.out.println(i + 1 + "." + entries[i]);
            }
        }
    }

    private static void markEntryAsDone(int entryNumber) {
        if (entryNumber > 0 && entryNumber <= numberOfEntries) {
            if (entries[entryNumber - 1].markEntryAsDone()) {
                System.out.println("Nice! I've marked this entry as done:");
                System.out.println("\t" + entries[entryNumber - 1]);
            } else {
                System.out.println("Entry is already marked as done!");
            }
        } else {
            System.out.println("Invalid Entry Number");
        }
    }

    private static int parseNumArgument(int startPoint, String input) {
        return Integer.parseInt(input.substring(startPoint));
    }

    private static String parseTiming(String timingPrefix, String input) {
        int delimiterIndex = input.indexOf(timingPrefix);
        if (delimiterIndex == -1) {
            return "";
        } else {
            int startPoint = delimiterIndex + timingPrefix.length() + 1;
            return input.substring(startPoint);
        }
    }

    private static String parseEntry(String command, String input) {
        int startPoint = command.length();
        int endPoint = input.indexOf("/");
        endPoint = endPoint == -1 ? input.length() : endPoint;
        return input.substring(startPoint, endPoint);
    }

    private static void parseAndHandleInput(String input) {
        int numberArg = 0;
        String timing = "";
        String entry = "";

        if (input.startsWith("done ")) {
            numberArg = parseNumArgument(5, input);
            input = MARK_ENTRY_DONE_COMMAND;
        } else if (input.startsWith("deadline ")) {
            timing = parseTiming("/by", input);
            if (!timing.equals("")) {
                entry = parseEntry(DEADLINE_COMMAND, input);
                input = DEADLINE_COMMAND;
            } else {
                input = "";
            }
        } else if (input.startsWith("todo ")) {
            entry = parseEntry(TODO_COMMAND, input);
            input = TODO_COMMAND;
        } else if (input.startsWith("event ")) {
            timing = parseTiming("/at", input);
            if (!timing.equals("")) {
                entry = parseEntry(EVENT_COMMAND, input);
                input = EVENT_COMMAND;
            } else {
                input = "";
            }
        }
        switch (input) {
            case LIST_ENTRIES_COMMAND: listEntries();
                break;

            case MARK_ENTRY_DONE_COMMAND: markEntryAsDone(numberArg);
                break;

            case TODO_COMMAND: addEntry(new Todo(entry));
                break;

            case EVENT_COMMAND: addEntry(new Event(entry, timing));
                break;

            case DEADLINE_COMMAND: addEntry(new Deadline(entry, timing));
                break;

            default:
                System.out.println("I can't process this! Try another command");
                break;
        }
    }

    public static void main(String[] args) {
        initialiseDuke();
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        while (!(input.equals(TERMINATION_COMMAND))) {
            parseAndHandleInput(input);
            input = inputScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
