package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {

    /**
     * Creates directory/file if it does not exist yet.
     *
     * @throws IOException directory not found.
     */
    public void createDirectoryFile() throws IOException {
        if (Files.notExists(Path.of("src/data"))) {
            Files.createDirectory(Path.of("src/data"));
        }
        if (Files.notExists(Path.of("src/data/duke.txt"))) {
            Files.createFile(Path.of("src/data/duke.txt"));
        }
        if (Files.notExists(Path.of("src/data/places.txt"))) {
            Files.createFile(Path.of("src/data/places.txt"));
        }
    }
    /**
     * Loads the TaskList stored in predetermined file.
     *
     * @return TaskList.
     */
    public TaskList loadTaskList() {
        TaskList tasks = new TaskList();
        Parser p = new Parser();
        try {
            createDirectoryFile();
            File tasksFile = new File("src/data/duke.txt");
            Scanner s = new Scanner(tasksFile);
            while (s.hasNext()) {
                String[] parsedFromFile = p.parseTasksFromFile(s.nextLine());
                switch (parsedFromFile[0]) {
                case "todo":
                    tasks.addTask(new Todo(parsedFromFile));
                    break;
                case "deadline":
                    tasks.addTask(new Deadline(parsedFromFile));
                    break;
                case "event":
                    tasks.addTask(new Event(parsedFromFile));
                    break;
                default:
                    throw new IOException("Invalid task detected");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Loads the PlaceList stored in predetermined file.
     *
     * @return PlaceList.
     */
    public PlaceList loadPlaceList() {
        PlaceList places = new PlaceList();
        Parser p = new Parser();
        try {
            createDirectoryFile();
            File placesFile = new File("src/data/places.txt");
            Scanner s = new Scanner(placesFile);
            while (s.hasNext()) {
                String[] parsedFromFile = p.parsePlacesFromFile(s.nextLine());
                places.addPlace(new Place(parsedFromFile[0], parsedFromFile[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }

    /**
     * Writes TaskList into file for storage.
     *
     * @param taskList TaskList to be stored.
     */
    public void write(TaskList taskList, PlaceList placeList) {
        try {
            FileWriter fw = new FileWriter("src/data/duke.txt");
            FileWriter placeWriter = new FileWriter("src/data/places.txt");
            for (int i = 0; i < taskList.getLength(); i++) {
                String taskToWrite = taskList.getTaskByIndex(i).toWrite();
                fw.write(taskToWrite);
            }
            for (int i = 0; i < placeList.getLength(); i++) {
                String placeToWrite = placeList.getPlaceByIndex(i).toWrite();
                placeWriter.write(placeToWrite);
            }
            fw.close();
            placeWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
