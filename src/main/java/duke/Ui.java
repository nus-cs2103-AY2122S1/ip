package duke;

import java.util.Scanner;

public class Ui {

    int tasks;

    public Ui(int tasks) {
        this.tasks = tasks;
    }

    public void startMessage() {
        String greeting = "Why hello there! It's Duke here!\n" + "How can I help you today master?";
        System.out.println(greeting);
    }

    public String startInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public void byeMessage() {
        System.out.println("See ya again later!");
    }

    public void setItems(int num) {
        tasks = num;
    }

    public void invalidInput() {
        try {
            throw new InputError("Invalid Input");
        } catch (InputError e) {
            System.out.println("Here is the error boss. " + e.getMessage());
            System.out.println("I'm not too sure what you meant.");
            System.out.println("Try again with these keywords.");
            System.out.println("todo deadline event");
        }
    }

    public void errorMessage(InputError e) {
        System.out.println("Here is the error boss. " + e.getMessage());
    }
}