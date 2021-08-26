import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static void handleCommands(String commandInput, List<Task> taskList) throws DukeException {
        if (commandInput.equals("list")) {
            System.out.println("Current List:");
            System.out.println("---------------");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println( (i+1) + ": " + taskList.get(i));
            }

        } else if (commandInput.matches("done\\s[0-9][0-9]?")) {
            int taskToComplete = Integer.valueOf(commandInput.split(" ")[1]);
            if (taskToComplete - 1 >= 0 && taskToComplete - 1 < taskList.size()) {
                taskList.get(taskToComplete - 1).markAsCompleted();
                System.out.println("I have marked the task as done!");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println( (i+1) + ": " + taskList.get(i));
                }
                try {
                    writeTaskFile(taskList);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new InvalidTaskIDException();
            }
        } else if (commandInput.matches("delete\\s[0-9][0-9]?")) {
            int taskToComplete = Integer.valueOf(commandInput.split(" ")[1]);
            if (taskToComplete - 1 >= 0 && taskToComplete - 1 <taskList.size()) {
                taskList.remove(taskToComplete - 1);
                System.out.println("I have deleted the task!");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println( (i+1) + ": " + taskList.get(i));
                }

                try {
                    writeTaskFile(taskList);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                throw new InvalidTaskIDException();
            }

        } else if (commandInput.matches("todo(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            } else {
                String taskname = commandInput.split(" ", 2)[1];
                ToDo todo = new ToDo(taskname);
                taskList.add(todo);
                System.out.println("A ToDo has been added\n");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println( (i+1) + ": " + taskList.get(i));
                }

                try {
                    writeTaskFile(taskList);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } else if (commandInput.matches("deadline(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            } else {
                String firstCommand = commandInput.split("/by", 2)[0];
                String taskname = firstCommand.split(" ", 2)[1];
                String dueDate = commandInput.split("/by", 2)[1];
                Deadline deadline = new Deadline(taskname.trim(), dueDate.trim());
                taskList.add(deadline);
                System.out.println("A Deadline has been added\n");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println( (i+1) + ": " + taskList.get(i));
                }

                try {
                    writeTaskFile(taskList);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } else if (commandInput.matches("event(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            } else {
                String firstCommand = commandInput.split("/at", 2)[0];
                String taskname = firstCommand.split(" ", 2)[1];
                String duration = commandInput.split("/at", 2)[1];
                Event event = new Event(taskname.trim(), duration.trim());
                taskList.add(event);
                System.out.println("An Event has been added\n");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ": " + taskList.get(i));
                }

                try {
                    writeTaskFile(taskList);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else
            throw new InvalidCommandException();
    }

    public static void writeTaskFile(List<Task> taskList) throws IOException {
        String home = System.getProperty("user.home");
        Path filePath = Paths.get(home, "tasks.txt");
        List<String> lines = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof ToDo) {
                String line = String.format("ToDo=%s=%s", String.valueOf(task.getCompleted()), task.getTaskName());
                lines.add(line);
            } else if (task instanceof Event) {
                String line = String.format("Event=%s=%s=%s", String.valueOf(task.getCompleted()), task.getTaskName(), ((Event) task).getTimePeriod());
                lines.add(line);
            } else {
                String line = String.format("Deadline=%s=%s=%s", String.valueOf(task.getCompleted()), task.getTaskName(), ((Deadline) task).getDeadline());
                lines.add(line);
            }
        }
        Files.write(filePath, lines, StandardCharsets.UTF_8);
    }

    public static void getTaskFile(List<Task> taskList) throws IOException {
        String home = System.getProperty("user.home");
        Path filePath = Paths.get(home,  "tasks.txt");
        List<String> contents = Files.readAllLines(filePath);

        for (String content : contents) {
            String[] taskDetails = content.split("=");

            if (taskDetails[0].equals("ToDo")) {
                ToDo todo = new ToDo(taskDetails[2]);
                if (taskDetails[1].equals("true")) {
                    todo.markAsCompleted();
                }
                taskList.add(todo);
            } else if (taskDetails[0].equals("Deadline")) {
                Deadline deadline = new Deadline(taskDetails[2], taskDetails[3]);
                if (taskDetails[1].equals("true")) {
                    deadline.markAsCompleted();
                }
                taskList.add(deadline);
            } else {
                Event event = new Event(taskDetails[2], taskDetails[3]);
                if (taskDetails[1].equals("true")) {
                    event.markAsCompleted();
                }
                taskList.add(event);
            }
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<Task>();
        System.out.println("Hi, I'm Duke, your personal assistant!\n");
        try {
            getTaskFile(taskList);
            System.out.println("Welcome Back!\n");
            System.out.println("Current List:");
            System.out.println("---------------");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ": " + taskList.get(i));
            }
        } catch (IOException e) {
            System.out.println("Your schedule is empty. What should I add to your schedule?");
        }

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye from Duke!");
                break;
            } else {
                try {
                    handleCommands(input, taskList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            }


        }
    }
}
