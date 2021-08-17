package jarvis.action;

import jarvis.message.OutputMessage;
import jarvis.output.Output;
import jarvis.task.Event;
import jarvis.task.TaskList;

public class EventAction extends Action {
    private final String eventDescription;
    private final String eventTime;

    public EventAction(String userInputWithoutActionTrigger) {
        String[] splitStrings = userInputWithoutActionTrigger.split("/at", 2);
        this.eventDescription = splitStrings[0].trim();
        this.eventTime = splitStrings[1].trim();
    }

    @Override
    public void execute(TaskList taskList) {
        Event newEvent = taskList.addEvent(eventDescription, eventTime);
        OutputMessage eventTaskAddedMessage = new OutputMessage("Got it. I have added this task:\n\t\t"
                + newEvent.toString()
                + "\n\t"
                + taskList.taskListSummary()
        );
        Output.showFormattedOutputMessage(eventTaskAddedMessage);
    }
}
