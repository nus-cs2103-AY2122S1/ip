package com.iP.yiheng;

import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final TaskList task = new TaskList();
    private static final Scanner scanner = new Scanner(System.in);
    private static final Parser parser = new Parser();
    private static final UserInterface userInterface = new UserInterface();

    public static void main(String[] args) {

        userInterface.greet();
        task.loadArrayList(); // Loads the array list based on our file on hard disk
        boolean breakWhile;

        while (scanner.hasNext()) {
            String firstWord = scanner.next().toLowerCase(Locale.ROOT);
            breakWhile = parser.firstCommandParser(firstWord, scanner);
            if (breakWhile) break;
        }
    }
}
