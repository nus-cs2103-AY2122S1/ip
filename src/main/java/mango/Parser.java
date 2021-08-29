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
     * @param duke The instance of Mango to provide a farewell message.
     */
    public static String parse(TaskList tasks, String input, Duke duke) {
        String output = "";
        if (!(input.equals("bye"))) {
            try {
                if (input.equals("list")) {
                    output = tasks.printList();
                } else if (input.contains("done")) {
                    output = tasks.complete(Character.getNumericValue(input.charAt(5)));
                } else if (input.contains("delete")) {
                    output = tasks.delete(Character.getNumericValue(input.charAt(7)));
                } else if (input.contains("find")){
                    output = tasks.find(input.split(" ", 2)[1]);
                } else {
                    output = tasks.add(input);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } else {
            output = duke.exit();
        }
        return output;
    }
}
