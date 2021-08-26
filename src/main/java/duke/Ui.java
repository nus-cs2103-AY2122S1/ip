package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private final static String DIVIDER = "  ---------------------------------------------";
    protected final static String INDENT_1 = "    ";
    protected final static String INDENT_2 = "      ";

    public Ui() {

        scanner = new Scanner(System.in);
    }

    public void showLine() {

        System.out.println(DIVIDER);
    }

    public void greetUser() {
        showLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        showLine();
    }

    public void sayBye() {
        System.out.println(INDENT_1 + "Bye! Hope to see you again soon :)");
        scanner.close();
    }

    public void listNumber(int num, Task temp) {

        System.out.println(INDENT_2 + (num + 1) + ". " + temp.toString());
    }

    public void showList() {
        System.out.println(INDENT_1
                + "Here are the tasks in your list:");
    }

    public void doneTask(Task temp) {
        System.out.println(INDENT_1 + "YAY good job for completing the task :)\n"
                + INDENT_1 + "I've marked it as done:\n" + INDENT_2 +
                        temp.toString());
    }

    public void removeTask(Task task) {
        System.out.println(INDENT_1 + "Sure! I've removed this task:\n");
        System.out.println(INDENT_2 + task.toString());
        ;
    }

    public void findTask() {
        System.out.println(INDENT_1 + "I have found these matching tasks!!");
    }

    public void noSuchTask() {
        System.out.println(INDENT_1 + "I cannot find any matching tasks :(");
    }

    public void addTask(Task task) {
        System.out.println(INDENT_1 + "Sure! I've added this task:");
        System.out.println(INDENT_2 + task.toString());
    }

    public void numberOfTasks(TaskList taskList) {
        String numberOfTasks = INDENT_1 + "Now you have " + taskList.size() +
                (taskList.size() == 1 ? " task" : " tasks")
                        + " in the list.\n";
        System.out.println(numberOfTasks);
    }

    public String readCommand() {
        if(scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return null;
        }
    }

    public String commandError() {
        return INDENT_1 + "☹ OH NO I'm sorry, but I don't "
                + "know what that means :-(";
    }

    public String dateError() {
        return INDENT_1 + "OH NO :( I can't seem to understand "
                + "the date you have entered.\n"
                        + INDENT_1 + "I can only understand if it "
                                + "is in  the yyyy-mm-dd format..";
    }

    public String dateTimeError() {
        return INDENT_1 + "OH NO :( I can't seem "
                + "to understand the date and time you have entered.\n"
                        + INDENT_1 + "I can only understand if it is in "
                                + "yyyy-MM-dd HH:mm format..";
    }

    public String incorrectAtOrBy(String taskType) {
        return INDENT_1 + "☹ OOPS!!! The " +
                (taskType.equals("event")
                        ? "period which the event occurs"
                        : "deadline") + " is not inputted correctly. Use "
                                + (taskType.equals("event") ? "/at" : "/by")
                                        + " to indicate ;)";
    }

    public String noDescription(String taskType) {
        return INDENT_1 + "☹ OOPS!!! The description of "
                + (taskType.equals("event") ? "an " : "a ")
                        + taskType + " cannot be empty.";
    }
    public String deleteInvalidError() {
        return INDENT_1 + "☹ OOPS!!! There is no "
                + "corresponding task to be deleted.";
    }

    public String deleteNoNumError() {
        return INDENT_1 + "☹ OOPS!!! The task to be deleted"
                + "is not indicated!!";
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String doneError() {
        return INDENT_1 + "☹ OOPS!!! There is no corresponding task to be "
                + "marked done.";
    }

    public String doneNoNumError() {

        return INDENT_1 + "☹ OOPS!!! The task to be marked done is not indicated!!";
    }

    public String loadingError() {

        return "OH NO :( The file cannot be found...";
    }





}
