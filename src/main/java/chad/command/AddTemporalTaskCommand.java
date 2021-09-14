package chad.command;

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
    public AddTemporalTaskCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    abstract String getTimeRelation();

    @Override
    void parseCommand(String[] tokens) throws ChadInvalidCommandException {
        int timeRelationIndex = findTimeRelationIndex(tokens);
        int timeStartIndex = timeRelationIndex + 1;
        String taskDescription = getTokenSequence(tokens, 1, timeRelationIndex);
        String timeStr = getTokenSequence(tokens, timeStartIndex, tokens.length);
        checkTaskDescriptionLength(taskDescription);
        checkTimeStringLength(timeStr);
        parseTime(timeStr);
        setTaskDescription(taskDescription);
        setTask(createTask());
    }

    LocalDateTime getTaskTime() {
        return time;
    }

    private int findTimeRelationIndex(String[] tokens) {
        String timeRelationToken = String.format("/%s", getTimeRelation());
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals(timeRelationToken)) {
                return i;
            }
        }
        return tokens.length;
    }

    private void checkTaskDescriptionLength(String taskDescription) throws ChadInvalidCommandException {
        if (taskDescription.length() == 0) {
            throw new ChadInvalidCommandException(String.format("A description is required for \"%s\" commands.",
                    getCommandType().getCommandDescription()));
        }
    }

    private void checkTimeStringLength(String timeStr) throws ChadInvalidCommandException {
        if (timeStr.length() == 0) {
            throw new ChadInvalidCommandException(String.format("A date and time is required for \"%s\" commands.",
                    getCommandType().getCommandDescription()));
        }
    }

    private void parseTime(String timeStr) throws ChadInvalidCommandException {
        try {
            time = LocalDateTime.parse(timeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ChadInvalidCommandException(String
                    .format("The date and time should be in the \"%s\" format.", DATE_TIME_FORMAT_PATTERN));
        }
    }
}
