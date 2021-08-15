import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String line = "-------------------------------------";
        System.out.println(line + "\n" + "Good Morning Master Wayne, Alfred here.\nWhat can I do for you today?\n" + line);

        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if(command.equals("list")) {

                System.out.println(line);

                for(int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.println((i+1) + ". " + " " + task.toString());
                }
                System.out.println(line);

                command = scanner.nextLine();
            } else if (command.startsWith("done ")) {

                System.out.println(line);

                String[] splitup = command.split(" ");
                int index = Integer.parseInt(splitup[1]);
                list.get(index - 1).markAsDone();
                System.out.println("Very well, Master Wayne. This task has been marked as per your request.");
                System.out.println((index) + ". " + list.get(index - 1)); //actual index is index - 1

                System.out.println(line);

                command = scanner.nextLine();

            } else if (command.startsWith("todo ")) {

                String[] splitup = command.split(" ");
                String desc = "";
                for (int i = 1; i < splitup.length; i++) {
                    if (i == 1) {
                        desc = splitup[1];
                    } else {
                        desc = desc + " " + splitup[i];
                    }
                }
                Todo todo = new Todo(desc);
                list.add(todo);

                System.out.println("Got it. I've added this task:");
                System.out.println(todo.toString());
                if (list.size() == 1) {
                    System.out.println("Now you have 1 task in the list.");
                } else {
                    System.out.println("Now you have " + list.size() +" tasks in the list.");
                }

                command = scanner.nextLine();

            } else if (command.startsWith("deadline ")) {

                String[] splitup = command.split(" ");
                String desc = "";
                String by = "";
                boolean byfound = false;
                for (int i = 1; i < splitup.length; i++) {
                    if (byfound) {
                        if (by == "") {
                            by = splitup[i];
                        } else {
                            by = by + " " + splitup[i];
                        }
                    } else {
                        if (i == 1) {
                            desc = splitup[1];
                        } else if (splitup[i].equals("/by")) {
                            byfound = true;
                        } else {
                            desc = desc + " " + splitup[i];
                        }
                    }
                }

                Deadline deadline = new Deadline(desc, by);
                list.add(deadline);

                System.out.println("Got it. I've added this task:");
                System.out.println(deadline.toString());
                if (list.size() == 1) {
                    System.out.println("Now you have 1 task in the list.");
                } else {
                    System.out.println("Now you have " + list.size() +" tasks in the list.");
                }

                command = scanner.nextLine();

            } else if (command.startsWith("event ")) {

                String[] splitup = command.split(" ");
                String desc = "";
                String at = "";
                boolean atFound = false;
                for (int i = 1; i < splitup.length; i++) {
                    if (atFound) {
                        if (at == "") {
                            at = splitup[i];
                        } else {
                            at = at + " " + splitup[i];
                        }
                    } else {
                        if (i == 1) {
                            desc = splitup[1];
                        } else if (splitup[i].equals("/at")) {
                            atFound = true;
                        } else {
                            desc = desc + " " + splitup[i];
                        }
                    }
                }

                Event event = new Event(desc, at);
                list.add(event);

                System.out.println("Got it. I've added this task:");
                System.out.println(event.toString());
                if (list.size() == 1) {
                    System.out.println("Now you have 1 task in the list.");
                } else {
                    System.out.println("Now you have " + list.size() +" tasks in the list.");
                }

                command = scanner.nextLine();

            } else {
                System.out.println(line + "\n" + "Understood, Master Wayne. Added: " + command + "\n" + line);
                Task t = new Task(command);
                list.add(t);
                command = scanner.nextLine();
            }
        };
        System.out.println("Have a pleasant day, Master Wayne.\n");
    }
}
