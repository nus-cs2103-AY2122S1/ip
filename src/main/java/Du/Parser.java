package Du;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class Parser {
    private TaskList tasks;

    /**
     * public constructor for Parser
     * @param tasks
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * takes in commands from user and parse them
     */
    public void parse() {
        // takes in command from user after greeting
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {
            // remove any extra spaces if there is any
            command = command.strip();
            if (Objects.equals(command, "list")) {
                tasks.print_list_of_tasks();
            } else {
                String[] split_string = command.split(" ", 2);
                if (Objects.equals(split_string[0], "done")) {
                    if (split_string.length <= 1) {
                        System.out.println("Oh noes, you need to tell me what item you have finished");
                        command = sc.nextLine();
                        continue;
                    }
                    tasks.find_finished_task(Integer.parseInt(split_string[1])); // might want to find a way to check whether split_string[1] is an integer
                } else if (Objects.equals(split_string[0], "find")) {
                    if (split_string.length <= 1) {
                        System.out.println("Oh noes, you need to tell me what you want to find");
                        command = sc.nextLine();
                        continue;
                    }
                    tasks.print(tasks.search(split_string[1]));
                } else if (Objects.equals(split_string[0], "delete")) {
                    tasks.remove_task(Integer.parseInt(split_string[1])); // might want to find a way to check whether split_string[1] is an integer
                } else if (Objects.equals(split_string[0], "todo")) {
                    // error handling when to do item is empty
                    if (split_string.length <= 1) {
                        System.out.println("Oh noes, the todo item cannot be empty, please input again");
                        command = sc.nextLine();
                        continue;
                    }
                    Task task = new Todo(split_string[1], false, tasks);
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
                        Task task = new Deadline(task_time[0], false, tasks, date);
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
                        Task task = new Event(task_time[0], false, tasks, date);
                        task.log_add_task();
                    } else {
                        System.out.println("Oh noes, I don't understand:(, please input again");
                    }
                }

            }
            command = sc.nextLine();
        }
    }
}
