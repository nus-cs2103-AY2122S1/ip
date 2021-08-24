import java.nio.file.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDateTime;

public class Duke {
    public static void main(String[] args) {
        // Introduction message
        String logo = "______ _____ _   _ \n" +
                "| ___ \\  ___| \\ | |\n" +
                "| |_/ / |__ |  \\| |\n" +
                "| ___ \\  __||     |\n" +
                "| |_/ / |___| |\\  |\n" +
                "\\____/\\____/\\_| \\_/\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        drawLine();

        //Initialise list to store tasks and Scanner to get user input
        List<Task> tasks = new ArrayList<>(100);
        Path dataPath = loadData(tasks);
        Scanner sc = new Scanner(System.in);

        // Main Code
        while(true) {
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                dukeReply("Bye. Hope to see you again soon!");
                break;
            } else if(userInput.equalsIgnoreCase("list")) {
                showTasks(tasks);
                // cant put done word as a task
            }else if (userInput.toLowerCase().startsWith("done")) {
                markTaskDone(userInput, tasks, dataPath);
            } else if(userInput.toLowerCase().startsWith("delete")) {
                deleteTask(userInput, tasks, dataPath);
            } else {
                addTask(userInput, tasks, dataPath);
            }
        }
    }

    /**
     * Loads all data stored on hard disk into local list upon start up.
     * @param tasks ArrayList where all task objects are stored.
     * @return Path of data file.
     */
    public static Path loadData(List<Task> tasks) {
        Path dirPath = Paths.get("dataSets");
        Path dataPath = Paths.get("datasets/Data.txt");

        try {
            if(!Files.exists(dataPath)) {
                Files.createDirectories(dirPath);
                Files.createFile(dataPath);
            }

            Files.lines(dataPath).forEach(x -> {
                String[] data = x.split(",");
                String taskType = data[0];
                Task toAdd;
                if(taskType.equalsIgnoreCase("todo")) {
                    toAdd = new ToDo(data[2]);
                }else if(taskType.equalsIgnoreCase("deadline")) {
                    toAdd = new Deadline(data[2], stringToDate(data[3]));
                } else if(taskType.equalsIgnoreCase("event")) {
                    toAdd = new Event(data[2], stringToDate(data[3]));
                } else {
                    throw new IllegalArgumentException("Unrecognised task type on loading");
                }

                if(data[1].equals("1")) {
                    toAdd.setDone();
                }
                tasks.add(toAdd);
            });

        } catch(Exception e) {
            System.out.println(e);
        }

        return dataPath;
    }

    /**
     * Adds a task to the List of tasks and to the data file.
     * @param userInput String of task to add.
     * @param tasks List of current tasks.
     * @param dataPath Path to the data storage file.
     */
    public static void addTask(String userInput, List<Task> tasks, Path dataPath) {
        Task taskToAdd;
        String dataToStore;

        try{
            if(userInput.toLowerCase().startsWith("todo")) {
                taskToAdd = new ToDo(userInput.substring(5));
            } else {
                String[] input = userInput.split(" ");

                int taskIndex = input[0].length() + 1;
                int dateIndex = userInput.indexOf("/");
                String[] dateAndTask = sepDateFromTask(dateIndex,taskIndex, userInput);

                String str = dateAndTask[1];
                LocalDateTime dateTime = stringToDate(str);

                if (userInput.toLowerCase().startsWith("deadline")){
                    taskToAdd = new Deadline(dateAndTask[0], dateTime);
                } else if(userInput.toLowerCase().startsWith("event")) {
                    taskToAdd = new Event(dateAndTask[0], dateTime);
                } else {
                    throw new IllegalArgumentException("Please specify type of task");
                }
            }
            // Add task to data file
            dataToStore = taskToAdd.getDataRep();
            Files.writeString(dataPath, dataToStore + System.lineSeparator(), StandardOpenOption.APPEND);
            // Add task To arrayList
            tasks.add(taskToAdd);

            dukeReply(String.format("Got it. I've added this task:\n" +
                    "%s\nNumber of tasks: %s", taskToAdd.toString(), tasks.size()));

        }catch(IllegalArgumentException e) {
            dukeReply("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch(Exception e) {
            dukeReply("Invalid Input format -> <taskType> <task> </by or /at> <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Deletes a task from the array of tasks.
     * @param userInput String of task to delete.
     * @param tasks List of current tasks.
     */
    public static void deleteTask(String userInput, List<Task> tasks, Path dataPath) {
        try {
            //Delete from ArrayList
            int taskToDel = Integer.parseInt(userInput.substring(7)) - 1;
            Task task = tasks.get(taskToDel);
            tasks.remove(taskToDel);
            dukeReply(String.format("Noted. I've removed this task:\n%s\nNow you have %s tasks in list"
                    , task, tasks.size()));

            //Delete from DataFile
            updateDataSet(tasks, dataPath);
        } catch(StringIndexOutOfBoundsException e) {
            dukeReply("OOPS!!! You cannot delete nothing!");
        } catch(NumberFormatException e) {
            dukeReply("OOPS!!! Must be a number bodoh");
        } catch(IndexOutOfBoundsException e) {
            dukeReply("OOPS!!! Number doesnt exist");
        }
    }

    /**
     * Marks a task as complete.
     * @param userInput Text beginning with 'done' followed by a number.
     * @param tasks List of current tasks.
     */
    public static void markTaskDone(String userInput, List<Task> tasks, Path dataPath) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
            Task task = tasks.get(taskIndex);
            if (task.checkStatus()) {
                dukeReply("Youve already marked this task as done!");
            } else {
                task.setDone();
                dukeReply("Nice! I've marked this task as done:\n" + task.toString());
                updateDataSet(tasks, dataPath);
            }
        } catch(StringIndexOutOfBoundsException e) {
            dukeReply("OOPS!!! You cannot mark nothing as done!");
        } catch(NumberFormatException e) {
            dukeReply("OOPS!!! Must be a number bodoh");
        } catch(IndexOutOfBoundsException e) {
            dukeReply("OOPS!!! NUmber doesnt exist");
        }
    }

    /**
     * Rewrites content in dataset according to the ArrayList.
     * @param list ArrayList of tasks.
     * @param dataPath Path to data set.
     */
    public static void updateDataSet(List<Task> list, Path dataPath) {
        try {
            Path tempPath = Paths.get("datasets/temp.txt");
            Files.createFile(tempPath);

            for(int i = 0; i < list.size(); i++) {
                String toAdd = list.get(i).getDataRep();
                Files.writeString(tempPath, toAdd + System.lineSeparator(), StandardOpenOption.APPEND);
            }

            Files.move(tempPath, tempPath.resolveSibling("Data.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("cant update dataset");
        }
    }

    /**
     * Seperates the date and task from the users input.
     * @param taskIndex Beginning index of the task in the given String.
     * @param dateIndex Beginning index of the date in the given String.
     * @param userInput String of task containing task and date.
     * @return Array with task at index 0 and date at index 1.
     */
    public static String[] sepDateFromTask(int dateIndex, int taskIndex, String userInput) {
        String task;
        String date;
        if(dateIndex > 0) {
            task = userInput.substring(taskIndex, dateIndex - 1);
            date = userInput.substring(dateIndex + 4);
        }else {
            task = userInput.substring(taskIndex);
            date = "?";
        }
        return new String[] {task, date};
    }

    /**
     * Converts a date from String class to LocalDateTime class
     * @param str
     * @return
     */
    public static LocalDateTime stringToDate(String str) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return (LocalDateTime.parse(str, inputFormat));
    }

    /**
     * Prints the list of todos sequentially.
     * @param tasks List of current tasks.
     */
    public static void showTasks(List<Task> tasks) {
        drawLine();
        System.out.println("Here are the tasks in your list:");
        for( int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
        }
        drawLine();
    }

    /**
     * Prints dukes reply in between two lines.
     * @param message Duke's reply.
     */
    public static void dukeReply(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    /**
     * Draws a line on the screen to separate speech.
     */
    public static void drawLine() {
        System.out.println("------------------------");
    }

}
