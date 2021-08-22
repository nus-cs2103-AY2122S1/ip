import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public enum Command {
        LIST("list"), DONE("done"), TODO("todo"),
        DEADLINE("deadline"), EVENT("event"), DELETE("delete");

        public final String command;

        private Command(String command) {
            this.command = command;
        }
    }

    public static ArrayList<Task> readTask(File taskFile) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(taskFile);
            while (fileScanner.hasNextLine()) {
                String current = fileScanner.nextLine();
                Task currentTask = new Task("");
                switch(current.charAt(1)) {
                    case 'T': {
                        currentTask = new Todo(current.substring(7));
                        break;
                    }

                    case 'D': {
                        int index = current.indexOf("by");
                        currentTask = new Deadline(current.substring(7, index - 2), current.substring(index + 4, current.length() - 1));
                        break;
                    }

                    case 'E': {
                        int index = current.indexOf("at");
                        currentTask = new Event(current.substring(7, index - 2), current.substring(index + 4, current.length() - 1));
                        break;
                    }
                }
                if (current.charAt(4) == 'X') {
                    currentTask.markAsDone();
                }
                tasks.add(currentTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("duke.txt not found!");
        }
        return tasks;
    }
    public static void addTask(File taskFile, String content) {
        try {
            FileWriter fileWriter = new FileWriter(taskFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error has occurred when writing to file!");
        }
    }

    public static void editTask(File taskFile, String oldContent, String newContent) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(taskFile));
            String line = reader.readLine();
            StringBuilder current_file_content = new StringBuilder();
            while (line != null) {
                current_file_content.append(line);
                current_file_content.append('\n');
                line = reader.readLine();
            }
            reader.close();
            boolean x = current_file_content.toString().contains(oldContent);
            String new_file_content = current_file_content.toString().replace(oldContent, newContent);
            FileWriter fileWriter = new FileWriter(taskFile);
            fileWriter.write(new_file_content);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("duke.txt not found!");
        } catch (IOException e) {
            System.out.println("Error has occurred when editing file!");
        }
    }

    public static void deleteTask(File taskFile, String taskContent) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(taskFile));
            String line = reader.readLine();
            String new_file_content = "";
            while (line != null) {
                if (!line.equals(taskContent)) {
                    new_file_content = new_file_content + line + System.lineSeparator();
                }
                line = reader.readLine();
            }
            FileWriter fileWriter = new FileWriter(taskFile);
            fileWriter.write(new_file_content);
            reader.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("duke.txt not found!");
        } catch (IOException e) {
            System.out.println("Error has occurred when editing file!");
        }
    }

    public static void main(String[] args) {
        File taskFile = new File("../data/duke.txt");
        if (!taskFile.exists()) {
            try {
                if (taskFile.getParentFile().mkdirs()) {
                    System.out.println("Directory for file created.");
                }
                if (taskFile.createNewFile()) {
                    System.out.println("File created: duke.txt");
                }
            } catch (IOException e) {
                System.out.println("Error has occurred when creating file!");
                return;
            }
        }
        //Greet
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?\n");
        //Holds the list of tasks
        ArrayList<Task> task = readTask(taskFile);

        //Get input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] parsed = input.split(" ", 2);

            try {
                boolean validCommand = false;
                for (Command current : Command.values()) {
                    if (current.command.equals(parsed[0])) {
                        validCommand = true;
                        switch (current) {
                            case LIST: {
                                if (task.isEmpty()) {
                                    System.out.println("There are no tasks!");
                                } else {
                                    System.out.println("Here are the tasks in your list:");
                                    for (int i = 1; i < task.size() + 1; i++) {
                                        System.out.printf("  %d.%s%n", i, task.get(i - 1));
                                    }
                                }
                                break;
                            }

                            case DONE: {
                                if (parsed.length == 1) {
                                    throw new NoDescriptionException("Description of task cannot be empty!");
                                } else {
                                    String description = parsed[1];
                                    int taskNo = Integer.parseInt(description);
                                    Task doneTask = task.get(taskNo - 1);
                                    String oldContent = doneTask.toString();
                                    doneTask.markAsDone();
                                    editTask(taskFile, oldContent, doneTask.toString());
                                    System.out.println("Nice! I've marked this task as done:\n  " + doneTask);
                                    break;
                                }
                            }

                            case DELETE: {
                                if (parsed.length == 1) {
                                    throw new NoDescriptionException("Description of task cannot be empty!");
                                } else {
                                    String description = parsed[1];
                                    int taskNo = Integer.parseInt(description);
                                    Task deletedTask = task.get(taskNo - 1);
                                    task.remove(taskNo - 1);
                                    System.out.println(deletedTask.toString());
                                    deleteTask(taskFile, deletedTask.toString());
                                    System.out.printf("Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.%n",
                                            deletedTask, task.size());
                                    break;
                                }
                            }

                            case TODO: {
                                if (parsed.length == 1) {
                                    throw new NoDescriptionException("Description of task cannot be empty!");
                                } else {
                                    String description = parsed[1];
                                    Task newTask = new Todo(description);
                                    task.add(newTask);
                                    addTask(taskFile, newTask.toString());
                                    System.out.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.%n",
                                            newTask, task.size());
                                }
                                break;
                            }

                            case DEADLINE: {
                                if (parsed.length == 1) {
                                    throw new NoDescriptionException("Description of task cannot be empty!");
                                } else {
                                    String description = parsed[1];
                                    int index = description.indexOf("by");
                                    if (index == -1) {
                                        throw new WrongDescriptionException("Deadline not included! Try: deadline ... /by ...");
                                    } else {
                                        Task newTask = new Deadline(description.substring(0, index - 2), description.substring(index + 3));
                                        task.add(newTask);
                                        addTask(taskFile, newTask.toString());
                                        System.out.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.%n",
                                                newTask, task.size());
                                    }
                                }
                                break;
                            }

                            case EVENT: {
                                if (parsed.length == 1) {
                                    throw new NoDescriptionException("Description of task cannot be empty!");
                                } else {
                                    String description = parsed[1];
                                    int index = description.indexOf("at");
                                    if (index == -1) {
                                        throw new WrongDescriptionException("Event time not included! Try: deadline ... /at ...");
                                    } else {
                                        Task newTask = new Deadline(description.substring(0, index - 2), description.substring(index + 3));
                                        task.add(newTask);
                                        addTask(taskFile, newTask.toString());
                                        System.out.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.%n",
                                                newTask, task.size());
                                    }
                                }
                                break;
                            }
                        }
                    }
                }

                if (!validCommand) {
                    throw new InvalidTaskException("Invalid command! Please enter the following commands only:\n" +
                            "list\ndone (task number)\n" +
                            "delete (task number)\ntodo (description)\n" +
                            "deadline (description) /by (time)\nevent (description) /at (time)");
                }

                input = sc.nextLine();

            } catch (DukeException e) {
                System.out.println(e.getMessage());
                input = sc.nextLine();
            }
        }
        
        System.out.println("Bye. Hope to see you again!");
    }
}