import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;
    private final Storage storage;

    public static Task stringToTask(String str){
        String[] taskStr = str.split(" \\| ");
        boolean isDone = taskStr[1].equals("1");

        switch (taskStr[0]) {
        case "D":
            return new Deadline(taskStr[2], isDone, taskStr[3]);

        case "E":
            return new Event(taskStr[2], isDone, taskStr[3]);

        default:
            return new ToDo(taskStr[2], isDone);
        }
    }

    protected TaskList(){
        list = new ArrayList<>();
        storage = new Storage();
    }

    public Task getTask(int taskNumber){
        return list.get(taskNumber - 1);
    }

    public void addTask(Task task){
        list.add(task);
        storage.save(list);
    }

    public Task deleteTask(int taskNumber){
        Task task = list.remove(taskNumber - 1);
        storage.save(list);
        return task;
    }

    public void markAsDone(int taskNumber){
        Task task = list.get(taskNumber - 1);
        task.markDone();
        storage.save(list);
    }

    public int size(){
        return list.size();
    }

    public void loadFromStorage(ArrayList<Task> listOfTasks){
        list = listOfTasks;
    }
}
