package duke;

/**
 * Represents a command class that find all tasks with matching keyword.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class FindCommand extends Command {

    /**
     * A constructor for FindCommand.
     *
     * @param tasks A list of current Tasks.
     * @param input User input.
     */
    public FindCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    /**
     * Finds all tasks with matching keyword.
     *
     * @return String representation of a list of matching tasks.
     */
    public String find() {
        Parser parser = new Parser(input);
        String keyword = parser.getKeyword();
        String result = "Here are the matching tasks in your list:";
        int count = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).contains(keyword)) {
                result += "\n" + count + "." + tasks.getTask(i);
                count += 1;
            }
        }
        return result;
    }
}