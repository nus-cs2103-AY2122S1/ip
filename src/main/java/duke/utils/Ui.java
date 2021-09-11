package duke.utils;

import java.util.Scanner;

/**
 * Class that handles taking in user input
 */
public class Ui {

    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String INTRO = "Hello from\n" + LOGO + "\n" + "What can I do for you?";
    private static final String ENDING = "Bye. Hope to see you again soon!\n";

    private Scanner scanner;

    /**
     * Constructor that initializes a Ui object
     */
    public Ui(){
        scanner = new Scanner(System.in);
    }

    /**
     *
     * @return String contents entered into the CLI
     */
    public String readCommand(){
        return scanner.nextLine();
    }

    /**
     * Returns the intro statement when app first launches
     */
    public void start(){
        System.out.println(INTRO);
    }

    /**
     * returns the ending statement before app closes
     */
    public void end(){
        System.out.println(ENDING);
    }

    /**
     * Prints a dashed divider line
     */
    public void showLine(){
        System.out.println( "\n________________________________________________________ \n");
    }

    /**
     * Prints out the string message of the given Exception.
     * @param e Exception
     */
    public void showError(Exception e){
        System.out.println(e.toString());
    }




}
