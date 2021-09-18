package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.UpdateCommand;
import duke.command.UpdateDateTimeCommand;
import duke.command.UpdateDescriptionCommand;
import duke.command.UpdateFullCommand;
import duke.exception.DukeException;

public class UpdateParser {

    private static final String INCORRECT_UPDATE_FORMAT_MESSAGE =
            "It seems like your format for the update command is incorrect.";

    /** The user's input to parse */
    private final String parseContent;

    /**
     * Constructor for a UpdateParser object
     *
     * @param parseContent The input of the user that is to be parsed.
     */
    public UpdateParser(String parseContent) {
        this.parseContent = parseContent;
    }

    /**
     * Parses the user's input.
     *
     * @return An UpdateCommand object that will be used to carry out the updating of the task.
     * @throws DukeException If the user enters the command in an invalid format, a
     * DukeException will be thrown.
     */
    public UpdateCommand parse() throws DukeException {
        try {
            String[] contentSplit = parseContent.split("\\s+", 2);
            String updateMethod = contentSplit[0].toLowerCase();
            String updateContent = contentSplit[1].toLowerCase();


            switch (updateMethod) {
            case "dt":
                return updateDateTimeParse(updateContent);
            case "desc":
                return updateDescriptionParse(updateContent);
            case "full":
                return updateFullParse(updateContent);
            default:
                throw new DukeException(INCORRECT_UPDATE_FORMAT_MESSAGE);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INCORRECT_UPDATE_FORMAT_MESSAGE);
        }

    }

    /**
     * Parses the user's input, knowing that the user wants to update the date
     * and time of the task.
     *
     * @param updateContent The user input that will be used to update the date and time.
     * @return An UpdateDateTimeCommand object that will be used to carry out the updating of the task.
     * @throws DukeException If the user enters the command in an invalid format, a
     * DukeException will be thrown.
     */
    private UpdateDateTimeCommand updateDateTimeParse(String updateContent) throws DukeException {
        Pattern updatePattern = Pattern.compile("(?<taskNumber>\\S+)(\\s+)(?<date>\\S+)(\\s+)(?<time>\\S+)");
        Matcher matcher = updatePattern.matcher(updateContent);
        if (!matcher.matches()) {
            throw new DukeException(INCORRECT_UPDATE_FORMAT_MESSAGE);
        }

        try {
            String taskNumberString = matcher.group("taskNumber");
            int taskNumber = Integer.parseInt(taskNumberString);
            String date = matcher.group("date");
            String time = matcher.group("time");
            return new UpdateDateTimeCommand(taskNumber, date, time);
        } catch (NumberFormatException e) {
            throw new DukeException(INCORRECT_UPDATE_FORMAT_MESSAGE);
        }
    }

    /**
     * Parses the user's input, knowing that the user wants to update the description
     * of the task.
     *
     * @param updateContent The user input that will be used to update the description.
     * @return An UpdateDescriptionCommand object that will be used to carry out the updating of the task.
     * @throws DukeException If the user enters the command in an invalid format, a
     * DukeException will be thrown.
     */
    private UpdateDescriptionCommand updateDescriptionParse(String updateContent) throws DukeException {
        Pattern updatePattern = Pattern.compile("(?<taskNumber>\\S+)(\\s+)(?<description>.+)");
        Matcher matcher = updatePattern.matcher(updateContent);
        if (!matcher.matches()) {
            throw new DukeException(INCORRECT_UPDATE_FORMAT_MESSAGE);
        }

        try {
            String taskNumberString = matcher.group("taskNumber");
            int taskNumber = Integer.parseInt(taskNumberString);
            String description = matcher.group("description");
            return new UpdateDescriptionCommand(taskNumber, description);
        } catch (NumberFormatException e) {
            throw new DukeException(INCORRECT_UPDATE_FORMAT_MESSAGE);
        }
    }

    /**
     * Parses the user's input, knowing that the user wants to update the description,
     * date and time of the task.
     *
     * @param updateContent The user input that will be used to update description,
     *                      date and time.
     * @return An UpdateDateTimeCommand object that will be used to carry out the updating of the task.
     * @throws DukeException If the user enters the command in an invalid format, a
     * DukeException will be thrown.
     */
    private UpdateFullCommand updateFullParse(String updateContent) throws DukeException {
        Pattern updatePattern = Pattern.compile(
                "(?<taskNumber>\\S+)(\\s+)(?<description>.+)/by(\\s+)(?<date>\\S+)(\\s+)(?<time>\\S+)");
        Matcher matcher = updatePattern.matcher(updateContent);
        if (!matcher.matches()) {
            throw new DukeException(INCORRECT_UPDATE_FORMAT_MESSAGE);
        }

        try {
            String taskNumberString = matcher.group("taskNumber");
            int taskNumber = Integer.parseInt(taskNumberString);
            String description = matcher.group("description").trim();
            String date = matcher.group("date");
            String time = matcher.group("time");
            return new UpdateFullCommand(taskNumber, description, date, time);
        } catch (NumberFormatException e) {
            throw new DukeException(INCORRECT_UPDATE_FORMAT_MESSAGE);
        }
    }
}
