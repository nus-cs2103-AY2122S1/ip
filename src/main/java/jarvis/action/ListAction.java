package jarvis.action;

import jarvis.message.OutputMessage;
import jarvis.output.Output;
import jarvis.task.TaskList;

public class ListAction extends Action {
    @Override
    public void execute(TaskList taskList) {
        OutputMessage taskListMessage = new OutputMessage(taskList.toString());
        Output.showFormattedOutputMessage(taskListMessage);
    }
}
