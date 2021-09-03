import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    
    private static void save(ArrayList<String> tasks) throws IOException {
        File f = new File("tasks.txt");
        System.out.print(f.createNewFile());
        FileWriter fw = new FileWriter("tasks.txt");
        for (String task : tasks) {
            fw.write(task + System.lineSeparator());
        }
        fw.close();
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
        s.close();
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ArrayList<String> list = new ArrayList<String>();
        int amount = 0;
        
        String lineBreak = "\t____________________________________________________________";
        System.out.println(lineBreak
                + "\n\t Hello! I'm Luke, your slightly useful personal assistant!\n"
                + "\t What can I do for you, my liege?\n"
                + "\t Type 'list' to show previous inputs\n"
                + "\t Type 'todo TASK' to indicate that TASK has to be done\n"
                + "\t Type 'deadline TASK /by DATE/TIME' to indicate that TASK has to be done by DATE/TIME\n"
                + "\t Type 'event TASK /at DATE/TIME PERIOD' to indicate that TASK occurs at DATE/TIME PERIOD\n"
                + "\t Type 'done #' to indicate that task # has been done\n"
                + "\t Type 'bye' to end\n"
                + lineBreak);

        String response = scanner.nextLine();

        while (!response.equals("bye")) {
            if (response.equals("list")) {
                System.out.println(lineBreak);
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < amount; i++) {
                    System.out.println("\t " + (i + 1) + "." + list.get(i));
                }
                System.out.println(lineBreak);
            } else if (response.matches("done ([0-9]|[1-9][0-9])")) {
                int taskNo = Integer.parseInt(response.replaceAll("\\D", "")) - 1;
                System.out.println(lineBreak);
                System.out.println("\t Nice! I've marked this task as done:");
                list.set(taskNo, list.get(taskNo).substring(0,4) + "X" + list.get(taskNo).substring(5));
                System.out.println("\t " + list.get(taskNo));
                System.out.println(lineBreak);
                save(list);
            } else if (response.matches("delete ([0-9]|[1-9][0-9])")) {
                int taskNo = Integer.parseInt(response.replaceAll("\\D", "")) - 1;
                System.out.println(lineBreak);
                System.out.println("\t Noted. I've removed this task:");
                System.out.println("\t " + list.get(taskNo));
                System.out.println("\t Now you have " + (amount - 1) + " tasks in the list.");
                System.out.println(lineBreak);
                list.set(taskNo, "deleted");
                save(list);
            } else if (response.matches("todo .+")) {
                System.out.println(lineBreak);
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t   [T][ ] " + response.substring(5));
                System.out.println("\t Now you have " + (amount + 1) + " tasks in the list.");
                System.out.println(lineBreak);
                list.add(amount, "[T][ ] " + response.substring(5));
                amount++;
                save(list);
            } else if (response.matches("deadline .+")) {
                System.out.println(lineBreak);
                System.out.println("\t Got it. I've added this task:");
                int pos = response.indexOf('/');
                list.add(amount, "[D][ ] "
                        + response.substring(9, pos - 1)
                        + " (by: " + response.substring(response.indexOf('/') + 4) + ")");
                System.out.println("\t   " + list.get(amount));
                System.out.println("\t Now you have " + (amount + 1) + " tasks in the list.");
                System.out.println(lineBreak);
                amount++;
                save(list);
            } else if (response.matches("event .+")) {
                System.out.println(lineBreak);
                System.out.println("\t Got it. I've added this task:");
                int pos = response.indexOf('/');
                list.add(amount, "[E][ ] "
                        + response.substring(6, pos - 1)
                        + " (at: " + response.substring(response.indexOf('/') + 4) + ")");
                System.out.println("\t   " + list.get(amount));
                System.out.println("\t Now you have " + (amount + 1) + " tasks in the list.");
                System.out.println(lineBreak);
                amount++;
                save(list);
            } else if (response.equals("todo")) {
                System.out.println(lineBreak);
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println(lineBreak);
            } else if (response.equals("deadline")) {
                System.out.println(lineBreak);
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                System.out.println(lineBreak);
            } else if (response.equals("event")) {
                System.out.println(lineBreak);
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                System.out.println(lineBreak);
            } else {
                System.out.println(lineBreak);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(lineBreak);
            }
            response = scanner.nextLine();
        }
        System.out.println(lineBreak);
        System.out.println("\t Bye! Talk again sometime!");
        System.out.println(lineBreak);

        scanner.close();
    }
}