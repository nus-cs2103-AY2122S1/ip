import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

public class Duke {

    private final static String LOGO =  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String PROJECT_ROOT = System.getProperty("user.dir");
    private final static Path DATA_DIRECTORY_PATH = Paths.get(PROJECT_ROOT,"data");

    private final static String TERMINATION_COMMAND = "bye";
    private final static String LIST_ENTRIES_COMMAND = "list";
    private final static String MARK_ENTRY_DONE_COMMAND = "done";
    private final static String DELETE_ENTRY_COMMAND = "delete";
    private final static String TODO_COMMAND = "todo";
    private final static String EVENT_COMMAND = "event";
    private final static String DEADLINE_COMMAND = "deadline";

    private static ArrayList<Entry> entries;

    private static int numberOfEntries;

    private static void addEntry(Entry entry, String command) throws DukeException {
        if (!entry.isEmpty()){
            entries.add(numberOfEntries++, entry);
            System.out.println("Awesome, Duke remembers this event:" + entry);
            if (numberOfEntries == 1) {
                System.out.println("We now have " + (numberOfEntries) + " task in our plan!");
            } else {
                System.out.println("We now have " + (numberOfEntries) + " tasks in our plan!");
            }
            saveEntries();
        } else {
            throw new DukeException("The " + command + " description can't be empty!");
        }
    }

    private static void saveEntries() throws DukeException {
        Path dataPath = DATA_DIRECTORY_PATH.resolve("duke.txt");
        File dukeData = new File(dataPath.toString());
        try {
            FileWriter fw = new FileWriter(dukeData);
            BufferedWriter dukeWriter = new BufferedWriter(fw);
            for (Entry entry : entries){
                String nextEntry = entry.saveString();
                dukeWriter.write(nextEntry);
                dukeWriter.write("\n");
            }
            dukeWriter.close();
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Duke's data file is corrupted/missing! Can't be saved");
        }
    }

    private static void deleteEntry(int index) throws DukeException {
        if (entries.isEmpty() || index < 1 || index > numberOfEntries) {
            throw new DukeException("Duke can't find anything to delete!");
        } else {
            Entry deletedEntry = entries.remove(index - 1);
            numberOfEntries--;
            System.out.println("Removed entry\n" + deletedEntry);
            saveEntries();
        }
    }

    private static void initialiseDuke() {
        entries = new ArrayList<>(100);
        numberOfEntries = 0;
        try {
            readData();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listEntries() throws DukeException {
        if (numberOfEntries > 0) {
            for (int i = 0; i < numberOfEntries; i++) {
                System.out.println(i + 1 + "." + entries.get(i));
            }
        } else {
            throw new DukeException("No entries to display!");
        }
    }

    private static void markEntryAsDone(int entryNumber) throws DukeException {
        if (entryNumber > 0 && entryNumber <= numberOfEntries) {
            if (entries.get(entryNumber - 1).markEntryAsDone()) {
                System.out.println("Nice! I've marked this entry as done:");
                System.out.println("\t" + entries.get(entryNumber - 1));
                saveEntries();
            } else {
                System.out.println("Entry is already marked as done!");
            }
        } else {
            throw new DukeException("There's no Entry corresponding to that Number!");
        }
    }

    private static void readData() throws DukeException {
        Path dataPath = DATA_DIRECTORY_PATH.resolve("duke.txt");
        File dukeData = new File(dataPath.toString());
        try {
            Scanner fileScanner = new Scanner(dukeData);
            fileScanner.useDelimiter("[,\n]");
            while (fileScanner.hasNext()) {
                String entryType = fileScanner.next();
                boolean isDone = Integer.parseInt(fileScanner.next()) == 1;
                String entryData = fileScanner.next();
                Entry nextEntry = new Todo("");
                boolean hasNextEntry = true;
                switch (entryType) {
                    case "T":
                        nextEntry = new Todo(entryData);
                        break;
                    case "D":
                        String deadlineTiming = fileScanner.next();
                        nextEntry = new Deadline(entryData, deadlineTiming);
                        break;
                    case "E":
                        String eventTiming = fileScanner.next();
                        nextEntry = new Event(entryData, eventTiming);
                        break;
                    default:
                        //Corrupted Entry Case
                        hasNextEntry = false;
                        break;
                }
                if (hasNextEntry) {
                    if (isDone) {
                        nextEntry.markEntryAsDone();
                    }
                    entries.add(nextEntry);
                    numberOfEntries++;
                }
            }
        } catch (FileNotFoundException e) {
            try {
                if (!(dukeData.createNewFile())) {
                    throw new DukeException("Uh-Oh! Your Data can't be stored!");
                }
            } catch (IOException err) {
                throw new DukeException(err.getMessage());
            }
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
            ArrayList<String> termsCopy = new ArrayList<>(terms);
            for (String term : termsCopy) {
                if (term.startsWith("/")) {
                    String entryDesc = entry.toString();
                    terms.add(0, entryDesc.substring(0, entryDesc.length()-1));
                    return;
                } else {
                    entry.append(term).append(' ');
                    terms.remove(0);
                }
            }
            String entryDesc = entry.toString();
            terms.add(0, entryDesc.substring(0, entryDesc.length()-1));
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

    private static void processInput (String input) throws DukeException {
        ArrayList<String> terms = new ArrayList<>();
        parseString(input, terms);
        String command = "";
        if (terms.isEmpty()) {
            throw new DukeException("Duke can't find any commands :(");
        } else {
            command = terms.remove(0);
        }
        parseEntry(terms);
        String entry = terms.isEmpty() ? "" : terms.remove(0);
        String timing = terms.isEmpty() ? "" : parseTiming(terms);
        //Process Command
        switch(command) {
            case LIST_ENTRIES_COMMAND:
                listEntries();
                break;

            case MARK_ENTRY_DONE_COMMAND:
                int id = Integer.parseInt(entry);
                markEntryAsDone(id);
                break;

            case TODO_COMMAND:
                addEntry(new Todo(entry), command);
                break;

            case EVENT_COMMAND:
                addEntry(new Event(entry, timing), command);
                break;

            case DEADLINE_COMMAND:
                addEntry(new Deadline(entry, timing), command);
                break;

            case DELETE_ENTRY_COMMAND:
                deleteEntry(Integer.parseInt(entry));
                break;

            default:
                throw new DukeException("Sorry! Duke can't understand what that means");
        }
    }

    public static void main(String[] args) {
        initialiseDuke();
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        while (!(input.equals(TERMINATION_COMMAND))) {
            try {
                processInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = inputScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
