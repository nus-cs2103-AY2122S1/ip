import java.util.ArrayList;
import java.util.List;

public class Dino {

    private final List<Task> taskList = new ArrayList<>();
    private String mode;
    enum TaskType {TODO, DEADLINE, EVENT}

    public Dino() {
        this.mode = "intelligent";
    }

    public void greeting() {
       System.out.println("Hello! I'm dino, your cute dinosaur bot.\n"
                + "I can help you manage tasks!\n"
                + "But if you just wanna play around with me, type 'echo' to enter the ECHO mode~\n");
    }

    public void farewell() {
        System.out.println("Goodbye~ \n"
                + "Your cute Dino is always around you :D");
    }

    public void echo(String input) {
        System.out.println(input);
    }

    public void setEcho() {
        this.mode = "echo";
        System.out.println("You are now in ECHO mode.\n"
                + "Type in any command to continue!");
    }

    public String getMode() {
        return this.mode;
    }

    public void readCommand(String cmd) {
        try {
            if (cmd.equals("list")) {
                if (taskList.size() == 0) throw new EmptyListException();
                else this.printTaskList();
            } else {
                String firstWord = Tool.getFirstWord(cmd);
                if (firstWord.equals("done") || firstWord.equals("delete")) {
                    int index = Tool.getIndex(cmd, firstWord);
                    if (index > 0) this.processTask(firstWord, index);
                } else {
                    this.addTask(cmd);
                }
            }
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTask(String s) throws InvalidInputException, EmptyTaskDescriptionException, TaskNotCategorizedException, TimeNotSpecifiedException {
        if (Tool.isTaskCategorized(s)) {
            TaskType type = TaskType.valueOf(Tool.getFirstWord(s).toUpperCase());
            String description;
            switch (type) {
                case TODO:
                    description = Tool.getTaskDescription(s, "todo");
                    ToDo todo = new ToDo(description);
                    taskList.add(todo);
                    break;
                case DEADLINE:
                    description = Tool.getTaskDescription(s, "deadline");
                    Deadline ddl = new Deadline(description, Tool.getTaskTime(s));
                    taskList.add(ddl);
                    break;
                case EVENT:
                    description = Tool.getTaskDescription(s, "event");
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

    public static void main(String[] args) {
        Tool.run();
    }
}
