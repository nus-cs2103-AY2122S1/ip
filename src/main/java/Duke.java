import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
            String logo = "\n" +
                    "       __       ___      .______     ____    ____  __       _______.\n" +
                    "      |  |     /   \\     |   _  \\    \\   \\  /   / |  |     /       |\n" +
                    "      |  |    /  ^  \\    |  |_)  |    \\   \\/   /  |  |    |   (----`\n" +
                    ".--.  |  |   /  /_\\  \\   |      /      \\      /   |  |     \\   \\    \n" +
                    "|  `--'  |  /  _____  \\  |  |\\  \\----.  \\    /    |  | .----)   |   \n" +
                    " \\______/  /__/     \\__\\ | _| `._____|   \\__/     |__| |_______/    \n" +
                    "                                                                    \n";
            System.out.println("Hello from\n" + logo);

            System.out.println("----------------------------------");
            System.out.println("Hi! I am Jarvis, your personal assistant :)\n");
            System.out.println("What can I do for you?");
            System.out.println("----------------------------------");

            //String instruction = "";
            Scanner sc = new Scanner(System.in);
            String instruction = sc.nextLine();

            while (!instruction.equals("bye")) {
                try {
                    if (instruction.equals("list")) {
                        int index = 0;
                        if (Task.getCounter() == 0) {
                            System.out.println("\tThere are currently no tasks on your list :)");
                        }
                        while (index < Task.getCounter()) {
                            System.out.println("\t" + (index + 1) + "." + Task.getTaskList().get(index).toString());
                            index++;
                        }
                    } else if (instruction.startsWith("done")) {
                        int taskNum = Integer.parseInt(instruction.substring(5)) - 1;
                        if (taskNum >= Task.getCounter()) {
                            throw new DukeException("\tHmm, I don't have task " + (taskNum + 1) +
                                    " in my list. Please key in 'list' if you'd like to " +
                                    "view your list of tasks again!");
                        } else {
                            Task.getTaskList().get(taskNum).markAsDone();
                            System.out.println("\tGood job! I've marked this task as done:");
                            System.out.println("\t\t" + Task.getTaskList().get(taskNum).toString());
                        }
                    } else if (instruction.startsWith("delete")) {
                        int taskNum = Integer.parseInt(instruction.substring(7)) - 1;
                        if (taskNum >= Task.getCounter()) {
                            throw new DukeException("\tHmm, I don't have task " + (taskNum + 1) +
                                    " in my list. Please key in 'list' if you'd like to " +
                                    "view your list of tasks again!");
                        } else {
                            System.out.println("\tNoted. I've removed this task:");
                            System.out.println("\t\t" + Task.getTaskList().get(taskNum).toString());
                            Task.getTaskList().get(taskNum).deleteTask();
                            System.out.println("\tNow you have " + Task.getCounter() +
                                    " task(s) in the list.");
                        }
                    } else if (instruction.startsWith("todo")) {
                        if (instruction.length() < 5) {
                            throw new DukeException("\tOOPS!!! The description of a todo " +
                                    "cannot be empty.");
                        } else {
                            String taskDescription = instruction.substring(4);
                            Todo newTodo = new Todo(taskDescription);
                            newTodo.addTask();
                            System.out.println("\tGot it! I've added this task:");
                            System.out.println("\t\t" + newTodo.toString());
                            System.out.println("\tNow you have " + Task.getCounter() +
                                    " task(s) in the list.");
                        }
                    } else if (instruction.startsWith("deadline")) {
                        if (instruction.length() < 10) {
                            throw new DukeException("\tOOPS!!! The description of a deadline " +
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
                                    currIndex + 5 >= instruction.length()) {
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
                            throw new DukeException("\tOOPS!!! The description of a event " +
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
                                throw new DukeException("\tI think you forgot to key in your event timing!");
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
                        throw new DukeException("\tOOPS!!! I'm sorry, but I don't " +
                                "know what that means :-(");
                    }
                } catch (DukeException e) {
                    System.err.println(e);
                }
                System.out.println("----------------------------------");
                instruction = sc.nextLine();
            }
            System.out.println("\t" + "Bye! Hope to see you soon :)");
            System.out.println("----------------------------------");
    }
}

