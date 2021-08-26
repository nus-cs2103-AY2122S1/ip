package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String END = "    ---------------------------------------------------------------------------------";

    private static final String INDENTATION = "     ";

    public String readInput() {
        return sc.nextLine();
    }
    public void printBreak() {
        System.out.println(END);
    }

    public void printGreetings() {
        System.out.println("Hello from\n" + LOGO);
    }

    public void printList(List list) {
        printBreak();
        ArrayList<Task> todos = list.getList();
        todos.trimToSize();
        if (todos.size() == 0) {
            System.out.println(INDENTATION + " ٩(◕‿◕｡)۶ Ehe no task for now.");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                System.out.println(INDENTATION + (i + 1) + ": " + todos.get(i).toString());
            }
        }
        printBreak();
    }


    public void printAdd(Task task, int taskLength) {
        printBreak();
        System.out.println(INDENTATION + "added: " + task.toString());
        System.out.println("\n" + INDENTATION + "You have " + taskLength + " task(s) to go! (]＞＜)]");
        printBreak();
    }

    public void printBye() {
        printBreak();
        System.out.println(INDENTATION + "Bye. Hope to see you again soon! (´｡• ω •｡`)");
        printBreak();
    }

    public void printDone(String completedTask){
        printBreak();
        System.out.println(INDENTATION + "(´• ω •`) What a rarity! This task has been marked as done:");
        System.out.println(INDENTATION + completedTask);
        printBreak();
    }

    public void printDelete(String deletedTask, int listLength) {
        printBreak();
        System.out.println(INDENTATION + "(￢_￢) Ok... This task has been deleted:");
        System.out.println(INDENTATION + "deleted: " + deletedTask);
        System.out.println("\n" + INDENTATION + "You have " +listLength + " task(s) to go! (]＞＜)]");
        printBreak();
    }

    public void printFileCreated() {
        printBreak();
        System.out.println(INDENTATION + "╰(▔∀▔)╯ File created!");
        printBreak();
    }

    public void printFileError(IOException e) {
        printBreak();
        System.out.println(INDENTATION + "╮(￣ω￣;)╭ File Error..." + e.getMessage());
        printBreak();
    }

    public void printError(String message, String emoticon) {
        printBreak();
        System.out.println(INDENTATION + emoticon + " " + message);
        printBreak();
    }

    public void printError(String message) {
        printBreak();
        System.out.println(INDENTATION + message);
        printBreak();
    }
}
