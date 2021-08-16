import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        MyList l = new MyList();

        System.out.println(
                "Yo! Duke here \n"
                + "What did you call me for? \n"
                + "It better be something useful or else... \n"
        );

        Scanner s = new Scanner(System.in);

        String input = s.next();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                l.listAll();
            } else if (input.equals("done")) {
                if (s.hasNextInt()) {
                    int index = s.nextInt();
                    l.markComplete(index);
                } else {
                    System.out.println("Invalid index, please try again");
                }
            } else if (input.equals("todo")) {
                Scanner s2 = new Scanner(s.nextLine());
                String description = "";
                while (s2.hasNextLine()) {
                    description = s2.nextLine();
                }
                try {
                    Todo newTodo = new Todo(description, false);
                    l.addTask(newTodo);
                } catch (WrongCommandFormatException e) {
                    System.out.println(e.getMessage());
                }

            } else if (input.equals("deadline")) {
                Scanner s3 = new Scanner(s.nextLine());
                String description = "";
                while (s3.hasNextLine()) {
                    description = s3.nextLine();
                }
                try {
                    Deadline newDeadline = new Deadline(description, false);
                    l.addTask(newDeadline);
                } catch (WrongCommandFormatException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.equals("event")) {
                Scanner s4 = new Scanner(s.nextLine());
                String description = "";
                while (s4.hasNextLine()) {
                    description = s4.nextLine();
                }
                try {
                    Event newEvent = new Event(description, false);
                    l.addTask(newEvent);
                } catch (WrongCommandFormatException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("No specific command specified. Please try again");
            }
            input = s.next();
        }

        System.out.println("Good riddance! Time to continue my beauty sleep :)");

    }
}
