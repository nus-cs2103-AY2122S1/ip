package jarvis.action;

import jarvis.message.OutputMessage;
import jarvis.output.Output;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class MarkAsDoneAction extends Action {
    private final int taskIndex;

    public MarkAsDoneAction(String userInputWithoutActionTrigger) {
        this.taskIndex = Integer.parseInt(userInputWithoutActionTrigger.trim()) - 1;
    }

    @Override
    public void execute(TaskList taskList) {
        Task task = taskList.markAsDone(taskIndex);
        OutputMessage doneMessage = new OutputMessage("Nice! I've marked this task as done: \n\t\t" + task.toString());
        Output.showFormattedOutputMessage(doneMessage);
    }
}
