import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

//    private static void printFileContents(File file) throws FileNotFoundException {
//        //File f = new File(filePath); // create a File for the given file path
//        Scanner s = new Scanner(file); // create a Scanner using the File as the source
//        while (s.hasNext()) {
//            System.out.println(s.nextLine());
//        }
//    }

    public static void main(String[] args) {
        String end = "bye";
        String display = "list";
        String lineBreak = "------------------------------";
        String markDone = "done";
        String deleteTask = "delete";
        String taskTodo = "todo";
        String taskDdl = "deadline";
        String taskEve = "event";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm", Locale.ENGLISH);

        // a List to store all user-generated Tasks
        ArrayList<Task> inputs = new ArrayList<>();

        // a List to store all Tasks read from data.txt
        ArrayList<String> dataRead = new ArrayList<>();

        // check and create folder and file to save data
        File data = new File("./data");
        File dataFile = new File("./data/data.txt");

        // if data folder does not exist, create folder and file
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

        // if data.txt does not exist, create file
        if (!dataFile.exists()) {
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

        // if data.txt exists
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                dataRead.add(scanner.nextLine());
            }
            for (int i = 0; i < dataRead.size(); i++) {
                String toAdd = dataRead.get(i);
                // case Todo
                if (toAdd.charAt(1) == 'T') {
                    String[] parts = toAdd.split("] ");
                    String todoName = parts[1];
                    Todo todoToAdd = new Todo(todoName);
                    if (toAdd.charAt(4) == 'X') {
                        todoToAdd.setDone();
                    }
                    inputs.add(todoToAdd);
                }
                // case Deadline
                if (toAdd.charAt(1) == 'D') {
                    String[] parts1 = toAdd.split("] ");
                    String nameTime = parts1[1];
                    String[] parts2 = nameTime.split(" \\(by: ");
                    String ddlName = parts2[0];
                    String ddlTimeStr = parts2[1].substring(0, parts2[1].length() - 1);
                    DateTimeFormatter formatterDdl = DateTimeFormatter.ofPattern(
                            "EEE, dd/MMM/yyyy hh:mm a");
                    LocalDateTime dateTime = LocalDateTime.parse(ddlTimeStr, formatterDdl);
                    Deadline ddlToAdd = new Deadline(ddlName, dateTime);
                    if (toAdd.charAt(4) == 'X') {
                        ddlToAdd.setDone();
                    }
                    inputs.add(ddlToAdd);
                }
                // case Event
                if (toAdd.charAt(1) == 'E') {
                    String[] parts1 = toAdd.split("] ");
                    String nameTime = parts1[1];
                    String[] parts2 = nameTime.split(" \\(at: ");
                    String eveName = parts2[0];
                    String eveTimeStr = parts2[1].substring(0, parts2[1].length() - 1);
                    DateTimeFormatter formatterEve = DateTimeFormatter.ofPattern(
                            "EEE, dd/MMM/yyyy hh:mm a");
                    LocalDateTime dateTime = LocalDateTime.parse(eveTimeStr, formatterEve);
                    Event eveToAdd = new Event(eveName, dateTime);
                    if (toAdd.charAt(4) == 'X') {
                        eveToAdd.setDone();
                    }
                    inputs.add(eveToAdd);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("data.txt is not found.");
        }

//        for (int i = 0; i < inputs.size(); i++) {
//            System.out.println("test read todo and ddl\n" + dataRead.get(i));
//        }

        String dataFileStr = "./data/data.txt";

        // Welcome message
        System.out.println(lineBreak
                + "\n"
                + "Hello! I'm Azure.\n"
                + "You can create Tasks here.\n"
                + "To add a Todo, simply key in `todo` followed by the name;\n"
                + "To add a Deadline, simply key in `deadline` followed by its name, `/by` and the deadline;\n"
                + "To add an Event, simply key in `event` followed by its name, `/at` and its time.\n"
                + "Please key in the date and time in the format of `26/08/2021 20:20`.\n"
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
                        writeToFile(dataFileStr, inputs.get(0).toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something is wrong... " + e.getMessage());
                    }

                    for (int i = 1; i < inputs.size(); i++) {
                        try {
                            appendToFile(dataFileStr, inputs.get(i).toString() + System.lineSeparator());
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
                        writeToFile(dataFileStr, inputs.get(0).toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something is wrong... " + e.getMessage());
                    }

                    for (int i = 1; i < inputs.size(); i++) {
                        try {
                            appendToFile(dataFileStr, inputs.get(i).toString() + System.lineSeparator());
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
                        writeToFile(dataFileStr, inputs.get(0).toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something is wrong... " + e.getMessage());
                    }

                    for (int i = 1; i < inputs.size(); i++) {
                        try {
                            appendToFile(dataFileStr, inputs.get(i).toString() + System.lineSeparator());
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
                    LocalDateTime dateTime = LocalDateTime.parse(ddlByTime, formatter);
                    Task newDeadline = new Deadline(ddlToAdd, dateTime);
                    inputs.add(newDeadline);

                    try {
                        writeToFile(dataFileStr, inputs.get(0).toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something is wrong... " + e.getMessage());
                    }

                    for (int i = 1; i < inputs.size(); i++) {
                        try {
                            appendToFile(dataFileStr, inputs.get(i).toString() + System.lineSeparator());
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
                    LocalDateTime dateTime = LocalDateTime.parse(eveAtTime, formatter);
                    Task newEvent = new Event(eveToAdd, dateTime);
                    inputs.add(newEvent);

                    try {
                        writeToFile(dataFileStr, inputs.get(0).toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something is wrong... " + e.getMessage());
                    }

                    for (int i = 1; i < inputs.size(); i++) {
                        try {
                            appendToFile(dataFileStr, inputs.get(i).toString() + System.lineSeparator());
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
