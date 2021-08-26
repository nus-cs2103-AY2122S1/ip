import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Magnolia\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        try {
            File list_data = new File("data/duke.txt");
            Scanner reader = new Scanner(list_data);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(" \\| ");
                switch (data[0]) {
                    case "todo":
                        list.add(new Task.Todo(data[2], data[1].equals("0") ? false : true));
                        break;
                    case "deadline":
                        list.add(new Task.Deadline(data[2], data[1].equals("0") ? false : true, data[3]));
                        break;
                    case "event":

                        list.add(new Task.Event(data[2], data[1].equals("0") ? false : true, data[3]));
                        break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            File newFile = new File("data/duke.txt");
            try {
                Files.createDirectories(Paths.get("data/"));
                newFile.createNewFile();
            } catch (IOException a) {
                System.out.println("error");
                a.printStackTrace();
            }


        }

        while(true) {
            try {
                String input = sc.nextLine();
                String first_word = input.split(" ")[0];


                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("All tasks:");
                    int i = 1;
                    for (Task item : list) {
                        System.out.println(i + ". " + item.getTaskType() + item.getStatusIcon() + " " + item.getDescription());
                        i++;
                    }

                } else if (first_word.equals("done")) {
                    int index;
                    try {
                        index = Integer.parseInt(input.split(" ")[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Sorry, please enter an integer after 'done'. (e.g. done 2)");
                    }
                    Task temp;
                    try {
                        temp = list.get(index);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Sorry, there are only " + list.size() + " tasks.");
                    }
                    temp.setStatus(true);
                    System.out.println("Marked as done:\n" + temp.getDescription());
                    save(list);

                } else if (first_word.equals("delete")) {
                    int index;
                    try {
                        index = Integer.parseInt(input.split(" ")[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Sorry, please enter an integer after 'delete'. (e.g. delete 2)");
                    }

                    Task temp = list.get(index);
                    System.out.println("Deleted:\n" + temp.getDescription());
                    list.remove(index);
                    System.out.println("There are " + list.size() + " tasks remaining in the list");
                    save(list);
                }
                else {
                    Task task = null;
                    String remaining;
                    try {
                        remaining = input.split(" ", 2)[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Sorry, tasks must include descriptions.");
                    }
                    if(first_word.equals("todo")) {
                        task = new Task.Todo(remaining, false);

                    } else if(first_word.equals("deadline")) {
<<<<<<< HEAD
                        String[] temp = remaining.split("by", 2);
                        task = new Task.Deadline(temp[0], false, temp[1]);

                    } else if(first_word.equals("event")) {
                        String[] temp = remaining.split("at", 2);
                        task = new Task.Event(temp[0], false, temp[1]);
=======
                        String[] temp = remaining.split("by ", 2);
                        task = new Task.Deadline(temp[0], temp[1]);

                    } else if(first_word.equals("event")) {
                        String[] temp = remaining.split("at ", 2);
                        task = new Task.Event(temp[0], temp[1]);
>>>>>>> branch-Level-8
                    } else {
                        throw new DukeException("Sorry, I don't understand what that means. :(");
                    }
                    list.add(task);
                    System.out.println("Added: " + task.getTaskType() + task.getStatusIcon() + " " + task.getDescription());
                    System.out.println("There are " + list.size() + " tasks in the list");
                    save(list);
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());

            }

        }
    }

    public static void save(ArrayList<Task> list) {
        try {
            File list_data = new File("data/duke.txt");
            FileWriter myWriter = new FileWriter(list_data);
            String data = "";
            for (Task task : list) {
                data += task.toString() + "\n";
            }
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
