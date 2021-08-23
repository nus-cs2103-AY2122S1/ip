package executor.parser;

import exception.ParserException;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StorageParser {
    public Task parse(String stringRep) {
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
        } else if (taskType == 'E'){
            String description = stringRep.split("]\\s")[1].trim().split("\\(")[0].trim();
            String time = stringRep.split("at: ")[1].split("\\)")[0].trim();

            String parsedTime = LocalDate.parse(time, DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new Event(description, isDone, parsedTime);
        } else {
         throw new ParserException("Unable to parse line from storage. Is storage corrupted?");
        }
    }
}
