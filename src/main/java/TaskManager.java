import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class TaskManager {
    Task[] taskList = new Task[100];
    int i = 0;

    TaskManager() {

    }

    public void executeCommand(String inData) {
        int inDataLength = inData.length();
        if (!Objects.equals(inData.toLowerCase(), "bye")) {
            if (Objects.equals(inData, "list")) {
                listAll();
            } else if (inData.contains("todo")) {
                try {
                    inData.substring(0, 6);
                    if (Objects.equals(inData.substring(0, 5), "todo ")) {
                        taskList[i] = new ToDo(inData.substring(5, inDataLength));
                        toDoAddedMessage();
                        i++;
                    }
                } catch (Exception e) {
                    errorEmptyMessage("todo");
                }
            } else if (inData.contains("deadline ")) {
                if (Objects.equals(inData.substring(0, 9), "deadline ")) {
                    String[] segments = inData.split("/by ");
                    String date = segments[segments.length - 1];

                    int segmentedLength = segments[0].length();
                    String description = segments[0].substring(9, segmentedLength);
                    taskList[i] = new Deadline(description, date);
                    deadlineAddedMessage();
                    i++;
                }
            } else if (inData.contains("event ")) {
                if (Objects.equals(inData.substring(0, 6), "event ")) {
                    String[] segments = inData.split("/at ");
                    String date = segments[segments.length - 1];

                    int segmentedLength = segments[0].length();
                    String description = segments[0].substring(6, segmentedLength);
                    taskList[i] = new Event(description, date);
                    eventAddedMessage();
                    i++;
                }
            } else if (inData.contains("done ")) {
                if (isNumeric(inData.substring(5, inDataLength))) {
                    int taskNo = Integer.parseInt(inData.substring(5, inDataLength));
                    if (taskNo <= 100 && taskNo <= i) {
                        taskList[taskNo - 1].markAsDone();
                        doneTaskMessage(taskNo);
                    } else {
                        errorInvalidTaskNo();
                    }
                }
            } else if (inData.contains("delete ")) {
                if (Objects.equals(inData.substring(0, 7), "delete ")) {
                    if (isNumeric(inData.substring(7, inDataLength))) {
                        int taskNo = Integer.parseInt(inData.substring(7, inDataLength));
                        if (taskNo <= 100 && taskNo <= i) {
                            deletedTaskMessage(taskNo);
                            taskList[taskNo - 1] = null;
                            if (taskNo < i + 1) {
                                for (int n = taskNo - 1; n < i; n++) {
                                    taskList[n] = taskList[n + 1];
                                }
                            }
                            i--;
                        }
                    }
                }
            } else {
                errorUnknownCommandMessage();
            }
        } else {
            byeMessage();
        }

    }

    public void listAll() {
        System.out.println("____________________________________________________________\n" +
                "Here are the tasks in your list: ");
        for (int j = 0; j < i; j++) {
            if (taskList[j] instanceof ToDo) {
                System.out.println(j + 1 + ". [" + taskList[j].getTask() + "]" +
                        "[" + taskList[j].getStatusIcon() + "] " + taskList[j].getDescription());
            } else {
                System.out.println(j + 1 + ". [" + taskList[j].getTask() + "]" +
                        "[" + taskList[j].getStatusIcon() + "] " + taskList[j].getDescription()
                        + taskList[j].getDate());
            }
        }
        System.out.println("____________________________________________________________");
    }

    public void toDoAddedMessage() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "Got it. I've added this task: \n"
                        + "[" + taskList[i].getTask() + "]"
                        + "[" + taskList[i].getStatusIcon() + "] "
                        + taskList[i].getDescription()
                        + "\n"
                        + "Now you have " + (i + 1) + " tasks in the list.\n"
                        + "____________________________________________________________");
    }

    public void deadlineAddedMessage() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "Got it. I've added this task: \n"
                        + "[" + taskList[i].getTask() + "]"
                        + "[" + taskList[i].getStatusIcon() + "] "
                        + taskList[i].getDescription()
                        + "\n"
                        + "Now you have " + (i + 1) + " tasks in the list.\n"
                        + "____________________________________________________________");
    }

    public void eventAddedMessage() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "Got it. I've added this task: \n"
                        + "[" + taskList[i].getTask() + "]"
                        + "[" + taskList[i].getStatusIcon() + "] "
                        + taskList[i].getDescription()
                        + "\n"
                        + "Now you have " + (i + 1) + " tasks in the list.\n"
                        + "____________________________________________________________");
    }

    public void doneTaskMessage(int taskNo) {
        System.out.println(
                "____________________________________________________________\n"
                        + "Nice! I've marked this task as done: \n"
                        + " [" + taskList[taskNo - 1].getStatusIcon() + "] "
                        + taskList[taskNo - 1].getDescription()
                        + "\n____________________________________________________________");
    }

    public void deletedTaskMessage(int taskNo) {
        System.out.println(
                "____________________________________________________________\n"
                        + "Noted. I've removed this task: \n"
                        + " [" + taskList[taskNo - 1].getStatusIcon() + "] "
                        + taskList[taskNo - 1].getDescription()
                        + "\n"
                        + "Now you have " + (i - 1) + " tasks in the list.\n"
                        + "____________________________________________________________");
    }

    public void errorUnknownCommandMessage() {
        System.out.println("____________________________________________________________\n" +
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "____________________________________________________________");
    }

    public void byeMessage() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "Bye. Hope to see you again soon! \n"
                        + "____________________________________________________________");
    }

    public void errorEmptyMessage(String task) {
        System.out.println("____________________________________________________________\n" +
                "☹ OOPS!!! The description of a " + task + " cannot be empty.\n" +
                "____________________________________________________________");
    }

    public void errorInvalidTaskNo() {
        System.out.println(
                "____________________________________________________________ \n"
                        + "invalid number!"
                        + "\n____________________________________________________________");
    }

    public static boolean isNumeric(String string) {
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

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
