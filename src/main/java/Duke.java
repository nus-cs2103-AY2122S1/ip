import java.util.Scanner;

public class Duke {
    private static boolean run;
    private static Task[] tasks;
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
        tasks = new Task[100];

        while(run) {
            run = eval();
        }
    }

    public static boolean eval() {
        Scanner inputReader = new Scanner(System.in);
        String input = inputReader.nextLine();
        String[] inputArray = input.split(" ");
        String[] params;
        switch (inputArray[0]) {
            case "bye":
                System.out.println("____________________________________________________________\n"
                        + "Bye. Don't come again!\n"
                        + "____________________________________________________________");
                return false;
            case "list":
                System.out.println("____________________________________________________________\n");
                for(int i = 0; i < index; i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks[i]);
                }
                System.out.println("____________________________________________________________");
                return true;
            case "done":
                int taskIndex = Integer.parseInt(inputArray[1]) - 1;
                tasks[taskIndex].setDone();

                System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done: \n"
                + tasks[taskIndex].toString() + "\n"
                + "____________________________________________________________\n");
                return true;
            case "event":
                params = input.split("/at");
                params[0] = params[0].substring(6, params[0].length() - 1);
                params[1] = params[1].substring(1);
                tasks[index] = new Event(params[0], params[1]);

                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task: \n"
                        + tasks[index].toString() + "\n"
                        + "____________________________________________________________\n");
                index++;
                return true;
            case "deadline":
                params = input.split("/by");
                params[0] = params[0].substring(9, params[0].length() - 1);
                params[1] = params[1].substring(1);
                tasks[index] = new Deadline(params[0], params[1]);

                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task: \n"
                        + tasks[index].toString() + "\n"
                        + "____________________________________________________________\n");
                index++;
                return true;
            default:
                tasks[index] = new ToDo(input);
                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task: \n"
                        + tasks[index].toString() + "\n"
                        + "____________________________________________________________\n");
//                Might need to check index < 100 in the future
                index++;
                return true;
        }

    }
}
