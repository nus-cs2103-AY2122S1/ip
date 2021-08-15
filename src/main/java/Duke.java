import java.util.Scanner;

public class Duke {
    private static boolean run;
    private static String[] tasks;
    private static int index;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Python>Java\n"
                + "What must I do for you?\n"
                + "____________________________________________________________";
        System.out.println(greeting);

        run = true;
        tasks = new String[100];

        while(run) {
            run = eval();
        }
    }

    public static boolean eval() {
        Scanner inputReader = new Scanner(System.in);
        String input = inputReader.nextLine();
        String[] inputArray = input.split(" ");
        switch (inputArray[0]) {
            case "bye":
                System.out.println("____________________________________________________________\n"
                        + "Bye. Don't come again!\n"
                        + "____________________________________________________________");
                return false;
            case "list":
                System.out.println("____________________________________________________________");
                for(int i = 0; i < index; i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks[i]);
                }
                System.out.println("____________________________________________________________");
                return true;
            default:
                System.out.println("____________________________________________________________\n"
                        + "added:" + input + "\n"
                        + "____________________________________________________________\n");
                tasks[index] = input;
//                Might need to check index < 100 in the future
                index += 1;
                return true;
        }

    }
}
