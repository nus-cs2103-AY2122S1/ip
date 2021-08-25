import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {

    public static int index = 0;

    public static void testSplit(String input) {
        String[] split = input.split("//");
        String output = "";
        for (int i = 0; i < split.length; i++) {
            output += (i + ": " + split[i] + "\n");
        }
        System.out.println(output);
    }

    public static List<Task> loadTasks(File f) {
        try {
            Scanner s = new Scanner(f);
            List<Task> result = new ArrayList<>();
            while (s.hasNext()) {
                String toParse = s.nextLine();
                String[] split = toParse.split("//"); //gonna redo the format to something like T//1//read book
                String taskType = split[0];
                String isDone = split[1];
                String description = split[2];
                if (taskType.equals("T")) {
                    Task task = new ToDo(description);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    result.add(task);
                } else if (taskType.equals("E")) {
                    Task task = new Event(description, split[3]);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    result.add(task);
                } else if (taskType.equals("D")) {
                    Task task = new Deadlines(description, split[3]);
                    if (isDone.equals("1")) {
                        task.markAsDone();
                    }
                    result.add(task);
                }
            }
            Duke.index = result.size();
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("File not found when trying to load tasks");
        }
        return null;
    }

    public static void main(String[] args) {

        File store = new File("data/duke.txt");
        try {
            Scanner s = new Scanner(store);
        } catch (FileNotFoundException e) {
            System.out.println("File <Duke.txt> not found! I'll create the file for you :>");
            File newStore = new File("data/duke.txt");
            try {
                newStore.createNewFile();
            } catch (IOException | SecurityException ex) {
                System.out.println("unable to create the file :<");
            } finally {
                System.exit(1);
            }
        }
        List<Task> storedInputs = Duke.loadTasks(store);
        Scanner scanObj = new Scanner(System.in);

        String greeting = "Hello! I'm BunnyBot, Joe Wel's personal slave!\n" + "What can I do for you?";
        System.out.println(greeting);

        while(true) {
            String userInput = scanObj.nextLine();
            Command c = new Command(storedInputs, userInput, index);
            String[] splitInput = userInput.split(" ");
            try {
                if (userInput.equals("bye")) {
                    c.bye_execute();
                    try {
                        FileWriter fw = new FileWriter("data/duke.txt");
                        fw.write(c.generateTasks());
                        fw.close();
                    } catch (IOException e) {
                        System.out.println("unable to write to file :<");
                    } finally {
                        break;
                    }
                } else if (userInput.equals("list")) {
                    c.list_execute();
                } else if (splitInput[0].equals("done")) {
                    c.done_execute();
                } else if (splitInput[0].equals("todo")) {
                    c.todo_execute();
                } else if (splitInput[0].equals("event")) {
                    c.event_execute();
                } else if (splitInput[0].equals("deadline")) {
                    c.deadline_execute();
                } else if (splitInput[0].equals("delete")) {
                    c.delete_execute();
                } else {
                    System.out.println("sorry! i'm not taught that command yet :<");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("sorry! i'm not taught that command yet :<<");
            }
        }
    }
}
