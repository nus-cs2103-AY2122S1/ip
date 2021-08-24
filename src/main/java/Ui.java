import java.util.Scanner;

public class Ui {
    private static final String horizontalLines = "-----------------------------------------";

    public void showLines() {
        System.out.println(horizontalLines);
    }
    public void showWelcome() {
        System.out.println(horizontalLines + "\nHello! I'm Naruto\nWhat can I do for you?\n" + horizontalLines);
    }
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        in.close();
        return userInput;
    }
    public void showTaskAddedInteraction(Task newTask, TaskList tasks) {
        System.out.printf("Great, I've added this task:\n  %s%n", newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", tasks.getSize());
    }
    public void showGettingAllTaskItemsInteraction(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        int taskNum;
        for (taskNum = 0; taskNum < tasks.getSize(); taskNum++) {
            Task task = tasks.getTask(taskNum);
            System.out.println(counter + "." + task.toString());
            counter += 1;
        }
    }
    public void showTaskDoneInteraction(Task task) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(task.toString());
    }
    public void showTaskDeletedInteraction(Task task, TaskList tasks) {
        System.out.printf("Roger that Sensei, I've removed this task:\n  %s%n", task.toString());
        System.out.printf("Now you have %d tasks in the list.%n", tasks.getSize());
    }
    public void showByeMessage() {
        System.out.println("See ya! Hope to see you again!" + "\n" + horizontalLines);
    }
}
