import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("----------------------");
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("----------------------");
        Scanner stdin = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        DateTimeFormatter dtformatter = DateTimeFormatter.ISO_DATE;
        while (true){
            String command = stdin.nextLine();
            if ("bye".equals(command)){
                System.out.println("----------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("----------------------");
                break;
            }
            else if ("list".equals(command)){
                System.out.println("Here are the tasks in your list:");
                for (Task task : tasks){
                    if (task.isTimed()){
                        System.out.println(((TimedTask)task).format(dtformatter));
                    } else {
                        System.out.println(task.toString());
                    }
                }
            }
            else if (command.startsWith("done ")) {
                for (Task task : tasks){
                    if (command.substring(5).equals(task.getName())){
                        task.markDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task);
                    }
                }
            }
            else if (command.startsWith("todo ")) {
                String name = command.substring(5);
                Task task = new ToDo(name);
                if (name.equals("")){
                    System.out.println("Oops! The description of the task cannot be empty :(");
                }
                else {
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                }
            }
            else if (command.startsWith("deadline ")){
                if (command.contains("/by ")) {
                    String name = command.split("/by ", 2)[0].substring(9);
                    String time = command.split("/by ", 2)[1];
                    if (name.equals("") || time.equals("")){
                        System.out.println("Oops! The name and/or time of a deadline cannot be empty");
                    }
                    else {
                        try {
                            Task task = new Deadline(name, time);
                            tasks.add(task);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(task);
                        } catch (DateTimeParseException e) {
                            System.err.println("Error: Date format not recognised");
                        }
                    }
                }
                else {
                    System.out.println("Oops! The deadline must be by a certain time :(");
                }
            }
            else if (command.startsWith("event ")){
                if (command.contains("/at ")) {
                    String name = command.split("/at ", 2)[0].substring(6);
                    String time = command.split("/at ", 2)[1];
                    if (name.equals("") || time.equals("")){
                        System.out.println("Oops! The name and/or time of an event cannot be empty");
                    }
                    else {
                        try {
                            Task task = new Event(name, time);
                            tasks.add(task);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(task);
                        } catch (DateTimeParseException e) {
                            System.err.println("Error: Date format not recognised");
                        }
                    }
                }
                else {
                    System.out.println("Oops! The event must be at a certain time :(");
                }
            }
            else if (command.startsWith("delete ")){
                String name = command.substring(7);
                boolean taskFound = false;
                for (Task task : tasks){
                    if (task.getName().equals(name)){
                        tasks.remove(task);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task);
                        taskFound = true;
                        break;
                    }
                }
                if (!taskFound){
                    System.out.println("Oops! A task of that name could not be found :(");
                }
            }
            else if (command.startsWith("dateformat ")){
                try {
                    String newDtformat = command.substring(11);
                    dtformatter = DateTimeFormatter.ofPattern(newDtformat);
                    System.out.println("Gotcha :D Dates are now printed as: " + newDtformat);
                } catch (DateTimeParseException e) {
                    System.err.println("Error: Date format not recognised");
                }
            }
            else {
                List<String> command_list = Arrays.asList("todo", "deadline", "event", "delete");
                if (command_list.contains(command)){
                    System.out.println("Oops! The task description cannot be empty L:(");
                }
                else {
                    System.out.println("Oops! I'm sorry, but I don't know what that means :(");
                }
            }
        }

    }
}
