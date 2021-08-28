import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class Duke {
    private static final ArrayList<Task> list = new ArrayList<>();

    private static void update(Task t) {
        System.out.println("Got it. I've added this task:\n  "
                + t.toString()
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void delete(int number) {
        Task toDelete = list.get(number - 1);
        list.remove(number - 1);
        System.out.println("Noted. I've removed this task:\n  "
                + toDelete.toString()
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void readAll() {
        File f = new File("./Data/");
        File f2 = new File("./Data/Duke.txt");
        if (!f.exists()) {
            f.mkdir();
        }

        try {
            f2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader b = new BufferedReader(new FileReader(f2));
            String line = b.readLine();
            while (line != null) {
                String[] split = line.split("\\|");
                switch(split[0]) {
                case("T"):
                    Todo t = new Todo(split[2]);
                    if (!split[1].equals("0")) {
                        t.markAsDone();
                    }
                    list.add(t);
                    break;
                case("E"):
                    Event e = new Event(split[2], split[3]);
                    if (!split[1].equals("0")) {
                        e.markAsDone();
                    }
                    list.add(e);
                    break;
                case("D"):
                    Deadline d = new Deadline(split[2], split[3]);
                    if (!split[1].equals("0")) {
                        d.markAsDone();
                    }
                    list.add(d);
                    break;
                }
                line = b.readLine();
            }
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeAll() {
        try {
            FileWriter w = new FileWriter("./Data/Duke.txt");
            for (Task task : list) {
                w.write(task.toWrite());
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String logo = "DUKE\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?");
        readAll();
        Scanner s = new Scanner(System.in);
        String command = s.nextLine();
        while(!command.equals("bye")) {
            switch(command.split(" ")[0]) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    int num = i + 1;
                    if (list.get(i) != null) {
                        System.out.println(num + "." + list.get(i).toString());
                    }
                }
                command = s.nextLine();
                break;
            case "done":
                int finished = Integer.parseInt(command.split(" ")[1]) - 1;
                list.get(finished).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + "  " + list.get(finished).toString());
                command = s.nextLine();
                break;
            case "todo":
                try {
                    if (command.equals("todo") || command.equals("todo ")) {
                        throw new DukeException();
                    }
                    Todo toAdd = new Todo(command.split(" ", 2)[1]);
                    list.add(toAdd);
                    update(toAdd);
                } catch (DukeException exception) {
                    System.out.println(exception.getMessage());
                }
                command = s.nextLine();
                break;
            case "deadline":
                String[] splitD = command.split(" ", 2)[1].split(" /by ", 2);
                String first = splitD[0];
                String second = splitD[1];
                Deadline toAdd2 = new Deadline(first, second);
                list.add(toAdd2);
                update(toAdd2);
                command = s.nextLine();
                break;
            case "event":
                String[] splitE = command.split(" ", 2)[1].split(" /at ", 2);
                String one = splitE[0];
                String two = splitE[1];
                Event toAdd3 = new Event(one, two);
                list.add(toAdd3);
                update(toAdd3);
                command = s.nextLine();
                break;
            case "delete":
                int del = Integer.parseInt(command.split(" ", 2)[1]);
                delete(del);
                command = s.nextLine();
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                command = s.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        writeAll();
    }
}
