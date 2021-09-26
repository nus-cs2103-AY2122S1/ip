package duke.utils;

import java.util.*;

/**
 * Works as the UI for people to interact with. Communicates with other parts of the program to effect changes.
 */
public class Ui {
    public void showLine(){
        System.out.println("____________________________________________________________");
    }

    public void showWelcome(){
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showLoadingError(){
        System.out.println("Hey bro there was a problem loading please contact the devs!");
    }
    
    public void showError(String e){
        System.out.println(e);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
