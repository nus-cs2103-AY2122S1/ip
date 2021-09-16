import java.io.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

/**
 * Class that handles commands
 */
public class TaskManager {
    Task[] taskList = new Task[100];
    int i = 0;
    File txtFile;
    String path;

    /**
     * Constructor for a TaskManager instance
     */
    TaskManager() {
        File txtFile;
        String path = System.getProperty("user.dir") + "\\src\\main\\data\\duke.txt";
        txtFile = new File(path);
        this.txtFile = txtFile;
        this.path = path;

        if (!txtFile.exists()) {
            //create new file
            try {
                txtFile.createNewFile();
                this.txtFile = txtFile;
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(txtFile));
                String txtLine;
                txtLine = br.readLine();
                while(txtLine != null) {
                    String[] segments = txtLine.split("\\|");
                    executeCommand(segments[2]);
                    if (Objects.equals(segments[0], "1")) {
                        String input = "done " + segments[1];
                        executeCommand(input);
                    }
                    txtLine = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("ERROR OCCURRED");
            }
        }
    }

    public String executeCommand(String inData) {
        int inDataLength = inData.length();
        if (!Objects.equals(inData.toLowerCase(), "bye")) {
            if (Objects.equals(inData, "list")) {
                return listAll();
            } else if (inData.contains("todo")) {
                try {
                    inData.substring(0, 6);
                    if (Objects.equals(inData.substring(0, 5), "todo ")) {
                        taskList[i] = new ToDo(inData.substring(5, inDataLength));
                        i++;
                        return toDoAddedMessage();
                    }
                } catch (Exception e) {
                    return errorEmptyMessage("todo");
                }
            } else if (inData.contains("deadline ")) {
                if (Objects.equals(inData.substring(0, 9), "deadline ")) {
                    String[] segments = inData.split("/by ");
                    String date = segments[segments.length - 1];

                    int segmentedLength = segments[0].length();
                    String description = segments[0].substring(9, segmentedLength);
                    try {
                        taskList[i] = new Deadline(description, date);
                        i++;
                        return deadlineAddedMessage();
                    } catch (Exception e) {
                        return "Please enter date in this format: yyyy-mm-dd (e.g., 2019-10-15)";
                    }
                }
            } else if (inData.contains("event ")) {
                if (Objects.equals(inData.substring(0, 6), "event ")) {
                    String[] segments = inData.split("/at ");
                    String date = segments[segments.length - 1];

                    int segmentedLength = segments[0].length();
                    String description = segments[0].substring(6, segmentedLength);
                    try {
                        taskList[i] = new Event(description, date);
                        i++;
                        return eventAddedMessage();
                    } catch (Exception e) {
                        return "Please enter date in this format: yyyy-mm-dd (e.g., 2019-10-15)";
                    }
                }
            } else if (inData.contains("done ")) {
                if (isNumeric(inData.substring(5, inDataLength))) {
                    int taskNo = Integer.parseInt(inData.substring(5, inDataLength));
                    if (taskNo <= 100 && taskNo <= i) {
                        taskList[taskNo - 1].markAsDone();
                        return doneTaskMessage(taskNo);
                    } else {
                        return errorInvalidTaskNo();
                    }
                }
            } else if (inData.contains("delete ")) {
                if (Objects.equals(inData.substring(0, 7), "delete ")) {
                    if (isNumeric(inData.substring(7, inDataLength))) {
                        int taskNo = Integer.parseInt(inData.substring(7, inDataLength));
                        if (taskNo <= 100 && taskNo <= i) {
                            String type = taskList[taskNo - 1].getTask();
                            String status = taskList[taskNo - 1].getStatusIcon();
                            String task = taskList[taskNo - 1].getDescription();
                            taskList[taskNo - 1] = null;
                            if (taskNo < i + 1) {
                                for (int n = taskNo - 1; n < i; n++) {
                                    taskList[n] = taskList[n + 1];
                                }
                            }
                            i--;
                            return deletedTaskMessage(type, status, task, taskNo);
                        }
                    }
                }
            } else {
                return errorUnknownCommandMessage();
            }
        } else {
            try {
                for (int k = 0; k < i; k++) {
                    String done;
                    if (taskList[k].getStatusIcon() == "X") {
                        done = "1|";
                    } else {
                        done = "0|";
                    }
                    if (taskList[k].getTask() == "T") {
                        String command = done + (k + 1) + "|todo " + taskList[k].getDescription();
                        if (k == 0) {
                            writeToFile(path, command);
                        } else {
                            appendToFile(path, command);
                        }
                    } else if (taskList[k].getTask() == "D") {
                        String command = done + (k + 1)+ "|deadline " + taskList[k].getDescription() + "/by " + taskList[k].getDateNum();
                        if (k == 0) {
                            writeToFile(path, command);
                        } else {
                            appendToFile(path, command);
                        }
                    } else if (taskList[k].getTask() == "E") {
                        String command = done + (k + 1)+ "|event " + taskList[k].getDescription() + "/at " + taskList[k].getDateNum();
                        if (k == 0) {
                            writeToFile(path, command);
                        } else {
                            appendToFile(path, command);
                        }
                    }
                }
            } catch (Exception e) {
                return "Unable to write to file";
            }
            return byeMessage();
        }
        return "";
    }

    private String listAll() {
        String output = "";
        for (int j = 0; j < i; j++) {
            if (taskList[j] instanceof ToDo) {
                output += j + 1 + ". [" + taskList[j].getTask() + "]" +
                        "[" + taskList[j].getStatusIcon() + "] " + taskList[j].getDescription() + "\n";
            } else {
               output += j + 1 + ". [" + taskList[j].getTask() + "]" +
                        "[" + taskList[j].getStatusIcon() + "] " + taskList[j].getDescription()
                        + taskList[j].getDate() + "\n";
            }
        }
        return "Here are the tasks in your list: \n" + output;
    }

    private String toDoAddedMessage() {
        String output = "Got it. I've added this task: \n"
                        + "[" + taskList[i - 1].getTask() + "]"
                        + "[" + taskList[i - 1].getStatusIcon() + "] "
                        + taskList[i - 1].getDescription()
                        + "\n"
                        + "Now you have " + (i) + " tasks in the list.";
        return output;
    }

    private String deadlineAddedMessage() {
        String output = "Got it. I've added this task: \n"
                        + "[" + taskList[i - 1].getTask() + "]"
                        + "[" + taskList[i - 1].getStatusIcon() + "] "
                        + taskList[i - 1].getDescription()
                        + "\n"
                        + "Now you have " + (i) + " tasks in the list.";
        return output;
    }

    private String eventAddedMessage() {
        String output = "Got it. I've added this task: \n"
                        + "[" + taskList[i - 1].getTask() + "]"
                        + "[" + taskList[i - 1].getStatusIcon() + "] "
                        + taskList[i - 1].getDescription()
                        + "\n"
                        + "Now you have " + (i) + " tasks in the list.";
        return output;
    }

    private String doneTaskMessage(int taskNo) {
        String output = "Nice! I've marked this task as done: \n"
                        + " [" + taskList[taskNo - 1].getStatusIcon() + "] "
                        + taskList[taskNo - 1].getDescription();
        return output;
    }

    private String deletedTaskMessage(String type, String status, String task, int taskNo) {
        String output = "Noted. I've removed this task: \n"
                        + " [" + type + "] "
                        + "[" + status + "] "
                        + task
                        + "\n"
                        + "Now you have " + (i) + " tasks in the list.";
        return output;
    }

    private String errorUnknownCommandMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    private String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    private String errorEmptyMessage(String task) {
        return "☹ OOPS!!! The description of a " + task + " cannot be empty.";
    }

    private String errorInvalidTaskNo() {
        return "invalid number!";
    }

    private static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }
}
