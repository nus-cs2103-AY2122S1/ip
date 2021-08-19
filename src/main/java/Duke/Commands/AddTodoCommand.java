package Duke.Commands;

import Duke.Duke;
import Duke.Task.Todo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class AddTodoCommand extends Command {
    private static final Set<String> KEYWORDS = new HashSet<>(List.of("todo"));
    private static final String ADD_TODO_SUCCESS_MESSAGE = "added: %s";

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        Todo newTodo = new Todo(input.getArgs());
        duke.getTaskList().add(newTodo);
        duke.say(String.format(ADD_TODO_SUCCESS_MESSAGE, newTodo));
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
