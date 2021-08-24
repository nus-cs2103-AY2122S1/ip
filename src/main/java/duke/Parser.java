package duke;

import java.util.Scanner;

/**
 * Parse user's input, identify whether it is a valid command and calls relevant method(s) to execute the command.
 * */

public class Parser {
    private TaskList tasks;
    private ParserUi ui;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.ui = new ParserUi();
    }
    /**
     * This method checks if a String starting with delete is a delete command.
     * A delete command is a string with the keyword delete followed by numbers and spaces only.
     * Examples of valid user inputs:
     * -> delete 3
     * -> delete   4(spaces trimmed)
     * -> delete 1 4(spaces trimmed and interpret as done 14)
     * Examples of invalid user inputs:
     * -> delete task
     * -> delete 2 task
     * @param userInput input from the user.
     * @return whether the user input can be taken as a delete command.
     * */
    private boolean isDeleteCommand(String userInput) {
        String copy = userInput.replace("delete", "");
        copy = copy.replaceAll("[0-9]", "");
        copy = copy.trim();
        return copy.isEmpty();
    }

    /**
     * Similar to isDeleteCommand This method checks if a String starting with done is a done command.
     * A done command is a string with the keyword done followed by numbers and spaces only.
     * @param userInput input from the user.
     * @return whether the user input can be taken as a done command.
     * **/
    private boolean isDoneCommand(String userInput) {
        String copy = userInput.replace("done", "");
        copy = copy.replaceAll("[0-9]", ""); //Learnt from https://attacomsian.com/blog/java-extract-digits-from-string
        copy = copy.trim();
        return copy.isEmpty();
    }

    /**
     * Parse the user's command and calls relevant method to execute the command.
     * @param scanner the scanner from ParserUi that reads the user's input.
     * */
    public void parse(Scanner scanner) {
        String userInput = scanner.nextLine();
        while(!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                ui.printUserInputRecord(tasks.getStorage().getUserInputRecord());
            } else if (userInput.startsWith("done")) {
                if (isDoneCommand(userInput)) {
                    tasks.markAsDone(userInput, tasks.getStorage().getUserInputRecord());
                } else {
                    ui.cannotInterpretMessage();
                }
            } else if (userInput.equals("deleteAll")) {
                tasks.deleteAll(tasks.getStorage().getUserInputRecord());
            } else if (userInput.startsWith("delete")) {
                if (isDeleteCommand(userInput)) {
                    tasks.delete(userInput, tasks.getStorage().getUserInputRecord());
                } else {
                    ui.cannotInterpretMessage();
                }
            } else if (userInput.startsWith("save ")) {
                tasks.getStorage().save(userInput);
            } else if (userInput.startsWith("load ")) {
                tasks.getStorage().load(userInput);
            } else {
                tasks.add(userInput, tasks.getStorage().getUserInputRecord());
            }
            userInput = scanner.nextLine();
        }
    }
}
