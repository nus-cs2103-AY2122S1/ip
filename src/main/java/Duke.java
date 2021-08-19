import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> userInput;
    private static boolean isBye;
    private static Scanner scanner;
    private static int counter;

    public Duke()
    {
        counter = 0;
        scanner = new Scanner(System.in);
        userInput = new ArrayList<>();
        isBye = false;
    }

    public static void getInput() {
            while (!isBye && scanner.hasNext()) {
                try {
                    String input = scanner.nextLine().trim();

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
                        while (point < userInput.size()) {
                            Task temp = userInput.get(point);
                            System.out.println("        " + (point + 1) + ". " + temp.toString());
                            point++;
                        }
                        System.out.println("    Now you have " + point + " tasks in the list.\n" +
                                "  ---------------------------------------------");
                    } else if (input.contains("done")) {
                        inputDone(input);
                    } else if (input.contains("delete")) {
                        deleteTask(input);
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

        public static void deleteTask(String input) throws DukeException {
            String[] splitInput = input.split(" +");
            if(splitInput.length == 2) {
                int taskNum = Integer.valueOf(splitInput[1]);
                if(taskNum <= 0 || taskNum > userInput.size()) {
                    throw new DukeException("     ☹ OOPS!!! There is no corresponding task to be " +
                            "deleted.");
                } else {

                    System.out.println("  ---------------------------------------------");
                    System.out.println("    Noted. I've removed this task:\n" + "      " +
                            userInput.get(taskNum - 1).toString());
                    userInput.remove(taskNum-1);
                    System.out.println("    Now you have " + userInput.size() +
                            (userInput.size() == 1 ? " task" : " tasks")
                                + " in the list.");
                    System.out.println("  ---------------------------------------------");

                }
            } else {
                throw new DukeException("     ☹ OOPS!!! The task to be deleted" +
                        "is not indicated!!");
            }
        }

    public static void inputDone(String input) throws DukeException {
        String[] splitInput = input.split(" +");
        if(splitInput.length == 2) {
            int taskNum = Integer.valueOf(splitInput[1]);
            if(taskNum > userInput.size() || taskNum <= 0) {
                throw new DukeException("     ☹ OOPS!!! There is no corresponding task to be " +
                        "marked done.");
            } else {
                Task temp = userInput.get(Integer.valueOf(splitInput[1]) - 1);
                temp.markAsDone();
                System.out.println("  ---------------------------------------------\n" +
                        "    Nice! I've marked this task as done:\n      " + temp.toString());
                System.out.println("  ---------------------------------------------");
            }
        } else if(splitInput.length == 1){
            throw new DukeException("     ☹ OOPS!!! The task to be marked done is not indicated!!");
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
                    userInput.add(todo);
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
                            userInput.add(event);
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
                            userInput.add(deadline);
                        }
                    }
                }

                System.out.println("  ---------------------------------------------");
                System.out.println("    Got it. I've added this task:");
                int index = userInput.size();
                System.out.println("      " + userInput.get(index-1).toString());
                System.out.println("    Now you have " + index + (index == 1 ?
                        " task in the list" : " tasks in the list."));
                System.out.println("  ---------------------------------------------");
            } else {
                throw new DukeException("     ☹ OOPS!!! The description of " +
                        (taskType.equals("event") ? "an " : "a ")
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