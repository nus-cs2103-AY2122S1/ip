import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /**
     * Takes in a line from the task file saved in disk and process it
     * @param taskLine A line from the file that is being read from.
     *
     */
    private static void process(String taskLine, ArrayList<Task> taskList) throws DukeException {
        String[] parsedLine = taskLine.split(" \\| ", 3);
        String command = parsedLine[0];
        Boolean isDone = parsedLine[1].equals("1");
        String next = parsedLine[2];
        if (command.equals("T")) {
            Todo todo = new Todo(next, isDone);
            taskList.add(todo);
        } else if (command.equals("D")) {
            String[] details = next.split(" \\| ", 2);
            String desc = details[0];
            LocalDate dueDate = LocalDate.parse(details[1]);
            Deadline dl = new Deadline(desc, isDone, dueDate);
            taskList.add(dl);
        } else if (command.equals("E")) {
            String[] details = next.split(" \\| ", 2);
            String desc = details[0];
            String time = details[1];
            Event e = new Event(desc, isDone, time);
            taskList.add(e);
        } else {
            throw new DukeException();
        }
    }

    private static void writeLineToFile(String line, File taskFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile));
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    public static void main(String[] args) {
        // Initial values
        String sepLine = "____________________________________________________________";
        boolean isRunning = true;

        Scanner sc = new Scanner(System.in);

        String start = "Hello! I'm Duke. \n"
                + "What can I do for you? \n"
                + sepLine;

        ArrayList<Task> taskList = new ArrayList<>();

        // Gets the task file and reads the lines
        String localDir = System.getProperty("user.dir");
        File taskFile = new File(localDir + File.separator + "data/tasks.txt");
        if (!taskFile.exists()) {
            taskFile.getParentFile().mkdirs();
            try {
                taskFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create new file");
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(taskFile))) {
            String line;
            while ((line = br.readLine()) != null && !line.equals("")) {
                Duke.process(line, taskList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception occurred");
        } catch (DukeException e) {
            System.out.println("Invalid command found in file");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid file format");
        }

        System.out.println(start);

        // Main loop for commands
        while (isRunning) {
            // split the input string into two parts:
            // first part is the command (list, to-do, deadline, etc.)
            // second part is any extra information based on what command was given
            String[] next = sc.nextLine().split(" ", 2);
            String command = next[0];
            if (command.equals("bye")) {
                System.out.println(sepLine + "\n" + "Bye. Hope to see you again soon!" + "\n" + sepLine);
                isRunning = false;
            } else if (command.equals("list")) {
                System.out.println(sepLine);
                System.out.println("These are your tasks! \n");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print(i + 1 + ". " + taskList.get(i) + "\n");
                }
                System.out.println(sepLine);
            } else if (command.equals("done")) {
                System.out.println(sepLine);
                try {
                    // Mark a task as done
                    // We assume the command is of the form "done xxx" where xxx is an integer
                    Integer taskNum = Integer.parseInt(next[1]);
                    Task taskToComplete = taskList.get(taskNum - 1);
                    taskToComplete.markAsDone();
                    System.out.println("The task has been marked as done!");
                    System.out.println(taskToComplete);
                    System.out.println(sepLine);
                    StringBuilder textString = new StringBuilder(100);
                    for (Task t : taskList) {
                        textString.append(t.saveText());
                    }
                    Duke.writeLineToFile(textString.toString(), taskFile);
                } catch (NumberFormatException e) {
                    System.out.println("It seems like you have entered an invalid number for done.");
                    System.out.println("Please enter the task number as shown in the list.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("That task number does not exist in your list of tasks.");
                } catch (IOException e) {
                    System.out.println("Failed to write to file");
                }
            } else if (command.equals("delete")) {
                System.out.println(sepLine);
                try {
                    Integer taskNum = Integer.parseInt(next[1]);
                    Task taskToDelete = taskList.get(taskNum - 1);
                    System.out.println("The task has been deleted!");
                    System.out.println(taskToDelete);
                    taskList.remove(taskNum - 1);
                    System.out.println(sepLine);
                    StringBuilder textString = new StringBuilder(100);
                    for (Task t : taskList) {
                        textString.append(t.saveText());
                    }
                    Duke.writeLineToFile(textString.toString(), taskFile);
                } catch (NumberFormatException e) {
                    System.out.println("It seems like you have entered an invalid number to delete.");
                    System.out.println("Please enter the task number as shown in the list.");
                    System.out.println(sepLine);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("That task number does not exist in your list of tasks.");
                    System.out.println(sepLine);
                } catch (IOException e) {
                    System.out.println("Failed to write to file.");
                }
            } else if (command.equals("help")) {
                String helpMessage = sepLine + "\n HELP \n" + sepLine + "\n"
                        + "Available commands: \n"
                        + "'help' - Opens this dialog. \n"
                        + "'list' - Opens your list of tasks. \n"
                        + "'todo (desc)' - Adds a todo item with the given description. \n"
                        + "'deadline (desc) /by (due date)' - Adds a deadline item to your task list "
                        + "with the given description and due date. \n"
                        + "'event (desc) /at (timing)' - Adds a event item to your task list "
                        + "with the given description and timing. \n"
                        + "'done (x)' - Marks the task with number x as done "
                        + "according to the list given by the command 'list' \n"
                        + "'delete (x)' - Deletes the task with number x "
                        + "according to the list given by the command 'list' \n"
                        + "'bye' - Quits this program. \n"
                        + sepLine + "\n"
                        + "To use any command, follow the structure as shown, entering your values \n"
                        + "in place of anything in brackets. \n"
                        + sepLine;
                System.out.println(helpMessage);
            } else {
                // Task is added to task list
                try {
                    if (command.equals("todo")) {
                        Todo todo = new Todo(next[1], false);
                        taskList.add(todo);
                        System.out.println(sepLine + "\n added: " + todo + "\n");
                        System.out.println("You now have " + taskList.size() + " tasks");
                        System.out.println(sepLine);
                    } else if (command.equals("deadline")) {
                        // Add a deadline to the task list
                        String[] text = next[1].split(" /by ");
                        try {
                            String desc = text[0];
                            LocalDate dueDate = LocalDate.parse(text[1]);
                            Deadline dl = new Deadline(desc, false, dueDate);
                            taskList.add(dl);
                            System.out.println(sepLine + "\n added: " + dl + "\n");
                            System.out.println("You now have " + taskList.size() + " tasks");
                            System.out.println(sepLine);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new MissingFieldException();
                        } catch (DateTimeParseException e) {
                            System.out.println("Failed to parse date given");
                        }
                    } else if (command.equals("event")) {
                        // Add an event to the task list
                        String[] text = next[1].split(" /at ");
                        try {
                            String desc = text[0];
                            String time = text[1];
                            Event event = new Event(desc, false, time);
                            taskList.add(event);
                            System.out.println(sepLine + "\n added: " + event + "\n");
                            System.out.println("You now have " + taskList.size() + " tasks");
                            System.out.println(sepLine);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new MissingFieldException();
                        }
                    } else {
                        System.out.println(sepLine + "\n I did not understand that command."
                                + " Type 'help' for more info \n" + sepLine);
                    }
                    try {
                        StringBuilder textString = new StringBuilder(100);
                        for (Task t : taskList) {
                            textString.append(t.saveText());
                        }
                        Duke.writeLineToFile(textString.toString(), taskFile);
                    } catch (IOException e) {
                        throw new IOException();
                    }
                } catch (MissingFieldException e) {
                    System.out.println("Please fill in a timing for your deadline / event.");
                    System.out.println("Use '/by' for deadlines and '/at' for events.");
                    System.out.println(sepLine);
                } catch (EmptyDescException e) {
                    System.out.println("Please fill in a description for your task.");
                    System.out.println(sepLine);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("It seems like your command was not formatted properly.");
                    System.out.println(sepLine);
                } catch (IOException e) {
                    System.out.println("Failed to save to file.");
                }
            }
        }
        sc.close();
    }
}
