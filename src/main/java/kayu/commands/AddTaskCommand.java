package kayu.commands;

import static kayu.commands.CommandMessage.ASSERT_FAIL_INCOMPLETE_PARAMS;
import static kayu.commands.CommandMessage.ASSERT_FAIL_NULL_TASK;
import static kayu.commands.CommandMessage.ERROR_EMPTY_PARAMS;
import static kayu.commands.CommandMessage.ERROR_IMPROPER_DATE;
import static kayu.commands.CommandMessage.ERROR_IMPROPER_FORMATTING;
import static kayu.commands.CommandMessage.ERROR_IMPROPER_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.parser.DateTimeFormat;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Task;

/**
 * Holds shared methods that are used by {@link kayu.commands.Command}s that adds {@link kayu.task.Task}s
 * such as extracting the String description or {@link java.time.LocalDate} from String parameters.
 */
public abstract class AddTaskCommand extends Command {

    private static final DateTimeFormat DATE_TIME_FORMAT = DateTimeFormat.generateInstance();

    /**
     * Initializes an AddTaskCommand instance.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public AddTaskCommand(String commandParams) {
        super(commandParams);
    }

    /**
     * Creates new {@link kayu.task.Task} instance based on the parameters fed
     * to the {@link kayu.commands.Command} instance.
     *
     * @return New {@link kayu.task.Task} instance.
     * @throws KayuException If creation of {@link kayu.task.Task} instance is unsuccessful.
     */
    public abstract Task createTask() throws KayuException;

    /**
     * Updates {@link kayu.service.TaskList} and saves new {@link kayu.task.Task} to file
     * using {@link kayu.storage.Storage}.
     *
     * @param taskList {@link kayu.service.TaskList} to save {@link kayu.task.Task} instance to.
     * @param storage {@link kayu.storage.Storage} to update to file.
     * @param task {@link kayu.task.Task} instance to save.
     * @throws StorageException If saving {@link kayu.storage.Storage} is unsuccessful.
     */
    public void updateTasks(TaskList taskList, Storage storage, Task task) throws StorageException {
        assert (task != null) : ASSERT_FAIL_NULL_TASK;
        taskList.addTask(task);
        updateFileStorage(taskList, storage);
    }

    protected String[] splitUserParams(String userParams, String commandName, String splitKey)
            throws KayuException {

        try {
            String[] splitOnKey = userParams.split(" /" + splitKey + ' ', 2);
            String[] dateTime = splitOnKey[1].split(" ", 2);
            return new String[] {splitOnKey[0], dateTime[0], dateTime[1]};

        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KayuException(String.format(ERROR_IMPROPER_FORMATTING, commandName, splitKey));
        }
    }

    protected String extractDesc(String[] paramArray, String commandName) throws KayuException {
        assert (paramArray.length >= 1) : ASSERT_FAIL_INCOMPLETE_PARAMS;

        String desc = paramArray[0].trim();
        if (desc.isBlank()) {
            throw new KayuException(String.format(ERROR_EMPTY_PARAMS, commandName));
        }
        return desc;
    }

    protected LocalDate extractDate(String[] paramArray) throws KayuException {
        assert (paramArray.length == 3) : ASSERT_FAIL_INCOMPLETE_PARAMS;

        String dateString = paramArray[1].trim();
        List<DateTimeFormatter> dateFormatterList = DATE_TIME_FORMAT.getDateFormats();

        for (DateTimeFormatter formatter: dateFormatterList) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException exception) {
                // skip this and attempt to parse with the next possible format
            }
        }
        throw new KayuException(ERROR_IMPROPER_DATE);
    }

    protected LocalTime extractTime(String[] paramArray) throws KayuException {
        assert (paramArray.length == 3) : ASSERT_FAIL_INCOMPLETE_PARAMS;

        String timeString = paramArray[2].trim().toUpperCase();
        List<DateTimeFormatter> timeFormatterList = DATE_TIME_FORMAT.getTimeFormats();

        for (DateTimeFormatter formatter: timeFormatterList) {
            try {
                return LocalTime.parse(timeString, formatter);
            } catch (DateTimeParseException exception) {
                // skip this and attempt to parse with the next possible format
            }
        }
        throw new KayuException(ERROR_IMPROPER_TIME);
    }
}
