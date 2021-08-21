package main.java;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DatabaseEngine {

    private Path dir;
    private Path fileDir;
    private File file;
    private DukeMessages messages = new DukeMessages();
    private ObjectMapper mapper = new ObjectMapper();

    public DatabaseEngine() {
        this.dir = Paths.get(System.getProperty("user.dir"), "database");
        this.fileDir = Paths.get(System.getProperty("user.dir"), "database", "database.json");
        this.file = new File(this.fileDir.toString());
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Writes current list to local storage's JSON.
     * @param taskList list to be saved.
     */
    public void writeToDatabase(List<Task> taskList) {
        try {
            mapper
                    .writerFor(new TypeReference<List<Task>>() {})
                    .writeValue(file, taskList);

        } catch (IOException e) {
            messages.displayText("Error writing");
        }
    }

    /**
     * Reads from local storage's JSON.
     * @return list containing Tasks stored in local storage.
     */
    public List<Task> readFromDatabase() {
        try {
            return mapper
                    .readerFor(new TypeReference<ArrayList<Task>>() {})
                    .readValue(file);

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error: Unable to read from database.");
            return new ArrayList<Task>();
        }
    }

    /**
     * Creates a json file if not present in local storage.
     */
    public void createDatabase() {
        try {
            File file = new File(String.valueOf(this.fileDir));
            file.createNewFile();
        } catch (IOException e) {
            //should not occur since we are writing to local memory as opposed to network file
            messages.displayText("Error: Unable to create json file.");
        }

    }

    /**
     * Creates a directory if not present in local storage.
     */
    public void createDirectory() {

        try {
            if(!Files.exists(this.dir)) {
                messages.displayText("You currently do not have a directory to save the database. " +
                        "Creating one now at: " + this.dir);
                Files.createDirectory(this.dir);
            }
        } catch (IOException e) {
            messages.displayText("Error: Unable to create the directory.");
        }
    }
}
