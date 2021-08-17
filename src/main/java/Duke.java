import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        boolean exit = false;
        ArrayList<Task> tasks = new ArrayList<>();

        while (!exit) {
            String userInput = myObj.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                exit = true;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i).getTask());
                }
                System.out.println("____________________________________________________________");
            } else if (isDoneCall(userInput)) {
                int index = Integer.parseInt(userInput.substring(5));
                System.out.println("____________________________________________________________");
                if (tasks.get(index - 1) != null) {
                    tasks.get(index - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("  " + tasks.get(index - 1).getTask());
                } else {
                    System.out.println("There is no such task.");
                }
                System.out.println("____________________________________________________________");
            } else if (isRemoveCall(userInput)) {
                int index = Integer.parseInt(userInput.substring(7));
                System.out.println("____________________________________________________________");
                if (tasks.get(index - 1) != null) {
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("  " + tasks.get(index - 1).getTask());
                    tasks.remove(index - 1);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    System.out.println("There is no such task.");
                }
            } else {
                System.out.println("____________________________________________________________");
                try {
                    if (userInput.startsWith("todo")) {
                        parseInput(userInput, tasks, Type.TODO);
                    } else if (userInput.startsWith("deadline")) {
                        parseInput(userInput, tasks, Type.DEADLINE);
                    } else if (userInput.startsWith("event")) {
                        parseInput(userInput, tasks, Type.EVENT);
                    } else {
                        throw new IllegalArgumentException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("____________________________________________________________");
            }
        }
    }

    public static boolean isDoneCall (String strNum) {
        if (strNum == null) {
            return false;
        }
        if (strNum.length() < 6) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum.substring(5));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!strNum.startsWith("done ")) {
            return false;
        }
        return true;
    }

    public static boolean isRemoveCall (String str) {
        if (str == null) {
            return false;
        }
        if (str.length() < 8) {
            return false;
        }
        try {
            int d = Integer.parseInt(str.substring(7));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!str.startsWith("remove ")) {
            return false;
        }
        return true;
    }

    public static void parseInput(String userInput, ArrayList<Task> tasks, Type type) throws IllegalArgumentException {
        if (type == Type.TODO) {
            if (userInput.substring(4).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.add(new Todo(userInput.substring(5)));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1).getTask());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else if (type == Type.DEADLINE) {
            if (userInput.substring(8).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            int slash = userInput.indexOf("/by");
            if (slash == -1) {
                throw new IllegalArgumentException(" Please set a deadline by adding /by");
            }
            tasks.add(new Deadline(userInput.substring(9, slash), userInput.substring(slash + 4)));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1).getTask());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            if (userInput.substring(5).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of an event cannot be empty.");
            }
            int slash = userInput.indexOf("/at");
            if (slash == -1) {
                throw new IllegalArgumentException(" Please set a deadline by adding /at");
            }
            tasks.add(new Event(userInput.substring(6, slash), userInput.substring(slash + 4)));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1).getTask());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}
