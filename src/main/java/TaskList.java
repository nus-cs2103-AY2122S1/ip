import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private final int todoLength = 3;
    private final int deadlineLength = 4;
    private final int eventLength = 4;

    public TaskList(ArrayList<String> loadingStrings) throws DukeException {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < loadingStrings.size(); i++) {
            String taskString = loadingStrings.get(i);
            String[] taskStringArr = taskString.split("\\|");
            String type = taskStringArr[0];
            Task task;
            switch (type) {
                case "T":
                    if (taskStringArr.length != todoLength){
                        throw new DukeException("ERROR READING FROM STORAGE");
                    }
                    task = new Todo(taskStringArr[2]);
                    break;
                case "D":
                    if (taskStringArr.length != deadlineLength){
                        throw new DukeException("ERROR READING FROM STORAGE");
                    }
                    String[] deadlineDateTimeArr = taskStringArr[3].split(" ");
                    String deadlineDateString = deadlineDateTimeArr[0];
                    String deadlineTimeString = deadlineDateTimeArr[1];
                    task = new Deadline(taskStringArr[2], deadlineDateString, deadlineTimeString);
                    // addTask(taskStringArr[2], taskStringArr[3], TaskType.DEADLINE);
                    break;
                case "E":
                    if (taskStringArr.length != eventLength){
                        throw new DukeException("ERROR READING FROM STORAGE");
                    }
                    String[] eventDateTimeArr = taskStringArr[3].split(" ");
                    String eventDateString = eventDateTimeArr[0];
                    String eventTimeString = eventDateTimeArr[1];
                    task = new Event(taskStringArr[2], eventDateString, eventTimeString);
                    // addTask(taskStringArr[2], taskStringArr[3], TaskType.EVENT);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
            if (taskStringArr[1].equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }

    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void deleteTask(int delIndex) {
        Task task = tasks.get(delIndex);
        tasks.remove(delIndex);
    }

    public void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    public void addTask(Task task) throws DukeException {
        this.tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }
}
