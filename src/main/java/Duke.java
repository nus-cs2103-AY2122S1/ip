/**
 * The Bhutu chatbot app
 */
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;


public class Duke {
    /**
     * Add task to taskList
     *t
     * @param taskList a list of tasks
     * @param task the task want to add to the list
     */
    public static void addTask(ArrayList<Task> taskList, Task task) {
        taskList.add(task);
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * To get the task object in a taskList using its index
     * @param taskList a list of tasks
     * @param index the index of task in the tasklist
     * @return return the task with corresponding index
     */
    public static Task getTask(ArrayList<Task> taskList, Integer index) {
        return taskList.get(index);
    }

    /**
     * Handle bye category input
     */
    public static void bye(){
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Handle list category input
     * @param taskList a list of tasks
     */
    public static void list(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("    " + (i + 1) + ". " + task.toString());
        }
    }

    /**
     * Handle done category input
     * @param task the task that was completed
     */
    public static void done(Task task) {
        task.doneTask();
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    " + task.toString());
    }

    /**
     * Delete a task from the list
     * @param taskList a list of tasks
     * @param task the task that want to be deleted from the list
     */
    public static void delete(ArrayList<Task> taskList, Task task) {
        taskList.remove(task);
        System.out.println("    Got it. I've removed this task:");
        System.out.println("     " + task.toString());
        System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void saveToFile(File file, ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file, false);
            for(int i=0; i<taskList.size(); i++) {
                Task task= taskList.get(i);
                fw.write(task.toString());
                fw.write("\r\n");
            }
            fw.close();
        }
        catch(IOException e) {
            throw new DukeException("☹ OOPS!!! Error occurs when save the data");
        }
    }

    public static ArrayList<Task> readFile(File file) throws DukeException{
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner s = new Scanner(file);
            boolean completed;
            String current, desc, name,  time;
            char type;
            while(s.hasNext()) {
                current = s.nextLine();
                type = current.charAt(1);
                if(type == 'T') {
                    Task newTask = new Todo(current.substring(7));
                    newTask.setCompleted(current.charAt(4)=='X');
                    taskList.add(newTask);
                }
                else if(type == 'E') {
                    desc = current.substring(7);
                    String[] split = desc.split("->at: ", 2);
                    name = split[0];
                    time = split[1];
                    Task newTask = new Event(name, time);
                    newTask.setCompleted(current.charAt(4)=='X');
                    taskList.add(newTask);
                }
                else {
                    desc = current.substring(7);
                    String[] split = desc.split("->by: ", 2);
                    name = split[0];
                    time = split[1];
                    Task newTask = new Deadline(name, time);
                    newTask.setCompleted(current.charAt(4)=='X');
                    taskList.add(newTask);
                }
            }
            return taskList;
        }
        catch(IOException e) {
            throw new DukeException("OOPS!!! Error occurs when reload the data");
        }
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

        File file;
        try {
            Files.createDirectories(Paths.get("data/"));
            file = new File("data/duke.txt");

            //Check if the file exists
            if (!file.exists()) {
                boolean r = file.createNewFile();
            }
        }
        catch(IOException e){
            throw new DukeException("☹ OOPS!!! Error occurs when try to create a new data file");
        }

        taskList = readFile(file);

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
                try {
                    int index = Integer.parseInt(pieces[1]);
                    Task task = getTask(taskList, index - 1);
                    done(task);
                }
                catch(IndexOutOfBoundsException e){
                    throw new DukeException("☹ OOPS!!! Invalid task number");
                }

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
                try {
                    int index = Integer.parseInt(pieces[1]);
                    Task task = taskList.get(index - 1);
                    delete(taskList, task);
                }
                catch(IndexOutOfBoundsException e){
                    throw new DukeException("☹ OOPS!!! Invalid task number");
                }

            }else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            saveToFile(file, taskList);
            System.out.println("    ____________________________________________________________");
        }
    }

}

