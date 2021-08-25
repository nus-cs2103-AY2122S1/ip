import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskList {

    public static Task stringToTask(String str){
        String[] taskData = str.split("\\|");
        boolean isDone = (taskData[1].equals("1")) ? true : false;

        switch (taskData[0]){
        case "D":
            LocalDateTime by = LocalDateTime.parse(taskData[3]);
            Deadline deadline = new Deadline(taskData[2], isDone, by);
            return deadline;

        case "E":
            LocalDateTime at = LocalDateTime.parse(taskData[3]);
            Event event = new Event(taskData[2], isDone, at);
            return event;

        default:
            ToDo toDo = new ToDo(taskData[2], isDone);
            return toDo;
        }
    }
}
