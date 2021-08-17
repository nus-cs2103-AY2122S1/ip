import java.util.Scanner;

public class Duke {

    private static class Entry {

        private String entry;

        private boolean isDone;

        Entry(String value) {
            this.entry = value;
            this.isDone = false;
        }

        public void revertEntry() {
            this.isDone = !this.isDone;
        }

        public boolean markEntryAsDone() {
            if (this.isDone) {
                return false;
            } else {
                this.isDone = true;
                return this.isDone;
            }
        }

        public String getEntryOnly() {
            return this.entry;
        }

        @Override
        public String toString () {
            char isDoneDisplay = this.isDone ? 'X' : ' ';
            return ("[" + isDoneDisplay + "] " + entry);
        }
    }

    private final static String LOGO =  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String TERMINATION_COMMAND = "bye";

    private final static String LIST_ENTRIES_KEYWORD = "list";

    private static String[] entries;

    private static int numberOfEntries;

    private static void addEntry(String entry) {
        entries[numberOfEntries++] = entry;
        System.out.println("added: "+ entry);
    }
    private static void initialiseDuke() {
        entries = new String[100];
        numberOfEntries = 0;
    }

    private static void listEntries() {
        if (numberOfEntries > 0) {
            for (int i = 0; i < numberOfEntries; i++) {
                System.out.println(i + 1 + ". " + entries[i]);
            }
        }
    }

    public static void main(String[] args) {
        initialiseDuke();
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        while (!(input.equals(TERMINATION_COMMAND))) {
            switch (input) {
                case LIST_ENTRIES_KEYWORD: listEntries();
                    break;

                default: addEntry(input);
                    break;
            }
            input = inputScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
