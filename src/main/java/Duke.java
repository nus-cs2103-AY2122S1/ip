/**
 * Contains skeleton of Duke chatbot for IP of CS2103 2021
 *
 * @author: Ren Weilin
 * @version: 19 August 2021
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import  java.util.Scanner;

public class Duke {

    private final ArrayList<Task> commandList;
    private final String DIR_NAME = "data";
    private final String FILE_NAME = "duke.txt";
    private File dataFile = null;

    private Duke() {
        commandList = new ArrayList<>(100);
        dataFile = retrieveFile(DIR_NAME, FILE_NAME);
       try {
           loadData();
       } catch (IOException e) {
           System.out.println("Error loading duke.txt");
       }
    }

    public static void main(String[] args) {
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Duke instance = new Duke();
        instance.begin();
    }

    public void begin() throws DukeException{
        //TODO
        //Complete better exception handling

        //Greet
        welcomeGreet();

        //Echo
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                //Exit
                printBreakLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printBreakLine();
                try {
                    writeData();
                } catch (IOException e) {
                    System.out.println("Error saving file");
                }
                break;

            } else  if (command.equals("list")) {
                printBreakLine();
                System.out.println("  Here are the tasks in your list:");
                for (int i = 1; i < commandList.size() + 1; i++) {
                    System.out.printf("  %d. %s%n", i, commandList.get(i - 1)).toString();
                }
                printBreakLine();

            } else if(command.contains("done")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                printBreakLine();
                commandList.get(index - 1).markAsDone();
                printBreakLine();

            } else if(command.contains("delete")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                printBreakLine();
                System.out.println("  Noted. I've removed this task:");
                System.out.println("  " + commandList.get(index - 1).toString());
                commandList.remove(index - 1);
                System.out.printf("  Now you have %d tasks in the list.%n", commandList.size());
                printBreakLine();

            } else if(command.contains("todo")){
                String description = command.split("/by ")[0].substring(5);
                if (description.isBlank()) {
                    printBreakLine();
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                Task newTask = new ToDo(description, false);
                commandList.add(newTask);
                addTaskMsg(newTask);

            } else if(command.contains("deadline")) {

                String description = command.split("/by ")[0].substring(9);
                String by = command.split("/by ")[1];

                if (description.isBlank()) {
                    printBreakLine();
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }

                Task newTask = new Deadline(description, by, false);
                commandList.add(newTask);
                addTaskMsg(newTask);

            } else if(command.contains("event")) {
                String description = command.split("/at ")[0].substring(6);
                String by = command.split("/at ")[1];

                if (description.isBlank()) {
                    printBreakLine();
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                }

                Task newTask = new Event(description, by, false);
                commandList.add(newTask);
                addTaskMsg(newTask);

            } else {
                printBreakLine();
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private File retrieveFile(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        File dataFile = new File(directory + "/" + filename);
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataFile;
    }

    private void loadData() throws IOException, DukeException{
        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();
            boolean isDone = taskString.charAt(4) == ' ' ? false : true;
            String description = null;
            String dateTime = null;
            int timeStart;
            switch (taskString.charAt(1)) {
            case 'T':
                description = taskString.substring(7) + " ";
                commandList.add(new ToDo(description, isDone));
                break;
            case 'E':
                timeStart = taskString.indexOf("at: ");
                description = taskString.substring(7, timeStart - 2) + " ";
                dateTime = taskString.substring(timeStart + 4, taskString.length() - 1);
                commandList.add(new Event(description, dateTime, isDone));
                break;
            case 'D':
                timeStart = taskString.indexOf("by: ");
                description = taskString.substring(7, timeStart - 2) + " ";
                dateTime = taskString.substring(timeStart + 4, taskString.length() - 1);
                commandList.add(new Deadline(description, dateTime, isDone));
            default:
                break;
            }
        }
    }

    private void writeData() throws IOException {
        FileWriter writer = new FileWriter(dataFile.getPath());
        for (Task task: commandList) {
            writer.write(task.toString() + System.lineSeparator());
        }
        writer.close();
    }

    private void addTaskMsg(Task newTask) {
        printBreakLine();
        System.out.println("  Got it. I've added this task: ");
        System.out.println("  " + newTask.toString());
        System.out.printf("  Now you have %d tasks in the list.%n", commandList.size());
        printBreakLine();
    }

    private void welcomeGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printBreakLine();
        System.out.println("  Hello! I'm Duke");
        System.out.println("  What can I do for you?");
        printBreakLine();
    }

    private void printBreakLine() {
        for (int i = 0; i < 12; i++) {
            System.out.print("===");
        }
        System.out.println();
    }
}
