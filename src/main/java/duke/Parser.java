package duke;

import java.util.ArrayList;

/**
 * Parse user's input, identify whether it is a valid command and calls relevant method(s) to execute the command.
 */

public class Parser {
    private final TaskList tasks;

    /**
     * The constructor for a Parser Object.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
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

    public String parse(String userInput) {
        if (userInput.equals("list")) {
            ArrayList<Task> userInputRecords = tasks.getStorage().getUserInputRecords();
            if (userInputRecords.isEmpty()) {
                return "Ah oh, seems like nothing is added yet :( \n" + "Try to input something first! \n";
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Here are the tasks in your list:\n");
                for (int i = 0; i < userInputRecords.size(); i++) {
                    stringBuilder.append("  " + (i + 1) + "." + userInputRecords.get(i) + "\n");
                }
                return stringBuilder.toString();
            }
        } else if (userInput.startsWith("done")) {
            if (checkDoneCommand(userInput)) {
                return tasks.markAsDone(userInput, tasks.getStorage().getUserInputRecords());
            } else {
                return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
            }
        } else if (userInput.equals("deleteAll")) {
            return tasks.deleteAll(tasks.getStorage().getUserInputRecords());
        } else if (userInput.startsWith("delete")) {
            if (checkDeleteCommand(userInput)) {
                return tasks.delete(userInput, tasks.getStorage().getUserInputRecords());
            } else {
                return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
            }
        } else if (userInput.startsWith("save ")) {
            return tasks.getStorage().save(userInput);
        } else if (userInput.startsWith("load ")) {
            return tasks.getStorage().load(userInput);
        } else if (userInput.startsWith("find")) {
            return tasks.search(userInput, tasks.getStorage().getUserInputRecords());
        } else if (userInput.equals("help")) {
            StringBuilder builder = new StringBuilder();
            builder.append("todo <description>\n" );
            builder.append("deadline <description> /by <time in format yyyy-mm-dd>\n");
            builder.append("event <description> /at <time in format yyyy-mm-dd>\n");
            builder.append("save <directory>\n");
            builder.append("load <directory>\n");
            builder.append("done <number>\n");
            builder.append("delete <number>\n");
            builder.append("deleteAll\n");
            builder.append("find <keyword>\n");
            return builder.toString();
        } else if (userInput.startsWith("todo ") || userInput.startsWith("deadline ") || userInput.startsWith("event ")) {
            return tasks.add(userInput, tasks.getStorage().getUserInputRecords());
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
    }
}
