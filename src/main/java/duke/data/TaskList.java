package duke.data;

import duke.commands.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

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

    public void add(Task task){
        list.add(task);
    }

    public Task delete(int taskNum){
        return list.remove(taskNum-1);
    }

    public Task mark(int taskNum){
        Task task = list.get(taskNum-1);
        task.markDone();
        return task;
    }

    public int size(){
        return list.size();
    }

    public ArrayList<Task> getList(){
        return list;
    }

    public void loadFromList(ArrayList<Task> list){
        this.list = list;
    }
}
