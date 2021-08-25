import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> userText = new ArrayList<>();
        String DONE_REGEX = "done [0-9]+";
        String DELETE_REGEX = "delete [0-9]+";
        String TODO_REGEX = "todo [\\w\\s-]+";
        String DEADLINE_REGEX = "deadline [\\w\\s-]+ \\/by [\\w\\s-]+";
        String EVENT_REGEX = "event [\\w\\s-]+ \\/at [\\w\\s-]+";
        Scanner scan = new Scanner(System.in);
        printResponse("Hello! I'm Duke", "What can I do for you?");

        // Get data
        String home = System.getProperty("user.home");
        Path dataPath = Paths.get(home, "dukedata.txt");
        if (!Files.exists(dataPath)) {
            try {
                Files.createFile(dataPath);
            }
            catch (IOException e) {
                printResponse(e.getMessage());
            }
        } else {
            try {
                Stream<String> lines = Files.lines(dataPath);
                lines.forEach(x -> userText.add(cleanData(x)));
            }
            catch (IOException e) {
                printResponse(e.getMessage());
            }
        }

        // Program loop
        while (true) {
            String command = scan.nextLine();

            try {
                if (command.equals("list")) {
                    String[] printArr = new String[userText.size() + 1];
                    printArr[0] = "Here are the tasks in your list:";
                    for (int i = 0; i < userText.size(); i++) {
                        int tempNum = i + 1;
                        printArr[tempNum] = tempNum + ". " + userText.get(i);
                    }
                    printResponse(printArr);
                } else if (command.equals("bye")) {
                    printResponse("Bye. Hope to see you again soon!");
                    break;
                } else if (Pattern.matches(DONE_REGEX, command)) {
                    String indexStr = command.substring(5);
                    int index = Integer.parseInt(indexStr) - 1;
                    userText.get(index).markAsDone();
                    printResponse("Nice! I've marked this task as done:", "  " + userText.get(index).toString());
                    rewriteData(userText, dataPath);
                } else if (Pattern.matches(DELETE_REGEX, command)) {
                    String indexStr = command.substring(7);
                    int index = Integer.parseInt(indexStr) - 1;
                    String removedTask = userText.get(index).toString();
                    userText.remove(index);
                    String numTasksLeft = "Now you have " + userText.size() + " tasks in the list.";
                    printResponse("Noted. I've removed this task:", "  " + removedTask, numTasksLeft);
                    rewriteData(userText, dataPath);
                } else if (command.equals("todo")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                } else if (Pattern.matches(TODO_REGEX, command)) {
                    String name = command.substring(5);
                    userText.add(new ToDo(name));
                    String numTasksLeft = "Now you have " + userText.size() + " tasks in the list.";
                    printResponse("Got it. I've added this task:", "  " + userText.get(userText.size()-1).toString(), numTasksLeft);
                    rewriteData(userText, dataPath);
                } else if (Pattern.matches(DEADLINE_REGEX, command)) {
                    int breakPos = command.indexOf("/by");
                    String name = command.substring(9, breakPos - 1);
                    String due = command.substring(breakPos + 4);
                    userText.add(new Deadline(name, due));
                    String numTasksLeft = "Now you have " + userText.size() + " tasks in the list.";
                    printResponse("Got it. I've added this task:", "  " + userText.get(userText.size()-1).toString(), numTasksLeft);
                    rewriteData(userText, dataPath);
                } else if (Pattern.matches(EVENT_REGEX, command)) {
                    int breakPos = command.indexOf("/at");
                    String name = command.substring(6, breakPos - 1);
                    String time = command.substring(breakPos + 4);
                    userText.add(new Event(name, time));
                    String numTasksLeft = "Now you have " + userText.size() + " tasks in the list.";
                    printResponse("Got it. I've added this task:", "  " + userText.get(userText.size()-1).toString(), numTasksLeft);
                    rewriteData(userText, dataPath);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means ):");
                }
            }
            catch (DukeException e) {
                printResponse(e.getMessage());
            }
        }
    }

    private static void printResponse(String ... lines) {
        String LINE = "    --------------------------------------------------";
        String INDENTATION = "      ";
        System.out.println(LINE);
        for (String line : lines) {
            System.out.println(INDENTATION + line);
        }
        System.out.println(LINE);
    }

    private static void rewriteData(ArrayList<Task> tasks, Path dataPath) { 
        String dataStr = "";
        for (int i = 0; i < tasks.size() - 1; i++) {
            Task data = tasks.get(i);
            dataStr += data.toSaveFormat() + "\n";
        }
        dataStr += tasks.get(tasks.size() - 1).toSaveFormat();
            try {
                Files.writeString(dataPath, dataStr, StandardCharsets.ISO_8859_1);
            }
            catch (IOException e) {
                printResponse(e.getMessage());
            }
        
    }

    private static Task cleanData(String data) {
        String[] dataParts = data.split("[|]");
        Task tempTask = new ToDo("temp");
        if (dataParts.length == 3) {
            tempTask = new ToDo(dataParts[2]);
        } else if (dataParts[0].equals("D")) {
            tempTask = new Deadline(dataParts[2], dataParts[3]);
        } else if (dataParts[0].equals("E")) {
            tempTask = new Event(dataParts[2], dataParts[3]);
        }

        //TODO: error handling

        if (dataParts[1].equals("1")) {
            tempTask.markAsDone();
        }

        return tempTask;
    }
}
