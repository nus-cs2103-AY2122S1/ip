import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you\n");

        DukeList dukeList = new DukeList();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] inputArray = input.split(" ");
            String reply = "";
            switch (inputArray[0]) {
                case "list":
                    reply = dukeList.displayTask();
                    break;
                case "done":
                    reply = dukeList.markTask(Integer.parseInt(inputArray[1]));
                    break;
                default:
                    reply = dukeList.addTask(input);
                    break;
            }

            System.out.println(reply);
            input = sc.nextLine();
        }

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
