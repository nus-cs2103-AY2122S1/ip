import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskList {
    
    private final ArrayList<Task> taskList = new ArrayList<>();
    private final Path filePath;
    
    TaskList() throws DukeException {
        try {
            String currDir = new File("").getAbsolutePath();
            Path path = Paths.get(currDir,  "data");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            path = Paths.get(currDir,  "data", "duke.txt");
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            File fileholder = new File(path.toString());
            this.filePath = path;
            Scanner s = new Scanner(fileholder);
            while (s.hasNext()) {
                String holder = s.nextLine();
                String[] content = holder.split(" \\| ");
                

                switch (content[0]) {
                    case "T":
                        Todo todo = new Todo("todo " + content[2].trim());
                        if (content[1].equals("1")) {
                            todo.doneTask();
                        }
                        this.taskList.add(todo);
                        break;

                    case "D":
                        Deadline deadline = new Deadline("deadline " 
                                + content[2].trim() + " /" + content[3].trim());
                        if (content[1].equals("1")) {
                            deadline.doneTask();
                        }
                        this.taskList.add(deadline);
                        break;

                    case "E":
                        Event event = new Event("event " + content[2].trim() + " /" + content[3].trim());
                        if (content[1].equals("1")) {
                            event.doneTask();
                        }
                        this.taskList.add(event);
                        break;

                }
            }
            s.close();
        } catch (IOException e) {
            throw new DukeException("☹ OH NOOOOO! I cannot locate the file!!" + e.getMessage());
        }
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("____________________________________________________________\n"
                + "Darling, here are the tasks in your list:\n");
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String entry = String.format("%d. %s\n",
                    i+1, task.toString());
            result.append(entry);
        }
        result.append("____________________________________________________________\n");
        return result.toString();
    }
    
    public int size() {
        return this.taskList.size();
    }
    
    public void listSchedule(String content) {
        LocalDate dateFilter = LocalDate.parse(content.substring(5).trim());
        ArrayList<Task> filteredTaskList = this.taskList.stream()
                .filter(Task::hasSchedule).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(String
                .format("____________________________________________________________\n"
                                + "Darling, here are the tasks with a schedule of %s:\n"
                        , dateFilter.toString()));
        for (int i = 0; i < filteredTaskList.size(); i++) {
            Task task = filteredTaskList.get(i);
            String entry = String.format("%d. %s",
                    i+1, task.toString());
            System.out.println(entry);
        }
        System.out.println("____________________________________________________________\n");

    }
    
    public void doneTask(int index) throws DukeException {
        Task holder = this.taskList.get(index - 1);
        holder.doneTask();
        update();
        System.out.println("____________________________________________________________\n"
                + "Noice! I've marked this task as done: \n"
                + holder.toString()
                + "\n"
                + "____________________________________________________________\n");
    }
    
    public void deleteTask(int index) throws DukeException {
        String holder = this.taskList.get(index - 1).toString();
        this.taskList.remove(index - 1);
        this.taskList.trimToSize();
        update();
        System.out.println("____________________________________________________________\n"
                + "okie! I've removed this annoying task: \n"
                + holder
                + "\nNow you have " + this.taskList.size() + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }
    
    public void addTodo(String content) throws DukeException {
        Todo task = new Todo(content);
        this.taskList.add(task);
        update();
        System.out.println("____________________________________________________________\n"
                + "Gotcha my dear. I've added this task for you: \n" + task.toString()
                + "\nNow you have " + this.taskList.size() + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }
    
    public void addDeadline(String content) throws DukeException {
        Deadline task = new Deadline(content);
        this.taskList.add(task);
        update();
        System.out.println("____________________________________________________________\n"
                + "Gotcha my dear. I've added this task for you: \n" + task.toString()
                + "\nNow you have " + this.taskList.size() + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }
    
    public void addEvent(String content) throws DukeException {
        Event task = new Event(content);
        this.taskList.add(task);
        update();
        System.out.println("____________________________________________________________\n"
                + "Gotcha my dear. I've added this task for you: \n" + task.toString()
                + "\nNow you have " + this.taskList.size() + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }
    
    public void update() throws DukeException {
        try {
            Path temp = Files.createTempFile(Paths.get(this.filePath.toString(), "..")
                    , "temp", ".txt");
            FileWriter fw = new FileWriter(temp.toString());
            File tempFile = new File(temp.toString());
            for (Task task : this.taskList) {
                fw.write(task.record() + System.lineSeparator());

            }
            fw.close();
            Files.delete(this.filePath);
            Files.copy(temp, this.filePath);
            //noinspection ResultOfMethodCallIgnored
            tempFile.delete();
        } catch (IOException e) {
            throw new DukeException("☹ OH NOOOOO! Something wrong with the file!!" + e.getMessage());
        }
    }
}
