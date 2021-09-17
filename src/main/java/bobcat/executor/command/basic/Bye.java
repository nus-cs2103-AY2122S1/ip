package bobcat.executor.command.basic;

import bobcat.exception.ExitException;
import bobcat.model.TaskList;

public class Bye extends BasicCommand {
    public static String[] execute(TaskList taskList, String... args) {
        throw new ExitException("Bye! Hope to see you again soon!");
    }
}
