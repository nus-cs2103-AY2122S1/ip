package Duke.Commands;

import Duke.Duke;
import Duke.Task.Todo;

class AddTodoCommand extends Command {
    private static final String KEYWORD = "todo";
    private static final String ADD_TODO_SUCCESS_MESSAGE = "added: %s";

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        Todo newTodo = new Todo(input.getArgs());
        duke.getTaskList().add(newTodo);
        duke.say(String.format(ADD_TODO_SUCCESS_MESSAGE, newTodo));
    }

    @Override
    protected String getKeyword() {
        return KEYWORD;
    }
}
