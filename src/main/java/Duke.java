import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        //Greet
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke.");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        ArrayList<Task> taskList = new ArrayList<>();

        //Echo
        while(!exit){
            String input = scanner.nextLine();
            String[] pieces = input.split(" ",2);
            String command = pieces[0];

            System.out.println("    ____________________________________________________________");

            if(command.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    System.out.println("    " + (i+1) + ". " + task.toString());
                }
            }else if(command.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                exit = true;
            }else if(command.equals("done")) {
                int index = Integer.parseInt(pieces[1]);
                Task task = taskList.get(index-1);
                task.doneTask();
                System.out.println("    Nice! I've marked this task as done: ");
                System.out.println(task.toString());
            } else if(command.equals("todo")) {
                Task newTask = new Todo(pieces[1]);
                taskList.add(newTask);
                System.out.println("    Got it. I've added this task:");
                System.out.println("     " + newTask.toString());
                System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
            } else if(command.equals("event")) {
                String[] eventPieces = pieces[1].split("/", 2);
                String name = eventPieces[0];
                String[] timePieces = eventPieces[1].split("at ", 2);
                String time = timePieces[1];
                Task newTask = new Event(name, time);
                taskList.add(newTask);
                System.out.println("    Got it. I've added this task:");
                System.out.println("     " + newTask.toString());
                System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
            } else if(command.equals("deadline")) {
                String[] eventPieces = pieces[1].split("/", 2);
                String name = eventPieces[0];
                String[] timePieces = eventPieces[1].split("by ", 2);
                String time = timePieces[1];
                Task newTask = new Deadline(name, time);
                taskList.add(newTask);
                System.out.println("    Got it. I've added this task:");
                System.out.println("     " + newTask.toString());
                System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
            } else{}


            System.out.println("    ____________________________________________________________");
        }
    }
}
