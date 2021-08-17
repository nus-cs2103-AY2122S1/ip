package jarvis.action;

import jarvis.message.OutputMessage;
import jarvis.output.Output;
import jarvis.task.Deadline;
import jarvis.task.TaskList;

public class DeadlineAction extends Action {
    private final String taskDescription;
    private final String deadline;

    public DeadlineAction(String userInputWithoutActionTrigger) {
        String[] splitStrings = userInputWithoutActionTrigger.split("/by", 2);
        this.taskDescription = splitStrings[0].trim();
        this.deadline = splitStrings[1].trim();
    }

    @Override
    public void execute(TaskList taskList) {
        Deadline newDeadlineTask = taskList.addTaskWithDeadline(taskDescription, deadline);
        OutputMessage deadlineTaskAddedMessage = new OutputMessage("Got it. I have added this task:\n\t\t"
                + newDeadlineTask.toString()
                + "\n\t"
                + taskList.taskListSummary()
        );
        Output.showFormattedOutputMessage(deadlineTaskAddedMessage);
    }
}
