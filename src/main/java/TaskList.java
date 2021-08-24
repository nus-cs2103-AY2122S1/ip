import java.util.Scanner;

public class TaskList {

    public static Task stringToTask(String str){
        String[] taskData = str.split("\\|");
        boolean isDone = (taskData[1].equals("1")) ? true : false;
        switch (taskData[0]){
        case "D":
            Deadline deadline = new Deadline(taskData[2], isDone, taskData[3]);
            return deadline;

        case "E":
            Event event = new Event(taskData[2], isDone, taskData[3]);
            return event;

        default:
            ToDo toDo = new ToDo(taskData[2], isDone);
            return toDo;
        }
    }
}
