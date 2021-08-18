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
        System.out.println("----------------------------------");

        String instruction = "";
        Scanner sc = new Scanner(System.in);
        do {
            if (sc.hasNextLine()) {
                instruction = sc.nextLine();
                switch (instruction) {
                    case ("bye"):
                        System.out.println("\t" + "Bye! Hope to see you soon :)");
                        break;
                    case ("list"):
                        int index = 0;
                        while (index < Task.getCounter()) {
                            System.out.println("\t" + (index + 1) + "." + Task.getTaskList()[index].toString());
                            index++;
                        }
                        break;
                    default:
                        if (instruction.startsWith("done")) {
                            int taskNum = Integer.parseInt(instruction.substring(5)) - 1;
                            Task.getTaskList()[taskNum].markAsDone();
                            System.out.println("\tGood job! I have marked this task as done:");
                            System.out.println("\t\t" + Task.getTaskList()[taskNum].toString());
                        } else if (instruction.startsWith("todo")) {
                            String taskDescription = instruction.substring(5);
                            Todo newTodo = new Todo(taskDescription);
                            newTodo.addTask();
                            System.out.println("\tGot it! I've added this task:");
                            System.out.println("\t\t" + newTodo.toString());
                            System.out.println("\tNow you have " + Task.getCounter() +
                                    " task(s) in the list.");
                        } else if (instruction.startsWith("deadline")) {
                            String taskDescription = "";
                            //String description = instruction.substring(9);
                            int currIndex = 9;
                            while (!instruction.substring(currIndex).startsWith(" /")) {
                                taskDescription += instruction.substring(currIndex, currIndex + 1);
                                currIndex++;
                            }
                            String by = instruction.substring(currIndex + 5);
                            Task newDeadline = new Deadline(taskDescription, by);
                            newDeadline.addTask();
                            System.out.println("\tGot it! I've added this task:");
                            System.out.println("\t\t" + newDeadline.toString());
                            System.out.println("\tNow you have " + Task.getCounter() +
                                    " task(s) in the list.");
                        } else if (instruction.startsWith("event")) {
                            String taskDescription = "";
                            //String description = instruction.substring(6);
                            int currIndex = 6;
                            while (!instruction.substring(currIndex).startsWith(" /")) {
                                taskDescription += instruction.substring(currIndex, currIndex + 1);
                                currIndex++;
                            }
                            String by = instruction.substring(currIndex + 5);
                            Task newEvent = new Event(taskDescription, by);
                            newEvent.addTask();
                            System.out.println("\tGot it! I've added this task:");
                            System.out.println("\t\t" + newEvent.toString());
                            System.out.println("\tNow you have " + Task.getCounter() +
                                    " task(s) in the list.");
                        } else {
                            Task newTask = new Task(instruction);
                            newTask.addTask();
                            System.out.println("\tadded: " + instruction);
                        }
                        break;
                }
                System.out.println("----------------------------------");
            }
        } while (!instruction.equals("bye"));
    }
}

