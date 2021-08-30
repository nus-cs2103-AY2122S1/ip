public class Ui {

    private String greetingMessage = "\tHola! I'm Blitz :)";
    private String goodbyeMessage = "Adiós. Hope to see you again soon!";

    /**
     * Prints dotted line.
     */
    public void printLine() {
        System.out.print('\t');
        for (int i = 0; i < 100; i++) {
            System.out.print('_');
        }
        System.out.println("");
    }

    /**
     * Prints given string within dotted lines.
     *
     * @param str string to be printed within dotted lines.
     */
    public void printFormatted(String str) {
        printLine();
        System.out.println("\t" + str);
        printLine();
    }

    /**
     * Prints the given list of tasks after the given string.
     *
     * @param tasks list of tasks to be printed.
     * @param str string to be printed before the list of tasks.
     */
    public void printList(TaskList tasks, String str) {
        int ctr = 1;
        System.out.println("\t" + str);
        if (tasks.size() == 0) {
            System.out.println("\t---No items stored yet---");
        } else {
            tasks.printList();
        }
    }

    /**
     * Prints greeting message preceeded by dotted line.
     */
    public void printGreeting() {
        printLine();
        System.out.println(this.greetingMessage);
    }

    public void printGoodbye() {
        printFormatted(this.goodbyeMessage);
    }

    /**
     * Displays loading error message.
     */
    public void showLoadingError() {
        System.err.print("Error loading contents from file!!");
    }

}
