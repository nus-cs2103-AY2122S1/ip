import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {

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
                //System.out.println("File is created!");
            } else {
                //System.out.println("Data file already exists.");
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

                    if (taskType.equals("D")) {
//                        System.out.println(task.split("--")[1]);
                        String dateAndTimeInString = task.split("--")[1];
                        //System.out.println(dateAndTimeInString);
                        LocalDateTime byDateAndTime = LocalDateTime.parse(dateAndTimeInString);
                        task = " " + task.split("--")[0].substring(7);
                        //System.out.println(task);
                        Deadline dead = new Deadline(task, byDateAndTime);
                        if (completed.equals("X")) {
                            dead.completeTask();
                        }
                        taskItems.add(dead);
                    } else if (taskType.equals("T")) {
                        task = " " + task.substring(7);
                        ToDo toDoItem = new ToDo(task);
                        if (completed.equals("X")) {
                            toDoItem.completeTask();
                        }
                        taskItems.add(toDoItem);
                    } else if (taskType.equals("E")) {
                        //System.out.println(task.split("--")[0]);
                        String dateAndTimeInString = task.split("--")[1];
                        //System.out.println(dateAndTimeInString);
                        LocalDateTime byDateAndTime = LocalDateTime.parse(dateAndTimeInString);
                        task = " " + task.split("--")[0].substring(7);
                        //System.out.println(task);
                        Event someEvent = new Event(task, byDateAndTime);
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

    public static void rewriteFile(TaskList taskList) {
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

            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toFileString() + "\n");
//                System.out.println("Wrote " + arrayList.get(i).toString() + " to file");
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Some error occurred while tyring to load.");
        }



    }




}
