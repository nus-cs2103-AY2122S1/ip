package bobcat.executor.command.basic;

import bobcat.model.TaskList;

public class Bye extends BasicCommand {
    public static String[] execute(TaskList taskList, String... args) {
        return new String[]{"Bye! Hope to see you again soon!"};
    }
}
