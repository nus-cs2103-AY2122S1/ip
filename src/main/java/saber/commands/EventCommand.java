package saber.commands;

import saber.exceptions.SaberTimeParserException;
import saber.task.Event;
import saber.tasklist.TaskList;
import saber.ui.EventUI;

/**
 * Encapsulates an EventCommand
 */
public class EventCommand extends SaberCommand {
    private Event event;
    private boolean isMissingDescription;
    private boolean isMissingTime;
    private boolean isParsingTimeError = false;

    private EventUI eventUI = new EventUI();

    /**
     * Constructs for EventCommand
     *
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
     * Executes the EventCommand
     *
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
     * {@inheritdoc}
     */
    public String getResponse(TaskList taskList) {
        if (isMissingDescription) {
            return eventUI.getMissingDescriptionError();
        }
        if (isMissingTime) {
            return eventUI.getMissingTimeError();
        }
        if (isParsingTimeError) {
            return eventUI.getParsingTimeError();
        }
        taskList.add(event);
        int totalTask = taskList.size();
        eventUI.setSuccessMessage(event, totalTask);
        return eventUI.getSuccessMessage();
    }
}
