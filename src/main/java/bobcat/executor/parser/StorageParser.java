package bobcat.executor.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bobcat.exception.BobCatException;
import bobcat.exception.ParserException;
import bobcat.model.task.Deadline;
import bobcat.model.task.Event;
import bobcat.model.task.Task;
import bobcat.model.task.ToDo;

/**
 * A parser object which process the lines in the storage file. Used for
 */
public class StorageParser {
    /**
     * Takes a string and parses it. The string is assumed to be tag with a task tag, a completion tag, a description,
     * and optionally a date of form "dd MMM yyyy".
     * @param stringRep string representation of the <code>Task</code>
     * @return a <code>Task</code> corresponding to the task tag in the string
     * @throws ParserException May be thrown if task tag cannot be found / not understandable
     */
    public Task parse(String stringRep) {
        try {
            char taskType = stringRep.charAt(1);
            boolean isDone = stringRep.charAt(4) == 'X';

            if (taskType == 'T') {
                String description = stringRep.split("]\\s")[1];
                return new ToDo(description, isDone);
            } else if (taskType == 'D') {
                String description = stringRep.split("]\\s")[1].split("\\(")[0].trim();
                String time = stringRep.split("by: ")[1].split("\\)")[0];

                String parsedTime = LocalDate.parse(time, DateTimeFormatter.ofPattern("dd MMM yyyy"))
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new Deadline(description, isDone, parsedTime);
            } else if (taskType == 'E') {
                String description = stringRep.split("]\\s")[1].trim().split("\\(")[0].trim();
                String time = stringRep.split("at: ")[1].split("\\)")[0].trim();

                String parsedTime = LocalDate.parse(time, DateTimeFormatter.ofPattern("dd MMM yyyy"))
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new Event(description, isDone, parsedTime);
            } else {
                throw new ParserException("Unable to parse line from storage: task tag not found / understandable.");
            }
        } catch (BobCatException e) {
            throw new ParserException("Unable to parse line from storage. " + e.getMessage());
        }
    }
}
