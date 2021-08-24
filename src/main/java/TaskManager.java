import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskManager keep track of the Task in the memory.
 */
public class TaskManager {
    private final List<Task> taskList = new ArrayList<>();

    public TaskManager() {
        try {
            File txtDataFile;
//            System.out.println(System.getProperty("user.dir"));
            String dirPath = System.getProperty("user.dir") + "\\src\\main\\data";
            if (Files.notExists(Path.of(dirPath))) {
                Files.createDirectories(Path.of(dirPath));
            }
            String path = dirPath + "\\dukeData.json";
            txtDataFile = new File(path);
            if (!txtDataFile.exists()) {
                System.out.println("No stored data! Starting a brand new state!");
                txtDataFile = new File(path);
                txtDataFile.createNewFile();
            } else {
                // file exist, read the file.
                readJson("dukeData.json");
//                System.out.println("Loading tasks from files...");
//                BufferedReader br = new BufferedReader(new FileReader(txtDataFile));
////                System.out.println("Reading stored data...");
//                String txtline;
//                while ((txtline = br.readLine()) != null) {
////                    System.out.println("Reading from dukeData...");
//                    String[] stringArr = txtline.split("\\|");
//                    boolean marked = stringArr[0].equals("1");
//                    executeCommand(stringArr[1], false, marked);
//                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * List all tasks inside the task manager.
     */
    public void listAll() {
        if (taskList.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                int index = i + 1;
                System.out.println(index + "." + taskList.get(i));
            }
            System.out.println();
        } else {
            System.out.println("Looks like there isn't any task!");
        }
    }

    public void markTaskDoneByIndex(int index) {
        Task taskToBeMarked = taskList.get(index - 1);
        taskToBeMarked.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + taskToBeMarked);
    }

    /**
     * Get the total number of task.
     *
     * @return the number of task in total.
     */
    public int getTotalNumberOfTask() {
        return taskList.size();
    }

    @SuppressWarnings("unchecked")
    public void readJson(String fileName) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        String dirPath = System.getProperty("user.dir") + "\\src\\main\\data";
        try (FileReader reader = new FileReader(dirPath + "\\" + fileName))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray taskArr = (JSONArray) obj;

            //Iterate over task array
            taskArr.forEach( task -> taskList.add(parseTaskObject( (JSONObject) task ) ) );

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    private Task parseTaskObject(JSONObject task)
    {
        //Get task object within list
        JSONObject taskObject = (JSONObject) task.get("task");

        String value = (String) taskObject.get("value");
        boolean done = (boolean) taskObject.get("done");
        String time = (String) taskObject.get("time");
        String type = (String) taskObject.get("type");
        type = type.toUpperCase();
        Task newTask = new Task("");

        if (type.equals(CommandList.TODO.toString())) {
            newTask = new Todo(value);
        } else if (type.equals(CommandList.DEADLINE.toString())) {
            newTask = new Deadline(value, time);
        } else if (type.equals(CommandList.EVENT.toString())) {
            newTask = new Event(value, time);
        }

        if (done) {
            newTask.markDone();
        }
        return newTask;
    }

    @SuppressWarnings("unchecked")
    public void saveData() {
        String dirPath = System.getProperty("user.dir") + "\\src\\main\\data\\dukeData.json";
        File savedData = new File(dirPath);

        //Add employees to list
        JSONArray jsonTaskList = new JSONArray();


        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            JSONObject TaskDetails = new JSONObject();
            String type = currTask.getClass().getName().toUpperCase();
            TaskDetails.put("type", type);
            TaskDetails.put("value", currTask.getValue());
            TaskDetails.put("time", currTask.getTime());
            TaskDetails.put("done", currTask.getDone());

            JSONObject taskObj = new JSONObject();
            taskObj.put("task", TaskDetails);
            jsonTaskList.add(taskObj);
        }

        try {
            savedData.delete();
            savedData.createNewFile();
        } catch (Exception e) {
            System.out.println("An error occurred");
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(dirPath)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(jsonTaskList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean executeCommand(String input, boolean printStatement, boolean done) throws IncompleteCommandException {
        boolean run = true;
        if (input.toUpperCase().equals(CommandList.BYE.toString())) {
            System.out.println("Bye. Hope to see you again soon!");
            saveData();
            run = false;
        } else if (input.toUpperCase().equals(CommandList.LIST.toString())) {
            listAll();
        } else if (input.toUpperCase().contains(CommandList.DELETE.toString())) {
            if (input.length() > 7) {
                String[] stringArr = input.split(" ");
                if (isNumeric(stringArr[1])) {
                    int taskId = Integer.parseInt(stringArr[1]) - 1;
                    Task taskToBeDeleted = taskList.get(taskId);
                    removeTaskById(taskId);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("\t" + taskToBeDeleted.toString());
                    System.out.println("Now you have " + getTotalNumberOfTask() + " task in the list.");

                }
            }
        } else if (isDone(input)) {
            String[] stringArr = input.split(" ");
            if (stringArr.length > 1) {
                String taskNumber = stringArr[1];
                if (isNumeric(taskNumber)) {
                    int taskNum = Integer.parseInt(taskNumber);
                    if (taskNum > 0 && (taskNum - 1) < getTotalNumberOfTask()) {
                        markTaskDoneByIndex(taskNum);
                    } else {
                        System.out.println("Task number is not in the list!");
                    }

                } else {
                    System.out.println("Please enter a valid task number!");
                }

            } else {
                throw new IncompleteCommandException("Please enter the task number after done! E.g \"done 2\"");
            }
        } else {
            Task newTask = null;
            if (input.toUpperCase().contains(CommandList.TODO.toString())) {
                if (input.length() > 5) {
                    String taskMessage = input.substring(5);
                    newTask = new Todo(taskMessage.strip());
                } else {
                    throw new IncompleteCommandException("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (input.toUpperCase().contains(CommandList.DEADLINE.toString())) {
                if (input.length() > 8) {
                    if (input.contains("/by")) {
                        String[] stringArr = input.substring(9).split("/by");
                        newTask = new Deadline(stringArr[0], stringArr[1].strip());
                    } else {
                        System.out.println("Your deadline is missing a /by (date)");
                    }
                } else {
                    throw new IncompleteCommandException("OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (input.toUpperCase().contains(CommandList.EVENT.toString())) {
                if (input.length() > 5) {
                    if (input.contains("/at")) {
                        String[] stringArr = input.substring(6).split("/at");
                        newTask = new Event(stringArr[0], stringArr[1].strip());
                    } else {
                        System.out.println("Your Event is missing a /at (date)");
                    }
                } else {
                    throw new IncompleteCommandException("OOPS!!! The description of a event cannot be empty.");
                }
            }
            if (newTask != null) {
                if (done) {
                    newTask.markDone();
                }
                addTask(newTask);
                if (printStatement) {
                    System.out.println("Got it. I've added this task.");
                    System.out.println(newTask);
                    System.out.println("Now you have " + getTotalNumberOfTask() + " tasks in the list.");
                }

            } else {
                System.out.println("Invalid command!");
                System.out.println("Try TODO, DEADLINE or EVENT follow by task description.");
            }

        }
        return run;
    }

    /**
     * Check if a given input has done command.
     *
     * @param input a string that is the input of the user.
     * @return a boolean if done command is found.
     */
    public boolean isDone(String input) {
        if (input.length() >= 4) {
            return input.toUpperCase().startsWith(CommandList.DONE.toString());
        } else {
            return false;
        }
    }

    /**
     * Check if input string is numeric or not.
     *
     * @param input a string input from user.
     * @return a boolean if input is numeric.
     */
    public boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void removeTaskById(int id) {
        taskList.remove(id);
    }

}
