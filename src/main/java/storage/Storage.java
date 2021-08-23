package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Duke;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File data_file = new File(filePath);
        // if data file already exists, load the file's data into tasks, else create a new data file
        if (!data_file.createNewFile()) {
            Scanner dataScanner = new Scanner(data_file);
            while (dataScanner.hasNextLine()) {
                String[] arr = dataScanner.nextLine().split(",");
                // if task is a task.Todo
                if (arr[0].equals("T")) {
                    tasks.add(new Todo(arr[2]));
                    // if task is done, mark as done
                    if (arr[1].equals("1")) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    }
                } else if (arr[0].equals("D")) {
                    tasks.add(new Deadline(arr[2], LocalDate.parse(arr[3].substring(0, 10)),
                            LocalTime.parse(arr[3].substring(11))));
                    // if task is done, mark as done
                    if (arr[1].equals("1")) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    }
                } else {
                    tasks.add(new Event(arr[2], LocalDate.parse(arr[3].substring(0, 10)),
                            LocalTime.parse(arr[3].substring(11))));
                    // if task is done, mark as done
                    if (arr[1].equals("1")) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    }
                }
            }
        }
        return tasks;
    }

    public void removeData(int number) {
        File fileToBeModified = new File(filePath);
        String newContent = "";
        try {
            Scanner scanner = new Scanner(fileToBeModified);
            int current = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (current != number) {
                    newContent = newContent + nextLine + "\n";
                }
                current++;
            }
            FileWriter writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

            try {
                //Closing the resources
                scanner.close();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void markAsDoneData(int number) {
        File fileToBeModified = new File(filePath);
        String newContent = "";
        try {
            Scanner scanner = new Scanner(fileToBeModified);
            int current = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (current == number) {
                    String[] arr = nextLine.split(",");
                    newContent = newContent + arr[0] + ",1," + arr[2] + "," + arr[3] + "\n";
                } else {
                    newContent = newContent + nextLine + "\n";
                }
                current++;
            }
            FileWriter writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

            try {
                //Closing the resources
                scanner.close();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void newTaskToData(String taskName, Duke.Type type, String time) {
        try {
            FileWriter dataWriter = new FileWriter(filePath, true);
            if (type == Duke.Type.TODO) {
                dataWriter.write("T,0," + taskName + ", \n");
                dataWriter.close();
            } else if (type == Duke.Type.DEADLINE) {
                dataWriter.write("D,0," + taskName + "," + time + "\n");
                dataWriter.close();
            } else {
                dataWriter.write("E,0," + taskName + "," + time + "\n");
                dataWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
