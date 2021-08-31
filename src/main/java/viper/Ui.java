package viper;

import tasks.Task;

import java.util.Scanner;

/**
 * deals with interactions with the user
 */
public class Ui {
    private static final String INDENT = "       ";
    private static final String DIVIDER = "\n ---------------------------------------------------------- \n";

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showMessage(String[] strings) {
        System.out.println(DIVIDER);

        for (String string : strings) {
            System.out.println(INDENT + string);
        }
        System.out.println(DIVIDER);
    }

    public void printLine(String str) {
        System.out.println(INDENT + str);
    }
    
    public void printDiv() {
        System.out.println(DIVIDER);
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

    public void showListHeader(String[] strings) {
        System.out.println(DIVIDER);
        for (String string : strings) {
            System.out.println(INDENT + string);
        }
    }
    
    public void showList(TaskList taskList, String keyword) {
        System.out.println(DIVIDER);
        if (taskList.getSize() > 0) {
            printLine("This is what I have found based on the keyword: " + keyword);
            for (int i = 0; i < taskList.getSize(); i++) {
                int taskNo = i + 1;
                Task curr = taskList.getTask(i);
                printLine(taskNo + "." + curr.toString());
            }
        } else {
            printLine("Oops, based on your keyword: " + keyword + ", I am not able to find any match :(");
        }
        System.out.println(DIVIDER);
    }
}
