package blue.handler;

import blue.TaskList;

public class ExitHandler extends CommandHandler {
    private static final String EXIT_CONTENT = "Bye! (Yay! Time to rest!)";

    public ExitHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    public String handle(String input) {
        return EXIT_CONTENT;
    }
}
