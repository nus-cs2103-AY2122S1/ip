package duke;

import java.util.List;

public class Ui {
    public Ui(){

    };

    public String showWelcome(){
        System.out.println("Welcome. I am your virtual assistant Duke. Sparkle up your day (TM).");
        return "Welcome. I am your virtual assistant Duke. Sparkle up your day (TM).";
    }

    void showList(List<String> list){
        int c = 1;
        for (String line : list) {
            System.out.println(c + ". " + line);
            c++;
        }
        if(c == 1){
            showEmptyListMsg();
        }
    }

    void showEmptyListMsg(){
        System.out.println("The list is empty. Good job.");
    }

    void showFileLocation(String path){
        System.out.println("Your task list is located in: " + path + ". SPARKLEOUS.");
    }

    void sayBye(){
        System.out.println("Have a SPARKULAR day.");
    }

    void showNotANumberMsg(){
        System.err.println("JUST GIVE ME A NUMBER, WHY ARE YOU DOING THIS");
    }

    void showOutOfBoundsMsg(int listsize){
        System.err.println("hello sir there are only " + listsize + " tasks in the list sir");
    }

    void showLessThanZeroMsg(int number){
        System.err.println("HOW CAN I COMPLETE THE TASK AT INDEX " + number + "? IT DOESNT MAKE ANY SENSE");
    }

    void showMarkedAsDone(String task){
        System.out.println(task + " has been marked as done");
    }

    void showAlreadyDone(){
        System.out.println("It is already done. How SPARKTACULAR.");
    }

    void showDeletionMsg(String toBeDeleted,int size){
        System.out.println("task " + toBeDeleted + " has been deleted.\n" +
                "There are " + size + " tasks left in the list.");
    }

    void showNoNameError(){
        System.err.println("I NEED A NAME SIR");
    }

    void showTaskAdded(String task){
        System.out.println(task + " added.");
    }

    void showListSize(int size){
        System.out.println("the list has " + size + " tasks now.");
    }

    void showAlreadyInList(String task){
        System.out.println(task + " is already in the list sir");
    }

    void showNoDateError(){
        System.err.println("BY WHEN? I DONT KNOW AHHHHHHHHHHH");
    }

    void showBadDateError(){
        System.err.println("hello sir I only understand YYYY-MM-DD format");
    }
}
