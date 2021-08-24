import java.util.List;

public class TaskList {

    private List<Task> taskList;
    enum TaskType {TODO, DEADLINE, EVENT};

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(String s) throws InvalidInputException, EmptyTaskDescriptionException, TaskNotCategorizedException, TimeNotSpecifiedException {
        if (Tool.isTaskCategorized(s)) {
            TaskType type = TaskType.valueOf(Tool.getFirstWord(s).toUpperCase());
            String description;
            switch (type) {
            case TODO:
                description = Tool.getTaskDescription(s, "todo").trim();
                ToDo todo = new ToDo(description);
                taskList.add(todo);
                break;
            case DEADLINE:
                description = Tool.getTaskDescription(s, "deadline").trim();
                Deadline ddl = new Deadline(description, Tool.getTaskTime(s));
                taskList.add(ddl);
                break;
            case EVENT:
                description = Tool.getTaskDescription(s, "event").trim();
                Event event = new Event(description, Tool.getTaskTime(s));
                taskList.add(event);
                break;
            default:
                throw new InvalidInputException();
            }
            int size = taskList.size();
            System.out.println("Got it. I've added this task: \n"
                    + "  " + taskList.get(size - 1));
            System.out.println("Now you have " + size +
                    (size > 1 ? " tasks" : " task") + " in the list.");
        } else {
            throw new TaskNotCategorizedException();
        }
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    public void processTask(String task, int index) throws InvalidIndexException, TaskAlreadyDoneException {
        if (task.equals("done")) this.markTaskDone(index);
        else if (task.equals("delete")) this.deleteTask(index);
    }

    public void markTaskDone(int index) throws InvalidIndexException, TaskAlreadyDoneException {
        if (index > taskList.size()) {
            throw new InvalidIndexException();
        } else {
            Task t = taskList.get(index - 1);
            if (t.getStatus()) throw new TaskAlreadyDoneException();
            t.setDone();
            System.out.println("Nice! I've marked this task as done: \n" + t);
        }
    }

    public void deleteTask(int index) throws InvalidIndexException {
        if (index > taskList.size()) {
            throw new InvalidIndexException();
        } else {
            Task t = taskList.remove(index - 1);
            System.out.println("Noted. I've removed this task: \n" + t);
            int size = taskList.size();
            System.out.println("Now you have " + size +
                    (size > 1 ? " tasks" : " task") + " in the list.");
        }
    }


}
