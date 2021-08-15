import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static boolean run;
    private static ArrayList<Task> tasks;
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
        tasks = new ArrayList<Task>();

        Scanner inputReader = new Scanner(System.in);

        while(run) {
            String input = inputReader.nextLine();
            run = eval(input);
        }

        inputReader.close();
    }

    public static boolean eval(String input) {
        String[] inputArray = input.split(" ");
        String[] params;
        int selectedTask;

        switch (inputArray[0]) {
            case "bye":
                System.out.println("____________________________________________________________\n"
                        + "Bye. Don't come again!\n"
                        + "____________________________________________________________");
                return false;
            case "list":
                System.out.println("____________________________________________________________\n");
                for(int i = 0; i < index; i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
                }
                System.out.println("____________________________________________________________");
                return true;
            case "done":
                selectedTask = Integer.parseInt(inputArray[1]) - 1;
                tasks.get(selectedTask).setDone();

                System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done: \n"
                + tasks.get(selectedTask).toString() + "\n"
                + "____________________________________________________________\n");
                return true;
            case "delete":
                selectedTask = Integer.parseInt(inputArray[1]) - 1;
                System.out.println("Noted. I've removed this task: \n" +
                        tasks.get(selectedTask).toString() + "\n" +
                        "Now you have " + (index - 1) + " tasks in the list.");

                tasks.remove(selectedTask);
                index--;
                return true;
            case "event":
                params = input.split("/at");
                params[0] = params[0].substring(6, params[0].length() - 1);
                params[1] = params[1].substring(1);
                tasks.add(new Event(params[0], params[1]));

                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task: \n"
                        + tasks.get(index).toString() + "\n"
                        + "____________________________________________________________\n");
                index++;
                return true;
            case "deadline":
                params = input.split("/by");
                params[0] = params[0].substring(9, params[0].length() - 1);
                params[1] = params[1].substring(1);
                tasks.add(new Deadline(params[0], params[1]));

                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task: \n"
                        + tasks.get(index).toString() + "\n"
                        + "____________________________________________________________\n");
                index++;
                return true;
            case "todo":
                try {
                    String name = input.substring(5);
                    if (name.equals("")) {
                        throw new StringIndexOutOfBoundsException("empty todo description");
                    }
                    tasks.add(new ToDo(name));
                    System.out.println("____________________________________________________________\n"
                            + "Got it. I've added this task: \n"
                            + tasks.get(index).toString() + "\n"
                            + "____________________________________________________________\n");
                    index++;
                }
                catch (StringIndexOutOfBoundsException e) {
                    System.out.println("____________________________________________________________\n" +
                            "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            "____________________________________________________________");
                }

                return true;
            default:
                System.out.println("____________________________________________________________\n" +
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________\n");
//                Might need to check index < 100 in the future
                index++;
                return true;
        }

    }
}
