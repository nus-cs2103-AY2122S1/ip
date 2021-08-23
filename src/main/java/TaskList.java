import java.util.List;

public class TaskList {

    private List<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        tasks = storage.readFromStorage();
    }

    public static Task createTask(char taskType, char taskDone, String taskDescription) {
        Task createdTask = new Task("Created");
        Boolean isDone = (taskDone == 'X');


        switch (taskType) {
        case 'T':
            Task taskToDo = new ToDo(taskDescription);
            if (isDone) {
                taskToDo.markAsDone();
            }
            createdTask = taskToDo;
            break;
        case 'D':
            Task taskDeadline = new Deadline(taskDescription);
            if (isDone) {
                taskDeadline.markAsDone();
            }
            createdTask = taskDeadline;
            break;
        case 'E':
            Task taskEvent = new Event(taskDescription);
            if (isDone) {
                taskEvent.markAsDone();
            }
            createdTask = taskEvent;
            break;
        }

        return createdTask;
    }

    public String addToList(Task newTask) {
        tasks.add(newTask);

        storage.writeToStorage(this.getList());

        return newTask.toString();
    }

    public String deleteFromList(int index) {
        Task curr = tasks.get(index - 1);
        tasks.remove(index - 1);
        storage.writeToStorage(this.getList());

        return curr.toString();
    }

    public String taskDone(int index) {
        Task curr = tasks.get(index - 1);
        curr.markAsDone();
        this.getList();
        return curr.toString();
    }

    public int taskCount() {
        return tasks.size();
    }

    public String getList() {
        int counter = 1;
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
                result = result + counter + "." + tasks.get(i) + "\n";
                counter++;
        }

        return result;
    }
}
