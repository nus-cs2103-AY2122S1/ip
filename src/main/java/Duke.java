import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        try {
            File savedFile = new File("data/duke.txt");
            Scanner loadSavedFile = new Scanner(savedFile);
            System.out.println("    ____________________________________________________________\n"
                    + "    Hello! I'm Duke\n" + "    What can I do for you?\n" +
                    "    ____________________________________________________________\n");
            FileLoader load = new FileLoader(loadSavedFile);
            TaskList taskList = load.load();

            boolean end;
            end = true;
            while (end) {
                String text = input.nextLine();
                String[] splitText = text.trim().split("\\s+", 2);
                System.out.println("    ____________________________________________________________\n");
                switch (splitText[0]) {
                case "list":
                    taskList.listOut();
                    break;
                case "bye":
                    end = false;
                    System.out.println("    Bye. Hope to see you again soon!");
                    String saveFileString = taskList.save();
                    PrintWriter saveFile = new PrintWriter("data/duke.txt");
                    saveFile.println(saveFileString);
                    saveFile.close();
                    break;
                case "done":
                    try {
                        int index = Integer.parseInt(splitText[1]);
                        taskList.finishTask(index);
                    } catch (Exception e) {
                        System.out.println("    ☹ OOPS!!! The value you inputted is not valid!");
                    }
                    break;
                case "todo":
                    try {
                        ToDo item = new ToDo(splitText[1]);
                        taskList.add(item, true);
                    } catch (Exception e) {
                        System.out.println("    ☹ OOPS!!!  The description of a todo cannot be empty.");
                    }
                    break;
                case "deadline":
                    try {
                        String[] furtherSplitDeadline = splitText[1].trim().split("/by");
                        Deadline deadline = new Deadline(furtherSplitDeadline[0], furtherSplitDeadline[1]);
                        taskList.add(deadline, true);
                    } catch (Exception e) {
                        System.out.println("    ☹ OOPS!!!  Use /by to add a deadline!");
                    }
                    break;
                case "event":
                    try {
                        String[] furtherSplitEvent = splitText[1].trim().split("/at");
                        Event event = new Event(furtherSplitEvent[0], furtherSplitEvent[1]);
                        taskList.add(event, true);
                    } catch (Exception e) {
                        System.out.println("    ☹ OOPS!!!  Use /at to add a timing for the event!");
                    }
                    break;
                case "delete":
                    try {
                        int index = Integer.parseInt(splitText[1]);
                        taskList.deleteTask(index);
                    } catch (Exception e) {
                        System.out.println("    ☹ OOPS!!!  Did you input an invalid index?");
                    }
                    break;
                default:
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                System.out.println("    ____________________________________________________________\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Please create an empty file in /ip/data named duke.txt to get started");
        }
    }
}
