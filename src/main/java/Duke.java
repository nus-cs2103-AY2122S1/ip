import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void greet(){
        print("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static void print(String message) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void print(ArrayList<Task> ls) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < ls.size() ; i++) {
            System.out.println((i + 1) + ". " + ls.get(i));
        }
        System.out.println(line);
    }

    public static boolean setDone(String phrase, int listLength) {
        String[] splited = phrase.split(" ");
//        System.out.println(splited);
        if (splited.length != 2 || Integer.valueOf(splited[1]) > listLength) {
            return false;
        } else {
            return splited[0].equals("done") && splited[1].matches("\\d+");
        }
    }

    public static void printAdd(Task toAdd, int size) {
        String message = "Got it. I've added this task:\n" + "  " + toAdd + "\nNow you have " + size + " tasks in the list.";
        print(message);
    }

    public static String[] splitInput(String input) {
        String[] str = input.split("/");
        String[] first = str[0].split(" ");
        String[] second = str[1].split(" ");
        String description = "";
        String deadline = "";
        for (int i = 1; i < first.length; i++) {
            description += first[i];
            if (i != first.length - 1) {
                description += " ";
            }
        }
        for (int i = 1; i < second.length; i++) {
            deadline += second[i];
            if (i != second.length - 1) {
                deadline += " ";
            }
        }
        return new String[]{description, deadline};
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        greet();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                print(tasks);
            } else if (setDone(next, tasks.size())){
                String[] splited = next.split(" ");
                int index = Integer.valueOf(splited[1]) - 1;
                tasks.get(index).markAsDone();
                String message = "Nice! I've marked this task as done:\n" + "  " + tasks.get(index);
                print(message);
            } else {
                String[] splited = next.split(" ");
                if (splited[0].equals("todo")) {
                    String toAdd = "";
                    for (int i = 1; i < splited.length; i++) {
                        toAdd += splited[i];
                        if (i != splited.length - 1) {
                            toAdd += " ";
                        }
                    }
                    ToDo add = new ToDo(toAdd);
                    tasks.add(add);
                    printAdd(add, tasks.size());
                } else if (splited[0].equals("deadline")) {
                    String[] str = splitInput(next);
                    Deadline add = new Deadline(str[0], str[1]);
                    tasks.add(add);
                    printAdd(add, tasks.size());
                } else if (splited[0].equals("event")) {
                    String[] str = splitInput(next);
                    Event add = new Event(str[0], str[1]);
                    tasks.add(add);
                    printAdd(add, tasks.size());
                }

            }
            next = sc.nextLine();

            //            print(next);
        //            next = sc.nextLine();
        }
        print("Bye. Hope to see you again soon!");
        sc.close();
    }
}
