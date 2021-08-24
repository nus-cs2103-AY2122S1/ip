import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

class MyList {

    private final List<Task> tasks;
    HashSet<String> listedItems;

    MyList() {
        this.tasks = new ArrayList<Task>(100);
        this.listedItems = new HashSet<String>();
    }

    protected String addItem(String command) throws DukeException.InvalidCommandException, 
            DukeException.InvalidTaskDescriptionException, DukeException.DuplicateTaskException,
            IOException, DateTimeParseException {
        String[] commandTokens = generateTokens(command, " ");
        String taskName;
        Task task;
        if (commandTokens[0].equals("todo")) {
            taskName = command.substring(5).trim();
            if (this.listedItems.contains(taskName)) {
                throw new DukeException.DuplicateTaskException("Task already in list!");
            } else if (taskName.length() == 0) {
               throw new DukeException.InvalidTaskDescriptionException("Missing task description!");
            } else {
                task = new ToDo(taskName);
                this.listedItems.add(taskName);
                this.tasks.add(task);
                if (Duke.canLog) {
                    this.appendNewLogEntry("T", "F", taskName);
                }
            }
        } else if (commandTokens[0].equals("event") || commandTokens[0].equals("deadline")) { //either event ot deadline
            String details = command.substring(commandTokens[0].length() + 1).trim();
            String[] detailTokens = generateTokens(details, "/");
            if (detailTokens.length < 2) {
                throw new DukeException.InvalidTaskDescriptionException("Invalid task description: missing date/time!");
            } else if (detailTokens.length > 2) {
                throw new DukeException.InvalidTaskDescriptionException("Invalid task description: "
                        + "invalid date/time\nPlease use [command type] [task name] / [dd-mm-yyyy] [time (in 24hr " +
                        "format)" +
                        "]\ne.g. event lecture / 21-02-2021 1500");
            } else if (this.listedItems.contains(detailTokens[0].trim())){ // item already in list
                throw new DukeException.DuplicateTaskException("Task already in list!");
            } else { // valid
                taskName = detailTokens[0].trim();
                String[] dateTimeString = detailTokens[1].trim().split(" "); 
                LocalDate date = LocalDate.parse(dateTimeString[0], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalTime time = LocalTime.parse(dateTimeString[1], DateTimeFormatter.ofPattern("HHmm"));
                LocalDateTime dateTime = LocalDateTime.of(date, time);
                if (commandTokens[0].trim().equals("event")) {
                    task = new Event(taskName, dateTime);
                    if (Duke.canLog) {
                        this.appendNewLogEntry("E", "F", taskName, dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    }
                } else { //deadline
                    task = new Deadline(taskName, dateTime);
                    if (Duke.canLog) {
                        this.appendNewLogEntry("D", "F", taskName, dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    }
                }
                this.listedItems.add(taskName);
                this.tasks.add(task);
            }
        } else { // invalid input
            throw new DukeException.InvalidCommandException("Invalid command!");
        }
        return String.format("New task added to list:\n%s", task);
    }
    
    protected void importPreviousTasks(File previousLog) throws IOException {
        Scanner s = new Scanner(previousLog);
        while (s.hasNextLine()) {
            String task = s.nextLine();
            String[] tokens = task.split(";");
            boolean isCompleted = tokens[1].equals("T");
            String taskName = tokens[2];
            // format: task type | isCompleted | event name | date/time
            switch (tokens[0]) {
            case "T":
                this.tasks.add(ToDo.createTask(taskName, isCompleted));
                break;
            case "E":
                this.tasks.add(Event.createTask(taskName, isCompleted, LocalDateTime.parse(tokens[3].trim(),
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                break;
            case "D":
                this.tasks.add(Deadline.createTask(taskName, isCompleted, LocalDateTime.parse(tokens[3].trim(),
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                break;
            }    
            this.listedItems.add(taskName);
        }
    }

    private String[] generateTokens(String taskDetails, String delimiter) {
        return taskDetails.split(delimiter);
    }

    protected String markAsCompleted(String taskName) throws IOException {
        //find the task to delete
        int i = 0;
        while (i < this.tasks.size()) {
            if (this.tasks.get(i).getTaskName().equals(taskName)) {
                Task completedTask = this.tasks.get(i);
                if (completedTask.getIsCompleted()) {
                    return "Task is already completed!";
                }
                this.tasks.remove(i);
                this.tasks.add(i, completedTask.markAsCompleted());
                if (Duke.canLog) {
                    this.updateLogEntryAsCompleted(i);
                }
                return "Task marked as completed:\n" + this.tasks.get(i).toString();
            } else {
                i++;
            }
        }
        return "task is not in list!!";
    }
    
    protected String deleteItem(String itemNum) throws DukeException.InvalidTaskNumException, IOException { 
        int val = Integer.parseInt(itemNum.trim());
        if (val > this.tasks.size() || val < 1) {
            throw new DukeException.InvalidTaskNumException("Task number " + val + " does not exist!");
        } else {
            Task toRemove = this.tasks.get(val - 1);
            this.tasks.remove(val - 1);
            this.listedItems.remove(toRemove.getTaskName());
            if (Duke.canLog) {
                this.deleteLogEntry(val - 1);
            }
            return "Successfully deleted:\n" + toRemove;
        }
    }
    
    private void updateLogEntryAsCompleted(int lineNum) throws IOException { // lineNum is 0 indexed
        int i = 0;
        Scanner sc = new Scanner(new File(Duke.logPath));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) { // read the entire file except the line to change, which would be ignored
            String entry = sc.nextLine();
            if (i != lineNum) { // line to be modified
                sb.append(entry);
            } else { // generate new entry
                sb.append(entry.replaceAll(";F;", ";T;"));
            }
            sb.append("\n");
            i++;
        }
        FileWriter fw = new FileWriter(Duke.logPath, false); // append false -> overwrite file
        fw.write(sb.toString());
        fw.close();
        sc.close();
    }
    
    private void deleteLogEntry(int lineNum) throws IOException { // lineNum is 0 indexed
        int i = 0;
        Scanner sc = new Scanner(new File(Duke.logPath));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) { // read the entire file except the line to change, which would be ignored
            String entry = sc.nextLine();
            if (i != lineNum) { // line to be modified
                sb.append(entry);
                sb.append("\n");
            }
            i++;
        }
        FileWriter fw = new FileWriter(Duke.logPath, false); // append false -> overwrite file
        fw.write(sb.toString());
        fw.close();
        sc.close();
    }
    
    private void appendNewLogEntry(String type, String isCompleted, String name) throws IOException {
        FileWriter fw = new FileWriter(Duke.logPath, true); // append flag set to true to prevent clearing
        fw.write(type + ";" + isCompleted + ";" + name + "\n");
        fw.close();
    }

    private void appendNewLogEntry(String type, String isCompleted, String name, String dateTime) throws IOException {
        appendNewLogEntry(type, isCompleted, name + ";" + dateTime);
    }

    protected String getAllTasks() {
        StringBuilder sb = new StringBuilder();
        if (this.tasks.size() == 0) {
            sb.append("There are no items in your list!");
            return sb.toString();
        }
        sb.append("Your list contains:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            String itemNum = i + 1 + ". ";
            sb.append(itemNum);
            sb.append(this.tasks.get(i).toString());
            if (i < this.tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
