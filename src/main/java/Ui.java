import java.util.NoSuchElementException;
import java.util.Scanner;

class Ui {

    private Scanner scanner = new Scanner(System.in);

    protected void showException(DukeException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    protected void greet() {
        System.out.println("Good Day Sir/Mdm, I am Duke.\nWhat can I do for you?\n");
    }

    protected void end() {
        scanner.close();
        System.out.println("Goodbye Sir/Mdm. Hope to serve you again soon!\n");
    }

    protected void add(Task task, TaskList tasks) {
        System.out.println("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + task);
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task." : " tasks.")
                + "\n");

    }

    protected void showList(TaskList tasks) {
        System.out.println("Here are your tasks Sir/Mdm:");
        System.out.println(list(tasks));
        System.out.println();
    }

    protected void markDone(Task task) {
        System.out.println("Good job Sir/Mdm! I shall mark this task as complete:\n   "
                + task + "\n");
    }

    protected void delete(Task task, TaskList tasks) {
        System.out.println("Much obliged Sir/Mdm! I shall delete this task:\n   " +
                task + "\n" + "Now you have " + tasks.size() +
                (tasks.size() == 1 ? " task." : " tasks.") + "\n");

    }

    protected String list(TaskList tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list = list + (i == 0 ? "" : "\n") + (i + 1) + ". " + tasks.get(i);
        }
        return list;
    }

    protected void find(TaskList foundTasks) {
        String message = "Here are the deadlines and events that match the date Sir/Mdm:\n"
                + list(foundTasks);
        System.out.println(message);
        System.out.println();
    }

    protected String readCommand() {
        return scanner.nextLine();
    }

}
