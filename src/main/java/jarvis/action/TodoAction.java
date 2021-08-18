package jarvis.action;

import jarvis.exception.TaskDetailsEmptyException;
import jarvis.message.OutputMessage;
import jarvis.output.Output;
import jarvis.task.TaskList;
import jarvis.task.Todo;

public class TodoAction extends Action {
    private final String todoDescription;

    public TodoAction(String todoDescription) throws TaskDetailsEmptyException {
        if (todoDescription.equals("")) {
            throw new TaskDetailsEmptyException("description");
        }
        this.todoDescription = todoDescription;
    }

    @Override
    public void execute(TaskList taskList) {
        Todo newTodo = taskList.addTodo(todoDescription);
        OutputMessage todoAddedMessage = new OutputMessage("Got it. I have added this task:\n\t\t"
                + newTodo.toString()
                + "\n\t"
                + taskList.taskListSummary()
        );
        Output.showFormattedOutputMessage(todoAddedMessage);
    }
}
