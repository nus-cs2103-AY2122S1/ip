import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class Du {
    public static void main(String[] args) {
        Command.greet();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {
            command = command.strip();
            if (Objects.equals(command, "list")) {
                Task.print_list_of_tasks();
            } else {
                String[] split_string = command.split(" ", 2);
                if (Objects.equals(split_string[0], "done")) {
                    Task.find_finished_task(Integer.parseInt(split_string[1]));
                } else if (Objects.equals(split_string[0], "delete")) {
                    Task.remove_task(Integer.parseInt(split_string[1]));
                } else if (Objects.equals(split_string[0], "todo")) {
                    // error handling when to do item is empty
                    if (split_string.length <= 1) {
                        System.out.println("Oh noes, the todo item cannot be empty, please input again");
                        command = sc.nextLine();
                        continue;
                    }
                    Task task = new Todo(split_string[1]);
                    task.log_add_task();
                } else {
                    // error handling when the deadline/event item is empty
                    if (split_string.length <= 1) {
                        System.out.println("Oh noes, the task item cannot be empty, please input again");
                        command = sc.nextLine();
                        continue;
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    if (Objects.equals(split_string[0], "deadline")) {

                        String[] task_time = split_string[1].split("/by ", 2);
                        // error handling when there is no time for deadline
                        if (task_time.length <= 1) {
                            System.out.println("Oh noes, the deadline item needs to have a time to be done by, please input again");
                            command = sc.nextLine();
                            continue;
                        }
                        try {
                            LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format, please input the date in this format: yyyy-MM-dd HH:mm");
                            command = sc.nextLine();
                            continue;
                        }
                        LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
                        Task task = new Deadline(task_time[0], date);
                        task.log_add_task();
                    } else if (Objects.equals(split_string[0], "event")) {

                        String[] task_time = split_string[1].split("/at ", 2);
                        // error handling when there is no time for event
                        if (task_time.length <= 1) {
                            System.out.println("Oh noes, the event item needs to have a time that it is occuring at, please input again");
                            command = sc.nextLine();
                            continue;
                        }
                        try {
                            LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format, please input the date in this format: yyyy-MM-dd HH:mm");
                            command = sc.nextLine();
                            continue;
                        }
                        LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
                        Task task = new Event(task_time[0], date);
                        task.log_add_task();
                    } else {
                        System.out.println("Oh noes, I don't understand:(, please input again");
                    }
                }




            }
            command = sc.nextLine();
        }
        Command.close_programme();
    }

}
