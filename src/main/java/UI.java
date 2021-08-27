public class UI {

    UI() {}

    private final static String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String HORIZONTAL_DIVIDE = "___________________________________________________________________";

    public void welcomeUser() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can Duke do for you today?");
        System.out.println(HORIZONTAL_DIVIDE);
    }

    public void addEntry (Entry entry, int ID) {
        System.out.println("I've added entry to your list!");
        this.printEntry(entry, ID);
        System.out.println(HORIZONTAL_DIVIDE);
    }

    public void end () {
        System.out.println(HORIZONTAL_DIVIDE);
    }

    public void printEntry(Entry entry, int ID) {
        System.out.println("\t" + ID + "." + entry);
    }

    public void goodByeUser() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_DIVIDE);
    }

    public void handleParsingError(DukeException parsingError) {
        System.out.println(parsingError.getMessage());
        System.out.println(HORIZONTAL_DIVIDE);
    }

    public void handleLoadingError(DukeException loadingError) {
        System.out.println(loadingError.getMessage());
        System.out.println(HORIZONTAL_DIVIDE);
    }

    public void showDeletedEntry(Entry deletedEntry) {
        System.out.println("Removed entry\n" + deletedEntry);
        System.out.println(HORIZONTAL_DIVIDE);
    }
}
