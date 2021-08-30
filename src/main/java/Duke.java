import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        List<Task> arr = new ArrayList<>();
        int count = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);
        File folder = new File("data");
        folder.mkdir();
        // System.out.println(file.mkdir());
        File file = new File(folder, "duke.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }

        Scanner myObj = new Scanner(System.in);
        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                String bye = "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";
                System.out.println(bye);
                return;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
/*
                for (int i = 0; i < count; i++) {
                    System.out.println("  " + (i + 1) + "." + arr.get(i).toString());
                }*/
                try {
                    Scanner reader = new Scanner(file);
                    int n = 0;
                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        // System.out.println(data);
                        String[] parsed = data.split(" \\| ");

                        String type = parsed[0];
                        String description = parsed[2];
                        n++;

                        if (type.equals("T")) {
                            Todo todo = new Todo(description);
                            if (parsed[1].equals("1")) {
                                todo.markAsDone();
                            }
                            System.out.println("  " + (n) + "." + todo.toString());
                        } else if (type.equals("D")) {
                            Deadline deadline = new Deadline(description, parsed[3]);
                            if (parsed[1].equals("1")) {
                                deadline.markAsDone();
                            }
                            System.out.println("  " + (n) + "." + deadline.toString());
                        } else if (type.equals("E")) {
                            Event event = new Event(description, parsed[3]);
                            if (parsed[1].equals("1")) {
                                event.markAsDone();
                            }
                            System.out.println("  " + (n) + "." + event.toString());
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("done ")) {
                int task = Integer.parseInt(input.split(" ")[1]);
                int n = 0;
                // arr.get(task).markAsDone();
                try {
                    Scanner reader = new Scanner(file);
                    List<String> lines = new ArrayList<>();
                    while (reader.hasNextLine()) {
                        n++;
                        String data = reader.nextLine();
                        if (task == n) {
                            String update = data.substring(0,4)+'1'+data.substring(5);
                            // System.out.println(update);
                            // System.out.println(data);
                            lines.add(update);
                        } else {
                            lines.add(data);
                        }
                        /*
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("    " + arr.get(task).toString());
                        System.out.println("____________________________________________________________");
                         */
                        // mark as done
                    }

                    file.delete();
                    try {
                        file.createNewFile();
                        PrintWriter writer = new PrintWriter(file);
                        for (String line : lines) {
                            writer.println(line);
                        }
                        writer.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("todo")) {
                try {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("____________________________________________________________\n" +
                                "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                "____________________________________________________________");
                    }
                    String task = input.split(" ")[1];
                    // arr.add(new Todo(task));
                    count++;
                    String s = "";
                    if (count > 1 && count != 0) {
                        s = "s";
                    }
                    String reply = "____________________________________________________________\n" +
                            "Got it. I've added this task:\n  " +
                            arr.get(count-1) + "\n" +
                            "Now you have " + count + " task" + s + " in the list.\n" +
                            "____________________________________________________________";
                    System.out.println(reply);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("deadline")) {
                String[] n = input.split(" ", 2)[1].split(" /by ");
                arr.add(new Deadline(n[0], n[1]));
                count++;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        arr.get(count-1) + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else if (input.startsWith("event")) {
                String[] n = input.split(" ", 2)[1].split(" /at ");
                arr.add(new Event(n[0], n[1]));
                count++;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        arr.get(count-1) + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else if (input.startsWith("delete")) {
                int pos = Integer.parseInt(input.split(" ")[1]);
                Task task = arr.get(pos-1);
                arr.remove(pos-1);
                count--;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Noted. I've removed this task:\n  " +
                        task + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else {
                System.out.println(new DukeException("____________________________________________________________\n" +
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________"));
            }
        }
    }
}


