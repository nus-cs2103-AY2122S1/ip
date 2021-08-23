import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;

public class Duke {
    public static Task[] tasks;
    public static Integer counter1 = 0;


    public static String niceLine() {
        return "______________________________________________________________\n";
    }


    public static void main(String[] args) throws DukeException1{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = niceLine() + "Hello! I'm Duke \n" + "What can I do for you?\n" + niceLine();
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayList<Task> list = new ArrayList<>();
        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println((niceLine() + "\tHere are the tasks in your list:"));
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + Integer.toString(i + 1) + "." + list.get(i).toString());
                }
                System.out.println("\n" + niceLine());
                input = scanner.nextLine();
            } else if(input.split(" ")[0].equals("done")) {
                Integer count = Integer.valueOf(input.split(" ")[1]);
                list.get(count - 1).markAsDone();
                System.out.println(niceLine() +  "\tNice! I've marked this task as done: \n \t \t" +
                        " [" + list.get(count - 1).getStatusIcon() + "] " + list.get(count - 1).description);
                System.out.println("\n" + niceLine());
                input = scanner.nextLine();
            } else if(input.split(" ")[0].equals("todo")) {
                try {
                    if(input.split(" ", 2).length == 1) {
                        ToDo todo = new ToDo(input.split(" ", 2)[0]);
                    } else {
                        ToDo todo = new ToDo (input.split(" ", 2)[1]);
                        list.add(todo);
                        System.out.println(niceLine() +  "\t" + "Got it. I've added this task: " + "\n\t\t" + todo.toString() +
                                "\n\tNow you have " + Integer.toString(list.size()) + " tasks in the list.");
                        System.out.println("\n" + niceLine());
                        input = scanner.nextLine();
                    }
                } catch(DukeException1 e) {
                    System.out.println(niceLine() +  "\tOOPS!!! The description of a todo cannot be empty.");
                    System.out.println("\n" + niceLine());
                    input = scanner.nextLine();
                }

            } else if(input.split(" ")[0].equals("deadline")) {
                try {
                    if (input.split(" ", 2).length == 1) {
                        Deadline deadline = new Deadline(input.split(" ", 2)[0], "");
                    } else {
                        Deadline deadline = new Deadline(input.split(" ", 2)[1].split(" /")[0], input.split("/by ")[1]);
                        list.add(deadline);
                        System.out.println(niceLine() +  "\t" + "Got it. I've added this task: " + "\n\t\t" + deadline.toString() +
                                "\n\tNow you have " + Integer.toString(list.size()) + " tasks in the list.");
                        System.out.println("\n" + niceLine());
                        input = scanner.nextLine();
                    }
                } catch(DukeException1 e) {
                    System.out.println(niceLine() +  "\tOOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("\n" + niceLine());
                    input = scanner.nextLine();
                }
            } else if(input.equals("blah")) {
                try {
                    Task task = new Task(input);
                } catch(DukeException1 e) {
                    System.out.println(niceLine() +  "\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("\n" + niceLine());
                    input = scanner.nextLine();
                }
            } else if(input.split(" ")[0].equals("delete")) {
                Integer number = Integer.valueOf(input.split(" ")[1]);
                System.out.println(niceLine() +  "\tNoted. I've removed this task: \n\t\t" + list.get(number - 1).toString() +
                        "\n\tNow you have " + Integer.toString(list.size() - 1) + " tasks in the list.");
                System.out.println("\n" + niceLine());
                list.remove(number - 1);
                input = scanner.nextLine();
            } else {
                try {
                    if (input.split(" ", 2).length == 1) {
                        Event event = new Event(input.split(" ", 2)[0], "");
                    } else {
                        Event event = new Event(input.split(" ", 2)[1].split(" /")[0], input.split("/at ")[1]);
                        list.add(event);
                        System.out.println(niceLine() + "\tGot it. I've added this task: " + "\n\t\t" + event.toString() +
                                "\n\tNow you have " + Integer.toString(list.size()) + " tasks in the list.");
                        System.out.println("\n" + niceLine());
                        input = scanner.nextLine();
                    }
                } catch(DukeException1 e) {
                    System.out.println(niceLine() + "\tOOPS!!! The description of a event cannot be empty.");
                    System.out.println("\n" + niceLine());
                    input = scanner.nextLine();
                }
            }
        }
        System.out.println(niceLine() + "\tBye. Hope to see you again soon!");
        System.out.println("\n" + niceLine());
    }
}
