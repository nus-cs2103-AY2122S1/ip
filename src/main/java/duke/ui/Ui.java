package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
   
    public static final String BORDER = "____________________________________________________________";
    public static final String PREFIX = "\t";
    
    private final Scanner sc;
    private final PrintStream out;
    
    public Ui() {
        this.sc = new Scanner(System.in);
        this.out = System.out;
    }

    public void printToUser(String message) {
        out.println(PREFIX + message);
    }

    public void printBorder() {
        out.println(BORDER);
    }
    
    public void welcome() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        printBorder();
        out.println(logo);
        printToUser("Hello! I'm Duke.");
        printToUser("What can I do for you?");
        printBorder();
    }
    
    public String readCommand() {
        out.print("Enter command: ");
        String fullCommand = sc.nextLine().trim();
        return fullCommand;
    }
    
    public void showError(String errorMessage) {
       printToUser("☹ OOPS!!! " + errorMessage);
    }
    
}
