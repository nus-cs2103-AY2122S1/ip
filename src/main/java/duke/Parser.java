package duke;

import java.util.Scanner;

/**
 * Parse user's input, identify whether it is a valid command and calls relevant method(s) to execute the command.
 */

public class Parser {
    private final TaskList tasks;
    private final ParserUi ui;

    /**
     * The constructor for a Parser Object.
     */
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
     *
     * @param userInput input from the user.
     * @return whether the user input can be taken as a delete command.
     */
    private boolean checkDeleteCommand(String userInput) {
        String copy = userInput.replace("delete", "");
        copy = copy.replaceAll("[0-9]", "");
        copy = copy.trim();
        return copy.isEmpty();
    }

    /**
     * Similar to isDeleteCommand This method checks if a String starting with done is a done command.
     * A done command is a string with the keyword done followed by numbers and spaces only.
     *
     * @param userInput input from the user.
     * @return whether the user input can be taken as a done command.
     **/
    private boolean checkDoneCommand(String userInput) {
        String copy = userInput.replace("done", "");
        //Learnt from https://attacomsian.com/blog/java-extract-digits-from-string
        copy = copy.replaceAll("[0-9]", "");
        copy = copy.trim();
        return copy.isEmpty();
    }

    /**
     * Parses the user's command and calls relevant method to execute the command.
     *
     * @param scanner the scanner from ParserUi that reads the user's input.
     */
    public void parse(Scanner scanner) {
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                ui.printUserInputRecord(tasks.getStorage().getUserInputRecords());
            } else if (userInput.startsWith("done")) {
                if (checkDoneCommand(userInput)) {
                    tasks.markAsDone(userInput, tasks.getStorage().getUserInputRecords());
                } else {
                    ui.printCannotInterpretMessage();
                }
            } else if (userInput.equals("deleteAll")) {
                tasks.deleteAll(tasks.getStorage().getUserInputRecords());
            } else if (userInput.startsWith("delete")) {
                if (checkDeleteCommand(userInput)) {
                    tasks.delete(userInput, tasks.getStorage().getUserInputRecords());
                } else {
                    ui.printCannotInterpretMessage();
                }
            } else if (userInput.startsWith("save ")) {
                tasks.getStorage().save(userInput);
            } else if (userInput.startsWith("load ")) {
                tasks.getStorage().load(userInput);
            } else if (userInput.startsWith("find")) {
                tasks.search(userInput, tasks.getStorage().getUserInputRecords());
            } else if (userInput.equals("help")) {
                ui.printHelpMessage();
            } else {
                tasks.add(userInput, tasks.getStorage().getUserInputRecords());
            }
            userInput = scanner.nextLine();
        }
    }
}
