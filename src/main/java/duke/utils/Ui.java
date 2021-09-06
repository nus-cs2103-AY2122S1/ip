package duke.utils;

import java.util.Scanner;

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

    public Ui(){
        scanner = new Scanner(System.in);
    }

    public String readCommand(){
        return scanner.nextLine();
    }

    public void start(){
        System.out.println(INTRO);
    }

    public void end(){
        System.out.println(ENDING);
    }

    public void showLine(){
        System.out.println( "\n________________________________________________________ \n");
    }

    public void showError(Exception e){
        System.out.println(e.toString());
    }




}
