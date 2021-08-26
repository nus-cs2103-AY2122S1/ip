import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String pathName = "data";
        String fileName = "tasks.txt";

        try {
            File dataDirectory = Storage.initialiseDirectory(pathName);
            File dataFile = Storage.initialiseFile(dataDirectory, fileName);

            List<Task> tasksList = new ArrayList<Task>();

            Print.printProgramStartMessage();

            Scanner sc = new Scanner(System.in);
            String commandLine = "";

            readTasksFromFile(dataFile, tasksList);

            do {
                System.out.println("ENTER COMMAND:");
                System.out.print("\t");
                commandLine = sc.nextLine().trim();
                String[] commandLineParts = commandLine.split("\\s+", 2);

                Command command;

                if (commandLineParts.length == 2) {
                    command = new Command(commandLineParts[0], commandLineParts[1]);
                } else {
                    command = new Command(commandLineParts[0], "");
                }

                try {
                    switch (command.getType()) {
                    case "bye":
                        Print.printResponse(ResponseMessage.exitMessage());
                        break;
                    case "list":
                        displayTasksList(tasksList);
                        break;
                    case "done":
                        markDone(command, tasksList);
                        saveTasksToFile(dataFile, tasksList);
                        break;
                    case "delete":
                        deleteTask(command, tasksList);
                        saveTasksToFile(dataFile, tasksList);
                        break;
                    case "todo":
                        addToDoTask(command, tasksList);
                        saveTasksToFile(dataFile, tasksList);
                        break;
                    case "deadline":
                        addDeadlineTask(command, tasksList);
                        saveTasksToFile(dataFile, tasksList);
                        break;
                    case "event":
                        addEventTask(command, tasksList);
                        saveTasksToFile(dataFile, tasksList);
                        break;
                    default:
                        throw new DukeException("Invalid command. Please try again!");
                    }
                } catch (DukeException e) {
                    Print.printErrorMessage(e.getMessage());
                }
            } while (!commandLine.equals("bye"));
        } catch (DukeException | IOException e) {
            Print.printErrorMessage(e.getMessage());
        }
    }

    static void readTasksFromFile(File dataFile, List<Task> tasksList) {
        try {
            FileReader fr = new FileReader(dataFile);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] task = line.trim().split("\\|");
                String taskType = task[0].trim();
                boolean isTaskDone = Boolean.parseBoolean(task[1].trim());
                String taskDescription = task[2].trim();
                String taskDateTime = "";

                switch (taskType) {
                case "T":
                    Task todoTask = readToDoTask(isTaskDone, taskDescription);
                    tasksList.add(todoTask);
                    break;
                case "D":
                    taskDateTime = task[3].trim();
                    Task deadlineTask = readDeadlineTask(isTaskDone, taskDescription, taskDateTime);
                    tasksList.add(deadlineTask);
                    break;
                case "E":
                    taskDateTime = task[3].trim();
                    Task eventTask = readEventTask(isTaskDone, taskDescription, taskDateTime);
                    tasksList.add(eventTask);
                    break;
                }
            }

            fr.close();
        } catch (IOException e) {
            Print.printErrorMessage(e.getMessage());
        }
    }

    static Task readToDoTask(boolean isDone, String description) {
        return new ToDo(TaskType.TODO, description, isDone);
    }

    static Task readDeadlineTask(boolean isDone, String description, String dateTime) {
        return new Deadline(TaskType.DEADLINE, description, dateTime, isDone);
    }

    static Task readEventTask(boolean isDone, String description, String dateTime) {
        return new Event(TaskType.EVENT, description, dateTime, isDone);
    }

    static void saveTasksToFile(File dataFile, List<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(dataFile,false);
            String taskDetails = "";

            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);

                TaskType type = task.getType();
                boolean isDone = task.isDone();
                String description = task.getDescription();
                String dateTime;

                if (type == TaskType.TODO) {
                    dateTime = "";
                } else {
                    dateTime = ((TaskWithDateTime) task).getDateTime();
                }

                taskDetails = taskDetailsSaveFormat(type, isDone, description, dateTime);
                fileWriter.write(taskDetails + System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            Print.printErrorMessage(e.getMessage());
        }
    }

    static String taskDetailsSaveFormat(TaskType type, boolean isDone, String description, String dateTime) {
        if (dateTime.equals("")) {
            return type.getAbbr() + " | " + (isDone ? "1" : "0") + " | " + description;
        } else {
            return type.getAbbr() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + dateTime;
        }
    }

    static void displayTasksList(List<Task> tasksList) throws DukeException {
        if (tasksList.size() != 0) {
            String response = ResponseMessage.tasksInYourListMessage(tasksList);
            Print.printResponse(response);
        } else {
            throw new DukeException("There are no tasks in your list.");
        }
    }

    static void markDone(Command command, List<Task> tasksList) throws DukeException {
        String commandDetails = command.getDetails();

        if (commandDetails.trim().length() > 0) {
            try {
                int taskNum = Integer.parseInt(commandDetails);

                if (taskNum >= 1 && taskNum <= tasksList.size()) {
                    tasksList.get(taskNum - 1).setDone();
                    Task taskDone = tasksList.get(taskNum - 1);

                    String response = ResponseMessage.taskDoneMessage(taskDone);
                    Print.printResponse(response);
                } else {
                    throw new DukeException("Please enter a valid task number to be marked as done.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to be marked as done.");
            }
        } else {
            throw new DukeException("Please enter a task number to be marked as done.");
        }
    }

    static void deleteTask(Command command, List<Task> tasksList) throws DukeException {
        String commandDetails = command.getDetails();

        if (commandDetails.trim().length() > 0) {
            try {
                int taskNum = Integer.parseInt(commandDetails);

                if (taskNum >= 1 && taskNum <= tasksList.size()) {
                    Task taskToDelete = tasksList.get(taskNum - 1);
                    tasksList.remove(taskToDelete);

                    String response = ResponseMessage.taskDeletedMessage(taskToDelete)
                            + System.lineSeparator() + ResponseMessage.numOfTasksInList(tasksList);
                    Print.printResponse(response);
                } else {
                    throw new DukeException("Please enter a valid task number to be deleted.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to be deleted.");
            }
        } else {
            throw new DukeException("Please enter a task number to be deleted.");
        }
    }

    static void addToDoTask(Command command, List<Task> tasksList) throws DukeException {
        String commandDetails = command.getDetails();

        if (commandDetails.trim().length() > 0) {
            Task newToDoTask = new ToDo(TaskType.TODO, commandDetails);
            tasksList.add(newToDoTask);

            String response = ResponseMessage.taskAddedMessage(newToDoTask)
                    + System.lineSeparator() + ResponseMessage.numOfTasksInList(tasksList);
            Print.printResponse(response);
        } else {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    static void addDeadlineTask(Command command, List<Task> tasksList) throws DukeException {
        String commandDetails = command.getDetails();

        if (commandDetails.trim().length() > 0) {
            String[] deadlineTaskDetails = commandDetails.split("/", 2);

            if (deadlineTaskDetails.length == 2) {
                if (deadlineTaskDetails[0].trim().length() > 0) {
                    if (deadlineTaskDetails[1].trim().startsWith("by")) {
                        String description = deadlineTaskDetails[0].trim();
                        String beforeDateTime = deadlineTaskDetails[1].trim();
                        String[] beforeDateTimeParts = beforeDateTime.split("\\s+", 2);

                        if (beforeDateTimeParts.length == 2) {
                            Task newDeadlineTask = new Deadline(TaskType.DEADLINE,
                                    description, beforeDateTimeParts[1]);
                            tasksList.add(newDeadlineTask);

                            String response = ResponseMessage.taskAddedMessage(newDeadlineTask)
                                    + System.lineSeparator() + ResponseMessage.numOfTasksInList(tasksList);
                            Print.printResponse(response);
                        } else {
                            throw new DukeException("The date/time of a deadline cannot be empty."
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /by before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("The date/time of a deadline is not valid."
                                + System.lineSeparator() + "\t"
                                + "[Note: Enter /by before specifying the date/time]");
                    }
                } else {
                    if (deadlineTaskDetails[1].trim().startsWith("by")) {
                        String beforeDateTime = deadlineTaskDetails[1].trim();
                        String[] beforeDateTimeParts = beforeDateTime.split("\\s+", 2);

                        if (beforeDateTimeParts.length == 2) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else {
                            throw new DukeException("The description of a deadline cannot be empty."
                                    + System.lineSeparator() + "\t"
                                    + "☹ The date/time of a deadline cannot be empty."
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /by before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("The description of a deadline cannot be empty."
                                + System.lineSeparator() + "\t"
                                + "☹ The date/time of a deadline is not valid."
                                + System.lineSeparator() + "\t"
                                + "[Note: Enter /by before specifying the date/time]");
                    }
                }
            } else {
                throw new DukeException("The date/time of a deadline cannot be empty."
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /by before specifying the date/time]");
            }
        } else {
            throw new DukeException("The description and date/time of a deadline cannot be empty.");
        }
    }

    static void addEventTask(Command command, List<Task> tasksList) throws DukeException {
        String commandDetails = command.getDetails();

        if (commandDetails.trim().length() > 0) {
            String[] eventTaskDetails = commandDetails.split("/", 2);

            if (eventTaskDetails.length == 2) {
                if (eventTaskDetails[0].trim().length() > 0) {
                    if (eventTaskDetails[1].trim().startsWith("at")) {
                        String description = eventTaskDetails[0].trim();
                        String startEndDateTime = eventTaskDetails[1].trim();
                        String[] startEndDateTimeParts = startEndDateTime.split("\\s+", 2);

                        if (startEndDateTimeParts.length == 2) {
                            Task newEventTask = new Event(TaskType.EVENT,
                                    description, startEndDateTimeParts[1]);
                            tasksList.add(newEventTask);

                            String response = ResponseMessage.taskAddedMessage(newEventTask)
                                    + System.lineSeparator() + ResponseMessage.numOfTasksInList(tasksList);
                            Print.printResponse(response);
                        } else {
                            throw new DukeException("The date/time of an event cannot be empty."
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /at before specifying the date/time.]");
                        }
                    } else {
                        throw new DukeException("The date/time of an event is not valid."
                                + System.lineSeparator() + "\t"
                                + "[Note: Enter /at before specifying the date/time]");
                    }
                } else {
                    if (eventTaskDetails[1].trim().startsWith("at")) {
                        String startEndDateTime = eventTaskDetails[1].trim();
                        String[] startEndDateTimeParts = startEndDateTime.split("\\s+", 2);

                        if (startEndDateTimeParts.length == 2) {
                            throw new DukeException("The description of an event cannot be empty.");
                        } else {
                            throw new DukeException("The description of an event cannot be empty."
                                    + System.lineSeparator() + "\t"
                                    + "☹ The date/time of an event cannot be empty."
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /at before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("The description of an event cannot be empty."
                                + System.lineSeparator() + "\t"
                                + "☹ The date/time of an event is not valid."
                                + System.lineSeparator() + "\t"
                                + "[Note: Enter /at before specifying the date/time]");
                    }
                }
            } else {
                throw new DukeException("The date/time of an event cannot be empty."
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /at before specifying the date/time]");
            }
        } else {
            throw new DukeException("The description and date/time of an event cannot be empty.");
        }
    }
}
