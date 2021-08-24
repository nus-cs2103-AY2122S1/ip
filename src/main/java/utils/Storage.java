package utils;

import java.util.*;
import tasks.*;
import java.io.*;

public class Storage {
    static File directory;
    static File data;
    static String folderPath;

    public Storage(String path) {
        directory = new File(path);
        data = new File(path + "/duke.txt");
        folderPath = path;
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> savedTasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(data);
            while (s.hasNext()) {
                String[] parsed = s.nextLine().split(",");
                String type = parsed[0];
                boolean done = (parsed[1].equals("1"));
                if (type.equals("todo")) {
                    savedTasks.add(new Todo(parsed[2], done));
                } else if (type.equals("event")) {
                    savedTasks.add(new Event(parsed[2], parsed[3], done));
                } else {
                    savedTasks.add(new Deadline(parsed[2], parsed[3], done));
                }
            }
            return savedTasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File was not found!");
        }
    }

    public static void removeLine(int lineNumber) throws IOException {
        int counter = 0;
        // Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(data);
        // instantiating the StringBuilder class
        StringBuffer buffer = new StringBuffer();
        // Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            if (counter != lineNumber) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            } else {
                sc.nextLine();
            }
            counter++;
        }
        String fileContents = buffer.toString();
        // closing the Scanner object
        sc.close();
        // instantiating the FileWriter class
        FileWriter writer = new FileWriter(data);
        writer.append(fileContents);
        writer.flush();
    }

    public static void updateLine(int lineNumber) throws IOException {
        int counter = 0;
        // Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(data);
        // instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        // Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            if (counter != lineNumber) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            } else {
                String[] stringArr = sc.nextLine().split(",");
                stringArr[1] = "1";
                String updatedString = String.join(",", stringArr);
                buffer.append(updatedString + System.lineSeparator());
            }
            counter++;
        }
        String fileContents = buffer.toString();
        // closing the Scanner object
        sc.close();
        // instantiating the FileWriter class
        FileWriter writer = new FileWriter(data);
        writer.append(fileContents);
        writer.flush();
    }

    public static void saveToDisc(String task) throws IOException {
        FileWriter fw = new FileWriter(data, true);
        fw.write(task);
        fw.write(System.lineSeparator());
        fw.close();
    }
}
