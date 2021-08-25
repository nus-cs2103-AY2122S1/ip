public class EventCommand extends SaberCommand {
    private Event event;
    private boolean isMissingDescription;
    private boolean isMissingTime;
    private boolean isParsingTimeError = false;

    private EventUI eventUI = new EventUI();

    public EventCommand (String eventTask,
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

    public void execute (TaskList taskList) {
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

    public boolean isExit() {
        return false;
    }
}
