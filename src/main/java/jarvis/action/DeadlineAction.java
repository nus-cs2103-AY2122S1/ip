package jarvis.action;

import jarvis.exception.InvalidDateTimeInputException;
import jarvis.exception.TaskDetailsEmptyException;
import jarvis.output.Output;
import jarvis.task.Deadline;
import jarvis.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineAction extends Action {
    private String taskDescription;
    private LocalDateTime deadline;
    private static final String INPUT_FORMAT =  "dd-MM-yyyy HHmm";

    public DeadlineAction(String userInputWithoutActionTrigger) throws TaskDetailsEmptyException, InvalidDateTimeInputException {
        String[] splitStrings = userInputWithoutActionTrigger.split("/by", 2);
        this.taskDescription = splitStrings[0].trim();
        if (taskDescription.equals("")) {
            throw new TaskDetailsEmptyException("description");
        }
        if (splitStrings.length < 2) {
            throw new TaskDetailsEmptyException("deadline");
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);

        try {
            this.deadline = LocalDateTime.parse(splitStrings[1].trim(), dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeInputException("deadline", INPUT_FORMAT);
        }
    }

    @Override
    public void execute(TaskList taskList) {
        Deadline newDeadlineTask = taskList.addTaskWithDeadline(taskDescription, deadline);
        Output.showTaskAddedMessage(newDeadlineTask, taskList);
    }
}
