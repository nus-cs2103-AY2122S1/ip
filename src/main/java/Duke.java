import java.util.Scanner;
public class Duke {

    private static Task[] userInput;
    private static boolean isBye;
    private static Scanner scanner;
    private static int counter;

    public Duke()
    {
        counter = 0;
        scanner = new Scanner(System.in);
        userInput = new Task[100];
        for(int i = 0; i < 100; i++)
        {
            userInput[i] = null;
        }
        isBye = false;
    }

    public static void getInput() {
            while (!isBye && scanner.hasNext()) {
                try {
                    String input = scanner.nextLine();

                    if (input.equals("bye") || input == "bye") {
                        System.out.println("  ---------------------------------------------");
                        System.out.println("    Bye. Hope to see you again soon!");
                        System.out.println("  ---------------------------------------------");
                        isBye = true;
                        scanner.close();
                    } else if (input.equals("list") || input == "list") {
                        System.out.println("  ---------------------------------------------\n" +
                                "    Here are the tasks in your list:");
                        int point = 0;
                        while (userInput[point] != null) {
                            Task temp = userInput[point];
                            System.out.println("        " + (point + 1) + ". " + temp.toString());
                            point++;
                        }
                        System.out.println("    Now you have " + point + " tasks in the list.\n" +
                                "  ---------------------------------------------");
                    } else if (input.contains("done")) {
                        inputDone(input, counter);
                    } else {
                        addTask(input);
                    }
                } catch(DukeException e) {
                    System.out.println("  ---------------------------------------------");
                    System.out.println(e.getMessage());
                    System.out.println("  ---------------------------------------------");
                }

            }
        }

    public static void inputDone(String input, int counter) throws DukeException {
        String[] splitInput = input.split(" ");
        if(splitInput.length == 2) {
            int taskNum = Integer.valueOf(splitInput[1]);
            if(taskNum > counter || taskNum <= 0) {
                throw new DukeException("     ☹ OOPS!!! There is no corresponding task to be " +
                        "marked done.");
            } else {
                Task temp = userInput[Integer.valueOf(splitInput[1]) - 1];
                temp.markAsDone();
                System.out.println("  ---------------------------------------------\n" +
                        "    Nice! I've marked this task as done:\n      " + temp.toString());
                System.out.println("  ---------------------------------------------");
            }
        }
    }

    public static void addTask(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        String taskType = splitInput[0];
        if(taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event")) {

            if (splitInput.length > 1) {
                if (taskType.equals("todo")) {
                    String description = input.split("todo ")[1];
                    Todo todo = new Todo(description);
                    userInput[counter] = todo;
                } else if (taskType.equals("event")) {
                    String[] splitEvent= input.split("/at");
                    if(splitEvent.length == 1) {
                        throw new DukeException("      ☹ OOPS!!! The period which the event occurs " +
                                "is not inputted correctly. Use /at to indicate the period ;)");
                    } else {
                        String[] splitEvent2 = splitEvent[0].split("event ");
                        if (splitEvent2.length == 0) {
                            throw new DukeException("     ☹ OOPS!!! The description of an " +
                                    "event cannot be empty.");
                        } else {
                            Event event = new Event(splitEvent2[1], splitEvent[1]);
                            userInput[counter] = event;
                        }
                    }
                } else {
                    String[] splitDl= input.split("/by");
                    if(splitDl.length == 1) {
                        throw new DukeException("      ☹ OOPS!!! The deadline is not " +
                                "inputted correctly. Use /by to indicate the deadline ;)");
                    } else {
                        String[] splitDl2 = splitDl[0].split("deadline ");
                        if(splitDl2.length == 0) {
                            throw new DukeException("     ☹ OOPS!!! The description of a " +
                                    "deadline cannot be empty.");
                        } else {
                            Deadline deadline = new Deadline(splitDl2[1], splitDl[1]);
                            userInput[counter] = deadline;
                        }
                    }
                }

                System.out.println("  ---------------------------------------------");
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + userInput[counter].toString());
                counter++;
                System.out.println("    Now you have " + counter + (counter == 1 ?
                        " task in the list" : " tasks in the list."));
                System.out.println("  ---------------------------------------------");
            } else {
                throw new DukeException("     ☹ OOPS!!! The description of" +
                        (taskType.equals("event") ? "an" : "a")
                            + taskType + " cannot be empty.");
            }
        } else {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't " +
                    "know what that means :-(");
        }

    }

    public static void main(String[] args) {

        System.out.println("  ---------------------------------------------");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("  ---------------------------------------------");

        Duke duke = new Duke();
        getInput();

    }
}