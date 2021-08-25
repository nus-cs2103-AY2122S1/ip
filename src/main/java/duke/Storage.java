package duke;

import duke.task.Deadline;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The Storage class encapsulates the attributes and behaviours of a dynamic storage used by the chat bot.
 *
 * @author leezhixuan
 */
public class Storage {
    private ToDoList tdl;

    /**
     * Creates an instance of the Storage class.
     *
     * @param tdl The ToDoList that will either be saving its contents onto memory or have its content loaded
     *            from memory.
     */
    protected Storage(ToDoList tdl) {
        this.tdl = tdl;
        Storage.createTaskListStorage();
    }

    /**
     * Checks if there is already a storage directory created on the user's machine.
     * If not, creates one of such for the user.
     */
    protected static void createTaskListStorage() {
        //creates a file to store content
        File s = new File("./data");
        boolean sResult;
        boolean fResult;
        try {
            sResult = s.mkdir();
            if (sResult) {
                File f = new File("./data/task-list.txt");
                fResult = f.createNewFile();
                if (fResult) {
                    System.out.println("I've created a task list for you.");
                }
            } else {
                System.out.println("Welcome back I guess..");
            }
        } catch (IOException e) {
            System.out.println("Hey.. You smell smoke..?");
        }
    }

    /**
     * Loads content from memory (if any) into the current instance of ToDoList.
     */
    protected void reloadTask() {
        try {
            int counter = 0;
            File file = new File("./data/task-list.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.isBlank()) {
                    break;
                }
                char type = str.charAt(3);
                char status = str.charAt(6);
                if (type == 'T') {
                    String item = str.substring(9);
                    this.tdl.addToDo(item);
                    if (status == 'X') {
                        this.tdl.getTask(counter).setCompleted();
                    }
                    counter++;
                } else if (type == 'E') {
                    try {
                        String temp = str.substring(9);
                        String item = temp.substring(0, temp.indexOf(" ")); //name
                        String temp2 = temp.substring(temp.indexOf("("));
                        String duration = temp2.substring(5, temp2.length() - 1);
                        this.tdl.addEvent(item, duration);
                        if (status == 'X') {
                            this.tdl.getTask(counter).setCompleted();
                        }
                        counter++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops file is corrupted");
                    }
                } else {
                    try {
                        String temp = str.substring(9);
                        String item = temp.substring(0, temp.indexOf(" ")); //name
                        String temp2 = temp.substring(temp.indexOf("("));
                        String deadline = temp2.substring(5, temp2.length() - 1);
                        LocalDateTime dl = LocalDateTime.parse(deadline.replace(' ','T'),
                                DateTimeFormatter.ISO_DATE_TIME);
                        this.tdl.addDeadline(item, dl);
                        if (status == 'X') {
                            this.tdl.getTask(counter).setCompleted();
                        }
                        counter++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops file is corrupted");
                    }
                }
            }
            System.out.println("Everything is in order now!\n");
        } catch (FileNotFoundException e) {
            File f = new File("./data/task-list.txt");
            try {
                boolean result = f.createNewFile();
            } catch (IOException ioException) {
                System.out.println("I smell smoke hmm...");
            }
        }
    }

    /**
     * Saves the contents in this instance of ToDoList onto memory.
     */
    public void save() {
        try {
            FileWriter fw = new FileWriter("./data/task-list.txt");
            Integer number = 1;
            for (Task a : this.tdl.getRecord()) {
                if (a.isCompleted()) {
                    if (a instanceof Deadline) {
                        fw.write(number.toString() + "." + a.logo() + "[X] " + a.getName()
                                + " (by: " + ((Deadline) a).getDeadline() + ")\n");
                    } else {
                        fw.write(number.toString() + "." + a.logo() + "[X] " + a.toString() + "\n");
                    }
                } else {
                    if (a instanceof Deadline) {
                        fw.write(number.toString() + "." + a.logo() + "[ ] " + a.getName()
                                + " (by: " + ((Deadline) a).getDeadline() + ")\n");
                    } else {
                        fw.write(number.toString() + "." + a.logo() + "[ ] " + a.toString()+ "\n");
                    }
                }
                number++;
            }
            System.out.println("Your task list has been updated successfully.");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("Something's wrong.. I can't find the file..");
        }
    }
}
