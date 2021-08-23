import java.util.Scanner;

public class Ui {
    private static final String LINE = "----------------------------------------------------";
    private static final String WELCOME_MESSAGE= "Hello! I'm Duke. " +
            "What can I do for you?\n\n" + LINE + "-----------------------------\n" +
            "|\tPlease enter one of the following commands:                                  |\n" +
            "|\t1. todo <description> (eg. todo paint)                                       |\n" +
            "|\t2. deadline <description> /by <date> (e.g deadline submit hw /by 2020-01-01) |\n" +
            "|\t3. event <description> /at <date> (e.g event party /at 2020-01-01)           |\n" +
            "|\t4. list - see list of tasks added                                            |\n" +
            "|\t5. delete <task number> (e.g delete 1) - delete a task from list             |\n" +
            "|\t6. done <task number> (e.g done 1) - mark a task in list as done             |\n" +
            "|\t7. bye - exit duke                                                           |\n" +
            LINE + "-----------------------------\n";
    private Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showTaskAdded(Task task, int listLength) {
        System.out.printf("added: " + task.toString()
                + "\nNow you have %s tasks in your list\n" , listLength);
    }

    public void showTaskDeleted(Task task, int listLength) {
        System.out.printf("Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have %s tasks in your list\n" , listLength);
    }

    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n"
                + task.toString());
    }

    public void showBye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public void printError(String message) {
        System.out.println(message);
    }


}
