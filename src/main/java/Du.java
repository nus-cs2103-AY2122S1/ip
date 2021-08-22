import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Du {

    public static void read_task(String task_name) {
        String[] task = task_name.split(" , ", 4);
        boolean done = false;
        Task t = null;
        if (Integer.parseInt(task[1]) == 1) {
            done = true;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy, HHmm");

        if (Objects.equals(task[0], "T")) {
            t = new Todo(task[2], done);
        } else if (Objects.equals(task[0], "D")) {
            t = new Deadline(task[2], done, LocalDateTime.parse(task[3], formatter));
        } else if (Objects.equals(task[0], "E")) {
            t = new Event(task[2], done, LocalDateTime.parse(task[3], formatter));
        }
        System.out.println(t);
    }

    public static void update_records(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : Task.list_of_tasks) {
            fw.write(t.log_record() + "\n");
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        Command.greet();
        String FILEPATH = "data/du.txt";

        File f = new File(FILEPATH);

        // check if data folder exists, if not, create it
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();

        // check if du.txt exists, if not, create it
        if (f.createNewFile()) {
            System.out.println("You have no previous records");
        } else {
            System.out.println("Here are your previous records:");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String task_name = s.nextLine();
                read_task(task_name);
            }
        }
        System.out.println("\nIs there anything I can do for you?");
        // takes in command from user after greeting
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {
            // remove any extra spaces if there is any
            command = command.strip();
            if (Objects.equals(command, "list")) {
                Task.print_list_of_tasks();
            } else {
                String[] split_string = command.split(" ", 2);
                if (Objects.equals(split_string[0], "done")) {
                    if (split_string.length <= 1) {
                        System.out.println("Oh noes, you need to tell me what item you have finished");
                        command = sc.nextLine();
                        continue;
                    }
                    Task.find_finished_task(Integer.parseInt(split_string[1])); // might want to find a way to check whether split_string[1] is an integer
                } else if (Objects.equals(split_string[0], "delete")) {
                    Task.remove_task(Integer.parseInt(split_string[1])); // might want to find a way to check whether split_string[1] is an integer
                } else if (Objects.equals(split_string[0], "todo")) {
                    // error handling when to do item is empty
                    if (split_string.length <= 1) {
                        System.out.println("Oh noes, the todo item cannot be empty, please input again");
                        command = sc.nextLine();
                        continue;
                    }
                    Task task = new Todo(split_string[1], false);
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
                        Task task = new Deadline(task_time[0], false, date);
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
                        Task task = new Event(task_time[0], false, date);
                        task.log_add_task();
                    } else {
                        System.out.println("Oh noes, I don't understand:(, please input again");
                    }
                }




            }
            command = sc.nextLine();
        }
        update_records(FILEPATH);
        Command.close_programme();
    }

}
