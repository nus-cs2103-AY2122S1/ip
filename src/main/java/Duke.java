import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    /**
     * Sends a message to the user in the specified format
     * @param message The message to be sent to the user
     */
    protected static void sendMessage(String message) {
        System.out.println("    ____________________________________________________________\n    " +
                message.replace("\n", "\n    ") +
                "\n    ____________________________________________________________");
    }

    protected static void writeTasksToFile(String pathName, List<Task> taskData) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathName));
        if (!taskData.isEmpty()) {
            writer.write(taskData.get(0).getFormattedData() + "\n");
        }

        for(int i = 1; i < taskData.size(); ++i) {
            writer.append(taskData.get(i).getFormattedData() + "\n");
        }

        writer.close();
    }

    protected static List<Task> loadTaskData(File taskFile) throws FileNotFoundException {
        Scanner sc = new Scanner(taskFile);
        List<Task> taskData = new ArrayList<>();
        while(sc.hasNextLine()) {
            String task = sc.nextLine();

            String[] taskDetails = task.split("\\|");
            boolean taskDone = Objects.equals(taskDetails[1], "1");

            switch(taskDetails[0]) {
                case "T":
                    taskData.add(new Todo(taskDetails[2], taskDone));
                    break;
                case "D":
                    taskData.add(new Deadline(taskDetails[2], taskDetails[3], taskDone));
                    break;
                case "E":
                    taskData.add(new Event(taskDetails[2], taskDetails[3], taskDone));
                    break;
            }
        }

        sc.close();
        return taskData;
    }

    public static void main(String[] args) {
//        final String pathDirectory = "data";
        final String fileName = "tasks.txt";
        final String pathName = fileName;
        final String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";

        try (Scanner sc = new Scanner(System.in)) {
//            File directory = new File(pathDirectory);
//            if(!directory.mkdirs()) {
//                sendMessage("Cannot create");
//            }

            File taskFile = new File(pathName);
            List<Task> taskData = new ArrayList<>();

            // If already exists, do nothing, leave taskData empty. Else, load the data
            if (!taskFile.createNewFile()) {
                taskData = loadTaskData(taskFile);
            }

            sendMessage(welcomeMessage);

            String userInput;
            boolean isTaskListUpdated = false;
            while (true) {
                try {
                    if (isTaskListUpdated) {
                        // update file
                        writeTasksToFile(pathName, taskData);
                        isTaskListUpdated = false;
                    }

                    userInput = sc.nextLine().trim();

                    if (userInput.equals("bye")) {
                        // EXIT command
                        sendMessage("Bye. Hope to see you again soon!");
                        break;
                    } else if (userInput.equals("list")) {
                        // LIST command
                        // Construct the string containing the list of items that have been stored in preparation to send to user
                        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list:");

                        // Add all elements in the list
                        for (int i = 0; i < taskData.size(); ++i) {
                            listMessage.append("\n").append(i + 1).append(". ").append(taskData.get(i));
                        }

                        sendMessage(listMessage.toString());
                    } else if (userInput.startsWith("done")) {
                        int index = Integer.parseInt(userInput.substring(5)) - 1;
                        taskData.get(index).markAsDone();
                        isTaskListUpdated = true;

                        sendMessage("Nice! I've marked this task as done:\n  " + taskData.get(index));
                    } else if (userInput.startsWith("delete")) {
                        int index = Integer.parseInt(userInput.substring(7)) - 1;
                        Task removedTask = taskData.remove(index);
                        isTaskListUpdated = true;

                        sendMessage("Noted. I've removed this task:\n  " + removedTask +
                                "\nNow you have " + taskData.size() + " tasks in the list.");
                    } else if (userInput.startsWith("todo")) {
                        // If no arguments provided
                        if (userInput.length() == 4) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        } else if (!userInput.startsWith("todo ")) {
                            // If it does not start with "todo " after trimming, it is an invalid word
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }

                        Todo newTodo = new Todo(userInput.substring(5).trim());
                        taskData.add(newTodo);
                        isTaskListUpdated = true;

                        sendMessage("Got it. I've added this task:\n  " + newTodo + "\nNow you have " + taskData.size() + " tasks in the list.");
                    } else if (userInput.startsWith("deadline")) {
                        // If no arguments provided
                        if (userInput.length() == 8) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else if (!userInput.startsWith("deadline ")) {
                            // If it does not start with "deadline " after trimming, it is an invalid word
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        } else if (!userInput.contains("/by ")) {
                            throw new DukeException(("A deadline must contain a deadline indicated after the /by command"));
                        }

                        String description = userInput.substring(9, userInput.indexOf("/by")).trim();
                        String deadlineDate = userInput.substring(userInput.indexOf("/by")).trim(); // Inclusive of the /by command
                        if (description.length() == 0) {
                            throw new DukeException("The description of a deadline cannot be empty");
                        }

                        Deadline newDeadline = new Deadline(description, deadlineDate.substring(4));
                        taskData.add(newDeadline);
                        isTaskListUpdated = true;

                        sendMessage("Got it. I've added this task:\n  " + newDeadline + "\nNow you have " + taskData.size() + " tasks in the list.");
                    } else if (userInput.startsWith("event")) {
                        // If no arguments provided
                        if (userInput.length() == 5) {
                            throw new DukeException("The description of a event cannot be empty.");
                        } else if (!userInput.startsWith("event ")) {
                            // If it does not start with "deadline " after trimming, it is an invalid word
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        } else if (!userInput.contains("/at ")) {
                            throw new DukeException(("An event must contain a datetime indicated after the /at command"));
                        }

                        String description = userInput.substring(5, userInput.indexOf("/at")).trim();
                        String datetime = userInput.substring(userInput.indexOf("/at")).trim(); // Inclusive of the /by command
                        if (description.length() == 0) {
                            throw new DukeException("The description of an event cannot be empty");
                        }

                        Event newEvent = new Event(description, datetime.substring(4));
                        taskData.add(newEvent);
                        isTaskListUpdated = true;

                        sendMessage("Got it. I've added this task:\n  " + newEvent + "\nNow you have " + taskData.size() + " tasks in the list.");
                    } else {
                        // Unknown command
                        throw new DukeException(("I'm sorry, but I don't know what that means :-("));
                    }
                } catch (DukeException e) {
                    sendMessage(e.getMessage());
                }
            }
        } catch (IOException e) {
            sendMessage(e.getMessage());
        } catch (Exception e) {
            sendMessage("An unexpected exception has occurred" + e.getMessage());
            e.printStackTrace();
        }
    }
}
