package duke.main;

import java.util.Scanner;

import duke.task.Storage;

public class Ui {
    private Scanner sc;
    private Parser parser;

    public Ui(Scanner sc, TaskList taskList, Storage storage) {
        this.sc = sc;
        this.parser = new Parser(this, taskList, storage);

        showLogo();
    }
    /**
     * Prints the introduction and logo.
     */
    public void showLogo() {
        String logo = " _______       ___      _______   __     __   _\n"
                + "|   ____|     / ^ \\     |   _  \\  \\ \\   / /  | |\n"
                + "|  | ___     / /_\\ \\    |  |_|  |  \\ \\ / /   | |\n"
                + "|  ||_  |   /  ___  \\   |  __  <    \\   /    |_|\n"
                + "|  |__| |  /  /   \\  \\  |  | \\  \\    | |      _ \n"
                + "|_______| /__/\t   \\__\\ |--|  \\--\\   |_|     |_|\n";

        printDoubleDivider();
        System.out.println("Hello! My name is\n" + logo);
        printDoubleDivider();
        System.out.println("How may I help you?");
        printDoubleDivider();
    }

    /**
     * Prompts the user for input.
     */
    public void getInput() {
        System.out.print("Input: ");
        boolean isContinue = parser.handleInput(sc.nextLine());

        if (isContinue) {
            getInput();
        }
    }

    /**
     * Prints a divider using "=".
     */
    public void printDoubleDivider() {
        System.out.println("\n=================================================\n");
    }

    /**
     * Prints a divider using "-".
     */
    public void printSingleDivider() {
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - -\n");
    }
}
