package saber.commands;

import saber.exceptions.SaberTimeParserException;
import saber.task.Deadline;
import saber.tasklist.TaskList;
import saber.ui.DeadlineUI;

/**
 * Encapsulates a Deadline Command
 */
public class DeadlineCommand extends SaberCommand {
    private Deadline deadline;
    private boolean isMissingDescription;
    private boolean isMissingTime;
    private boolean isParsingTimeError = false;

    private DeadlineUI deadlineUI = new DeadlineUI();

    /**
     * Constructs for DeadlineCommand
     *
     * @param deadlineTask the description of the deadline task to be added
     * @param deadlineTime the time the deadline task should be due by
     * @param isMissingDescription whether the deadline task description is missing in the command
     * @param isMissingTime whether the deadline task time is missing in the command
     */
    public DeadlineCommand(String deadlineTask,
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

    /**
     * Executes the DeadlineCommand
     *
     * @param taskList the TaskList to which the newly created deadline task is added to
     */
    public void execute(TaskList taskList) {
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

    /**
     * {@inheritdoc}
     */
    public String getResponse(TaskList taskList) {
        if (isMissingDescription) {
            return deadlineUI.getMissingDescriptionError();
        }
        if (isMissingTime) {
            return deadlineUI.getMissingTimeError();
        }
        if (isParsingTimeError) {
            return deadlineUI.getParsingTimeError();
        }
        taskList.add(deadline);
        int totalTask = taskList.size();
        deadlineUI.setSuccessMessage(deadline, totalTask);
        return deadlineUI.getSuccessMessage();
    }
}
