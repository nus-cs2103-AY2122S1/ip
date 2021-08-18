package jarvis.action;

import jarvis.exception.TaskDetailsEmptyException;
import jarvis.output.Output;
import jarvis.task.TaskList;
import jarvis.task.Todo;

public class TodoAction extends Action {
    private String todoDescription;

    public TodoAction(String todoDescription) throws TaskDetailsEmptyException {
        if (todoDescription.equals("")) {
            throw new TaskDetailsEmptyException("description");
        }
        this.todoDescription = todoDescription.trim();
    }

    @Override
    public void execute(TaskList taskList) {
        Todo newTodo = taskList.addTodo(todoDescription);
        Output.showTaskAddedMessage(newTodo, taskList);
    }
}
