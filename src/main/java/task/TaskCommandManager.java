package task;

import util.GnosisConstants;
import gnosis.GnosisException;
import model.Deadline;
import model.Event;
import model.Task;
import model.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class TaskCommandManager {

    private List<Task> tasks;

    public TaskCommandManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Todo addTodo(String todo) throws GnosisException {
        if (todo.trim().equalsIgnoreCase("")) {
            // t0do empty exception
            throw new GnosisException(GnosisConstants.TODO_EMPTY_EXCEPT_MESSAGE);
        }
        Todo td = new Todo(todo);
        tasks.add(td);
        return td;
    }

    public Event addEvent(String event) throws GnosisException {
        String[] splitTaskInput = event.split("/at");
        if (splitTaskInput.length != 2) {
            //event empty exception
            throw new GnosisException(GnosisConstants.EVENT_EMPTY_EXCEPT_MESSAGE);
        }
        String taskName = splitTaskInput[0];
        String taskSchedule = splitTaskInput[1];

        Event et = new Event(taskName,StringToDate(taskSchedule));
        tasks.add(et);
        return et;
    }

    public Deadline addDeadline(String deadline) throws GnosisException {
        String[] splitTaskInput = deadline.split("/by");
        if (splitTaskInput.length != 2) {
            //deadline empty exception
            throw new GnosisException(GnosisConstants.DEADLINE_EMPTY_EXCEPT_MESSAGE);
        }
        String taskName = splitTaskInput[0];
        String taskDeadline = splitTaskInput[1];

        Deadline dl = new Deadline(taskName, StringToDate(taskDeadline));
        tasks.add(dl);
        //System.out.println("model.Deadline added:");
        //System.out.println(dl);
        return dl;
    }

    public Task markTaskAsDone(int taskIndex) throws GnosisException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GnosisException(GnosisConstants.TASK_INDEX_OUT_OF_BOUNDS_EXCEPT_MESSAGE);
        }
        tasks.get(taskIndex).setDone(true);
        //System.out.println("model.Task " + (taskIndex+1) +" marked as done:" );
        //System.out.println("\t" + tasks.get(taskIndex));
        return tasks.get(taskIndex);
    }

    public Task deleteTask(int taskIndex) throws GnosisException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GnosisException(GnosisConstants.TASK_INDEX_OUT_OF_BOUNDS_EXCEPT_MESSAGE);
        }
        Task t = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return t;
    }

    public List<Task> findMatchingTasks(String taskKeyword){
        return this.tasks.stream()
                .filter(task -> task.getTaskName().contains(taskKeyword))
                .collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getNumOfTasks() {
        return this.getTasks().size();
    }

    // helper function
    public static LocalDateTime StringToDate(String dateString) throws GnosisException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime ldt;
        try {
            ldt = LocalDateTime.parse(dateString.stripLeading(),formatter);
        } catch (DateTimeParseException e) {
            throw new GnosisException(GnosisConstants.DATETIME_FORMAT_EXCEPT_MESSAGE);
        }

        return ldt;
    }

}
