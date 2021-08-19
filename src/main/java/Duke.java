import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static final Task[] list = new Task[100];
    private static int listNumber = 0;

    private static void update(Task t) {
        System.out.println("Got it. I've added this task:\n  "
                + t.toString()
                + "\nNow you have " + listNumber + " tasks in the list.");
    }

    public static void main(String[] args) {
        String logo = "DUKE\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?");
        Scanner s = new Scanner(System.in);
        String command = s.nextLine();
        while(!command.equals("bye")) {
            switch(command.split(" ")[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listNumber; i++) {
                        int num = i + 1;
                        if (list[i] != null) {
                            System.out.println(num + "." + list[i].toString());
                        }
                    }
                    command = s.nextLine();
                    break;
                case "done":
                    int finished = Integer.parseInt(command.split(" ")[1]) - 1;
                    list[finished].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "  " + list[finished].toString());
                    command = s.nextLine();
                    break;
                case "todo":
                    Todo toAdd = new Todo(command.substring(5));
                    list[listNumber] = toAdd;
                    listNumber += 1;
                    update(toAdd);
                    command = s.nextLine();
                    break;
                case "deadline":
                    String[] splitD = command.split(" ", 2)[1].split(" /by ", 2);
                    String first = splitD[0];
                    String second = splitD[1];
                    Deadline toAdd2 = new Deadline(first, second);
                    list[listNumber] = toAdd2;
                    listNumber += 1;
                    update(toAdd2);
                    command = s.nextLine();
                    break;
                case "event":
                    String[] splitE = command.split(" ", 2)[1].split(" /at ", 2);
                    String one = splitE[0];
                    String two = splitE[1];
                    Event toAdd3 = new Event(one, two);
                    list[listNumber] = toAdd3;
                    listNumber += 1;
                    update(toAdd3);
                    command = s.nextLine();
                    break;
                default:
                    System.out.println("added: " + command);
                    list[listNumber]= new Task(command);
                    listNumber += 1;
                    command = s.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
