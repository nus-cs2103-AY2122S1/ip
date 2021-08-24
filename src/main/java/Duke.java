import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import static java.lang.Integer.parseInt;

/**
 * The Duke class acts as a text bot that records down the tasks given to it. It can
 * then mark these tasks as completed and delete them based on the inputs given.
 */

public class Duke {
    /**
     * Checks the date input from the user to see if it is a valid date
     *
     * @param dateStr Date taken in to check for validity
     * @return If date is valid
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter in the format: yyyy-mm-dd");
            return false;
        }
        return true;
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
    }

    public void run () {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        ui.input = input;
        try {
            while (input.hasNextLine()) {
                String userInput = ui.readInput();
                Parser.parse(userInput, this.ui, this.storage, this.tasks);
            }
        } catch (IllegalStateException e) {
            storage.saveFile(tasks);
            }
        }

    public static void main(String[] args) {
        new Duke("C:\\Users\\ronal\\OneDrive\\Desktop\\CS2103\\DUKE\\data\\duke.txt").run();
    }
        /* Scanner input = new Scanner(System.in);
        ArrayList<Task> storedInfo = new ArrayList<Task>();
        int count = 0;
        try {
            File taskList = new File("C:\\Users\\ronal\\OneDrive\\Desktop\\CS2103\\DUKE\\data\\duke.txt");
            Scanner taskReader = new Scanner(taskList);
            while (taskReader.hasNextLine()) {
                String item = taskReader.nextLine();
                if (item.charAt(1) == 'T') {
                    ToDoTask addedTask = new ToDoTask(item.substring(7));
                    if (item.charAt(4) == 'X') {
                        addedTask.markAsDone();
                    }
                    storedInfo.add(addedTask);
                    count++;
                } else if (item.charAt(1) == 'D') {
                    int i = item.indexOf("(");
                    DeadlineTask addedTask = new DeadlineTask(item.substring(7, i),
                            item.substring(i+5, item.length()-1));
                    if (item.charAt(4) == 'X') {
                        addedTask.markAsDone();
                    }
                    storedInfo.add(addedTask);
                    count++;
                } else if (item.charAt(1) == 'E') {
                    int i = item.indexOf("(");
                    EventTask addedTask = new EventTask(item.substring(7, i),
                            item.substring(i+5, item.length()-1));
                    storedInfo.add(addedTask);
                    if (item.charAt(4) == 'X') {
                        addedTask.markAsDone();
                    }
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tasklist not found. Creating new tasklist");
        }
        //Task[] storedInfo = new Task[100];
        while (input.hasNextLine()) {
            String in = input.nextLine();
            if (in.equals("bye") || in.equals("Bye")) {
                break;
            }
            if (in.equals("list") || in.equals("List")) {
                int counter = 1;
                System.out.println(" Here are the tasks in your list:");
                for (Task item: storedInfo) {

                    if(item != null) {
                        System.out.println(counter + ". " + item.toString());
                        counter++;
                    }

                }
                continue;
            }
            if (in.length() > 3 && in.substring(0,4).equals("done") ) {
                /*if (in.substring(4,5) != " ") {
                    System.out.println("Invalid input for done command");
                };
                if (in.length() < 6) {
                    System.out.println("Invalid Input for done command");
                    continue;
                }
                int taskDone = parseInt(in.substring(5));
                if (taskDone > 100) {
                    System.out.println("Invalid Input for done command");
                    continue;
                }
                //System.out.println(taskDone);
                if (taskDone > count) {
                    System.out.println("Invalid Input for done command");
                    continue;
                }
                if (!storedInfo.get(taskDone - 1).isDone) {
                    System.out.println("Nice! I've marked this task as done:");
                    storedInfo.get(taskDone - 1).markAsDone();
                    System.out.println(storedInfo.get(taskDone - 1));
                    continue;
                } else {
                    System.out.println("This task is already marked as done");
                    continue;
                }

            }
            if (in.length() > 5 && in.substring(0,6).equals("delete")) {
                if (in.length() < 8) {
                    System.out.println("Invalid Input for delete command");
                    continue;
                }
                if (storedInfo.isEmpty()) {
                    System.out.println("The list is empty.");
                    continue;
                }
                int taskDeleted = parseInt(in.substring(7));
                try {
                    if (taskDeleted > 100) {
                        System.out.println("Invalid Input for delete command");
                        continue;
                    }
                    //System.out.println(taskDone);
                    if (taskDeleted > count) {
                         System.out.println("Invalid Input for delete command");
                        continue;
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(storedInfo.get(taskDeleted - 1));
                    storedInfo.remove(taskDeleted-1);
                    count--;
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input for delete command");
                    continue;
                }
            }
            if (in.length() > 3 && in.substring(0,4).equals("todo") ) {
                if (in.length() == 4) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                storedInfo.add(new ToDoTask(in.substring(4)));
                System.out.println(storedInfo.get(count));
                count++;
                if (count == 1) {
                    System.out.println("Now you have " + count + " task in the list.");
                } else {
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else if (in.length() > 4 && in.substring(0,5).equals("event")) {
                int i = in.indexOf("/");
                if (i < 0) {
                    System.out.println("Time not detected. Please try again");
                    continue;
                }
                try {
                    if (!isValid(in.substring(i+1, i+11))) {
                        continue;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Invalid date and time. Use yyyy-mm-dd format for date");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                storedInfo.add(new EventTask(in.substring(6, i), in.substring(i + 1)));
                System.out.println(storedInfo.get(count));
                count++;
                if (count == 1) {
                    System.out.println("Now you have " + count + " task in the list.");
                } else {
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else if (in.length() > 7 && in.substring(0,8).equals("deadline")) {
                int i = in.indexOf("/");
                if (i < 0) {
                    System.out.println("Time not detected. Please try again");
                    continue;
                }
                try {
                    if (!isValid(in.substring(i+1, i+11))) {
                        continue;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Invalid date and time");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                storedInfo.add(new DeadlineTask(in.substring(9, i), in.substring(i + 1)));
                System.out.println(storedInfo.get(count));
                count++;
                if (count == 1) {
                    System.out.println("Now you have " + count + " task in the list.");
                } else {
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            }
        //Insert save protocol here
        File taskList = new File("C:\\Users\\ronal\\OneDrive\\Desktop\\CS2103\\DUKE\\data\\duke.txt");
        try {
            if (taskList.createNewFile()) {
                System.out.println("Tasklist created and saved");
            } else {
                System.out.println("Tasklist updated");
            }
        } catch (IOException e) {
            System.out.println("File could not be created");
        }
        try {
            FileWriter listEditor = new FileWriter("C:\\Users\\ronal\\OneDrive\\Desktop\\CS2103\\DUKE\\data\\duke.txt");
            for (Task item: storedInfo) {
                if(item != null) {
                    listEditor.write(item.toString());
                    listEditor.write("\n");
                }
            }
            listEditor.close();
            System.out.println("Saving list..");

        } catch (IOException e) {
            System.out.println("File does not exist");
        }

        System.out.println("Bye. Hope to see you again!");
    }*/
}
