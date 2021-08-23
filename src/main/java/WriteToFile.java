import java.io.*;
import java.util.ArrayList;

public class WriteToFile {

    private static boolean appendMode = true;
    private static String filePath = "./data/dukeData.txt";

    public static ArrayList<TaskItem> loadData() {
        File folder = new File("./data/");

        if (!folder.exists()) {
            folder.mkdir();
        }
        File dukeData = new File("./data/dukeData.txt");
        try {
            if (dukeData.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("Data file already exists.");
            }

            if (dukeData.length() == 0) {
                return new ArrayList<TaskItem>();
            } else {
                BufferedReader bufReader = new BufferedReader(new FileReader("./data/dukeData.txt"));
                ArrayList<TaskItem> taskItems = new ArrayList<>();
                String task = bufReader.readLine();
                while (task != null) {
                    String completed = task.substring(4, 5);
                    String taskType = task.substring(1, 2);
                    task = " " + task.substring(7);

                    if (taskType.equals("D")) {
                        Deadline dead = new Deadline(task);
                        if (completed.equals("X")) {
                            dead.completeTask();
                        }
                        taskItems.add(dead);
                    } else if (taskType.equals("T")) {
                        ToDo toDoItem = new ToDo(task);
                        if (completed.equals("X")) {
                            toDoItem.completeTask();
                        }
                        taskItems.add(toDoItem);
                    } else if (taskType.equals("E")) {
                        Event someEvent = new Event(task);
                        if (completed.equals("X")) {
                            someEvent.completeTask();
                        }
                        taskItems.add(someEvent);
                    }
//                    taskItems.add(task);
                    task = bufReader.readLine();
                }
                bufReader.close();
                return taskItems;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Some error occurred while tyring to load.");
        }
        return new ArrayList<TaskItem>();
    }

    public static void appendToFile(String content) {
        try {
            FileWriter fw = new FileWriter(filePath, appendMode);

            fw.write(content + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Some error occurred while trying to appendToFile.");
        }
    }

    public static void rewriteFile(ArrayList<TaskItem> arrayList) {
        File dukeData = new File("./data/dukeData.txt");
        dukeData.delete();


        try {
            File dukeData2 = new File("./data/dukeData.txt");
//            if (dukeData.createNewFile()) {
//                System.out.println("File is created!");
//            } else {
//                System.out.println("Data file already exists.");
//            }

            FileWriter fw = new FileWriter(filePath, appendMode);

            for (int i = 0; i < arrayList.size(); i++) {
                fw.write(arrayList.get(i).toString() + "\n");
//                System.out.println("Wrote " + arrayList.get(i).toString() + " to file");
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Some error occurred while tyring to load.");
        }



    }




}
