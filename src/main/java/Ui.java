public class Ui {

    private final static String DIVIDER = "  ---------------------------------------------";
    private final static String INDENT_1 = "    ";
    private final static String INDENT_2 = "      ";

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
    }

    public void showList() {
        System.out.println(INDENT_1 + "Here are the tasks in your list:");
    }

    public void removeTask(Task task) {
        System.out.println(INDENT_1 + "Sure! I've removed this task:\n");
        System.out.println(INDENT_2 + task.toString());
        ;
    }

    public void addTask(Task task) {
        System.out.println(INDENT_1 + "Sure! I've added this task:");
        System.out.println(INDENT_2 + task.toString());
    }

    public void numberOfTasks(int size) {
        String numberOfTasks = INDENT_1 + "Now you have " + size +
                (size == 1 ? " task" : " tasks") + " in the list.\n"
                        + DIVIDER;
        System.out.println(numberOfTasks);
    }


}
