import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t____________________________________________________________");
        System.out.println(logo);
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        String arguments = "";

        while (!command.equals("bye")) {
            System.out.println("\t____________________________________________________________");
            try {
                if (command.equals("list")) {
                    System.out.println("\tHere are the tasks in your list:");
                    if (tasks.isEmpty()) {
                        System.out.println("\t  You currently have no tasks. Why not add a task?");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            Task currTask = tasks.get(i);
                            System.out.println("\t" + (i + 1) + ". " + currTask);
                        }
                    }
                } else if (command.equals("done")) {
                    arguments = sc.nextLine().stripLeading();
                    int index = Integer.parseInt(arguments);
                    if (index < 1 || index > tasks.size()) {
                        throw new DoneIndexException();
                    } else {
                        Task taskToBeMarked = tasks.get(index - 1);
                        taskToBeMarked.markTaskAsDone();
                        System.out.println("\tNice! I've marked this task as done:");
                        System.out.println("\t  " + taskToBeMarked);
                    }
                } else if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
                    arguments = sc.nextLine().stripLeading();
                    if (arguments.isEmpty()) {
                        throw new MissingArgumentsException(command);
                    }
                    System.out.println("\tGot it. I've added this task:");
                    switch (command) {
                        case "todo": {
                            Todo newTask = new Todo(arguments);
                            tasks.add(newTask);
                            System.out.println("\t  " + newTask);
                            break;
                        }
                        case "deadline": {
                            String[] argArr = arguments.split("/by");
                            Deadline newTask = new Deadline(argArr[0], argArr[1]);
                            tasks.add(newTask);
                            System.out.println("\t  " + newTask);
                            break;
                        }
                        case "event": {
                            String[] argArr = arguments.split("/at");
                            Event newTask = new Event(argArr[0], argArr[1]);
                            tasks.add(newTask);
                            System.out.println("\t  " + newTask);
                            break;
                        }
                    }
                    System.out.printf("\tNow you have %s " +
                            (tasks.size() == 1 ? "task" : "tasks")
                            + " in the list.%n", tasks.size());
                } else {
                    throw new InvalidCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("\t____________________________________________________________");
            command = sc.next();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
        sc.close();
    }
}
