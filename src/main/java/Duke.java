import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            System.out.println("----------------------------------");
            System.out.println("Hello! I am Jarvis :)\n");
            System.out.println("What can I do for you?");
            System.out.println("----------------------------------\n");

            String instruction = "";
            Scanner sc = new Scanner(System.in);
            do {
                try {
                    if (sc.hasNextLine()) {
                        instruction = sc.nextLine();
                        switch (instruction) {
                            case ("bye"):
                                System.out.println("\t" + "Bye! Hope to see you soon :)");
                                break;
                            case ("list"):
                                int index = 0;
                                while (index < Task.getCounter()) {
                                    System.out.println("\t" + (index + 1) + "." + Task.getTaskList().get(index).toString());
                                    index++;
                                }
                                break;
                            default:
                                if (instruction.startsWith("done")) {
                                    int taskNum = Integer.parseInt(instruction.substring(5)) - 1;
                                    if (taskNum >= Task.getCounter()) {
                                        throw new DukeException("Hmm, I do not have task " + (taskNum + 1) +
                                                " in my list. Please key in 'list' if you would like to " +
                                                "view your list of tasks again!");
                                    } else {
                                        Task.getTaskList().get(taskNum).markAsDone();
                                        System.out.println("\tGood job! I have marked this task as done:");
                                        System.out.println("\t\t" + Task.getTaskList().get(taskNum).toString());
                                    }
                                } else if (instruction.startsWith("delete")) {
                                        int taskNum = Integer.parseInt(instruction.substring(7)) - 1;
                                        if (taskNum >= Task.getCounter()) {
                                            throw new DukeException("Hmm, I do not have task " + (taskNum + 1) +
                                                    " in my list. Please key in 'list' if you would like to " +
                                                    "view your list of tasks again!");
                                        } else {
                                            System.out.println("\tNoted. I've removed this task:");
                                            System.out.println("\t\t" + Task.getTaskList().get(taskNum).toString());
                                            Task.getTaskList().get(taskNum).deleteTask();
                                            System.out.println("\tNow you have " + Task.getCounter() +
                                                    " task(s) in the list.");
                                        }
                                } else if (instruction.startsWith("todo")) {
                                    if (instruction.length() < 6) {
                                        throw new DukeException("OOPS!!! The description of a todo " +
                                                "cannot be empty.");
                                    } else {
                                        String taskDescription = instruction.substring(5);
                                        Todo newTodo = new Todo(taskDescription);
                                        newTodo.addTask();
                                        System.out.println("\tGot it! I've added this task:");
                                        System.out.println("\t\t" + newTodo.toString());
                                        System.out.println("\tNow you have " + Task.getCounter() +
                                                " task(s) in the list.");
                                    }
                                } else if (instruction.startsWith("deadline")) {
                                    if (instruction.length() < 10) {
                                        throw new DukeException("OOPS!!! The description of a deadline " +
                                                "cannot be empty.");
                                    } else {
                                        String taskDescription = "";
                                        int currIndex = 8;
                                        while (currIndex < instruction.length() &&
                                                !instruction.substring(currIndex).startsWith(" /")) {
                                            taskDescription += instruction.substring(currIndex, currIndex + 1);
                                            currIndex++;
                                        }
                                        if (currIndex == instruction.length() ||
                                                currIndex + 5 >= instruction.length())  {
                                                throw new DukeException("I think you forgot to key in your deadline!");
                                        } else {
                                            String by = instruction.substring(currIndex + 5);
                                            Task newDeadline = new Deadline(taskDescription, by);
                                            newDeadline.addTask();
                                            System.out.println("\tGot it! I've added this task:");
                                            System.out.println("\t\t" + newDeadline.toString());
                                            System.out.println("\tNow you have " + Task.getCounter() +
                                                    " task(s) in the list.");
                                        }
                                    }
                                } else if (instruction.startsWith("event")) {
                                    if (instruction.length() < 7) {
                                        throw new DukeException("OOPS!!! The description of a event " +
                                                "cannot be empty.");
                                    } else {
                                        String taskDescription = "";
                                        int currIndex = 5;
                                        while (currIndex < instruction.length() &&
                                                !instruction.substring(currIndex).startsWith(" /")) {
                                            taskDescription += instruction.substring(currIndex, currIndex + 1);
                                            currIndex++;
                                        }
                                        if (currIndex == instruction.length() ||
                                                currIndex + 5 >= instruction.length()) {
                                            throw new DukeException("I think you forgot to key in your event timing!");
                                        } else {
                                            String by = instruction.substring(currIndex + 5);
                                            Task newEvent = new Event(taskDescription, by);
                                            newEvent.addTask();
                                            System.out.println("\tGot it! I've added this task:");
                                            System.out.println("\t\t" + newEvent.toString());
                                            System.out.println("\tNow you have " + Task.getCounter() +
                                                    " task(s) in the list.");
                                        }
                                    }
                                } else {
                                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't " +
                                            "know what that means :-(");
                                }
                                break;
                        }
                        System.out.println("----------------------------------");
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                    System.out.println("----------------------------------");
                }
            } while (!instruction.equals("bye"));

    }
}

