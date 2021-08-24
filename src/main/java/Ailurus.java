import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main task chatbot class for Ailurus Chatbot
 *
 * @author Leeroy Liu
 */
public class Ailurus {
    private static final String CHATBOT = "Ailurus";
    private static final String YOU = "You";
    private static ArrayList<Task> list = new ArrayList<>();
    private static final String PATH = "./data/";

    /**
     * Customized display for chatbot messages
     *
     * @param message display message to be printed
     */
    private static void say(String message) {
        System.out.println(String.format("%s: %s", Ailurus.CHATBOT, message));
    }

    /**
     * Printing out of the list of tasks
     */
    private static void sayList() {
        if (list.size() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYLIST);
        }
        Ailurus.say("");
        for(int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, list.get(i).toString()));
        }
    }

    /**
     * Marking a specific task as done
     *
     * @param str String to be converted to integer, representing task number to be marked as done
     */
    private static void done(String str) {
        try {
            int taskNo = Integer.parseInt(str);
            if (taskNo > list.size() || taskNo < 1) {
                throw new AilurusException(AilurusException.Error.NUMBER);
            } else {
                Task task = list.get(taskNo - 1);
                task.markAsDone();
                Ailurus.say(String.format("Nice! I've marked this task as done:\n\t%s", task));
            }
        } catch (NumberFormatException e) {
            throw new AilurusException(AilurusException.Error.NAN);
        }
    }

    /**
     * Adding task to list of tasks
     *
     * @param task Task that has been added
     */
    private static void addTask(Task task) {
        list.add(task);
        Ailurus.say(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                task.toString(), list.size()));
    }

    /**
     * Deleting task from a list of tasks
     *
     * @param str String to be converted to integer, representing task number to be deleted
     */
    private static void delete(String str) {
        try {
            int taskNo = Integer.parseInt(str);
            if (taskNo > list.size() || taskNo < 1) {
                throw new AilurusException(AilurusException.Error.NUMBER);
            } else {
                Task task = list.get(taskNo - 1);
                list.remove(taskNo - 1);
                Ailurus.say(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.",
                        task, list.size()));
            }
        } catch (NumberFormatException e) {
            throw new AilurusException(AilurusException.Error.NAN);
        }
    }

    /**
     * Parse message and return string after command
     *
     * @param message message to be parsed
     */
    public static String parseMessage(String message) {
        int index = message.indexOf(" ");
        if (index > 0) {
            return message.substring(index + 1);
        } else {
            return "";
        }
    }

    /**
     * Parse data stored in hard disk
     *
     * @param data data to be parsed
     */
    public static Task parseData(String data) {
        String[] dataArr = data.split("[|]");
        switch (dataArr[0]) {
            case "T":
                if (dataArr.length < 3) {
                    return null;
                }
                Task todo = new Todo(dataArr[2]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                if (dataArr.length < 4) {
                    return null;
                }
                Task deadline = new Deadline(dataArr[2] + "/by " + dataArr[3]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                if (dataArr.length < 4) {
                    return null;
                }
                Task event = new Event(dataArr[2] + "/at " + dataArr[3]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        File directory = new File(PATH);
        File ailurusObj = new File(PATH.concat("ailurus.txt"));
        FileWriter writer = null;

        if (!directory.exists()) {
            // create new directory
            directory.mkdir();
        }

        if (!ailurusObj.exists()) {
            // create new file
            try {
                ailurusObj.createNewFile();
                writer = new FileWriter(PATH.concat("ailurus.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // load tasks from file
            try {
                Scanner reader = new Scanner(ailurusObj);
                while (reader.hasNextLine()) {
                    Task task = parseData(reader.nextLine());
                    if (task != null) {
                        list.add(task);
                    }
                }
                writer = new FileWriter(PATH.concat("ailurus.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boolean endChat = false;
        Scanner scanner = new Scanner(System.in);

        Ailurus.say(String.format("Hello! I'm %s. What can I do for you?", Ailurus.CHATBOT));
        while (!endChat) {
            System.out.print(Ailurus.YOU + ": ");
            String input = scanner.nextLine();
            String first = input.split(" ")[0];
            switch (first) {
                case "bye":
                    endChat = true;
                    break;
                case "list":
                    try {
                        Ailurus.sayList();
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                case "done":
                    try {
                        String str = parseMessage(input);
                        Ailurus.done(str);
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                case "todo":
                    try {
                        String todoMessage = parseMessage(input);
                        Ailurus.addTask(new Todo(todoMessage));
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                case "deadline":
                    try {
                        String deadlineMessage = parseMessage(input);
                        Ailurus.addTask(new Deadline(deadlineMessage));
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                case "event":
                    try {
                        String eventMessage = parseMessage(input);
                        Ailurus.addTask(new Event(eventMessage));
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                case "delete":
                    try {
                        String str = parseMessage(input);
                        Ailurus.delete(str);
                    } catch (AilurusException e) {
                        Ailurus.say(e.toString());
                    }
                    break;
                default:
                    Ailurus.say("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        // write all tasks to file
        if (writer != null) {
            try {
                for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        task.log(writer);
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Ailurus.say("Bye. Hope to see you again soon!");
    }
}
