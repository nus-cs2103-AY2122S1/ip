package jarvis.action;

import jarvis.exception.TaskDetailsEmptyException;
import jarvis.output.Output;
import jarvis.task.Event;
import jarvis.task.TaskList;

public class EventAction extends Action {
    private String eventDescription;
    private String eventTime;

    public EventAction(String userInputWithoutActionTrigger) throws TaskDetailsEmptyException {
        String[] splitStrings = userInputWithoutActionTrigger.split("/at", 2);
        this.eventDescription = splitStrings[0].trim();
        if (eventDescription.equals("")) {
            throw new TaskDetailsEmptyException("description");
        }
        if (splitStrings.length < 2) {
            throw new TaskDetailsEmptyException("event time");
        }
        this.eventTime = splitStrings[1].trim();
    }

    @Override
    public void execute(TaskList taskList) {
        Event newEvent = taskList.addEvent(eventDescription, eventTime);
        Output.showTaskAddedMessage(newEvent, taskList);
    }
}
