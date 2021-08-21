package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInstructionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> taskList = new ArrayList<>();

    private static final String filePath = "taskList.txt";

    private static void saveTasks() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.get(i).toString() + System.lineSeparator());
        }
        fw.close();
    }

    private static void getFileContents() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String task = s.next();
            char type = task.charAt(1);
            boolean status = task.length() != 4;
            if (task.length() == 4) {
                s.next();
            }
            String first = s.next();
            String second = null;
            switch (type) {
            case 'T':
                taskList.add(new ToDo(first));
                break;
            case 'D':
                s.next();
                second = s.next();
                taskList.add(new Deadline(first, second.substring(0, second.length() - 1)));
                break;
            case 'E':
                s.next();
                second = s.next();
                taskList.add(new Event(first, second.substring(0, second.length() - 1)));
                break;
            }
            if (status) {
                taskList.get(taskList.size() - 1).complete();
            }
            s.nextLine();
        }
    }

    private static String createTask(String task, String description) throws InvalidInputException, IOException {

        if (description.length() == 0) {
            throw new InvalidInputException(task + " task needs a description.");
        }

        String center = null;
        switch (task) {
        case "todo":
            center = "";
            break;
        case "deadline":
            center = "/by";
            break;
        case "event":
            center = "/at";
            break;
        }

        assert center != null;
        int centerIndex = description.indexOf(center);
        if (centerIndex == -1) {
            throw new InvalidInputException("To create a " + task + " task: enter \"" + task + " (task details) "
                    + center + " (task details)\"");
        }
        String first = null;
        String second = null;
        if (!task.equals("todo")) {
            first = description.substring(0, centerIndex).trim();
            second = description.substring(centerIndex).trim();
            if (first.length() == 0 || second.length() <= center.length()) {
                throw new InvalidInputException(task + " task must have details before and after " + center + ".");
            }
            second = second.substring(center.length()).trim();
        }

        switch (task) {
        case "todo":
            taskList.add(new ToDo(description));
            break;
        case "deadline":
            taskList.add(new Deadline(first, second));
            break;
        case "event":
            taskList.add(new Event(first, second));
            break;
        }

        String taskDetails = taskList.get(taskList.size() - 1).toString();
        return "Got it. I've added this task:\n" +
                "\t\t" + taskDetails + "\n" +
                "\t" + "Now you have " + taskList.size() + " tasks in the list.";
    }

    public static void main(String[] args) throws IOException {

        try {
            getFileContents();
        } catch(FileNotFoundException e) {
            new File(filePath);
        }

        String line = "------------------------------------------------------------------------------" +
                        "-------------------------------\n\n";
        String savedTasks = "";
        if (!taskList.isEmpty()) {
            savedTasks += "\tSaved tasks:\n";
            for (int i = 0; i < taskList.size(); i++) {
                savedTasks += "\t\t" + (i + 1) + ". " + taskList.get(i).toString();
                if (i != taskList.size() - 1) savedTasks += '\n';
            }
            savedTasks += "\n\n";
        }
        // greeting
        System.out.print(
                line
                + "\tHello! I'm Duke\n\n"
                + savedTasks
                + "\tWhat can I do for you?\n\n"
                + line
        );

        Scanner scanner = new Scanner(System.in);
        try {

            String task;
            String text;
            boolean leave = false;
            while (!leave) {
                // get task
                task = scanner.next();
                // get text
                text = scanner.nextLine().trim();

                try {
                    switch (task) {
                        case "bye":
                            text = "Bye. Hope to see you again soon!";
                            leave = true;
                            saveTasks();
                            break;
                        case "list":
                            if (taskList.isEmpty()) text = "There are no items in the list.";
                            else {
                                text = "";
                                for (int i = 0; i < taskList.size(); i++) {
                                    if (i != 0) text += "\t";
                                    text += (i + 1) + ". " + taskList.get(i).toString();
                                    if (i != taskList.size() - 1) text += "\n";
                                }
                            }
                            break;
                        case "done":
                            try {
                                int index = Integer.parseInt(text);
                                taskList.get(index - 1).complete();
                                text = "Nice! I've marked this task as done:\n" +
                                        "\t\t" + taskList.get(index - 1).toString();
                            } catch (NumberFormatException e) {
                                throw new InvalidInputException("To complete a task: enter \"done (task number)\"");
                            } catch (IndexOutOfBoundsException e) {
                                throw new InvalidInputException("Task number does not exist.");
                            }
                            break;
                        case "delete":
                            try {
                                int index = Integer.parseInt(text);
                                text = "Noted. I've removed this task: \n" +
                                        "\t\t" + taskList.get(index - 1).toString() + "\n" +
                                        "\tNow you have " + (taskList.size() - 1) + " tasks in the list.";
                                taskList.remove(index - 1);
                            } catch (NumberFormatException e) {
                                throw new InvalidInputException("To delete a task: enter \"delete (task number)\"");
                            } catch (IndexOutOfBoundsException e) {
                                throw new InvalidInputException("Task number does not exist.");
                            }
                            break;
                        case "todo": case "deadline": case "event":
                            text = createTask(task, text);
                            break;
                        default:
                            throw new InvalidInstructionException(task);
                    }
                } catch (InvalidInputException e) {
                    text = e.toString();
                } catch (InvalidInstructionException e) {
                    text = e.toString();
                } catch (IOException e) {
                    text = "" + e;
                }

                // print text
                System.out.print(
                        "\n" + line +
                        "\t" + text + "\n" +
                        "\n" + line
                );
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
            try {
                saveTasks();
            } catch (IOException ie) {
                System.out.println(ie);
            }
            System.out.println(e);
        }
    }
}
