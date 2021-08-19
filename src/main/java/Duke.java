import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();

        System.out.println("──────────────────────────────────────────");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?");
        System.out.println("──────────────────────────────────────────");

        String input = scanner.next();

        while (!Objects.equals(input, "bye")) {
            switch(input) {
                case "list":
                    System.out.println("──────────────────────────────────────────");
                    list.display();
                    System.out.println("──────────────────────────────────────────");
                    input = scanner.next();
                    break;

                case "done":
                    try {
                        int temp = scanner.nextInt();
                        Task cur = list.get(temp);
                        cur.Done();
                        System.out.println("──────────────────────────────────────────");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(cur);
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    }

                case "todo":
                    String tdLabel = scanner.nextLine();
                    System.out.println("──────────────────────────────────────────");
                    try {
                        Todo todo = new Todo(tdLabel);
                        list.add(todo);
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    }

                case "deadline":
                    try {
                        String dlLabel = scanner.nextLine();
                        System.out.println("──────────────────────────────────────────");
                        int dlSep = Checker.check(dlLabel, "by ");
                        String dl1 = dlLabel.substring(0, dlSep - 1);
                        String dl2 = dlLabel.substring(dlSep + 3);
                        Deadline deadline = new Deadline(dl1, dl2);
                        list.add(deadline);
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    }

                case "event":
                    try {
                        String eLabel = scanner.nextLine();
                        System.out.println("──────────────────────────────────────────");
                        int eSep = Checker.check(eLabel, "at ");
                        String e1 = eLabel.substring(0, eSep - 1);
                        String e2 = eLabel.substring(eSep + 3);
                        Event event = new Event(e1, e2);
                        list.add(event);
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    }

                case "delete":
                    try {
                        System.out.println("──────────────────────────────────────────");
                        int temp = scanner.nextInt();
                        list.remove(temp);
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    }

                default:
                    scanner.nextLine();
                    System.out.println("──────────────────────────────────────────");
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("──────────────────────────────────────────");
                    input = scanner.next();
                    break;
            }
        }
        System.out.println("──────────────────────────────────────────");
        System.out.println("Bye, hope to see you again soon!");
    }
}
