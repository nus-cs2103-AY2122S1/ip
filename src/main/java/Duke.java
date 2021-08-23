/**
 * Todo App.
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Starts the ToDo app.
     * Loads save file on start and saves list of Todo on close.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        loadSave();
        echo();
        saveList();
    }

    /**
     * Listens for user input via scanner and responds accordingly.
     * list - shows current list of Task.
     * bye - closes and saves the app.
     * todo [description] - adds todo to list.
     * deadline [description] /by [date] - adds deadline to list.
     * event [description] /at [date] - adds events to list.
     */
    private static void echo() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // Loading save file


        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        while (!s.equals("bye")) {
            try {
                if (s.equals("list")) {
                    int index = 1;

                    System.out.println("---------");
                    for (Task item : tasks) {
                        System.out.println(index + ". " + item.toString());
                        index++;
                    }
                    System.out.println("---------");
                } else if (s.startsWith("done")) {
                    int index = Integer.parseInt(s.split(" ")[1]) - 1;
                    tasks.get(index).setStatus(true);

                    System.out.println("---------");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index));
                    System.out.println("---------");
                } else if (s.startsWith("delete")) {
                    int index = Integer.parseInt(s.replace("delete", "").trim()) - 1;

                    System.out.println("---------");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(index));
                    tasks.remove(index);
                    System.out.println("Now you have " + tasks.size() + " task in the list");
                    System.out.println("---------");

                } else {
                    // Adding tasks
                    if (s.startsWith("todo")) {
                        if (s.replace("todo", "").trim().equals("")) {
                            throw new DukeException("ToDos need to have a description");
                        }
                        tasks.add(new ToDo(s.replace("todo", "").trim()));
                    } else if (s.startsWith("deadline")) {
                        if (!s.contains("/by")) {
                            throw new DukeException("deadlines require a /by");
                        }
                        String[] data = s.replace("deadline ", "").split("/by");
                        tasks.add(new Deadline(data[0].trim(), data[1].trim()));
                    } else if (s.startsWith("event")) {
                        if (!s.contains("/at")) {
                            throw new DukeException("events require an /at");
                        }

                        String[] data = s.replace("event ", "").split("/at");
                        tasks.add(new Event(data[0].trim(), data[1].trim()));
                    } else {
                        throw new DukeException("Sorry I don't know what that means");
                    }

                    int len = tasks.size();
                    System.out.println("---------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(len - 1).toString());
                    System.out.println("Now you have " + len + " task in the list");
                    System.out.println("---------");
                }

                // saveList();
                s = in.nextLine();
            } catch (DukeException err) {
                System.out.println("---------");
                System.out.println(err.getMessage());
                System.out.println("---------");
                s = in.nextLine();
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }

    /**
     * Loads save file from data/save.txt.
     */
    public static void loadSave() {
        try {
            File f = new File("data/save.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                String[] nextItem = nextLine.split("\\Q|\\E", 0);
                switch (nextItem[0]) {
                case "T":
                    tasks.add(new ToDo(nextItem[1].trim(), nextItem[2].trim().equals("1")));
                    break;
                case "D":
                    tasks.add(new Deadline(nextItem[1].trim(), nextItem[2].trim().equals("1"), nextItem[3].trim()));
                    break;
                case "E":
                    tasks.add(new Event(nextItem[1].trim(), nextItem[2].trim().equals("1"), nextItem[3].trim()));
                    break;
                }
            }

            System.out.println("Save File Imported");
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("No save file found.");
        }
    }

    /**
     * Overwrites data/save.txt with the items currently in list.
     * If data folder or save.txt file does not exist, file or folder is created.
     */
    public static void saveList() {
        try {
            // Check for valid directory
            File f = new File("data/save.txt");
            if (!f.getParentFile().exists()) {
                //noinspection ResultOfMethodCallIgnored
                f.getParentFile().mkdirs();
            }


            FileWriter fw = new FileWriter("data/save.txt");


            for (Task t : tasks) {
                fw.write(t.save() + "\n");
            }
            fw.close();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
