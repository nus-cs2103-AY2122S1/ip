package saber.commands;

import saber.*;
import saber.exceptions.SaberTimeParserException;
import saber.task.Deadline;
import saber.ui.DeadlineUI;

public class DeadlineCommand extends SaberCommand {
    private Deadline deadline;
    private boolean isMissingDescription;
    private boolean isMissingTime;
    private boolean isParsingTimeError = false;

    private DeadlineUI deadlineUI = new DeadlineUI();

    public DeadlineCommand (String deadlineTask,
                            String deadlineTime,
                            boolean isMissingDescription,
                            boolean isMissingTime) {
        try {
            this.deadline = new Deadline(deadlineTask, deadlineTime, false);
        } catch (SaberTimeParserException e) {
            this.isParsingTimeError = true;
        }
        this.isMissingDescription = isMissingDescription;
        this.isMissingTime = isMissingTime;
    }

    public void execute (TaskList taskList) {
        if (isMissingDescription) {
            deadlineUI.showMissingDescriptionError();
            return;
        }
        if (isMissingTime) {
            deadlineUI.showMissingTimeError();
            return;
        }
        if (isParsingTimeError) {
            deadlineUI.showParsingTimeError();
            return;
        }
        taskList.add(deadline);
        int totalTask = taskList.size();
        deadlineUI.setSuccessMessage(deadline, totalTask);
        deadlineUI.showSuccess();
    }

    public boolean isExit() {
        return false;
    }
}
