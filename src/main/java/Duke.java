import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner; // import the Scanner class
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static void main (String[] args) throws DukeException, IOException {
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        File data;
        try {
            data = new File("./data/duke.txt");
            Scanner dataReader = new Scanner(data);
            while (dataReader.hasNextLine()) {
                String task = dataReader.nextLine();
                System.out.println(task);
                String[] split = task.split(" , ");
                String type = split[0];
                boolean done;
                if (split[1].equals("1")) {
                    done = true;
                } else {
                    done = false;
                }
                String description = split[2];
                if (type.equals("D")) {
                    String time = split[3];
                    LocalDate date = LocalDate.parse(time);
                    Task deadline = new Deadline(description, date, done);
                    tasks.add(deadline);
                } else if (type.equals("E")) {
                    String time = split[3];
                    LocalDate date = LocalDate.parse(time);
                    Task event = new Event(description, date, done);
                    tasks.add(event);
                } else {
                    Task todo = new Todo(description, done);
                    tasks.add(todo);
                }
            }
            dataReader.close();
        } catch (FileNotFoundException e) {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            data = new File("./data/duke.txt");
            if (!data.exists()) {
                data.createNewFile();
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        boolean end = false;
        Task task;
        while (!end) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                end = true;
            } else if (userInput.equals("list")) {
                int count = 1;
                for (Task t : tasks) {
                    System.out.println(count + "." + t);
                    count += 1;
                }
            } else if (userInput.startsWith("done")) {
                String[] split = userInput.split(" ");
                int index = Integer.parseInt(split[1]);
                Task doneTask = tasks.get(index - 1);
                doneTask.markAsDone();
                Scanner dataReader = new Scanner(data);
                String taskData = null;
                StringBuffer buffer = new StringBuffer();
                while (index > 0) {
                    taskData = dataReader.nextLine();
                    index -= 1;
                }
                dataReader.close();
                String newData = taskData.replaceFirst("0", "1");
                dataReader = new Scanner(data);
                while (dataReader.hasNextLine()) {
                    buffer.append(dataReader.nextLine()+System.lineSeparator());
                }
                String fileContents = buffer.toString();
                fileContents = fileContents.replaceAll(taskData, newData);
                FileWriter writer = new FileWriter("./data/duke.txt");
                writer.append(fileContents);
                writer.flush();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(doneTask);
            } else if (userInput.startsWith("delete")) {
                String[] split = userInput.split(" ");
                int index = Integer.parseInt(split[1]);
                Task deleteTask = tasks.get(index - 1);
                tasks.remove(index - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(deleteTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            else if (userInput.startsWith("todo")) {
                if (userInput.equals("todo")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                String[] split = userInput.split("todo ");
                task = new Todo(split[1], false);
                tasks.add(task);
                String dataDescription = "T , 0 , " + split[1];
                addToData(dataDescription, data);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("deadline")) {
                if (userInput.equals("deadline")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] split = userInput.split("deadline ");
                String description = split[1].split(" /")[0];
                String time = split[1].split("/by ")[1];
                LocalDate date = LocalDate.parse(time);
                task = new Deadline(description, date, false);
                tasks.add(task);
                String dataDescription = "D , 0 , " + description + " , " + time;
                addToData(dataDescription, data);
                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("event")) {
                if (userInput.equals("event")) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String[] split = userInput.split("event ");
                String description = split[1].split(" /")[0];
                String time = split[1].split("/at ")[1];
                LocalDate date = LocalDate.parse(time);
                task = new Event(description, date, false);
                tasks.add(task);
                String dataDescription = "E , 0 , " + description + " , " + time;
                addToData(dataDescription, data);
                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void addToData(String data, File file) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(file, true));
        output.append(data + "\n");
        output.close();
    }
}
