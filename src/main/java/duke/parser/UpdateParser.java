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
    private String parseContent;

    public UpdateParser(String parseContent) {
        this.parseContent = parseContent;
    }

    public UpdateCommand parse() throws DukeException {
        try {
            String[] contentSplit = parseContent.split("\\s+", 2);
            String updateMethod = contentSplit[0].toLowerCase();
            String updateContent = contentSplit[1].toLowerCase();


            switch (updateMethod) {
            case "datetime":
                return updateDateTimeParse(updateContent);
            case "description":
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

    public UpdateCommand updateDateTimeParse(String updateContent) throws DukeException {
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

    public UpdateCommand updateDescriptionParse(String updateContent) throws DukeException {
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

    public UpdateCommand updateFullParse(String updateContent) throws DukeException {
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
