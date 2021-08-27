package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final Ui dukeUi;
    private final Parser dukeParser;
    private final Storage dukeStorage;
    private EntryList entries;

    private final String TERMINATION_COMMAND = "bye";
    private final String LIST_ENTRIES_COMMAND = "list";
    private final String MARK_ENTRY_DONE_COMMAND = "done";
    private final String DELETE_ENTRY_COMMAND = "delete";
    private final String TODO_COMMAND = "todo";
    private final String EVENT_COMMAND = "event";
    private final String DEADLINE_COMMAND = "deadline";
    private final String FIND_COMMAND = "find";

    Duke() {
        this.dukeUi = new Ui();
        this.dukeParser = new Parser();
        this.dukeStorage = new Storage();
        try {
            this.entries = this.dukeStorage.readData();
        } catch (DukeException e) {
            //specific to loading error dukeUi
            this.dukeUi.handleLoadingError(e);
            this.entries = new EntryList();
        }
    }

    private void processInput(ArrayList<String> parsedTerms) throws DukeException {
        if (parsedTerms.size() < 3) {
            throw new DukeException("Duke Cannot Understand Your Entry!");
        }
        String command = parsedTerms.get(0);
        String entry = parsedTerms.get(1);
        String timing = parsedTerms.get(2);
        //Process Command
        switch(command) {
        case LIST_ENTRIES_COMMAND:
            entries.displayEntries(this.dukeUi);
            break;

        case MARK_ENTRY_DONE_COMMAND:
            entries.markEntryAsDone(Integer.parseInt(entry));
            break;

        case TODO_COMMAND:
            entries.addEntry(new Todo(entry), command, this.dukeUi);
            break;

        case EVENT_COMMAND:
            entries.addEntry(new Event(entry, timing), command, this.dukeUi);
            break;

        case DEADLINE_COMMAND:
            entries.addEntry(new Deadline(entry, timing), command, this.dukeUi);
            break;

        case DELETE_ENTRY_COMMAND:
            entries.deleteEntry(Integer.parseInt(entry), this.dukeUi);
            break;

        case FIND_COMMAND:
            entries.findEntry(entry, this.dukeUi);
            break;

            default:
                throw new DukeException("Sorry! Duke can't understand what that means");
        }
    }

    private void run() {
        this.dukeUi.welcomeUser();
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        while (!(input.equals(TERMINATION_COMMAND))) {
            try {
                //Parser class
                ArrayList<String> parsedTerms = this.dukeParser.parseInput(input);
                this.processInput(parsedTerms);
                this.dukeStorage.saveEntries(this.entries);
            } catch (DukeException e) {
                this.dukeUi.handleParsingError(e);
            }
            input = inputScanner.nextLine();
        }
        this.dukeUi.goodByeUser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public String toString() {
        return "I'm a Duke, a simple chatbot to help you remember tasks!";
    }
}
