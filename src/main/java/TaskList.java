import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

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

    public void GetSpecificDateEvent(String time){
        Parser p = new Parser("");
        int count = 0;//count the number of the events happen on the time.

        for (int i = 0; i < tasks.size(); i++) {
            String Message = tasks.get(i).PrintTaskInfo();
            String UnParsedInfo = tasks.get(i).GetTime();
            String timeInFormat =(p.ParseTime(time) != null)?
                    p.ParseTime(time).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH))
                    :"Nope";

            if ((UnParsedInfo != null && (UnParsedInfo.contains(time) || UnParsedInfo.contains(timeInFormat)))
                            || Message.contains(time) || Message.contains(timeInFormat)) {
                count++;
                System.out.println(count + "." + Message);
            }
        }
        if (count == 0) {
            System.out.println("Sorry. There is no event occurred on the time you give me!! :(");
        }
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
