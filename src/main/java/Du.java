import java.util.Objects;
import java.util.Scanner;

public class Du {
    public static void main(String[] args) {
        Command.greet();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {
            if (Objects.equals(command, "list")) {
                Task.print_list_of_tasks();
            } else {
                Task task = new Task(command);
                task.log_add_task();
            }
            command = sc.nextLine();
        }
        Command.close_programme();
    }

}
