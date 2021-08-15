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
                } else if (Objects.equals(split_string[0], "todo")) {
                    Task task = new Todo(split_string[1]);
                    task.log_add_task();
                } else {
                    if (Objects.equals(split_string[0], "deadline")) {
                        String[] task_time = split_string[1].split("/by ", 2);
                        Task task = new Deadline(task_time[0], task_time[1]);
                        task.log_add_task();
                    } else if (Objects.equals(split_string[0], "event")) {
                        String[] task_time = split_string[1].split("/at ", 2);
                        Task task = new Event(task_time[0], task_time[1]);
                        task.log_add_task();
                    }
                }




            }
            command = sc.nextLine();
        }
        Command.close_programme();
    }

}
