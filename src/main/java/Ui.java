import java.util.Scanner;

public class Ui {

    Parser parser;

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String greeting = "\nHello I'm Duke!\n"
            + "What can I do for you?\n";
    private static String exit = "Bye. Hope to see you again soon!";

    public Ui(DukeList list) {
        this.parser = new Parser(list);
    }

    public void run() {
        System.out.println("Hello from\n" + logo + greeting);

        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        while(!input.equals("bye")) {
            this.parser.parse(input);

            input = scan.nextLine();
        }

        System.out.println(exit);
    }
}
