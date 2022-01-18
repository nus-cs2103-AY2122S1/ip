import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\r\nWhat can I do for you?");
        ArrayList<Task> taskList = new ArrayList<>(); // init arraylist outside infinite loop.
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine(); // Can also convert result to lower-case to handle cases.
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(command.equals("list")) {
                for(int i = 0; i < taskList.size(); i++) {
                    System.out.println((i+1) + "." + taskList.get(i).getStatusIcon() + " " + taskList.get(i).description);
                }
            } else if(command.startsWith("mark")) {
                try {
                    int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                    Task currentTask = taskList.get(value-1);
                    currentTask.setDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(currentTask.getStatusIcon() + " " + currentTask.getDescription());
                } catch(Exception e) {
                    System.out.println("Task is invalid, please select a valid task number.");
                }
            }  else if(command.startsWith("unmark")) {
                try {
                    int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                    Task currentTask = taskList.get(value - 1);
                    currentTask.setUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currentTask.getStatusIcon() + " " + currentTask.getDescription());
                } catch(Exception e) {
                    System.out.println("Task is invalid, please select a valid task number.");
                }
            } else if(command.startsWith("todo")) {
                Todo todoTask = new Todo(command.substring(5));
                taskList.add(todoTask);
                System.out.println("Got it. I've added this task: ");
                System.out.println(todoTask);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else if(command.startsWith("deadline")) {
                String[] taskText = command.split(" /by");
                Deadline deadlineTask = new Deadline(taskText[0],taskText[1]);
                taskList.add(deadlineTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(deadlineTask);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else if(command.startsWith("event")) {
                String[] taskText = command.split(" /at");
                Event eventTask = new Event(taskText[0],taskText[1]);
                taskList.add(eventTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(eventTask);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else {
                Task newTask = new Task(command);
                taskList.add(newTask);
                System.out.println("Added: " + command);
            }
        }
    }
}
