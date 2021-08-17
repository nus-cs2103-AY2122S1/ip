import java.util.Objects;
import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        System.out.println("Howwwwwwdy! I'm Bob");
        System.out.println("What do you want?\n");

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        String response = scanner.nextLine();

        while (!Objects.equals(response, "bye")) {

            if (Objects.equals(response, "list")) { //show list of tasks
                System.out.println(taskList.getList());
                response = scanner.nextLine();
            } else if (response.matches("done(.*)")) { //complete a task
                String[] splitResponse = response.split(" ", 2);
                System.out.println(taskList.markIndexCompleted(Integer.parseInt(splitResponse[1]) - 1));
                response = scanner.nextLine();
            } else if (response.matches("todo(.*)") || response.matches("deadline(.*)") //add a new task
                    || response.matches("event(.*)")) {
                String[] splitResponse = response.split(" ", 2);
                Task newTask;

                if (Objects.equals(splitResponse[0], "todo")) {
                    newTask = new Todo(splitResponse[1]);
                } else if (Objects.equals(splitResponse[0], "deadline")) {
                    String[] splitAgain = splitResponse[1].split(" /by ", 2);
                    newTask = new Deadline(splitAgain[0], splitAgain[1]);
                } else {
                    String[] splitAgain = splitResponse[1].split(" /at ", 2);
                    newTask = new Event(splitAgain[0], splitAgain[1]);
                }

                taskList.addTask(newTask); //add a task
                System.out.println("Okay okay I've added the task:");
                System.out.println(newTask.printTask());
                System.out.println("Yay " + taskList.noOfTasks() + " tasks! \n");
                response = scanner.nextLine();
            }
        }

        System.out.println("Bye! Shoo!");
    }
}
