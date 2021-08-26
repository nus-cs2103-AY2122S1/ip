package viper;

import java.util.Scanner;

/**
 * deals with interactions with the user
 */

public class Ui {
    private static final String indent = "       ";
    private static final String div = "\n ---------------------------------------------------------- \n";

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    public void showMessage (String[] strings) {
        System.out.println(div);
        for (String string : strings) {
            System.out.println(indent + string);
        }
        System.out.println(div);
    }
    
    public void printLine (String str) {
        System.out.println(indent + str);
    }
    
    public void printDiv () {
        System.out.println(div);
    }
    
    public void showWelcome() {
        String[] msg = {"Hello! I'm Viper :)", "I love to help!", "How can I help you today?"};
        showMessage(msg);
    }
    
    public void showBye() {
        String[] msg = {"It was soooo fun talking to you!", "Bye! See you again~", 
                "I will be seeing you again... right?"};
        showMessage(msg);
    }
    
    public void showLoadingError() {
        String[] msg = {"Oh no, there was a problem loading this file...", "Wanna try another file instead?"};
        showMessage(msg);
    }
    
    public void showInvalidTypeError() {
        String[] msg = {"Oh no, I do not understand this type...", "So far, I am only able to understand these types:",
            "Todo, Deadline, Event, List, Delete, Done, Bye"};
        showMessage(msg);
    }
    
    public void showInvalidIndexError() {
        String[] msg = {"Oh no, this index does not exist!", "Please make sure that index < size of tasks"};
        showMessage(msg);
    }
}
