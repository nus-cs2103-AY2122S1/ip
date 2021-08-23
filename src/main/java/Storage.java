import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createNewFile() {
        try {
            File myFile = new File(filePath);

            if (myFile.createNewFile()){
                System.out.println("Woof! File is Created!");
            }else{
                System.out.println("File Already Exists Master.");
            }
        } catch (IOException e) {
            System.out.println("Master, Your Computer Has Issues!");
        }
    }

    public ArrayList<Task> readTasks() {
        ArrayList<Task> taskList = new ArrayList<Task>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNext()) {
                String[] tokens = scanner.nextLine().split(" \\| ");
                String taskType = tokens[0];
                String completionStatus = tokens[1];
                String taskDescription = tokens[2];

                if(taskType.contains("D")) {
                    Task deadlineTask = new Deadline(taskDescription, new TaskDateAndTime(tokens[3]));
                    if(completionStatus.contains("1")) {
                        deadlineTask.markAsDone();
                    }
                    taskList.add(deadlineTask);
                }

                if(taskType.contains("E")) {
                    Task eventTask = new Event(taskDescription, new TaskDateAndTime(tokens[3]));
                    if(completionStatus.contains("1")) {
                        eventTask.markAsDone();
                    }
                    taskList.add(eventTask);
                }

                if(taskType.contains("T")) {
                    Task todoTask = new Todo(taskDescription);
                    if(completionStatus.contains("1")) {
                        todoTask.markAsDone();
                    }
                    taskList.add(todoTask);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Master, File Does Not Exist, Give Me A Treat, And\n"
                    + "I Shall Create One For You! :)");
            createNewFile();

        }
        return taskList;
    }

    public void writeTask(Task.TaskType task, String taskDescription, String taskDeadline){
        String taskAsText;

        switch (task) {
        case DEADLINE:
            taskAsText = "D | 0 | " + taskDescription + " | " + taskDeadline + System.getProperty("line.separator");
            break;
        case EVENT:
            taskAsText = "E | 0 | " + taskDescription + " | " + taskDeadline + System.getProperty("line.separator");
            break;
        case TODO:
            taskAsText = "T | 0 | " + taskDescription + System.getProperty("line.separator");
            break;
        default:
            taskAsText = " ";
            break;
        }

        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(taskAsText);
            fw.close();
        } catch (IOException e) {
            System.out.println("Master, Sorry! I Ate The File!");
        }
    }

    public void deleteTask(int taskNumber) {
        int counter = 0;
        File inputFile = new File(filePath);
        File tempFile = new File("tempFile.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                if(counter == taskNumber) {

                } else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                counter++;
            }

            writer.close();
            reader.close();

            boolean isNameChangeSuccessful = tempFile.renameTo(inputFile);
        } catch (IOException e) {
            System.out.println("Master! Error Reading File. Gimme Treats, And I Help You!");
        }
    }

    public void updateTaskStatusToDone(int taskNumber) {
        int counter = 0;
        File inputFile = new File(filePath);
        File tempFile = new File("tempFile.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                if(counter == taskNumber) {
                    String[] taskToUpdate = currentLine.split(" \\| ");

                    String newLine = taskToUpdate[0] + " | 1 | " + taskToUpdate[2];

                    if(!taskToUpdate[0].contains("T")) {
                        newLine += " | " + taskToUpdate[3];
                    }

                    writer.write(newLine + System.getProperty("line.separator"));
                } else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                counter++;
            }

            writer.close();
            reader.close();

            boolean isNameChangeSuccessful = tempFile.renameTo(inputFile);
        } catch (IOException e) {
            System.out.println("Master! Error Reading File. Gimme Treats, And I Help You!");
        }
    }
}
