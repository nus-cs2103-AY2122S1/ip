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
        ArrayList<Task> commandList = new ArrayList<>(); // init arraylist outside infinite loop.
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine(); // Can also convert result to lower-case to handle cases.
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(command.equals("list")) {
                for(int i = 0; i < commandList.size(); i++) {
                    System.out.println((i+1) + "." + commandList.get(i).getStatusIcon() + " " + commandList.get(i).description);
                }
            } else if(command.startsWith("mark")) {
                try {
                    int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                    Task currentTask = commandList.get(value-1);
                    currentTask.setDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(currentTask.getStatusIcon() + " " + currentTask.getDescription());
                } catch(Exception e) {
                    System.out.println("Task is invalid, please select a valid task number.");
                }
            }  else if(command.startsWith("unmark")) {
                try {
                    int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                    Task currentTask = commandList.get(value - 1);
                    currentTask.setUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currentTask.getStatusIcon() + " " + currentTask.getDescription());
                } catch(Exception e) {
                    System.out.println("Task is invalid, please select a valid task number.");
                }
            } else {
                Task newTask = new Task(command);
                commandList.add(newTask);
                System.out.println("Added: " + command);
            }
        }



    }
}
