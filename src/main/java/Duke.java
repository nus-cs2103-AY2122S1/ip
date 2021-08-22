import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  // Import the Scanner class

public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke(String pathname, String dir) throws IOException {
        this.storage = new Storage(pathname, dir);
        this.tasks = new TaskList(storage.load());
    }

    public void run() throws IOException {
        Scanner input = new Scanner(System.in);
        String line = "-------------------------------------";
        System.out.println(line + "\n" + "Good Morning Master Wayne, Alfred here.\nWhat can I do for you today?\n" + line);

        String commandLine = input.nextLine();
        boolean done = false;

        while (!done) {
            String[] split = commandLine.split(" ");
            String command = split[0];
            String desc;
            FileWriter fw = new FileWriter("data/tasks.txt", true);

            switch (command) {
            case "bye":
                System.out.println("Have a pleasant day, Master Wayne.\n");
                done = true;
                break;

            case "list":
                System.out.println(line);
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + ". " + " " + task.toString());
                }
                System.out.println(line);

                commandLine = input.nextLine();
                break;

            case "done":
                try {
                    checkLength(split.length);
                    int doneIndex = Integer.parseInt(split[1]);
                    checkIndex(doneIndex, tasks.size());

                    System.out.println(line);
                    tasks.get(doneIndex - 1).markAsDone();
                    System.out.println("Very well, Master Wayne. This task has been marked as per your request.");
                    System.out.println((doneIndex) + ". " + tasks.get(doneIndex - 1)); //actual index is index - 1
                    System.out.println(line);

                    List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of("data/tasks.txt"), StandardCharsets.UTF_8));

                    String oldLine = fileContent.get(doneIndex - 1);
                    StringBuilder newLine = new StringBuilder(oldLine);
                    newLine.setCharAt(4, '1');

                    fileContent.set(doneIndex - 1, newLine.toString());
                    Files.write(Path.of("data/tasks.txt"), fileContent, StandardCharsets.UTF_8);

                } catch (DukeException e) {
                    System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
                }

                commandLine = input.nextLine();
                break;

            case "delete":
                try {
                    checkLength(split.length);
                    int deleteIndex = Integer.parseInt(split[1]);
                    checkIndex(deleteIndex, tasks.size());

                    System.out.println(line);
                    System.out.println("Very well, Master Wayne. This task has been deleted as per your request.");
                    System.out.println((deleteIndex) + ". " + tasks.get(deleteIndex - 1)); //actual index is index - 1

                    tasks.remove(deleteIndex - 1);
                    if (tasks.size() == 1) {
                        System.out.println("Now you have 1 task in the list.");
                    } else {
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }

                    System.out.println(line);

                    List<String> content = new ArrayList<>(Files.readAllLines(Path.of("data/tasks.txt"), StandardCharsets.UTF_8));

                    content.remove(deleteIndex - 1);
                    Files.write(Path.of("data/tasks.txt"), content, StandardCharsets.UTF_8);

                } catch (DukeException e) {
                    System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
                }

                commandLine = input.nextLine();
                break;

            case "todo":
                StringBuilder todoBuilder = new StringBuilder();
                for (int i = 1; i < split.length; i++) {
                    if (i != 1) {
                        todoBuilder.append(" ");
                    }
                    todoBuilder.append(split[i]);
                }
                desc = todoBuilder.toString();
                try {
                    checkDesc(desc);
                    Todo todo = new Todo(desc, false);
                    tasks.add(todo);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);

                    if (tasks.size() == 1) {
                        System.out.println("Now you have 1 task in the list.");
                    } else {
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }

                    fw.write("T | 0 | " + desc + "\n");
                    fw.close();

                } catch (DukeException e) {
                    System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
                }

                commandLine = input.nextLine();
                break;

            case "deadline":
                StringBuilder deadlineBuilder = new StringBuilder();
                StringBuilder byBuilder = new StringBuilder();
                String by;
                boolean byFound = false;

                for (int i = 1; i < split.length; i++) {
                    if (byFound) {
                        if (!byBuilder.toString().equals("")) {
                            byBuilder.append(" ");
                        }
                        byBuilder.append(split[i]);
                    } else {
                        if (i == 1) {
                            deadlineBuilder.append(split[i]);
                        } else if (split[i].equals("/by")) {
                            byFound = true;
                        } else {
                            deadlineBuilder.append(" ");
                            deadlineBuilder.append(split[i]);
                        }
                    }
                }
                desc = deadlineBuilder.toString();
                by = byBuilder.toString();
                try {
                    checkDesc(desc);
                    Deadline deadline = new Deadline(desc, by, false);
                    tasks.add(deadline);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    if (tasks.size() == 1) {
                        System.out.println("Now you have 1 task in the list.");
                    } else {
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }

                    fw.write("D | 0 | " + desc + " | " + by + "\n");
                    fw.close();

                } catch (DukeException e) {
                    System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
                }

                commandLine = input.nextLine();
                break;

            case "event":
                StringBuilder eventBuilder = new StringBuilder();
                StringBuilder atBuilder = new StringBuilder();
                String at;
                boolean atFound = false;

                for (int i = 1; i < split.length; i++) {
                    if (atFound) {
                        if (!atBuilder.toString().equals("")) {
                            atBuilder.append(" ");
                        }
                        atBuilder.append(split[i]);
                    } else {
                        if (i == 1) {
                            eventBuilder.append(split[i]);
                        } else if (split[i].equals("/at")) {
                            atFound = true;
                        } else {
                            eventBuilder.append(" ");
                            eventBuilder.append(split[i]);
                        }
                    }
                }
                desc = eventBuilder.toString();
                at = atBuilder.toString();
                try {
                    checkDesc(desc);
                    Event event = new Event(desc, at, false);
                    tasks.add(event);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);

                    fw.write("E | 0 | " + desc + " | " + at + "\n");
                    fw.close();

                    if (tasks.size() == 1) {
                        System.out.println("Now you have 1 task in the list.");
                    } else {
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                } catch (DukeException e) {
                    System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
                }

                commandLine = input.nextLine();
                break;

            default:
                System.out.println("*** Apologies, Master Wayne. But I don't know what that means ***");
                commandLine = input.nextLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt", "data").run();
    }


    public static void checkDesc(String test) throws DukeException {
        if (test.equals("")) {
            throw new DukeException("The description is empty");
        }
    }

    public static void checkLength(int l) throws DukeException {
        if (l == 1) {
            throw new DukeException("Please give an index number");
        }
    }

    public static void checkIndex(int i, int lengthOfList) throws DukeException {
        if (i <= 0) {
            throw new DukeException("Please give an index number > 0");
        } else if (i > lengthOfList) {
            throw new DukeException("Maximum index number is " + lengthOfList);
        }
    }
}