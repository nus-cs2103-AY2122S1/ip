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

    public static void addTask(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    added: " + input);
        System.out.println("    ____________________________________________________________");
    }

    public static void list() {
        System.out.println("    ____________________________________________________________");
        for (Task task : Duke.tasks) {
            System.out.println("    " + task.taskId + ". " + task.getStatus());
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void done(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done: \n      " + task.getStatus());
        System.out.println("    ____________________________________________________________");
    }
}
