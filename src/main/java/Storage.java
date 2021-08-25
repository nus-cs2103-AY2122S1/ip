import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

// todo
// 1. Check existence of file and create it

public class Storage {
    private static final String fileErrorText = "Ribiit! Something was wrong with the file!";

    private File toDoFile;
    private ToDo taskList;

    public Storage(String filePath) throws IOException, KermitException {
        // Check if Kermit data exists, else create it
        this.toDoFile = new File(filePath);
        this.taskList = readToDoData(this.toDoFile);
    }

    private ToDo readToDoData(File file) throws IOException, KermitException {
        if (toDoFile.getParentFile() != null) {
            toDoFile.getParentFile().mkdirs();
        }

        boolean didCreateFile = toDoFile.createNewFile();
        ToDo todo = new ToDo();
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
                    } catch (DateTimeParseException e){
                        throw new KermitException("Invalid date in data file!");
                    }
                }

                // Create task based on line data
                switch (taskShortForm) {
                    case "T":
                        task = new ToDos(description, isCompleted);
                        todo.add(task);
                        break;
                    case "D":
                        task = new Deadline(description, date, isCompleted);
                        todo.add(task);
                        break;
                    case "E":
                        task = new Event(description, date, isCompleted);
                        todo.add(task);
                        break;
                }
            }
        }
        return todo;
    }

    private String formatWriteString(Task task) {
        String delimiter = " | ";

        String taskComplete = task.isComplete() ? "1" : "0";
        String formattedString = String.join(delimiter, task.getShortForm(), taskComplete, task.getDescription());

        if (task instanceof DateDependentTask) {
            DateDependentTask dateTask = (DateDependentTask) task;
            formattedString = String.join(delimiter, formattedString, dateTask.getDate());
        }
        return formattedString;
    }

    public ToDo getToDoList() {
        return this.taskList;
    }

    // Saves task list data to file, file is overwritten
    public void save() throws KermitException {
        try {
            FileWriter fw = new FileWriter(toDoFile);
            BufferedWriter bw = new BufferedWriter(fw);
            Iterator<Task> taskIter = taskList.iterator();
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