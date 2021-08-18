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

    public void addTask(String s) {
        String type = Tool.getFirstWord(s);
        String description = Tool.getTaskDescription(s);
        if (type.equals("todo")) {
            ToDo todo = new ToDo(description);
            taskList[taskCount] = todo;
        } else if (type.equals("deadline")) {
            Deadline ddl = new Deadline(description, Tool.getTaskTime(s));
            taskList[taskCount] = ddl;
        } else if (type.equals("event")) {
            Event event = new Event(description, Tool.getTaskTime(s));
            taskList[taskCount] = event;
        } else {
            Task t =  new Task(s);
            taskList[taskCount] = t;
        }
        System.out.println("Got it. I've added this task: \n"
                + "  " + taskList[taskCount]);
        taskCount++;
        System.out.println("Now you have " + taskCount +
                (taskCount > 1 ? " tasks" : " task") + " in the list.");
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
    }

    public void markTaskDone(int index) {
        if (index > taskCount) {
            System.out.println("Wait...you don't even have this task in your list!");
        } else {
            Task t = taskList[index - 1];
            t.setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t);
        }
    }


}
