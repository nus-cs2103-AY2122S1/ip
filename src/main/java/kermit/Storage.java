package kermit;

import kermit.tasks.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String fileErrorText = "Ribiit! Something was wrong with the file!";
    private String filePath;
    private File file;
    public Storage(String filePath) {
        // Check if kermit.command.Kermit data exists, else create it
        this.filePath = filePath;
    }

    public List<Task> load() throws KermitException {
        try {
            file = new File(filePath);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            boolean didCreateFile = file.createNewFile();
            ArrayList<Task> taskList = new ArrayList<>();
            String line;
            Task task;
            Scanner sc = new Scanner(file);
            // Read file line by line
            if (!didCreateFile) {
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    String[] commands = line.split(" \\| ");
                    String taskShortForm = commands[0];
                    boolean isCompleted = commands[1].equals("1");
                    String description = commands[2];

                    // todo
                    LocalDate date = LocalDate.now();
                    if (taskShortForm.equals("E") || taskShortForm.equals("D")) {
                        try {
                            date = LocalDate.parse(commands[3]);
                        } catch (DateTimeParseException e) {
                            throw new KermitException("Invalid date in data file!");
                        }
                    }

                    // Create task based on line data
                    switch (taskShortForm) {
                        case "T":
                            task = new ToDos(description, isCompleted);
                            taskList.add(task);
                            break;
                        case "D":
                            task = new Deadline(description, date, isCompleted);
                            taskList.add(task);
                            break;
                        case "E":
                            task = new Event(description, date, isCompleted);
                            taskList.add(task);
                            break;
                    }
                }
            }
            return taskList;
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            throw new KermitException(fileErrorText);
        }
    }

    private String formatWriteString(Task task) {
        String delimiter = " | ";

        String taskComplete = task.isComplete() ? "1" : "0";
        String formattedString = String.join(delimiter, task.getShortForm(), taskComplete, task.getDescription());

        if (task instanceof DateDependentTask) {
            DateDependentTask dateTask = (DateDependentTask) task;
            formattedString = String.join(delimiter, formattedString, dateTask.getDateString());
        }
        return formattedString;
    }

    // Saves task list data to file, file is overwritten
    public void save(TaskList todo) throws KermitException {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            Iterator<Task> taskIter = todo.iterator();
            while (taskIter.hasNext()) {
                Task task = taskIter.next();
                String taskData = formatWriteString(task);
                bw.write(taskData);
                if (taskIter.hasNext()) {
                    bw.write("\n");
                }
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new KermitException(fileErrorText);
        }
    }
}