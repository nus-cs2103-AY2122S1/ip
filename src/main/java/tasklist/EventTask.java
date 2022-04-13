package tasklist;

import java.time.LocalDate;
import java.time.LocalTime;

import exception.InvalidCommandFormatException;
import exception.InvalidDateTimeFormatException;
import exception.InvalidFormatInStorageException;
import exception.InvalidNumOfStringPartsException;
import exception.InvalidTimePeriodException;
import parser.CommandParser;
import parser.DateTimeParser;
import type.CommandTypeEnum;
import type.DateFormatTypeEnum;
import type.TaskIconTypeEnum;
import type.TimeFormatTypeEnum;

/**
 * Encapsulates a task with that will occur on a date, at a specified time period.
 */
public class EventTask extends Task {
    private static final String SPLITTER_ACTION_TIME = "/at";
    private static final String SPLITTER_DATE_TIME = " ";
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private EventTask(String description, boolean isDone, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description, isDone);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates an event task from a description.
     *
     * @param description Description including action, date and time information.
     * @return Event task.
     * @throws InvalidDateTimeFormatException If the description has an invalid datetime format.
     * @throws InvalidCommandFormatException If the description has an invalid format.
     * @throws InvalidTimePeriodException If the start time is after the end time.
     */
    public static EventTask createTask (String description) throws
            InvalidDateTimeFormatException,
            InvalidCommandFormatException,
            InvalidTimePeriodException {
        try {
            String[] actionAndTimeDescriptions = CommandParser.splitStringBySplitter(
                    description,
                    EventTask.SPLITTER_ACTION_TIME
            );
            CommandParser.validateCorrectNumOfParts(2, actionAndTimeDescriptions);
            String actionDescription = actionAndTimeDescriptions[0];
            String dateTimeDescription = actionAndTimeDescriptions[1];

            String[] dateTimeDescriptions = CommandParser.splitStringBySplitter(
                    dateTimeDescription, SPLITTER_DATE_TIME);
            CommandParser.validateCorrectNumOfParts(3, dateTimeDescriptions);
            LocalDate date = DateTimeParser.changeDateStringToDate(
                    dateTimeDescriptions[0], DateFormatTypeEnum.INPUT.toString());
            LocalTime startTime = DateTimeParser.changeTimeStringToTime(
                    dateTimeDescriptions[1], TimeFormatTypeEnum.INPUT.toString());
            LocalTime endTime = DateTimeParser.changeTimeStringToTime(
                    dateTimeDescriptions[2], TimeFormatTypeEnum.INPUT.toString());
            validateStartTimeBeforeOrAtEndTime(startTime, endTime);

            return new EventTask(actionDescription, false, date, startTime, endTime);
        } catch (InvalidNumOfStringPartsException e) {
            throw new InvalidCommandFormatException(CommandTypeEnum.EVENT);
        }
    }

    /**
     * Formats the task in string form, displaying the task type, status, description and time.
     *
     * @return Task in a displayed string format.
     */
    @Override
    public String toString() {
        return String.format(
                "[%s]%s (at: %s %s - %s)",
                TaskIconTypeEnum.EVENT.toString(),
                super.toString(),
                DateTimeParser.changeDateToDateString(this.date, DateFormatTypeEnum.OUTPUT.toString()),
                DateTimeParser.changeTimeToTimeString(this.startTime, TimeFormatTypeEnum.OUTPUT.toString()),
                DateTimeParser.changeTimeToTimeString(this.endTime, TimeFormatTypeEnum.OUTPUT.toString())
        );
    }

    /**
     * Formats the task to storage string form.
     *
     * @return Task in storage string format.
     */
    public String toStorageString() {
        return String.format(
                "[%s]%s %s %s %s %s",
                TaskIconTypeEnum.EVENT.toString(),
                super.toString(),
                SPLITTER_ACTION_TIME,
                DateTimeParser.changeDateToDateString(this.date, DateFormatTypeEnum.INPUT.toString()),
                DateTimeParser.changeTimeToTimeString(this.startTime, TimeFormatTypeEnum.INPUT.toString()),
                DateTimeParser.changeTimeToTimeString(this.endTime, TimeFormatTypeEnum.INPUT.toString())
        );
    }

    /**
     * Creates an app representation of an event task from the storage representation of the task.
     *
     * @param description Storage representation of an event task.
     * @return App representation of an event task.
     * @throws InvalidFormatInStorageException If there is an invalid format in storage.
     */
    public static EventTask createTaskFromStoredString(String description) throws InvalidFormatInStorageException {
        String dateFormat = DateFormatTypeEnum.INPUT.toString();
        String timeFormat = TimeFormatTypeEnum.INPUT.toString();

        try {
            boolean isDone = Task.isStorageTaskDone(description);

            int descriptionStartPos = 3;
            String[] actionAndTimeDescriptions = CommandParser.splitStringBySplitter(
                    description.substring(descriptionStartPos),
                    SPLITTER_ACTION_TIME
            );
            CommandParser.validateCorrectNumOfParts(2, actionAndTimeDescriptions);

            String actionDescription = actionAndTimeDescriptions[0];
            String timeDescription = actionAndTimeDescriptions[1];

            String[] dateTimeDescriptions = CommandParser.splitStringBySplitter(timeDescription, SPLITTER_DATE_TIME);
            CommandParser.validateCorrectNumOfParts(3, dateTimeDescriptions);
            LocalDate date = DateTimeParser.changeDateStringToDate(dateTimeDescriptions[0], dateFormat);
            LocalTime startTime = DateTimeParser.changeTimeStringToTime(dateTimeDescriptions[1], timeFormat);
            LocalTime endTime = DateTimeParser.changeTimeStringToTime(dateTimeDescriptions[2], timeFormat);

            return new EventTask(actionDescription, isDone, date, startTime, endTime);
        } catch (InvalidNumOfStringPartsException | InvalidDateTimeFormatException e) {
            String expectedFormat = String.format("[ ] <description> %s %s %s %s",
                    SPLITTER_ACTION_TIME, dateFormat, timeFormat, timeFormat);
            throw new InvalidFormatInStorageException(description, expectedFormat);
        }
    }

    @Override
    protected boolean isDuplicateOf(Task task) {
        // A different type of task is definitely not a duplicate
        if (!(task instanceof EventTask)) {
            return false;
        }

        // A different description means the task is definitely not a duplicate
        if (!this.isSameDescription(task)) {
            return false;
        }

        EventTask eventTask = (EventTask) task;

        if (!this.date.equals(eventTask.date)) {
            return false;
        }

        if (!this.startTime.equals(eventTask.startTime)) {
            return false;
        }

        return this.endTime.equals(eventTask.endTime);
    }

    private static void validateStartTimeBeforeOrAtEndTime(LocalTime startTime, LocalTime endTime)
            throws InvalidTimePeriodException {
        if (startTime.compareTo(endTime) > 0) {
            throw new InvalidTimePeriodException();
        }
    }
}
