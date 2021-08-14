import java.util.Objects;
import java.util.Scanner;

// TODO: add in exceptions for invalid input
public class Du {
    public static void main(String[] args) {
        Command.greet();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {
            if (Objects.equals(command, "list")) {
                Task.print_list_of_tasks();
            } else {
                String[] split_string = command.split(" ", 2);
                if (Objects.equals(split_string[0], "done")) {
                    Task.find_finished_task(Integer.parseInt(split_string[1]));
                } else {
                    Task task = new Task(command);
                    task.log_add_task();
                }

            }
            command = sc.nextLine();
        }
        Command.close_programme();
    }

}
