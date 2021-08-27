package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() {        
        ArrayList<Task> tasks = new ArrayList<>();
        
        try {
            Scanner dataReader = new Scanner(this.file);

            while (dataReader.hasNextLine()) {
                String[] data = dataReader.nextLine().split("\\|");
                String type = data[0];
                boolean isDone = data[1] == "1" ? true : false;
                String description = data[2];

                if (type.equals("T")) {
                    tasks.add(new ToDo(description, isDone));
                } else if (type.equals("D")) {
                    LocalDate time = LocalDate.parse(data[3].trim());
                    tasks.add(new Deadline(description, time, isDone));
                } else if (type.equals("E")) {
                    LocalDate startTime = LocalDate.parse(data[3].trim());
                    LocalDate endTime = LocalDate.parse(data[3].trim());
                    tasks.add(new Event(description, startTime, endTime, isDone));
                }
            }

            dataReader.close();
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                return tasks; 
            }
        }
        return tasks; 
    }

    public void save(String formattedTaskList){
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(formattedTaskList);
            writer.close();

        } catch (IOException e) {
            return;
        }
    }
    
}