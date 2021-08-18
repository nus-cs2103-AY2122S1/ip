package core;

import commands.*;

import java.util.Scanner;

public class Parser {
    public static Command parse(String input) {
        String[] splittedInput = input.split(" ");
        switch (splittedInput[0]) {
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(Integer.valueOf(splittedInput[1]));
            case "bye":
                return new ByeCommand();
            default:
                return new AddTaskCommand(input);
        }
    }

    // Add static method to check validity

}
