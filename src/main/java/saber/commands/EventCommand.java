package saber.commands;

import saber.task.Event;
import saber.exceptions.SaberTimeParserException;
import saber.TaskList;
import saber.ui.EventUI;

/**
 * A class to encapsulate an EventCommand
 */
public class EventCommand extends SaberCommand {
    private Event event;
    private boolean isMissingDescription;
    private boolean isMissingTime;
    private boolean isParsingTimeError = false;

    private EventUI eventUI = new EventUI();

    /**
     * A constructor for EventCommand
     * @param eventTask the description of the event to be added
     * @param eventTime the time of the event
     * @param isMissingDescription whether the event description is missing in the command
     * @param isMissingTime whether the event time is missing in the command
     */
    public EventCommand(String eventTask,
                            String eventTime,
                            boolean isMissingDescription,
                            boolean isMissingTime) {
        try {
            this.event = new Event(eventTask, eventTime, false);
        } catch (SaberTimeParserException e) {
            this.isParsingTimeError = true;
        }
        this.isMissingDescription = isMissingDescription;
        this.isMissingTime = isMissingTime;
    }

    /**
     * A function to execute the EventCommand
     * @param taskList the TaskList to which the newly created event is added to
     */
    public void execute(TaskList taskList) {
        if (isMissingDescription) {
            eventUI.showMissingDescriptionError();
            return;
        }
        if (isMissingTime) {
            eventUI.showMissingTimeError();
            return;
        }
        if (isParsingTimeError) {
            eventUI.showParsingTimeError();
            return;
        }
        taskList.add(event);
        int totalTask = taskList.size();
        eventUI.setSuccessMessage(event, totalTask);
        eventUI.showSuccess();
    }

    /**
     * A function to determine whether the current command is a terminating command (a ByeCommand)
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
