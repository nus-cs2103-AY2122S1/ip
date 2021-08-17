import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "\nHello I'm Duke!\n"
                + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!";

        DukeList list = new DukeList();

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
                } else if (segment[0].equals("todo")) {
                    list.addTodo(input.split("todo", 2)[1]);
                } else if (segment[0].equals("deadline")) {
                    list.addDeadlines(input.split("deadline", 2)[1]);
                } else if (segment[0].equals("event")) {
                    list.addEvents(input.split("event", 2)[1]);
                } else {
                    list.add(input);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            input = scan.nextLine();
        }

        System.out.println(exit);
    }
}
