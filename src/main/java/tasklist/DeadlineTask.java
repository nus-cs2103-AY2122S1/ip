package tasklist;

import java.time.LocalDate;
import java.time.LocalTime;

import exception.InvalidCommandFormatException;
import exception.InvalidDateTimeFormatException;
import exception.InvalidFormatInStorageException;
import exception.InvalidNumOfStringPartsException;
import parser.CommandParser;
import parser.DateTimeParser;
import type.CommandTypeEnum;
import type.DateFormatTypeEnum;
import type.TaskIconTypeEnum;
import type.TimeFormatTypeEnum;

/**
 * Encapsulates a task with a deadline.
 */
public class DeadlineTask extends Task {
    private static final String SPLITTER_ACTION_TIME = "/by";
    private static final String SPLITTER_DATE_TIME = " ";
    private LocalDate date;
    private LocalTime time;

    private DeadlineTask(String description, boolean isDone, LocalDate date, LocalTime time) {
        super(description, isDone);
        this.date = date;
        this.time = time;
    }

    /**
     * Creates a deadline task from a description.
     *
     * @param description Description including action and deadline information.
     * @return Deadline task.
     * @throws InvalidDateTimeFormatException If the description has an invalid datetime format.
     * @throws InvalidCommandFormatException If the description has an invalid format.
     */
    public static DeadlineTask createTask(String description)
            throws InvalidDateTimeFormatException, InvalidCommandFormatException {
        try {
            String[] actionAndDateTimeDescriptions = CommandParser.splitStringBySplitter(
                    description, SPLITTER_ACTION_TIME);
            CommandParser.validateCorrectNumOfParts(2, actionAndDateTimeDescriptions);

            String actionDescription = actionAndDateTimeDescriptions[0];
            String dateTimeDescription = actionAndDateTimeDescriptions[1];

            String[] dateTimeDescriptions = CommandParser.splitStringBySplitter(
                    dateTimeDescription, SPLITTER_DATE_TIME);
            CommandParser.validateCorrectNumOfParts(2, dateTimeDescriptions);
            LocalDate date = DateTimeParser.changeDateStringToDate(
                    dateTimeDescriptions[0], DateFormatTypeEnum.INPUT.toString());
            LocalTime time = DateTimeParser.changeTimeStringToTime(
                    dateTimeDescriptions[1], TimeFormatTypeEnum.INPUT.toString());

            return new DeadlineTask(actionDescription, false, date, time);
        } catch (InvalidNumOfStringPartsException e) {
            throw new InvalidCommandFormatException(CommandTypeEnum.DEADLINE);
        }
    }

    /**
     * Formats the task in string form, displaying the task type, status, description and deadline.
     *
     * @return Task in a displayed string format.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s %s)",
                TaskIconTypeEnum.DEADLINE.toString(),
                super.toString(),
                DateTimeParser.changeDateToDateString(this.date, DateFormatTypeEnum.OUTPUT.toString()),
                DateTimeParser.changeTimeToTimeString(this.time, TimeFormatTypeEnum.OUTPUT.toString())
        );
    }

    /**
     * Formats the task to storage string form.
     *
     * @return Task in storage string format.
     */
    @Override
    public String toStorageString() {
        return String.format("[%s]%s %s %s %s",
                TaskIconTypeEnum.DEADLINE.toString(),
                super.toString(),
                SPLITTER_ACTION_TIME,
                DateTimeParser.changeDateToDateString(this.date, DateFormatTypeEnum.INPUT.toString()),
                DateTimeParser.changeTimeToTimeString(this.time, TimeFormatTypeEnum.INPUT.toString())
        );
    }

    /**
     * Creates an app representation of a deadline task from the storage representation of the task.
     *
     * @param description Storage representation of a deadline task.
     * @return App representation of a deadline task.
     * @throws InvalidFormatInStorageException If there is an invalid format in storage.
     */
    public static DeadlineTask createTaskFromStoredString(String description) throws InvalidFormatInStorageException {
        String dateFormat = DateFormatTypeEnum.INPUT.toString();
        String timeFormat = TimeFormatTypeEnum.INPUT.toString();

        try {
            boolean isDone = Task.isStorageTaskDone(description);

            int descriptionStartPos = 3;
            String[] actionAndDateTimeDescriptions = CommandParser.splitStringBySplitter(
                    description.substring(descriptionStartPos),
                    SPLITTER_ACTION_TIME
            );
            CommandParser.validateCorrectNumOfParts(2, actionAndDateTimeDescriptions);

            String actionDescription = actionAndDateTimeDescriptions[0];
            String dateTimeDescription = actionAndDateTimeDescriptions[1];

            String[] dateTimeDescriptions = CommandParser.splitStringBySplitter(
                    dateTimeDescription, SPLITTER_DATE_TIME);
            CommandParser.validateCorrectNumOfParts(2, dateTimeDescriptions);
            LocalDate date = DateTimeParser.changeDateStringToDate(dateTimeDescriptions[0], dateFormat);
            LocalTime time = DateTimeParser.changeTimeStringToTime(dateTimeDescriptions[1], timeFormat);

            return new DeadlineTask(actionDescription, isDone, date, time);
        } catch (InvalidDateTimeFormatException | InvalidNumOfStringPartsException e) {
            String expectedFormat = String.format("[ ] <description> %s %s %s",
                    SPLITTER_ACTION_TIME, dateFormat, timeFormat);
            throw new InvalidFormatInStorageException(description, expectedFormat);
        }
    }

    @Override
    protected boolean isDuplicateOf(Task task) {
        // A different type of task is definitely not a duplicate
        if (!(task instanceof DeadlineTask)) {
            return false;
        }

        // A different description means the task is definitely not a duplicate
        if (!this.isSameDescription(task)) {
            return false;
        }

        DeadlineTask deadlineTask = (DeadlineTask) task;
        if (!this.date.equals(deadlineTask.date)) {
            return false;
        }

        return this.time.equals(deadlineTask.time);
    }
}
