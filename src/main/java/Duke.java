import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = "\n" +
                "       __       ___      .______     ____    ____  __       _______.\n" +
                "      |  |     /   \\     |   _  \\    \\   \\  /   / |  |     /       |\n" +
                "      |  |    /  ^  \\    |  |_)  |    \\   \\/   /  |  |    |   (----`\n" +
                ".--.  |  |   /  /_\\  \\   |      /      \\      /   |  |     \\   \\    \n" +
                "|  `--'  |  /  _____  \\  |  |\\  \\----.  \\    /    |  | .----)   |   \n" +
                " \\______/  /__/     \\__\\ | _| `._____|   \\__/     |__| |_______/    \n" +
                "                                                                    \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------------------");
        System.out.println("Hi! I am Jarvis, your personal assistant :)\n");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------");

        try {
            //File myObj = new File("/Users/preshita/Desktop/jarvis.txt");
            File dir = new File("data");
            dir.mkdirs();
            File tmp = new File(dir, "jarvis.txt");
            tmp.createNewFile();

            retrieveFileContents("data/jarvis.txt");
//            File myObj = new File("/Users/preshita/Desktop/Java Projects/myipfork/jarvis.txt");
//            if (myObj.createNewFile()) {
//                System.out.println("File created: " + myObj.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //String instruction = "";
        Scanner sc = new Scanner(System.in);
        String instruction = sc.nextLine();

        while (!instruction.equals("bye")) {
            try {
                if (instruction.equals("list")) {
                    int index = 0;
                    if (Task.getCounter() == 0) {
                        System.out.println("\tThere are currently no tasks on your list :)");
                    } else {
                        printFileContents("data/jarvis.txt");
                    }
//                    if (Task.getCounter() == 0) {
//                        System.out.println("\tThere are currently no tasks on your list :)");
//                    }
//                    while (index < Task.getCounter()) {
//                        System.out.println("\t" + (index + 1) + "." + Task.getTaskList().get(index).toString());
//                        index++;
//                    }
                } else if (instruction.startsWith("done")) {
                    int taskNum = Integer.parseInt(instruction.substring(5)) - 1;
                    if (taskNum >= Task.getCounter()) {
                        throw new DukeException("\tHmm, I don't have task " + (taskNum + 1) +
                                " in my list. Please key in 'list' if you'd like to " +
                                "view your list of tasks again!");
                    } else {
                        Task.getTaskList().get(taskNum).markAsDoneAndUpdate();
                        System.out.println("\tGood job! I've marked this task as done:");
                        System.out.println("\t\t" + Task.getTaskList().get(taskNum).toString());
                    }
                } else if (instruction.startsWith("delete")) {
                    int taskNum = Integer.parseInt(instruction.substring(7)) - 1;
                    if (taskNum >= Task.getCounter()) {
                        throw new DukeException("\tHmm, I don't have task " + (taskNum + 1) +
                                " in my list. Please key in 'list' if you'd like to " +
                                "view your list of tasks again!");
                    } else {
                        System.out.println("\tNoted. I've removed this task:");
                        System.out.println("\t\t" + Task.getTaskList().get(taskNum).toString());
                        Task.getTaskList().get(taskNum).deleteTaskAndUpdate();
                        System.out.println("\tNow you have " + Task.getCounter() +
                                " task(s) in the list.");
                    }
                } else if (instruction.startsWith("todo")) {
                    if (instruction.length() < 5) {
                        throw new DukeException("\tOOPS!!! The description of a todo " +
                                "cannot be empty.");
                    } else {
                        String taskDescription = instruction.substring(4);
                        Todo newTodo = new Todo(taskDescription);
                        newTodo.addTaskAndUpdate();
                        System.out.println("\tGot it! I've added this task:");
                        System.out.println("\t\t" + newTodo.toString());
                        System.out.println("\tNow you have " + Task.getCounter() +
                                " task(s) in the list.");
                    }
                } else if (instruction.startsWith("deadline")) {
                    if (instruction.length() < 10) {
                        throw new DukeException("\tOOPS!!! The description of a deadline " +
                                "cannot be empty.");
                    } else {
                        String taskDescription = "";
                        int currIndex = 8;
                        while (currIndex < instruction.length() &&
                                !instruction.substring(currIndex).startsWith(" /")) {
                            taskDescription += instruction.substring(currIndex, currIndex + 1);
                            currIndex++;
                        }
                        if (currIndex == instruction.length() ||
                                currIndex + 5 >= instruction.length()) {
                            throw new DukeException("I think you forgot to key in your deadline!");
                        } else {
                            String by = instruction.substring(currIndex + 5);
                            Task newDeadline = new Deadline(taskDescription, by);
                            newDeadline.addTaskAndUpdate();
                            System.out.println("\tGot it! I've added this task:");
                            System.out.println("\t\t" + newDeadline.toString());
                            System.out.println("\tNow you have " + Task.getCounter() +
                                    " task(s) in the list.");
                        }
                    }
                } else if (instruction.startsWith("event")) {
                    if (instruction.length() < 7) {
                        throw new DukeException("\tOOPS!!! The description of a event " +
                                "cannot be empty.");
                    } else {
                        String taskDescription = "";
                        int currIndex = 5;
                        while (currIndex < instruction.length() &&
                                !instruction.substring(currIndex).startsWith(" /")) {
                            taskDescription += instruction.substring(currIndex, currIndex + 1);
                            currIndex++;
                        }
                        if (currIndex == instruction.length() ||
                                currIndex + 5 >= instruction.length()) {
                            throw new DukeException("\tI think you forgot to key in your event timing!");
                        } else {
                            String by = instruction.substring(currIndex + 5);
                            Task newEvent = new Event(taskDescription, by);
                            newEvent.addTaskAndUpdate();
                            System.out.println("\tGot it! I've added this task:");
                            System.out.println("\t\t" + newEvent.toString());
                            System.out.println("\tNow you have " + Task.getCounter() +
                                    " task(s) in the list.");
                        }
                    }
                } else {
                    throw new DukeException("\tOOPS!!! I'm sorry, but I don't " +
                            "know what that means :-(");
                }
            } catch (DukeException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
            System.out.println("----------------------------------");
            instruction = sc.nextLine();
        }
        System.out.println("\t" + "Bye! Hope to see you soon :)");
        System.out.println("----------------------------------");
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println("\t" + s.nextLine());
        }
    }

    private static void retrieveFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currLine = s.nextLine();
            currLine = currLine.substring(2);
            if (currLine.startsWith("[T]")) {
                String description = " " + currLine.substring(7);
                Todo newTodo = new Todo(description);
                newTodo.addTask();
                if (currLine.charAt(4) == 'X') {
                    newTodo.markAsDone();
                }
            } else if (currLine.startsWith("[D]")) {
                int currIndex = 7;
                while (!currLine.substring(currIndex).startsWith("by: ")) {
                    currIndex++;
                }
                String description = " " + currLine.substring(7, currIndex - 2);
                String by = currLine.substring(currIndex + 4, currLine.length() - 1);
                Task newDeadline = new Deadline(description, by);
                newDeadline.addTask();
                if (currLine.charAt(4) == 'X') {
                    newDeadline.markAsDone();
                }
            } else if (currLine.startsWith("[E]")) {
                int currIndex = 7;
                while (!currLine.substring(currIndex).startsWith("at: ")) {
                    currIndex++;
                }
                String description = " " + currLine.substring(7, currIndex - 2);
                String by = currLine.substring(currIndex + 4, currLine.length() - 1);
                Task newEvent = new Event(description, by);
                newEvent.addTask();
                if (currLine.charAt(4) == 'X') {
                    newEvent.markAsDone();
                }
            }
        }
    }
}

