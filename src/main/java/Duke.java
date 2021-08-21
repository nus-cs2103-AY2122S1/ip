import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String greeting = "\nHello I'm Duke!\n"
            + "What can I do for you?\n";
    private static String exit = "Bye. Hope to see you again soon!";

    private static String PATH;

    public static void main(String[] args) {
        PATH = System.getProperty("user.dir");

        DukeList list = new DukeList();

        Storage storage = new Storage(PATH, list);

        try {
            storage.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        Scanner scan = new Scanner(System.in);

        System.out.println("Hello from\n" + logo + greeting);

        String input = scan.nextLine();
        String[] segment;

        while(!input.equals("bye")) {
            segment = input.split(" ", 2);

            try {
                if(input.equals("list")) {
                    list.list();
                } else if (segment[0].equals("done") && segment.length == 2) {
                    list.done(Integer.parseInt(segment[1]));
                } else if (segment[0].equals("delete") && segment.length == 2) {
                    list.delete(Integer.parseInt(segment[1]));
                } else if (segment[0].equals("todo")) {
                    list.addToDo(input.split("todo", 2)[1]);
                } else if (segment[0].equals("deadline")) {
                    list.addDeadlines(input.split("deadline", 2)[1]);
                } else if (segment[0].equals("event")) {
                    list.addEvents(input.split("event", 2)[1]);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry,"
                            + " but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            input = scan.nextLine();
        }

        storage.save();

        System.out.println(exit);
    }
}
