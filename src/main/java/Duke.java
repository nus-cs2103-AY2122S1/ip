import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you\n");

        DukeList dukeList = new DukeList();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(dukeList.handleInput(input));
            input = sc.nextLine();
        }

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
