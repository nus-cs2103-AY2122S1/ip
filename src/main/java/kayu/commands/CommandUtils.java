package kayu.commands;

import static kayu.commands.CommandMessage.ASSERT_FAIL_INCOMPLETE_PARAMS;
import static kayu.commands.CommandMessage.ERROR_EMPTY_PARAMS;
import static kayu.commands.CommandMessage.ERROR_IMPROPER_DATE;
import static kayu.commands.CommandMessage.ERROR_IMPROPER_FORMATTING;
import static kayu.commands.CommandMessage.ERROR_IMPROPER_TIME;
import static kayu.commands.CommandMessage.MESSAGE_ITEM_FORMAT;
import static kayu.commands.CommandMessage.MESSAGE_LIST_CONTENTS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import kayu.exception.KayuException;
import kayu.parser.DateTimeFormat;

/**
 * Holds shared methods that are used by {@link kayu.commands.Command}s such as extracting
 * the String description or {@link java.time.LocalDate} from String parameters.
 */
public class CommandUtils {

    private static final DateTimeFormat DATE_TIME_FORMAT = DateTimeFormat.generateInstance();

    protected static String[] splitUserParams(String userParams, String commandName, String splitKey)
            throws KayuException {

        try {
            String[] splitOnKey = userParams.split(" /" + splitKey + ' ', 2);
            String[] dateTime = splitOnKey[1].split(" ", 2);
            return new String[] {splitOnKey[0], dateTime[0], dateTime[1]};

        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KayuException(String.format(ERROR_IMPROPER_FORMATTING, commandName, splitKey));
        }
    }

    protected static String extractDesc(String[] paramArray, String commandName) throws KayuException {
        assert (paramArray.length >= 1) : ASSERT_FAIL_INCOMPLETE_PARAMS;

        String desc = paramArray[0].trim();
        if (desc.isBlank()) {
            throw new KayuException(String.format(ERROR_EMPTY_PARAMS, commandName));
        }
        return desc;
    }

    protected static LocalDate extractDate(String[] paramArray) throws KayuException {
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

    protected static LocalTime extractTime(String[] paramArray) throws KayuException {
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

    // Wildcard generic used as we are using the toString method for both Task and Notes.
    protected static String generateFormattedItemListResponse(List<?> list) {
        StringBuilder tasksAsString = new StringBuilder(MESSAGE_LIST_CONTENTS);

        String stringedTasks = IntStream.range(0, list.size())
                .boxed()
                .map(idx -> convertToItemString(idx, list))
                .collect(Collectors.joining("\n"));

        tasksAsString.append(stringedTasks);
        return tasksAsString.toString();
    }

    protected static String convertToItemString(int idx, List<?> list) {
        int number = idx + 1;
        return String.format(MESSAGE_ITEM_FORMAT, number, list.get(idx));
    }
}
