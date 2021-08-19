public class Message {
    public static void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Jani, your personal chat bot.\n" +
                "    How may I assist you today?");
        System.out.println("    ____________________________________________________________");
    }

    public static void exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Goodbye! Please visit me again soon :(");
        System.out.println("    ____________________________________________________________");
    }

    public static void addTask(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task: \n      " + task);
        System.out.println("    Now you have " + Task.totalTasks + " in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static void list() {
        System.out.println("    ____________________________________________________________");
        for (Task task : TaskManager.tasks) {
            System.out.println("    " + task.taskId + ". " + task);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void done(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done: \n      " + task);
        System.out.println("    ____________________________________________________________");
    }
}
