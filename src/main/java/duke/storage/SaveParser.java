package duke.storage;



import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.util.Scanner;
/**
 * duke.Parser class to create Task objects from string commands
 */
public class SaveParser {
    Scanner scanner;

    SaveParser(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * test if file has next line
     * @return true if next line present
     */
    public boolean hasNextLine() {
        return this.scanner.hasNextLine();
    }

    /**
     * extract next task from save file and return it as object
     * @return new task from save file
     * @throws DukeException if there is an invalid task
     */
    public Task getNextTask() throws DukeException {
        String[] taskType = this.scanner.nextLine().split(":", 2);
        System.out.println(taskType[0]);
        System.out.println(taskType[1]);
        if (!taskType[0].equals("Task")) {
            System.out.println(":(");
            throw new DukeException("Invalid Task");
        }

        String name;
        boolean done;
        boolean[] argsFound;
        String nl;
        switch (taskType[1]) {
        case ("todo"):
            name = "";
            done = false;
            argsFound = new boolean[2];

            while (this.scanner.hasNextLine()) {
                nl = this.scanner.nextLine();
                if (nl.equals("")){
                    break;
                }
                if (nl.startsWith("\tName")) {
                    name = nl.split(":", 2)[1];
                    argsFound[0] = true;
                }
                if (nl.startsWith("\tDone")) {
                    done = Boolean.parseBoolean(nl.split(":", 2)[1]);
                    argsFound[1] = true;
                }
            }
            if (argsFound[0] && argsFound[1]) {
                return new Todo(name, done);
            }
            throw new DukeException("missing arg in save file");
//            break;

        case ("deadline"):
            name = "";
            done = false;
            String by = "";
            argsFound = new boolean[3];
            while (this.scanner.hasNextLine()) {
                nl = this.scanner.nextLine();
                if (nl.equals("")){
                    break;
                }
                if (nl.startsWith("\tName")) {
                    name = nl.split(":", 2)[1];
                    argsFound[0] = true;
                }
                if (nl.startsWith("\tDone")) {
                    done = Boolean.parseBoolean(nl.split(":", 2)[1]);
                    argsFound[1] = true;
                }
                if (nl.startsWith("\tBy")) {
                    by = nl.split(":", 2)[1];
                    argsFound[2] = true;
                }
            }
            if (argsFound[0] && argsFound[1] && argsFound[2]) {
                return new Deadline(name, done, by);
            }
            throw new DukeException("missing arg in save file");
//            break;
        case ("event"):
            name = "";
            done = false;
            String at = "";
            argsFound = new boolean[3];
            while (this.scanner.hasNextLine()) {
                nl = this.scanner.nextLine();
                if (nl.equals("")){
                    break;
                }
                if (nl.startsWith("\tName")) {
                    name = nl.split(":", 2)[1];
                    argsFound[0] = true;
                }
                if (nl.startsWith("\tDone")) {
                    done = Boolean.parseBoolean(nl.split(":", 2)[1]);
                    argsFound[1] = true;
                }
                if (nl.startsWith("\tAt")) {
                    at = nl.split(":", 2)[1];
                    argsFound[2] = true;
                }
            }
            if (argsFound[0] && argsFound[1] && argsFound[2]) {
                return new Event(name, done, at);
            }
            throw new DukeException("missing arg in save file");
//            break;
        default:
            throw new DukeException("Invalid Task");
        }
    }
}