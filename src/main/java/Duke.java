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
        while (true){
            String command = stdin.nextLine();
            if ("bye".equals(command)){
                System.out.println("----------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("----------------------");
                break;
            }
            else if ("list".equals(command)){
                for (Task task : tasks){
                    System.out.println(task);
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
                tasks.add(new ToDo(name));
            }
            else if (command.startsWith("deadline ")){
                String name = command.split(" /by ", 2)[0].substring(9);
                String time = command.split(" /by ", 2)[1];
                tasks.add(new Deadline(name, time));
            }
            else if (command.startsWith("event ")){
                String name = command.split(" /at ", 2)[0].substring(6);
                String time = command.split(" /at ", 2)[1];
                tasks.add(new Event(name, time));
            }
        }

    }
}
