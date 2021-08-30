import java.util.ArrayList;
import java.time.LocalDateTime;

public class TaskList {

    private ArrayList<Task> list;

    public static Task stringToTask(String str){
        String[] taskStr = str.split(" \\| ");
        boolean isDone = taskStr[1].equals("1");

        switch (taskStr[0]) {
        case "D":
            LocalDateTime by = LocalDateTime.parse(taskStr[3]);
            return new Deadline(taskStr[2], isDone, by);

        case "E":
            LocalDateTime at = LocalDateTime.parse(taskStr[3]);
            return new Event(taskStr[2], isDone, at);

        default:
            return new ToDo(taskStr[2], isDone);
        }
    }

    protected TaskList(){
        list = new ArrayList<>();
    }

    public Task getTask(int taskNumber){
        return list.get(taskNumber - 1);
    }

    public void addTask(Task task){
        list.add(task);
    }

    public Task deleteTask(int taskNumber) throws NoSuchTaskException {
        if (taskNumber < 0 || taskNumber >= list.size()) {
            throw new NoSuchTaskException(new Ui());
        }
        Task task = list.remove(taskNumber - 1);
        return task;
    }

    public Task markAsDone(int taskNumber) throws NoSuchTaskException {
        if (taskNumber < 0 || taskNumber >= list.size()) {
            throw new NoSuchTaskException(new Ui());
        }
        Task task = list.get(taskNumber - 1);
        task.markDone();
        return task;
    }

    public int size(){
        return list.size();
    }

    public ArrayList<Task> getList(){
        return this.list;
    }

    public void loadFromStorage(ArrayList<Task> listOfTasks){
        list = listOfTasks;
    }
}
