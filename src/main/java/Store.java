import java.io.*;

public class Store {
    private static final String text = "./data/duke.txt";
    private static BufferedWriter writer;

    //takes in a task and adds it to the list while writing it to a file
    static void writeToFile(String task) throws DukeException {
        try {
            switch (task) {
                case "deadline" -> throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                case "event" -> throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                case "todo" -> throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            if (task.startsWith("deadline")) {
                Deadline d = new Deadline(task);
                TaskList.add(d);
                writer.write(d.toString());
            } else if (task.startsWith("event") && task.contains("/at ")) {
                Event e = new Event(task);
                TaskList.add(e);
                writer.write(e.toString());
            } else if (task.startsWith("todo")) {
                Todo t = new Todo(task);
                TaskList.add(t);
                writer.write(t.toString() + "\n");
            }
            writer.close();
            FileWriter fWriter = new FileWriter(text, true);
            writer = new BufferedWriter(fWriter);
        } catch (DukeException e) {
            System.out.println("DukeException occurred");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    static void readFromFile() throws IOException {
        try {
            //create a BufferedReader which loads the data when Duke starts up
            String line;
            FileReader fReader = new FileReader(text);
            BufferedReader reader = new BufferedReader(fReader);
            FileWriter fWriter = new FileWriter(text, true);
            writer = new BufferedWriter(fWriter);

            //reading data
            while((line = reader.readLine()) != null) {
                String[] curr = new String[4];
                curr[0] = line.substring(1,2);
                curr[1] = line.substring(4,5);
                curr[2] = line.substring(7);
                switch (curr[1]) {
                    case "T" -> {
                        Tasks todo = new Todo("todo " + curr[2]);
                        TaskList.add(todo);
                        if (curr[1].equals("X")) {
                            todo.markAsDone();
                        }
                    }
                    case "E" -> {
                        Tasks event = new Event("event " + curr[2]);
                        TaskList.add(event);
                        if (curr[1].equals("X")) {
                            event.markAsDone();
                        }
                    }
                    case "D" -> {
                        Tasks deadline = new Deadline("deadline " + curr[2]);
                        TaskList.add(deadline);
                        if (curr[1].equals("X")) {
                            deadline.markAsDone();
                        }
                    }
                    default -> {
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            File dir = new File(text);
            FileWriter fWriter = new FileWriter(text, true);
            writer = new BufferedWriter(fWriter);
        }
    }

    public static void overwrite() throws IOException {
        FileWriter fWriter = new FileWriter(text, false);
        writer = new BufferedWriter(fWriter);
        writer.write(TaskList.overwrite());
        writer.close();
        FileWriter newWriter = new FileWriter(text, true);
        writer = new BufferedWriter(newWriter);
    }
}
