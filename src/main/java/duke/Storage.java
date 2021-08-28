package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File list_data = new File(filepath);
        Scanner reader;
        try {
            reader = new Scanner(list_data);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] data = line.split(" \\| ");
            switch (data[0]) {
                case "todo":
                    list.add(new Task.Todo(data[2], data[1].equals("0") ? false : true));
                    break;
                case "deadline":
                    list.add(new Task.Deadline(data[2], data[1].equals("0") ? false : true, data[3]));
                    break;
                case "event":
                    list.add(new Task.Event(data[2], data[1].equals("0") ? false : true, data[3]));
                    break;
            }
        }
        reader.close();
        return list;
    }

    public void save(TaskList list) {
        try {
            File list_data = new File(filepath);
            Files.createDirectories(Paths.get("data/"));
            FileWriter myWriter = new FileWriter(list_data);
            myWriter.write(list.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

}
