import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Parser {
    public Parser() {
    }

    public String parse(String inData, TaskList taskList, Storage storage) {
        assert inData != null : "assertion error";
        int inDataLength = inData.length();
        if (!Objects.equals(inData.toLowerCase(), "bye")) {
            if (Objects.equals(inData, "list")) {
                return taskList.listAll();
            } else if (Objects.equals(inData, "help")) {
                return helpMessage();
            } else if (inData.contains("todo")) {
                try {
                    inData.substring(0, 6);
                    if (Objects.equals(inData.substring(0, 5), "todo ")) {
                        taskList.todoCommand(inData.substring(5, inDataLength));
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
                        taskList.deadlineCommand(description, date);
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
                        taskList.eventCommand(description, date);
                    } catch (Exception e) {
                        return "Please enter date in this format: yyyy-mm-dd (e.g., 2019-10-15)";
                    }
                }
            } else if (inData.contains("done ")) {
                if (isNumeric(inData.substring(5, inDataLength))) {
                    int taskNo = Integer.parseInt(inData.substring(5, inDataLength));
                    if (taskNo <= 100 && taskNo <= taskList.getCount()) {
                        taskList.doneCommand(taskNo);
                    } else {
                        return errorInvalidTaskNo();
                    }
                }
            } else if (inData.contains("delete ")) {
                if (Objects.equals(inData.substring(0, 7), "delete ")) {
                    if (isNumeric(inData.substring(7, inDataLength))) {
                        int taskNo = Integer.parseInt(inData.substring(7, inDataLength));
                        if (taskNo <= 100 && taskNo <= taskList.getCount()) {
                            taskList.deleteCommand(taskNo);
                        }
                    }
                }
            } else {
                return errorUnknownCommandMessage();
            }
        } else {
            storage.writeData(taskList);
            return byeMessage();
        }
        return "";
    }


    private String errorUnknownCommandMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    private String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    private String errorEmptyMessage(String task) {
        return "OOPS!!! The description of a " + task + " cannot be empty.";
    }

    private String errorInvalidTaskNo() {
        return "invalid number!";
    }

    private String helpMessage() {
        return "Please enter commands in this format: \n"
                + "todo (task) \n"
                + "deadline (task) /by (yyyy-mm-dd) \n"
                + "event (task) /by (yyyy-mm-dd)";
    }

    private static boolean isNumeric(String string) {
        int intValue;

        if (string == null || string.equals("")) {
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
}
