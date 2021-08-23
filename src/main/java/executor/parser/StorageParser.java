package executor.parser;

import exception.ParserException;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.ToDo;

public class StorageParser {
    public Task parse(String stringRep) {
        char taskType = stringRep.charAt(1);
        boolean isDone = stringRep.charAt(4) == 'X';

        if (taskType == 'T') {
            String description = stringRep.split("]\\s")[1];
            return new ToDo(description, isDone);
        } else if (taskType == 'D') {
            String description = stringRep.split("]\\s")[1].split("\\(")[0];
            String time = stringRep.split("by: ")[1].split("\\)")[0];
            return new Deadline(description, isDone, time);
        } else if (taskType == 'E'){
            String description = stringRep.split("]\\s")[1].split("\\(")[0];
            String time = stringRep.split("by: ")[1].split("\\)")[0];
            return new Event(description, isDone, time);
        } else {
         throw new ParserException("Oops! Unable to parse line from storage. Is storage corrupted?");
        }
    }
}
