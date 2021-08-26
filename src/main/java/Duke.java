import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {
        String end = "bye";
        String display = "list";
        String lineBreak = "------------------------------";
        String markDone = "done";
        String deleteTask = "delete";
        String taskTodo = "todo";
        String taskDdl = "deadline";
        String taskEve = "event";
        ArrayList<Task> inputs = new ArrayList<>();

        // check and create folder and file to save data
        File data = new File("./data");
        if (!data.exists()) {
            data.mkdir();
            try {
                File dataDirectory = new File("./data/data.txt");
                if (dataDirectory.createNewFile()) {
                    System.out.println("Data file has been created.");
                } else {
                    // System.out.println("Data folder is already present.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        try {
            File dataDirectory = new File("./data/data.txt");
            if (dataDirectory.createNewFile()) {
                System.out.println("Data file has been created.");
            } else {
                // System.out.println("Data folder is already present.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String dataFile = "./data/data.txt";

        // Welcome message
        System.out.println(lineBreak
                + "\n"
                + "Hello! I'm Azure.\n"
                + "How can I help you today?\n"
                + lineBreak + "\n");

        Scanner myObj = new Scanner(System.in);

        // String input
        while(true) {
            String input = myObj.nextLine();
            Task newTask = new Task(input);

            // mark a task as Done
            Pattern pattern = Pattern.compile("done\\s\\d+");
            Matcher matcher = pattern.matcher(input);
            boolean matchFound = matcher.find();
            if (matchFound) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                if (parts[0].equals(markDone) && index < inputs.size()) {
                    inputs.get(index).setDone();

                    try {
                        writeToFile(dataFile, inputs.get(0).toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something is wrong... " + e.getMessage());
                    }

                    for (int i = 1; i < inputs.size(); i++) {
                        try {
                            appendToFile(dataFile, inputs.get(i).toString() + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something is wrong... " + e.getMessage());
                        }
                    }

                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(inputs.get(index).toString());
                    System.out.println(lineBreak + "\n");
                }
                continue;
            }

            // delete a task
            Pattern patternDelete = Pattern.compile("delete\\s\\d+");
            Matcher matcherDelete = patternDelete.matcher(input);
            boolean matchFoundDelete = matcherDelete.find();
            if (matchFoundDelete) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                if (parts[0].equals(deleteTask) && index < inputs.size()) {
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(inputs.get(index).toString());
                    inputs.remove(index);

                    try {
                        writeToFile(dataFile, inputs.get(0).toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something is wrong... " + e.getMessage());
                    }

                    for (int i = 1; i < inputs.size(); i++) {
                        try {
                            appendToFile(dataFile, inputs.get(i).toString() + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something is wrong... " + e.getMessage());
                        }
                    }

                    System.out.println(lineBreak + "\n");
                }
                continue;
            }

            // normal input
            if (!input.equals(end) && !input.equals(display)) {
                String[] group = input.split(" ");

                // Case Todo
                if (group[0].equals(taskTodo) && group.length > 1) {
                    String todoToAdd = input.substring(5); // name of the task is after "todo" and space
                    Task newTodo = new Todo(todoToAdd);
                    inputs.add(newTodo);

                    try {
                        writeToFile(dataFile, inputs.get(0).toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something is wrong... " + e.getMessage());
                    }

                    for (int i = 1; i < inputs.size(); i++) {
                        try {
                            appendToFile(dataFile, inputs.get(i).toString() + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something is wrong... " + e.getMessage());
                        }
                    }

                    System.out.println("Roger! Added the task: ");
                    System.out.println("    " + newTodo.toString());
                    System.out.println("Now you have " + inputs.size() + " tasks in your list.");
                    System.out.println(lineBreak + "\n");
                    continue;
                }
                if (group[0].equals(taskTodo) && group.length == 1) {
                    System.out.println(" OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(lineBreak + "\n");
                    continue;
                }

                // Case Deadline
                if (group[0].equals(taskDdl) && input.contains(" /by ")) {
                    String[] ddlGroup = input.split(" /by ");
                    String ddlToAdd = ddlGroup[0].substring(9); // name of the task is after "deadline" and space
                    String ddlByTime = ddlGroup[1];
                    Task newDeadline = new Deadline(ddlToAdd, ddlByTime);
                    inputs.add(newDeadline);

                    try {
                        writeToFile(dataFile, inputs.get(0).toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something is wrong... " + e.getMessage());
                    }

                    for (int i = 1; i < inputs.size(); i++) {
                        try {
                            appendToFile(dataFile, inputs.get(i).toString() + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something is wrong... " + e.getMessage());
                        }
                    }

                    System.out.println("Roger! Added the task: ");
                    System.out.println("    " + newDeadline.toString());
                    System.out.println("Now you have " + inputs.size() + " tasks in your list.");
                    System.out.println(lineBreak + "\n");
                    continue;
                }

                // Case Event
                if (group[0].equals(taskEve) && input.contains(" /at ")) {
                    String[] eveGroup = input.split(" /at ");
                    String eveToAdd = eveGroup[0].substring(6); // name of the task is after "event" and space
                    String eveAtTime = eveGroup[1];
                    Task newEvent = new Event(eveToAdd, eveAtTime);
                    inputs.add(newEvent);

                    try {
                        writeToFile(dataFile, inputs.get(0).toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something is wrong... " + e.getMessage());
                    }

                    for (int i = 1; i < inputs.size(); i++) {
                        try {
                            appendToFile(dataFile, inputs.get(i).toString() + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something is wrong... " + e.getMessage());
                        }
                    }

                    System.out.println("Roger! Added the task: ");
                    System.out.println("    " + newEvent.toString());
                    System.out.println("Now you have " + inputs.size() + " tasks in your list.");
                    System.out.println(lineBreak + "\n");
                    continue;
                }

            }

            // display saved inputs
            if (input.equals(display)) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < inputs.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + inputs.get(i).toString());
                }
                System.out.println(lineBreak + "\n");
                continue;
            }

            // exit
            if (input.equals(end)) {
                System.out.println("     Bye! See you next time! :)" + "\n" + lineBreak + "\n");
                break;
            }

            // other inputs
            System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(lineBreak + "\n");
            continue;
        }
    }
}
