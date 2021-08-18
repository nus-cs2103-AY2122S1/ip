import java.util.ArrayList;
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
        if (!entry.isEmpty()){
            entries[numberOfEntries++] = entry;
            System.out.println("Awesome, Duke remembers this event:" + entry);
            if (numberOfEntries < 2) {
                System.out.println("We now have " + numberOfEntries + " task in our plan!");
            } else {
                System.out.println("We now have " + numberOfEntries + " tasks in our plan!");
            }
        } else {
            System.out.println("Empty Entry/Timing! :( Try Again");
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
        } else {
            System.out.println("No tasks added!");
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

    private static String parseTiming(ArrayList<String> terms) {
        if (terms.get(0).startsWith("/")) {
            terms.remove(0);
            StringBuilder timing = new StringBuilder();
            int len = terms.size();
            for (int i = 0; i < len; i++) {
                if (i != len - 1){
                    timing.append(terms.get(i)).append(" ");
                } else {
                    timing.append(terms.get(i));
                }
            }
            return timing.toString();
        } else {
            return "";
        }
    }

    private static void parseEntry(ArrayList<String> terms) {
        if (!terms.isEmpty()) {
            StringBuilder entry = new StringBuilder();
            //Combine All Strings Until End of List or '/' character is found
            int count = 0;
            ArrayList<String> termsCopy = new ArrayList<>(terms);
            for (String term : termsCopy) {
                if (term.startsWith("/")) {
                    terms.add(0, entry.toString());
                    return;
                } else {
                    entry.append(term);
                    terms.remove(count++);
                }
            }
            terms.add(0, entry.toString());
        }
    }

    private static void parseString (String input, ArrayList<String> terms) {
        //Function to store all terms in input as separate Strings (separated by space in the input)
        int length = input.length();
        if (length >= 1) {
            StringBuilder currentWord = new StringBuilder();
            for (int i = 0; i < length; i++) {
                char currentChar = input.charAt(i);
                if (currentChar == ' ') {
                    terms.add(currentWord.toString());
                    currentWord.setLength(0);
                } else {
                    currentWord.append(currentChar);
                }
            }
            terms.add(currentWord.toString());
        }
    }

    private static void processInput (String input) {
        ArrayList<String> terms = new ArrayList<>();
        parseString(input, terms);
        String command = terms.isEmpty() ? "" : terms.remove(0);
        parseEntry(terms);
        String entry = terms.isEmpty() ? "" : terms.remove(0);
        String timing = terms.isEmpty() ? "" : parseTiming(terms);
        //Process Command
        switch(command) {
            case LIST_ENTRIES_COMMAND:
                listEntries();
                break;

            case MARK_ENTRY_DONE_COMMAND:
                markEntryAsDone(Integer.parseInt(entry));
                break;

            case TODO_COMMAND:
                addEntry(new Todo(entry));
                break;

            case EVENT_COMMAND:
                addEntry(new Event(entry, timing));
                break;

            case DEADLINE_COMMAND:
                addEntry(new Deadline(entry, timing));
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
            processInput(input);
            input = inputScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
