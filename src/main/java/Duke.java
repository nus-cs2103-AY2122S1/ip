import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void addTask(ArrayList<Task> taskList, Task task) {
        taskList.add(task);
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    public static Task getTask(ArrayList<Task> taskList, Integer index) {
        return taskList.get(index);
    }

    public static void bye(){
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public static void list(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("    " + (i + 1) + ". " + task.toString());
        }
    }

    public static void done(Task task) {
        task.doneTask();
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    " + task.toString());
    }

    public static void delete(ArrayList<Task> taskList, Task task) {
        taskList.remove(task);
        System.out.println("    Got it. I've removed this task:");
        System.out.println("     " + task.toString());
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void main(String[] args) throws DukeException{
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
        while (!exit) {
            String input = scanner.nextLine();
            String[] pieces = input.split(" ", 2);
            String command = pieces[0];

            System.out.println("    ____________________________________________________________");

            if (command.equals("list")) {
                list(taskList);
            } else if (command.equals("bye")) {
                bye();
                exit = true;
            } else if (command.equals("done")) {
                int index = Integer.parseInt(pieces[1]);
                Task task = getTask(taskList, index-1);
                done(task);
            } else if (command.equals("todo")) {
                try {
                    Task newTask = new Todo(pieces[1]);
                    addTask(taskList, newTask);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (command.equals("event")) {
                try {
                    String[] eventPieces = pieces[1].split("/", 2);
                    String name = eventPieces[0];
                    String[] timePieces = eventPieces[1].split("at ", 2);
                    String time = timePieces[1];
                    Task newTask = new Event(name, time);
                    addTask(taskList, newTask);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    throw new DukeException("☹ Please enter the event command in 'event [task description]/at [start time]' format");
                }
            } else if (command.equals("deadline")) {
                try {
                    String[] eventPieces = pieces[1].split("/", 2);
                    String name = eventPieces[0];
                    String[] timePieces = eventPieces[1].split("by ", 2);
                    String time = timePieces[1];
                    Task newTask = new Deadline(name, time);
                    addTask(taskList, newTask);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    throw new DukeException("☹ Please enter the event command in 'deadline [task description]/by [end time]' format");
                }
            } else if(command.equals("delete")){
                int index = Integer.parseInt(pieces[1]);
                Task task = taskList.get(index-1);
                delete(taskList, task);

            }else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            System.out.println("    ____________________________________________________________");
        }
    }

}