package main.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DatabaseEngine {

    private Path dir;
    private Path fileDir;
    private DukeMessages messages = new DukeMessages();
    private ObjectMapper mapper;


    public DatabaseEngine() {
        this.dir = Paths.get(System.getProperty("user.dir"), "database");
        this.fileDir = Paths.get(System.getProperty("user.dir"), "database", "database.txt");
    }

    public void writeToDatabase(List<Task> taskList) {

        try {
            //deletes original file if it is present.
            this.clearDatabase();
            FileWriter writer = new FileWriter(this.fileDir.toString());


            writer.close();

        } catch (Exception e) {
            //TODO: Should be using DukeMessages
            System.out.println("Might not be working?");
            System.out.println(this.dir);
            System.out.println(this.fileDir);
            messages.displayText("An error occurred while trying to write to the database.");
        }
    }

    public List<Task> readFromDatabase() {

        try {
            this.createDirectory();
        } catch (IOException e) {
            messages.displayText("Cannot create file directory. Please check if you have the" +
                    "read / write file permissions");
        }

        try {
            Reader reader = Files.newBufferedReader(this.fileDir);

            //TODO: replace
            List<Task> taskList = new ArrayList<Task>();
            //List<Task> taskList = Arrays.asList(gson.fromJson(reader, Task[].class));

            reader.close();
            return taskList;

        } catch (Exception e) {
            //as we are writing to local memory, IOException should not be thrown at all.
            messages.displayText("No database inside directory or it is empty. Creating one now at: " + this.dir);
            this.createDatabase();

            List<Task> taskList = new ArrayList<Task>();
            return taskList;
        }
    }


    public void createDatabase() {
        try {
            File file = new File(String.valueOf(this.fileDir));
            file.createNewFile();
        } catch (IOException e) {
            //should not occur since we are writing to local memory as opposed to network file
            messages.displayText("An issue occurred while trying to create a directory. Please check that " +
                    "you have admin permissions and can read and write to folders. Any work done in this program" +
                    "session will not be saved.");
        }

    }

    public void clearDatabase() throws IOException {
        new File(this.fileDir.toString()).delete();
    }

    public void createDirectory() throws IOException {
        if(!Files.exists(this.dir)) {
            messages.displayText("You currently do not have a directory to save the database. " +
                    "Creating one now at: " + this.dir);
            Files.createDirectory(this.dir);
        }
    }



}
