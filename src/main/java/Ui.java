import java.util.Scanner;

class Ui {

    private Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    private String logo = " ____        _        \n"
    + "|  _ \\ _   _| | _____ \n"
    + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n"
    + "|____/ \\__,_|_|\\_\\___|\n";

    void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    void showLoadingError() {
        System.out.println("File Not Found");
    }

    void showError(String error) {
        System.out.println(error);
    }

    void printAdd(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list", tasks.size()));
    }

    void printDone(TaskList tasks, int toComplete) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  %s", tasks.get(toComplete)));
    }

    void printDelete(TaskList tasks, int toDelete){
        System.out.println("Noted. I've removed this task: ");
        System.out.println(String.format("  %s", tasks.get(toDelete)));
        System.out.println(String.format("Now you have %d tasks in the list", tasks.size() - 1));
    }

    void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    void showLine() {
        System.out.println("_______");
    }

    void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    String readCommand() {
        String input = sc.nextLine();
        return input;
    }
}