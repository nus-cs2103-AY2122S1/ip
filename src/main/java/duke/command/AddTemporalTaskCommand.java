package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a generic "Add Temporal Task" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class AddTemporalTaskCommand extends AddTaskCommand {

    private static final String DATE_TIME_FORMAT_PATTERN = "yyyy/M/d HHmm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN);

    private LocalDateTime time;

    /**
     * Creates an AddTemporalTaskCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public AddTemporalTaskCommand(String command) {
        super(command);
    }

    abstract String getTimeRelation();

    @Override
    void parseCommand(String[] tokens) {
        StringBuilder taskDescriptionSb = new StringBuilder();
        int timeStartIndex = tokens.length;
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals(String.format("/%s", getTimeRelation()))) {
                timeStartIndex = i + 1;
                break;
            }
            taskDescriptionSb.append(token).append(" ");
        }
        StringBuilder timeSb = new StringBuilder();
        for (int i = timeStartIndex; i < tokens.length; i++) {
            timeSb.append(tokens[i]).append(" ");
        }
        String taskDescription = taskDescriptionSb.toString().strip();
        String timeStr = timeSb.toString().strip();
        if (taskDescription.length() == 0) {
            throw new DukeInvalidCommandException(String.format("A description is required for \"%s\" commands.", getCommandType().getCommandDescription()));
        }
        if (timeStr.length() == 0) {
            throw new DukeInvalidCommandException(String.format("A date and time is required for \"%s\" commands.", getCommandType().getCommandDescription()));
        }
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(timeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidCommandException(String
                    .format("The date and time should be in the %s format.", DATE_TIME_FORMAT_PATTERN));
        }
        setTaskDescription(taskDescription);
        this.time = time;
        setTask(createTask());
    }

    LocalDateTime getTaskTime() {
        return time;
    }
}
