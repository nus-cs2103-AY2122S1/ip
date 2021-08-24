import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getAllTasks(){
        return this.tasks;
    }

    public void AddToList(Task task) {
        this.tasks.add(task);
    }

    public void MarkDone(int index) throws DukeException{
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the index is invalid :-(");
        } else {
            this.tasks.get(index).MarkDone();
        }
    }

    public void Delete(int index) throws DukeException{
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the index is invalid :-(");
        } else {
            this.tasks.remove(index);
        }
    }

    public void add(String taskType, String task, String time) throws DukeException{
        Parser p = new Parser("");

        LocalDateTime parsedTime = p.ParseTime(time);
        OperationType[] taskTypes = OperationType.values();
        for (OperationType t : taskTypes) {
            if (t.toString().equals(taskType)){
                Task newTask = t.AssignTaskType(t, task, parsedTime);
                tasks.add(newTask);
                break;
            }
        }

    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int size(){
        return this.tasks.size();
    }

    public enum OperationType{
        bye, done, delete, tell, list, todo, deadline, event;

        public Task AssignTaskType(OperationType t,String task, LocalDateTime time){
            switch (t) {
            case todo: return new ToDos(false, task);
            case deadline: return new Deadlines(false, task, time);
            case event: return new Events(false, task, time);
            default: return null;
            }
        }
    }
}
