package duke.ui;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner reader;

    String logo = "┏━━┓╋╋╋┏━━┓╋╋╋┏┓\n" +
            "┃┏┓┃╋╋╋┃┏┓┃╋╋┏┛┗┓\n" +
            "┃┗┛┗┳━┳┫┗┛┗┳━┻┓┏┛\n" +
            "┃┏━┓┃┏╋┫┏━┓┃┏┓┃┃\n" +
            "┃┗━┛┃┃┃┃┗━┛┃┗┛┃┗┓\n" +
            "┗━━━┻┛┗┻━━━┻━━┻━┛";


    public Ui() {
        this.reader = new Scanner(System.in);
    }

    public void sayHello() {
        System.out.println("Greetings from\n" + logo);
        this.printLine();
        System.out.println("What can I do for you?");
        this.printLine();
    }

    public String readCommand() {
        return reader.nextLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void markTask(Task task) {
        this.printLine();
        System.out.println(task);
        this.printLine();
    }

    public void printTask(Task task, int index) {
        System.out.println(index + ". " + task);
    }

    public void printStartList() {
        this.printLine();
        System.out.println("Here are the tasks in your list:");
    }

    public void printUnknownCommand() {
        this.printLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        this.printLine();
    }

    public void sayGoodBye() {
        this.printLine();
        System.out.println("Bye. Hope to see you soon!");
        this.printLine();
    }

    public void printDukeException(String message) {
        this.printLine();
        System.out.println(message);
        this.printLine();
    }

    public void printAddedTask(Task task) {
        this.printLine();
        System.out.println("Done! added the following task:");
        System.out.println(task);
        this.printLine();
    }

    public void printDeletedTask(Task task) {
        this.printLine();
        System.out.println("Done! deleted the following task:");
        System.out.println(task);
        this.printLine();
    }

    public void closeScanner() {
        this.reader.close();
    }

    public void showLoadingError() {
        System.out.println("ERROR LOADING FILE");
    }

    public void printMessage(String s) {
        System.out.println(s);
    }
}
