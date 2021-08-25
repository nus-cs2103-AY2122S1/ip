package duke;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class Storage {
    private final BufferedReader reader;
    private BufferedWriter writer;
    private final Formatter formatter;
    public static final String DEFAULT_FILE_NAME = "duke.txt";
    public static final String DEFAULT_FILE_DIRECTORY = "data";
    private final Path targetDirectory;

    private Storage() throws DukeException {
        try {
            this.targetDirectory =
                    Paths.get(".", DEFAULT_FILE_DIRECTORY, DEFAULT_FILE_NAME)
                            .toAbsolutePath()
                            .normalize();
            if (!java.nio.file.Files.exists(this.targetDirectory)) {
                try {
                    Files.createDirectory(Paths.get(".", DEFAULT_FILE_DIRECTORY).toAbsolutePath().normalize());
                } catch (java.nio.file.FileAlreadyExistsException e) {
                    System.out.println("Directory exists but file does not. Creating file...");
                }
                Files.createFile(this.targetDirectory);
            }
            this.reader = Files.newBufferedReader(this.targetDirectory, StandardCharsets.UTF_8);
            this.formatter = new Formatter();
        } catch (IOException e) {
            throw new DukeException("Something happened when initializing the duke.Storage.\n" + e);
        }
    }

    private Storage(String[] args) throws DukeException {
        try {
            this.targetDirectory =
                    Paths.get(".", args).toAbsolutePath().normalize();
            if (!java.nio.file.Files.exists(this.targetDirectory)) {
                int i = 0;
                StringBuilder directory = new StringBuilder("./");
                while (i < args.length - 1) {
                    try {
                        directory.append(args[i]);
                        Files.createDirectory(Paths.get(directory.toString()).toAbsolutePath().normalize());
                        directory.append("/");
                    } catch (java.nio.file.FileAlreadyExistsException e) {
                        System.out.println("Directory exists but file does not. Creating file...");
                    }
                    i++;
                }
                Files.createFile(this.targetDirectory);
            }
            this.reader = Files.newBufferedReader(this.targetDirectory, StandardCharsets.UTF_8);
            this.formatter = new Formatter();
        } catch (IOException e) {
            throw new DukeException("Something happened when initializing the duke.Storage.\n" + e);
        }
    }

    public static Storage createStorage() throws DukeException {
        return new Storage();
    }

    public static Storage createStorage(String filePath) throws DukeException {
        return new Storage(filePath.split("/"));
    }


    public TaskList load(TaskList taskList) throws DukeException {
        try {
            String line = this.reader.readLine();
            if (line == null || line.isEmpty()) {
                System.out.println("No previous record found.");
                return taskList;
            }
            while (line != null) {
                taskList.addTask(this.formatter.formatStringToTask(line));
                line = this.reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Unable to load file.");
            e.printStackTrace();
        }
        return taskList;
    }

    public void save(TaskList taskList) throws DukeException {
        try{
            this.writer = Files.newBufferedWriter(this.targetDirectory, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Exception occurred while initialising writer.");
            e.printStackTrace();
        }
        System.out.println("Saving your list now!");
        for (int i = 1; i <= taskList.size(); i++) {
            try {
                this.writer.write(this.formatter.formatTaskToString(taskList.get(i)));
                this.writer.newLine();
            } catch (IOException e) {
                System.out.println("Exception occurred while writing");
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Exception occurred while closing writer.");
            e.printStackTrace();
        }
    }

}
