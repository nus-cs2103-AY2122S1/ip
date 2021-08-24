import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    int numOfTasks;
    ArrayList<Task> tasks;

    Duke() {
        this.numOfTasks = 0;
        this.tasks = new ArrayList<Task>();
    }

    public boolean createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created.");
                return true;
            } else {
                System.out.println("File fetched.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return false;
        }
    }

    public void readFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                parseLineInFile(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    //format of string should be typeOfTask||status||description||time
    public void parseLineInFile(String string) {
        try {
            if (string.length() < 7) {
                throw new DukeException("Cannot read file.");
            }
            char type = string.charAt(0);
            char status = string.charAt(3);
            boolean isDone;
            if (status == ' ') {
                isDone = false;
            } else if (status == 'X') {
                isDone = true;
            } else {
                throw new DukeException("Cannot read file.");
            }
            String description = string.substring(6);
            if (type == 'T') {
                tasks.add(new ToDo(description, isDone));
                numOfTasks = numOfTasks + 1;
            } else if (type == 'E') {
                int index = description.indexOf("||");
                String time = description.substring(index + 2);
                tasks.add(new Event(description.substring(0, index), isDone, time));
                numOfTasks = numOfTasks + 1;
            } else if (type == 'D') {
                int index = description.indexOf("||");
                String time = description.substring(index + 2);
                tasks.add(new Deadline(description.substring(0, index), isDone, time));
                numOfTasks = numOfTasks + 1;
            } else {
                throw new DukeException("Cannot read file.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(String filePath, ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String line = task.saveTaskToFile() + "\n";
                if (i == tasks.size() - 1) {
                    line = task.saveTaskToFile();
                }
                fileWriter.write(line);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot update file.");
        }
    }

    public void list() {
        if (this.numOfTasks == 0) {
            System.out.println("List is empty!");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.numOfTasks; i++) {
            Task temp = this.tasks.get(i);
            System.out.printf("%s. %s\n", i + 1, temp);
        }
        System.out.println();
    }

    public void markAsDone(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index > numOfTasks - 1) {
                if (numOfTasks > 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There are only %s tasks in your list.", taskNumber, numOfTasks));
                } else if (numOfTasks == 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is only %s task in your list.", taskNumber, numOfTasks));
                } else {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is no task in your list.", taskNumber));
                }
            } else if (index < 0) {
                throw new DukeException(String.format("There is no task %s.", taskNumber));
            }
            this.tasks.get(index).markAsDone();
            System.out.println();
        } catch (NumberFormatException ex) {
            System.out.println("Task must be an integer!");
            System.out.println();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public void addTask(String task) {
        try {
            if (task.startsWith("todo")) {
                if (task.substring(4).equals("") || task.substring(4).equals(" ")) {
                    throw new DukeException("The description of todo cannot be empty");
                }
                this.tasks.add(new ToDo(task.substring(5)));
            } else if (task.startsWith("event")) {
                int index = task.indexOf("/at ");
                if (index == -1) {
                    throw new DukeException("You must specify the time for an event.");
                }
                String description = task.substring(6, index - 1);
                String time = task.substring(index + 4);
                this.tasks.add(new Event(description, time));
            } else if (task.startsWith("deadline")) {
                int index = task.indexOf("/by ");
                if (index == -1) {
                    throw new DukeException("You must specify the deadline.");
                }
                String description = task.substring(9, index - 1);
                String time = task.substring(index + 4);
                this.tasks.add(new Deadline(description, time));
            } else {
                throw new DukeException("Sorry I don't understand what that means:(");
            }
            System.out.println("Got it! I have added this task:");
            System.out.println(this.tasks.get(this.numOfTasks));
            this.numOfTasks = this.numOfTasks + 1;
            if (this.numOfTasks > 1) {
                System.out.printf("Now you have %s tasks in your list.\n", this.numOfTasks);
            } else if (this.numOfTasks == 1) {
                System.out.printf("Now you have 1 task in your list.\n");
            } else {
                System.out.println("Your list is empty!");
            }
            System.out.println();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public void deleteTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index > numOfTasks - 1) {
                if (numOfTasks > 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There are only %s tasks in your list.", taskNumber, numOfTasks));
                } else if (numOfTasks == 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is only 1 task in your list.", taskNumber));
                } else {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is no task in your list.", taskNumber));
                }
            } else if (index < 0) {
                throw new DukeException(String.format("There is no task %s.", taskNumber));
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(this.tasks.get(index));
            this.tasks.remove(index);
            this.numOfTasks = this.numOfTasks - 1;
            if (this.numOfTasks > 1) {
                System.out.printf("You have %s tasks left on your list.\n", this.numOfTasks);
            } else {
                System.out.printf("You have %s task left on your list.\n", this.numOfTasks);
            }
            System.out.println();
        } catch (NumberFormatException ex) {
            System.out.println("Task must be an integer!");
            System.out.println();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello I am Duke.\nWhat can I do for you?");
        System.out.println();
        duke.createFile("file.text");
        duke.readFile("file.text");
        if (duke.numOfTasks > 0) {
            System.out.println("Current number of tasks: " + duke.numOfTasks);
            duke.list();
            System.out.println();
        }
        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine();
        while (true) {
            if (echo.equals("bye")) {
                System.out.println("Bye! See you next time!");
                duke.writeToFile("file.text",duke.tasks);
                break;
            }
            if (echo.equals("List")) {
                duke.list();
                echo = scanner.nextLine();
                continue;
            }
            if (echo.startsWith("done")) {
                String[] parsed = echo.split(" ");
                if (parsed.length > 1) {
                    duke.markAsDone(parsed[1]);
                } else {
                    duke.addTask(echo);
                }
                echo = scanner.nextLine();
                continue;
            }
            if (echo.equals("")) {
                System.out.println("Please enter a new task or action.");
                echo = scanner.nextLine();
                continue;
            }
            if (echo.startsWith("delete")) {
                String[] parsed = echo.split(" ");
                if (parsed.length > 1) {
                    duke.deleteTask(parsed[1]);
                } else {
                    duke.addTask(echo);
                }
                echo = scanner.nextLine();
                continue;
            }
            duke.addTask(echo);
            echo = scanner.nextLine();
        }

    }
}
