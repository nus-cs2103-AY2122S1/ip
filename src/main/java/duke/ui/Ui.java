import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
   
    private final Scanner sc;
    private final PrintStream out;
    
    public Ui() {
        this.sc = new Scanner(System.in);
        this.out = System.out;
    }
    
    public void welcome() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        out.println("\t____________________________________________________________");
        out.println(logo);
        out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        out.println("\t____________________________________________________________");
    }
    
    public String readCommand() {
        out.print("Enter command:");
        return sc.nextLine();
    }
    
    public void showError(String errorMessage) {
       out.println("\t☹ OOPS!!! " + errorMessage);
    }

    public void end() {
        out.println("\t____________________________________________________________");
        out.println("\tBye. Hope to see you again soon!");
        out.println("\t____________________________________________________________");
    }

    public void println(String message) {
        out.println("\t" + message);
    }

    public void printBorder() {
        System.out.println("\t____________________________________________________________");
    }
    
}
