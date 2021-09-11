package tasklist;

import java.time.LocalDate;
import java.time.LocalTime;

import exception.InvalidCommandFormatException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatInStorageException;
import parser.CommandParser;
import parser.DateTimeParser;
import type.CommandTypeEnum;
import type.DateFormatTypeEnum;
import type.TaskIconTypeEnum;
import type.TimeFormatTypeEnum;

/**
 * Encapsulates a task with that will occur at a specified time period.
 * It inherits from `DukeTask`.
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
     * Processes the input string to create an event task with an action and time.
     *
     * @param description Input task string.
     * @return App representation of a task containing an action description and time information.
     */
    public static EventTask createTask (String description) throws
            InvalidCommandFormatException,
            InvalidDateTimeException {
        String[] actionAndTimeDescriptions = CommandParser.splitStringBySplitter(
                description,
                EventTask.SPLITTER_ACTION_TIME
        );
        CommandParser.validateCorrectNumOfParts(2, actionAndTimeDescriptions, CommandTypeEnum.EVENT);
        String actionDescription = actionAndTimeDescriptions[0];
        String dateTimeDescription = actionAndTimeDescriptions[1];

        String[] dateTimeDescriptions = CommandParser.splitStringBySplitter(dateTimeDescription, SPLITTER_DATE_TIME);
        CommandParser.validateCorrectNumOfParts(3, dateTimeDescriptions, CommandTypeEnum.EVENT);
        LocalDate date = DateTimeParser.changeDateStringToDate(
                dateTimeDescriptions[0], DateFormatTypeEnum.INPUT.toString());
        LocalTime startTime = DateTimeParser.changeTimeStringToTime(
                dateTimeDescriptions[1], TimeFormatTypeEnum.INPUT.toString());
        LocalTime endTime = DateTimeParser.changeTimeStringToTime(
                dateTimeDescriptions[2], TimeFormatTypeEnum.INPUT.toString());

        return new EventTask(actionDescription, false, date, startTime, endTime);
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
     */
    public static EventTask createTaskFromStoredString(String description) throws InvalidFormatInStorageException {
        try {
            boolean isDone = Task.isStorageTaskDone(description);

            int descriptionStartPos = 3;
            String[] actionAndTimeDescriptions = CommandParser.splitStringBySplitter(
                    description.substring(descriptionStartPos),
                    SPLITTER_ACTION_TIME
            );
            CommandParser.validateCorrectNumOfParts(
                    2, actionAndTimeDescriptions, CommandTypeEnum.EVENT);

            String actionDescription = actionAndTimeDescriptions[0];
            String timeDescription = actionAndTimeDescriptions[1];

            String[] dateTimeDescriptions = CommandParser.splitStringBySplitter(timeDescription, SPLITTER_DATE_TIME);
            CommandParser.validateCorrectNumOfParts(3, dateTimeDescriptions, CommandTypeEnum.EVENT);
            LocalDate date = DateTimeParser.changeDateStringToDate(
                    dateTimeDescriptions[0], DateFormatTypeEnum.INPUT.toString());
            LocalTime startTime = DateTimeParser.changeTimeStringToTime(
                    dateTimeDescriptions[1], TimeFormatTypeEnum.INPUT.toString());
            LocalTime endTime = DateTimeParser.changeTimeStringToTime(
                    dateTimeDescriptions[2], TimeFormatTypeEnum.INPUT.toString());

            return new EventTask(actionDescription, isDone, date, startTime, endTime);
        } catch (InvalidCommandFormatException | InvalidDateTimeException e) {
            throw new InvalidFormatInStorageException(e.getMessage() + ": " + description);
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
}
