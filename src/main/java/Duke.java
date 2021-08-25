
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.util.stream.*;


public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void talk() {

        java.nio.file.Path dirpath = Paths.get("data");
        Path taskListPath = Paths.get("data", "DukeTask.txt");
        boolean directoryExists = Files.exists(dirpath);
        boolean fileExist = Files.exists(taskListPath);
        if(!directoryExists) {
            try{
                Files.createDirectory(dirpath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if(!fileExist) {
            try{
                Files.createFile(taskListPath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                File taskList = new File(taskListPath.toString());
                Scanner scanner = new Scanner(taskList);
                while (scanner.hasNext()) {

                    Task currTask = null;
                    String curr = scanner.nextLine();
                    if(curr.equals("")) {
                        continue;
                    }
                    Character tasktype = curr.charAt(1);
                    Character isDone = curr.charAt(4);
                    String desc = curr.substring(6);
                    boolean done = isDone == 'X';
                    if(tasktype == 'T') {
                        currTask = new Todo(desc, done);
                    } else if(tasktype == 'E') {
                        int index = desc.indexOf("at:");
                        int n = desc.length();
                        String date = desc.substring(index + 4, n-1);
                        currTask = new Event(desc.substring(0,index), done, LocalDate.parse(date));
                    } else if(tasktype == 'D') {
                        int index = desc.indexOf("by:");
                        int n = desc.length();
                        String date = desc.substring(index + 4, n-1);

                        currTask = new Deadline(desc.substring(0, index), done, LocalDate.parse(date));
                    }
                    tasks.add(currTask);
                }

            } catch(FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }
        String userInput = "";
        System.out.println("Hello, What Can I do for you ?\n -------------------------------");
        Scanner scanner = new Scanner(System.in);
        while(!userInput.equals("bye")) {
            System.out.println("Enter Input Here: ");

            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Duke : Bye, Hope to see you again soon !");
                break;
            } else if (userInput.equals("list")) {
                int numberOfTasks = tasks.size();
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
                System.out.println("\n ----------------------------------");

            } else if (userInput.startsWith("done")) {
                int id = Integer.valueOf(userInput.substring(5));
                Task currTask = null;
                try {
                    currTask = tasks.get(id - 1);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
                if (currTask != null) {
                    currTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n" + currTask);
                    System.out.println("\n ----------------------------------");
                }

            } else if (userInput.startsWith("delete")) {
                int id = Integer.valueOf(userInput.substring(7));

                try {
                    Task removedTask = tasks.get(id - 1);
                    tasks.remove(id);
                    System.out.println("Successfully removed task : " + removedTask);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Task newTask = null;

                try {
                    newTask = handleInput(userInput);
                } catch (DukeException duke) {
                    System.out.println(duke.getMessage());
                }
                if (newTask != null) {
                    tasks.add(newTask);
                    System.out.println("Duke: Added Task " + userInput);

                }
                System.out.println("\n ----------------------------------");
            }
            try{
                String tasksinstring = tasks.stream().map(x -> x.toString()).reduce("",
                        (x,y) -> x + "\n" + y);
                Files.write(taskListPath, tasksinstring.getBytes());
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static Task handleInput(String userInput) throws DukeException{
        if(userInput.startsWith("todo")) {
            int id = userInput.indexOf("todo") + 4;
            String task = userInput.substring(id);
            if(task.replaceAll("\\s+","").equals("")){
                //if remaining string is whitespace or empty
                throw new DukeException("Todo needs to have description !");
            }
            return new Todo(task, false);
        } else if(userInput.startsWith("deadline")) {
            int start_id = userInput.indexOf("deadline");
            int task_id = userInput.indexOf("/by");
            String task = userInput.substring(start_id + 9, task_id);
            String date = userInput.substring(task_id + 3);
            if(task_id == -1) {
                throw new DukeException("You need to specify at using /by !");
            }
            if(task.replaceAll("\\s+","").equals("")){
                //if remaining string is whitespace or empty
                throw new DukeException("Task needs to have description !");
            }
            if(date.replaceAll("\\s+","").equals("")) {
                //if remaining string is whitespace or empty
                throw new DukeException("deadline needs to have dates !");
            }
            return new Deadline(task, false, LocalDate.parse(date.trim()));
        } else if(userInput.startsWith("event")) {
            int start_id = userInput.indexOf("event");
            int task_id = userInput.indexOf("/at");
            String task = userInput.substring(start_id + 6, task_id);
            String date = userInput.substring(task_id + 3);

            if(task_id == -1) {
                throw new DukeException("You need to specify at using /at !");
            }
            return new Event(task, false, LocalDate.parse(date.trim()));
        } else {
            throw new DukeException("I don't understand what you are talking about !");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.talk();
    }
}
