public class Ui {
    private boolean isRunning;
    private static final String INDENT = "    ";
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String CLOSING_STATEMENT = "Bye, hope to see you again! :)";


    public Ui() {
        this.start();
        printFormatted("Hello from\n" + LOGO);
    }

    public void start() {
        //START SCANNER?
        this.isRunning = true;
    }

    public void close() {
        this.isRunning = false;
        printFormatted("Bye, hope to see you again! :)");
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public static void printFormatted(String text) {
        String topBorder = "____________________________________";
        String bottomBorder = "------------------------------------";
        String textWithBorders = topBorder + "\n"+  text + "\n" + bottomBorder + "\n";
        String[] lines = textWithBorders.split("\n");
        for (String line : lines) {
            System.out.println(Ui.INDENT + line);
        }
    }
}
