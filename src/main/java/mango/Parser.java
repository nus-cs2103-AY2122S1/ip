package mango;

/**
 * Represents a parser that makes sense of the user's commands by looking for keywords in the input.
 */
public class Parser {

    /**
     * Parses user input through the Scanner, and manipulates the list of tasks according to the commands.
     *
     * @param tasks The list of tasks to be manipulated.
     * @param input The user input to be parsed.
     * @param mango The instance of Mango to provide a farewell message.
     * @return The confirmation message for the user command.
     */
    public static String parse(TaskList tasks, String input, Mango mango) {
        if (input.equals("bye")) {
            return mango.exit();
        }
        
        String message = "";

        try {
            if (input.equals("list")) {
                message = tasks.toString();
            } else if (input.contains("done")) {
                int indexOfCompletedTask = Character.getNumericValue(input.charAt(5));
                message = tasks.completeTask(indexOfCompletedTask);
            } else if (input.contains("delete")) {
                int indexOfDeletedTask = Character.getNumericValue(input.charAt(7));
                message = tasks.deleteTask(indexOfDeletedTask);
            } else if (input.contains("find")) {
                String searchTerms = input.split(" ", 2)[1];
                message = tasks.findTasks(searchTerms);
            } else {
                message = tasks.addTask(input); // addTask will throw a MangoException if the input is unrecognisable
            }
        } catch (MangoException e) {
            return e.getMessage();
        }
        
        return message;
    }
}
