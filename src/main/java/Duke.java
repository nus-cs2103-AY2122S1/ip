public class Duke {

    private static void insertSeparateLine() {
        String separateLine = "____________________________________________________________";
        System.out.println("\t" + separateLine);
    }

    public static void greetings() {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        insertSeparateLine();
        System.out.println(logo);
        System.out.println("\t" + " Hello! I'm Duke");
        System.out.println("\t" + " What can I do for you?");
        insertSeparateLine();
    }

    public static void main(String[] args) {
        Duke.greetings();

    }
}
