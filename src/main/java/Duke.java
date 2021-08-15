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
                Todo newTodo = new Todo(description, false);
                l.addTask(newTodo);
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
            }
            input = s.next();
        }

        System.out.println("Good riddance! Time to continue my beauty sleep :)");

    }
}
