package duke;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    static String dash = "__________________________________";

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner in;
    private final PrintStream out;

    public Ui(){
        this.in = new Scanner(System.in);
        this.out = new PrintStream(System.out);
    }

    public void showWelcomeMessage() {
        out.println("Hello from\n" + logo);
        out.println(dash);
        out.println("Howdy! I'm Duke" + '\n'+ "How may I assist you?");
        out.println(dash);
    }

    public void showFarewellMessage() {
        System.out.println(dash);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(dash);
        this.in.close();
        System.exit(0);
    }

    public String getUserCommand() {
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }



}