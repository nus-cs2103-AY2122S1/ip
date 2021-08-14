import java.util.Scanner;

public class Ui {
    private Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("You have no remaining tasks.\n");
        } else {
            int uncompletedTask = 0;
            System.out.println("Here " + (taskList.size() > 1 ? "are" : "is")
                    + " your " + (taskList.size() > 1 ? "tasks:" : "task:"));
            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                System.out.println((i + 1) + ". " + currTask);
                if (!currTask.isDone()) {
                    uncompletedTask++;
                }
            }
            System.out.println("You have " + uncompletedTask + " uncompleted " + (uncompletedTask > 1 ? "tasks"
                    : "task") + ".\n");
        }
    }

    public void showTask(Task task) {
        System.out.println(task);
    }

    public void showDeletedTask(Task task) {
        System.out.println("I have removed the task:\n" + task + "\n");
    }

    public void showAddedTask(Task task) {
        System.out.println("I have added this task for you:");
        System.out.println(task + "\n");
    }

    public void showCompletedTask(Task task) {
        System.out.println("You have completed the " + task.getClass().getName() + ":\n"
                + task.getName() + "\n");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage + "\n");
    }

    public void greet(TaskList taskList) {
        String lifeline = " _      _____ ______ ______ _      _____ _   _ ______\n"
                + "| |    |_   _|  ____|  ____| |    |_   _| \\ | |  ____|\n"
                + "| |      | | | |__  | |__  | |      | | |  \\| | |__\n"
                + "| |      | | |  __| |  __| | |      | | | . ` |  __|\n"
                + "| |____ _| |_| |    | |____| |____ _| |_| |\\  | |____\n"
                + "|______|_____|_|    |______|______|_____|_| \\_|______|\n";
        System.out.println("Hello! I am\n" + lifeline);
        showTaskList(taskList);
        System.out.println("What can I help you with today?\n");
    }

    public void exit() {
        System.out.println("Goodbye! Thanks for chatting with me!\n");
    }

    public void echo(String input) {
        System.out.println("You have said \"" + input + "\"\n");
    }
}
