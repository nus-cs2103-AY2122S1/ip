package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private FormatAdapter adapter;
    private ArrayList<Task> userInputRecord;

    public Storage() {
        this.userInputRecord = new ArrayList<>();
        this.adapter = new FormatAdapter();
    }

    public void autoSave() {
        boolean directoryExists = Files.exists(Paths.get("data"));
        boolean fileExists = Files.exists(Paths.get("data","record"));
        if(!directoryExists) {
            try {
                Files.createDirectory(Path.of("data"));
            } catch (IOException e) {
                System.out.println("Something is wrong " + e);
            }
        } else if(!fileExists) {
            try {
                Files.createFile(Path.of("data","record"));
            } catch (IOException e) {
                System.out.println("Something is wrong " + e);
            }
        }
        try {
            System.out.println(Paths.get("data","record"));
            FileWriter writer = new FileWriter(Paths.get("data","record").toString());
            for (Task task : userInputRecord) {
                writer.write(task.toString());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something is wrong " + e);
        }
    }

    public void autoLoad() {
        try {
            Scanner scanner = new Scanner(Paths.get("data","record"));
            while (scanner.hasNextLine()) {
                String itemInfo = scanner.nextLine();
                if(itemInfo.startsWith("[T]")) {
                    Task task = new ToDo(itemInfo.substring(7));
                    if(itemInfo.contains("[X]")) {
                        task.setDone(true);
                    }
                    userInputRecord.add(task);
                } else if(itemInfo.startsWith("[D]")) {
                    Task task = new Deadline(itemInfo.substring(7, itemInfo.indexOf("(by")),
                            adapter.convertToLocalTime(itemInfo.substring(itemInfo.indexOf("(by") + 5, itemInfo.length() -1)));
                    if(itemInfo.contains("[X]")) {
                        task.setDone(true);
                    }
                    userInputRecord.add(task);
                } else if(itemInfo.startsWith("[E]")) {
                    Task task = new Event(itemInfo.substring(7, itemInfo.indexOf("(at")),
                            adapter.convertToLocalTime(itemInfo.substring(itemInfo.indexOf("(at") + 5, itemInfo.length() -1)));
                    if(itemInfo.contains("[X]")) {
                        task.setDone(true);
                    }
                    userInputRecord.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(adapter.formatMessage("Saved data not found, a new data file created.\n"));
        }
    }

    public ArrayList<Task> getUserInputRecord() {
        return userInputRecord;
    }
}
