public class Ui {
    public static void welcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void goodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void listMessage(TaskList tasks) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void doneMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + task.toString());
        System.out.println("____________________________________________________________");
    }

    public static void noSuchTaskMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("There is no such task.");
        System.out.println("____________________________________________________________");
    }

    public static void removeMessage(Task task, int num) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + num + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void showLoadingError() {
        System.out.println("____________________________________________________________");
        System.out.println("There was an error loading the data file.");
        System.out.println("____________________________________________________________");
    }
}
