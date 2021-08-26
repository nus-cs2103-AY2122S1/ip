//Ui: deals with interactions with the user
import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void showDivider() {
        System.out.println(DIVIDER);
    }

    public void showWelcomeMessage() {
        String welcome = "Hello! I'm Duke. A friendly chatbot!! :)\n"
                + "What can I do for you?\n";
        System.out.println(DIVIDER + "\n" + LOGO + "\n" + welcome + DIVIDER);
    }

    public void showGoodbyeMessage() {
        String end_message = "Bye. I hope to talk to you again soon! :)";
        System.out.println(end_message + "\n" + DIVIDER);
    }

    public void readCommand() {
        //String prompt_message = "Add to-do list ({input})/ View list (list) / Complete task (done {input}) / End (bye) :";
        String prompt_message = "Please input your command: ";
        System.out.println(prompt_message);
    }
}

//Code to expand on in the future

//    public void printMarkedDone(Task t) {
//        String message = "Nice! I've marked this task as done:\n";
//        printUiMessage(message + t);
//    }
//
//    public void printDeleteMessage(Task t, int size) {
//        String message = "Noted. I've removed this task: \n";
//        String update = "Now you have " + size + " tasks in the list.";
//        printUiMessage(message + t + "\n" + update);
//    }
//
//    public void printAddMessage(Task t, int size) {
//        String message = "Noted. I've added this task: \n";
//        String update = "Now you have " + size + " tasks in the list.";
//        printUiMessage(message + t + "\n" + update);
//    }
//
//    public void printUiMessage(String message) {
//        System.out.println(DIVIDER + "\n"  + message);
//    }
//
//    public void showError(String message) {
//    }
//
//    public void showLoadingError() {
//    }

