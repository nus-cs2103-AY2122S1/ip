package Duke.Commands;

import Duke.Duke;
import Duke.Todo.Todo;

class DoneTodoCommand extends Command {
    private static final String DONE_TODO_SUCCESS_MESSAGE = "Good job! I've marked this todo as done:\n\t%s";
    private static final String KEYWORD = "done";

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        int todoIndex = Integer.parseInt(input.getArgs()) - 1;
        Todo todo = duke.getTodoList().get(todoIndex);
        todo.markAsDone();
        duke.say(String.format(DONE_TODO_SUCCESS_MESSAGE, todo));
    }

    @Override
    protected String getKeyword() {
        return KEYWORD;
    }
}
