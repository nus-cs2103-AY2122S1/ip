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
            if (Objects.equals(response, "list")) {
                System.out.println(taskList.getList());
                response = scanner.nextLine();
            } else if (response.matches("done(.*)")) {
                String[] splitResponse = response.split(" ", 2);
                System.out.println(taskList.markIndexCompleted(Integer.parseInt(splitResponse[1]) - 1));
                response = scanner.nextLine();
            } else {
                taskList.addTask(new Task(response));
                System.out.println("added: " + response + "\n");
                response = scanner.nextLine();
            }
        }

        System.out.println("Bye! Shoo!");
    }
}
