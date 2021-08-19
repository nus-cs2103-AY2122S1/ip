public class Dino {

    private final Task[] taskList = new Task[100];
    private int taskCount = 0;

    public void greeting() {
       System.out.println("Hello! I'm dino, your cute dinosaur bot.\n"
                + "Anything I can do for you?");
    }

    public void farewell() {
        System.out.println("Goodbye~ \n"
                + "Your cute Dino is always around you :D");
    }

    public void readCommand(String cmd) {
        if (cmd.equals("list")) {
            this.printTaskList();
        } else {
            try {
                String firstWord = Tool.getFirstWord(cmd);
                if (firstWord.equals("done")) {
                    int index = Tool.getDoneIndex(cmd);
                    if (index > 0) this.markTaskDone(index);
                } else this.addTask(cmd);
            } catch (DinoException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addTask(String s) throws InvalidInputException, EmptyTaskDescriptionException, TaskNotCategorizedException, TaskListFullException {
        if (taskCount + 1 > 100) throw new TaskListFullException();
        if (Tool.isTaskCategorized(s)) {
            String type = Tool.getFirstWord(s);
            String description = Tool.getTaskDescription(s);
            switch (type) {
                case "todo":
                    ToDo todo = new ToDo(description);
                    taskList[taskCount] = todo;
                    break;
                case "deadline":
                    Deadline ddl = new Deadline(description, Tool.getTaskTime(s));
                    taskList[taskCount] = ddl;
                    break;
                case "event":
                    Event event = new Event(description, Tool.getTaskTime(s));
                    taskList[taskCount] = event;
                    break;
                default:
                    throw new InvalidInputException();
            }
            System.out.println("Got it. I've added this task: \n"
                    + "  " + taskList[taskCount]);
            taskCount++;
            System.out.println("Now you have " + taskCount +
                    (taskCount > 1 ? " tasks" : " task") + " in the list.");
        }
    }


    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
    }

    public void markTaskDone(int index) throws NoSuchTaskException {
        if (index > taskCount) {
            throw new NoSuchTaskException();
        } else {
            Task t = taskList[index - 1];
            t.setDone();
            System.out.println("Nice! I've marked this task as done: \n" + t);
        }
    }


}
