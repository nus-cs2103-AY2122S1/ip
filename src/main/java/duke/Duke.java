package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";

    List<Task> taskList;

    public Duke() {
        taskList = new ArrayList<>(100);
    }

    void run() {
        Scanner sc = new Scanner(System.in);

        greeting("Alex");
        boolean isExit = false;
        while (!isExit) {
            // conversation loop
            String input = sc.nextLine().strip();
            String[] strArr = input.split(" ");
            String cmd = strArr[0];


            if(cmd.equals("bye")) {
                sayBye("Alex");
                break;
            }
            String description = "";
            try {
                switch (cmd) {
                case "":
                    throw new DukeException("Input cannot be empty!");
                case "list":
                    listTask();
                    break;
                case "delete":
                    deleteTask(parseIndex(strArr));
                    break;
                case "done":
                    markTaskDone(parseIndex(strArr));
                    break;
                case "todo":
                    description = parseDescription(strArr);
                    addTask(new Task(description));
                    break;
                case "deadline":
                    description = parseDescription(strArr);
                    LocalDateTime dateTime = parseDeadlineDateTime(strArr);
                    addTask(new Deadline(description, dateTime));
                    break;
                case "event":
                    description = parseDescription(strArr);
                    LocalDateTime[] dateTimes = parseEventDateTime(strArr);
                    addTask(new Event(description, dateTimes[0], dateTimes[1]));
                    break;
                default:
                    throw new DukeException("Invalid command");
                }
            } catch (DukeException ex) {
                System.err.println(ex);
            }
        }
    }

    private void addTask(Task task) {
        this.taskList.add(task);
        printHorizLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printHorizLine();
        try {
            writeListToFile(FILE_PATH);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void deleteTask(int idx) {
        try {
            Task curr = this.taskList.remove(idx-1);
            printHorizLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println("\t" + curr);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            printHorizLine();
            writeListToFile(FILE_PATH);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("The task index is invalid!");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listTask() {
        printHorizLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= this.taskList.size(); i++) {
            System.out.format("%d.%s\n", i, taskList.get(i-1));
        }
        printHorizLine();
    }

    private String listString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= this.taskList.size(); i++) {
           sb.append(String.format("%s\n", taskList.get(i-1)));
        }
        return sb.toString();
    }

    private void markTaskDone(int idx) {
        try {
            Task curr = this.taskList.get(idx - 1);
            curr.markDone();
            printHorizLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + curr);
            printHorizLine();
            writeListToFile(FILE_PATH);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("The task index is invalid!");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int parseIndex(String[] strArr) throws DukeException {
        if(strArr.length < 2) {
            throw new DukeException("Missing index after command");
        }
        return Integer.parseInt(strArr[1]);
    }

    private String parseDescription(String[] strArr) throws DukeException {
        StringBuilder sb = new StringBuilder();
        if(strArr.length >= 2 && !isDateTimeDelim(strArr[1])) {
            sb.append(strArr[1]);
        }
        for(int i = 2; i < strArr.length; i++) {
            if(isDateTimeDelim(strArr[i])) {
                break;
            }
            sb.append(" " + strArr[i]);
        }
        if(sb.length() < 1) {
            throw new DukeException("The task description cannot be empty");
        }

        return sb.toString();
    }

    private LocalDateTime parseDeadlineDateTime(String[] strArr) throws DukeException {
        // format should be "yyyy-MM-dd" or "yyyy-MM-dd HHmm"
        int i = 1;
        while(!isDateTimeDelim(strArr[i]) && i < strArr.length) {
            i++;
        }
        if(i == strArr.length - 1) {
            throw new DukeException("The time of deadline cannot be empty");
        }

        String dateStr = strArr[i + 1];
        String timeStr = "2359";  // default value in case time is missing from input
        if(strArr.length - i - 1 >= 2) {
            timeStr = strArr[i + 2];
        }

        return LocalDateTime.parse(dateStr + " " + timeStr,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    private LocalDateTime[] parseEventDateTime(String[] strArr) throws DukeException {
        // format should be "yyyy-MM-dd HHmm HHmm"
        int i = 1;
        while(!isDateTimeDelim(strArr[i]) && i < strArr.length) {
            i++;
        }
        if(strArr.length - 1 - i < 3) {
            throw new DukeException("Invalid start and end dateTime format");
        }

        String dateStr = strArr[i + 1];
        String startTimeStr = strArr[i + 2];
        String endTimeStr = strArr[i + 3];

        LocalDateTime startDateTime = LocalDateTime
                .parse(String.format("%s %s", dateStr, startTimeStr),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        LocalDateTime endDateTime = LocalDateTime
                .parse(String.format("%s %s", dateStr, endTimeStr),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        return new LocalDateTime[] {startDateTime, endDateTime};
    }

    private boolean isDateTimeDelim(String str) {
        return str.equals("/by") || str.equals("/at");
    }

    private void checkContent(String content) throws DukeException {
        if(content.equals("")) {
            throw new DukeException("Oops! The description of a todo cannot be empty.");
        }
    }

    private void checkTime(String time) throws DukeException {
        if(time.equals("")) {
            throw new DukeException("Oops! The time of a todo cannot be empty.");
        }
    }

    private void writeListToFile(String path) throws IOException {
        File file = linkFileOrCreateFile(path);
        FileWriter fw = new FileWriter(file);
        fw.write(listString());
        fw.close();
    }

    private void appendToFile(String path, String text) throws IOException {
        File file = linkFileOrCreateFile(path);
        FileWriter fw = new FileWriter(file, true);
        fw.write(text);
        fw.close();
    }

    private File linkFileOrCreateFile(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()) {
            checkAndFixParentDirectory(file.getParentFile());
            file.createNewFile();
        }
        return file;
    }

    /** Recursively check if current directory and anscestor directory exsits
     *  if not, create directories.
     * @param currDir current directory to check and fix
     */
    private void checkAndFixParentDirectory(File currDir) {
        if(!currDir.exists()) {
            checkAndFixParentDirectory(currDir.getParentFile());
            currDir.mkdir();
        }
    }

    private void greeting(String name) {
        printHorizLine();
        System.out.println("Hello " + name + "!");
        System.out.println("I'm Duke");
        printHorizLine();
        System.out.println("");
    }

    private void sayBye(String name) {
        printHorizLine();
        System.out.println("Bye " + name + ", hope to see you soon!");
        printHorizLine();
    }

    private void printHorizLine() {
        System.out.println("————————————————————————————————————————");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.run();
    }
}
