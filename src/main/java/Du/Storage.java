package Du;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Storage {

    protected String filepath;
    protected TaskList tasks;

    /**
     * Public constructor for Storage
     * @param filepath where the saved file is located at
     * @param tasks Tasklist with the list of tasks
     */
    public Storage(String filepath, TaskList tasks) {
        this.filepath = filepath;
        this.tasks = tasks;
    }

    /**
     * Loads previously recorded items if there is any
     * @throws IOException
     */
    public String load() throws IOException {
        File f = new File(filepath);
        String print = "";
        // check if data folder exists, if not, create it
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();

        // check if du.txt exists, if not, create it
        if (f.createNewFile()) {
            return print;
        } else {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String task_name = s.nextLine();
                Task t = read_task(task_name);
                print = print + t + "\n";
            }
            return print;
        }
    }


    /**
     * Reads the tasks if there is any from the saved file
     * @param task_name name from the file
     */
    public Task read_task(String task_name) {
        String[] task = task_name.split(" , ", 4);
        boolean done = false;
        Task t = null;
        if (Integer.parseInt(task[1]) == 1) {
            done = true;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (Objects.equals(task[0], "T")) {
            t = new Todo(task[2], done, tasks);
        } else if (Objects.equals(task[0], "D")) {
            t = new Deadline(task[2], done, tasks, LocalDateTime.parse(task[3], formatter));
        } else if (Objects.equals(task[0], "E")) {
            t = new Event(task[2], done, tasks, LocalDateTime.parse(task[3], formatter));
        }
        return t;
    }

    /**
     * Updates the records when programme ends
     * @param tasks list of tasks that needs to be added
     * @throws IOException
     */
    public void update_records(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        for (Task t : tasks.getList_of_tasks()) {
            fw.write(t.log_record() + "\n");
        }
        fw.close();
    }



}
