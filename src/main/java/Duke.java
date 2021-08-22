import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The main class for the bot
 * Contains the methods 'to do', 'deadline', 'event', 'list',
 * 'delete' and 'bye'
 */
public class Duke {
    private static String format;
    public static void main(String[] args) {
        start();
    }

    /**
     * Method to get the current format setting.
     * @return the DateTimeFormatter
     */
    public static String getFormat() {
        return format;
    }

    public static void setFormat(String newFormat) throws IllegalArgumentException {
        DateTimeFormatter.ofPattern(newFormat);
        format = newFormat;
    }


    /**
     * Method to start the bot and waits for the user's input.
     */
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
                Scanner s1 = new Scanner(s.nextLine());
                int counter = 0;
                while (s1.hasNextInt()) {
                    int index = s1.nextInt();
                    l.markComplete(index);
                    counter ++;
                }
                if (counter == 0) {
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
            } else if (input.equals("delete")) {
                Scanner s5 = new Scanner(s.nextLine());
                if (s5.hasNextInt()) {
                    int index = s5.nextInt();
                    l.deleteTask(index);
                } else {
                    System.out.println("Invalid index, please try again");
                }
            } else if (input.equals("setFormat")) {
                Scanner s6 = new Scanner(s.nextLine());
                if (s6.hasNextLine()) {
                    try {
                        setFormat(s6.nextLine().substring(1));
                        System.out.println(
                                "Date format has been updated to: "
                                        + getFormat()
                        );
                        l.writeToFile();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Not an acceptable format. Please try again");
                    }
                }
            } else if (input.equals("format")) {
                System.out.println("Current format " + getFormat());
            } else {
                System.out.println("No specific command specified. Please try again");
            }
            input = s.next();
        }

        System.out.println("Good riddance! Time to continue my beauty sleep :)");

    }
}
