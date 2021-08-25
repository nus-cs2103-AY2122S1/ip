import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;


public class Blitz {

    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        try {
            readFileContents("data/blitz.txt");
        } catch (IOException e) {
            System.err.println(e);
        }

        printLine();
        System.out.println("\tHola! I'm Blitz :)");
        printList();
        printFormatted("So what can I do for you today?");

        Scanner scanner = new Scanner(System.in);

        //command from the user
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            try {
                checkCommand(command);
            } catch (BlitzException ex) {
                printFormatted(ex.toString());
            }
            command = scanner.nextLine();
        }

        try {
            updateFile();
        } catch (IOException e) {
            System.err.println(e);
        }


        //if the user enter "bye"
        printFormatted("Adiós. Hope to see you again soon!");
    }

    /* To print horizontal line */
    private static void printLine() {
        System.out.print('\t');
        for (int i = 0; i < 100; i++) {
            System.out.print('_');
        }
        System.out.println("");
    }

    /* To print a given string between horizontal lines */
    private static void printFormatted(String str) {
        printLine();
        System.out.println("\t" + str);
        printLine();
    }

    /* To print list of tasks between horizontal lines */
    public static void printList() {
        int ctr = 1;
        System.out.println("\tHere are the tasks in your list:");
        if(tasks.size() == 0) {
            System.out.println("\t---No items stored yet---");
        } else {
            for (Task task: tasks) {
                System.out.println("\t" + ctr + "." + tasks.get(ctr - 1));
                ctr++;
            }

        }
    }

    /* To get the index of the task completed or to be deleted */
    private static int getTaskNumber(String keyword, String task) {
        int idx = 0;
        for (int i = task.length() - 1; i > keyword.length(); i--) {
            idx+= (task.charAt(i) - 48) * Math.pow(10, task.length() - 1 - i);
        }
        return idx - 1;
    }

    /* Returns index of task completed or to be deleted if valid, otherwise, throws exception */
    private static int getIndex(String command) throws BlitzException {
        String keyword = command.substring(0, command.indexOf(' '));
        int index = getTaskNumber(keyword,command);
        if (index < 0 || index >= tasks.size()) {
            throw new BlitzException("You are attempting to "
                    + (keyword.equals("done") ? "mark" : "delete") + " an invalid task number!");
        }
        return index;
    }

    /* Reads the tasks from the file and stores them in the list when program starts */
    private static void readFileContents(String filePath) throws IOException {
        File f = new File(filePath);
        if (f.exists()) {
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String task = s.nextLine();
                String[] keywords = task.split(" \\| ");
                String done = keywords[1];
                Task current = new Task("");
                switch(task.charAt(0)) {
                case 'T' :
                    current = new Todo(keywords[2]);
                    break;
                case 'D' :
                    current = new Deadline(keywords[2], LocalDateTime.parse(keywords[3],
                            DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));
                    break;
                default :
                    current = new Event(keywords[2], LocalDateTime.parse(keywords[3],
                            DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));
                }
                if(done.equals(1)) {
                    current.markAsDone();
                }
                tasks.add(current);
            }
        } else {
            if(f.getParentFile() != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        }
    }

    /* Updates the text file with current state of list before exiting */
    private static void updateFile() throws IOException {
        FileWriter fw = new FileWriter("data/blitz.txt");
        for(Task t : tasks) {
            if(t instanceof Todo) {
                fw.write("T | " + t.getStatusIcon() + " | " + t.getDescription()
                        + System.lineSeparator());
            } else if (t instanceof Deadline) {
                fw.write("D | " + t.getStatusIcon() + " | " + t.getDescription()
                        + " | " + ((Deadline) t).getBy() + System.lineSeparator());
            } else {
                fw.write("E | " + t.getStatusIcon() + " | " + t.getDescription()
                        + " | " + ((Event) t).getDate() + System.lineSeparator());
            }
        }
        fw.close();
    }

    /* Method to check for the validity of user input */
    public static void checkCommand(String command) throws BlitzException {
        String[] keywords = command.split(" ");

        //stores the first word (keyword) in the user input
        String firstKeyword = keywords[0];

        //stores whether keyword is a task or calling a feature
        boolean isTask = firstKeyword.equals("todo") || firstKeyword.equals("event")
                || firstKeyword.equals("deadline");
        boolean isFeature = firstKeyword.equals("list") || firstKeyword.equals("done")
                || firstKeyword.equals("delete");

        //suppose it's a valid keyword
        if(isTask || isFeature) {

            //when keyword is not followed by anything
            if (isTask && command.length() < firstKeyword.length() + 2) {
                throw new BlitzException("The description of a " + firstKeyword
                        + " cannot be empty.");
            }

            if (isFeature) {
                switch (firstKeyword) {
                case "list":
                    if (tasks.size() == 0) {
                        throw new BlitzException("No items added to list yet!");
                    }
                    printLine();
                    printList();
                    printLine();
                    break;
                case "done":
                    try {
                        int index = getIndex(command);
                        Task finished = tasks.get(index);
                        finished.markAsDone();
                        printFormatted("Nice! I've marked this task as done:\n" + "\t\t"
                                + finished);
                        } catch (BlitzException ex) {
                            printFormatted(ex.toString());
                        }
                        break;
                case "delete":
                        try {
                            int index = getIndex(command);
                            Task deleted = tasks.remove(index);
                            printFormatted("Noted. I've removed this task:" + "\n\t\t" + deleted
                                    + "\n\tNow you have " + tasks.size() + " tasks in the list.");
                        } catch (BlitzException ex) {
                            printFormatted(ex.toString());
                        }
                        break;
                }
            } else {
                Task current = new Task("");
                switch (firstKeyword) {
                case "todo":
                    current = new Todo(command.substring(5));
                    break;
                case "deadline":
                    String date = command.substring(command.indexOf('/') + 4);
                    try {
                        LocalDateTime d = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                        current = new Deadline(command.substring(9, command.indexOf('/') - 1), d);
                    } catch (DateTimeParseException e) {
                        throw new BlitzException("Incorrect date/time format! Please enter "
                                + "deadline date/time in \"d M yyyy HHmm\" format");
                    }

                    break;
                case "event":
                    String eventDate = command.substring(command.indexOf('/') + 4);
                    try {
                        LocalDateTime ed = LocalDateTime.parse(eventDate, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                        current = new Event(command.substring(6, command.indexOf('/') - 1), ed);
                    } catch (DateTimeParseException e) {
                        throw new BlitzException("Incorrect date/time format! Please enter "
                                + "event date/time in \"d M yyyy HHmm\" format");
                    }

                    break;
                }

                tasks.add(current);
                printFormatted("Got it. I've added this task:" + "\n\t\t" + current + "\n\tNow you have "
                        + tasks.size() + " tasks in the list.");
            }
        } else {
            //if the keyword is not valid
            throw new BlitzException("Sorry, but I don't know what that means :-(");
        }
    }
}
