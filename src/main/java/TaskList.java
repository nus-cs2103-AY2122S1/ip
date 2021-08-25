import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> todoList;
    
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public TaskList() {
        this.todoList = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> tasks) {
        this.todoList = tasks;
    }

    /** Prints the to-do list in order */
    public void displayList() {
        System.out.println("Your task list:");
        for (int i = 0; i < todoList.size(); i++) {
            Task task = todoList.get(i);
            int num = i+1;
            System.out.println(num + "." + task.toString());
        }
    }

    /** Add a task to the to-do list */
    public Task addTask(TaskType taskType, String details) throws DukeException {
        Task task;
        if (taskType.equals(TaskType.TODO)) {
            task = new ToDo(details);
        } else if (taskType.equals(TaskType.DEADLINE)) {
            int position = details.indexOf("/by");
            String description, by;
            if (position >= 0) {
                description = details.substring(0, position);
                by = details.substring(position + 3);
            } else {
                throw new DukeException("Please indicate the deadline eg \"/by Sunday\" ");
            }
            task = new Deadline(description.trim(), by.trim());
        } else if (taskType.equals(TaskType.EVENT)) {
            int position = details.indexOf("/at");
            String description, at;
            if (position >= 0) {
                description = details.substring(0, position);
                at = details.substring(position + 3);
            } else {
                throw new DukeException("Please indicate the event time eg \"/at Mon 2-4pm\" ");
            }
            task = new Event(description.trim(), at.trim());
        } else {
            // should not reach here
            throw new DukeException("Invalid task type.");
        }
        todoList.add(task);
        
        return task;
    }

    /** Mark a task with given task number as done */
    public Task markTaskDone(Integer taskNum) throws DukeException {
        Task task = todoList.get(taskNum - 1);
        task.markAsDone();

        return task;
    }

    /** Delete a task with given task number */
    public Task deleteTask(Integer taskNum) throws DukeException {
        Task task = todoList.remove(taskNum - 1);

        return task;
    }
    
    public List<String> getListData() {
         return todoList.stream().map(task -> task.toDataString("|")).collect(Collectors.toList());
    }
    
    public Integer getListSize() {
        return todoList.size(); 
    }
}
