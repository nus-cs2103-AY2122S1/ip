package Duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public static void parse(String input, Storage storage) {
        int count = 0; //tmp
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
                Scanner reader = new Scanner(storage.getFile());
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
                        Deadline deadline = new Deadline(description, LocalDate.parse(parsed[3]));
                        if (parsed[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        System.out.println("  " + (n) + "." + deadline.toString());
                    } else if (type.equals("E")) {
                        Event event = new Event(description, LocalDate.parse(parsed[3]));
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
                Scanner reader = new Scanner(storage.getFile());
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

                storage.getFile().delete();
                try {
                    storage.getFile().createNewFile();
                    PrintWriter writer = new PrintWriter(storage.getFile());
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
                Todo todo = new Todo(task);
                // arr.add(new Duke.Todo(task));
                try {
                    Scanner reader = new Scanner(storage.getFile());
                    List<String> lines = new ArrayList<>();
                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        lines.add(data);
                    }

                    storage.getFile().delete();
                    try {
                        storage.getFile().createNewFile();
                        PrintWriter writer = new PrintWriter(storage.getFile());
                        for (String line : lines) {
                            writer.println(line);
                        }
                        writer.println(todo.addToFile());
                        writer.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
                count++;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        todo.toString() + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } catch (DukeException e) {
                System.out.println(e);
            }
        } else if (input.startsWith("deadline")) {
            String[] n = input.split(" ", 2)[1].split(" /by ");
            // arr.add(new Duke.Deadline(n[0], n[1]));
            String task = n[0];
            LocalDate time = LocalDate.parse(n[1]);
            Deadline deadline = new Deadline(task, time);
            try {
                Scanner reader = new Scanner(storage.getFile());
                List<String> lines = new ArrayList<>();
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    lines.add(data);
                }

                storage.getFile().delete();
                try {
                    storage.getFile().createNewFile();
                    PrintWriter writer = new PrintWriter(storage.getFile());
                    for (String line : lines) {
                        writer.println(line);
                    }
                    writer.println(deadline.addToFile());
                    writer.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
            count++;
            String s = "";
            if (count > 1 && count != 0) {
                s = "s";
            }
            String reply = "____________________________________________________________\n" +
                    "Got it. I've added this task:\n  " +
                    deadline.toString() + "\n" +
                    "Now you have " + count + " task" + s + " in the list.\n" +
                    "____________________________________________________________";
            System.out.println(reply);
        } else if (input.startsWith("event")) {
            String[] n = input.split(" ", 2)[1].split(" /at ");
            // arr.add(new Duke.Event(n[0], n[1]));
            String task = n[0];
            LocalDate time = LocalDate.parse(n[1]);
            Event event = new Event(task, time);
            try {
                Scanner reader = new Scanner(storage.getFile());
                List<String> lines = new ArrayList<>();
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    lines.add(data);
                }

                storage.getFile().delete();
                try {
                    storage.getFile().createNewFile();
                    PrintWriter writer = new PrintWriter(storage.getFile());
                    for (String line : lines) {
                        writer.println(line);
                    }
                    writer.println(event.addToFile());
                    writer.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
            count++;
            String s = "";
            if (count > 1 && count != 0) {
                s = "s";
            }
            String reply = "____________________________________________________________\n" +
                    "Got it. I've added this task:\n  " +
                    event.toString() + "\n" +
                    "Now you have " + count + " task" + s + " in the list.\n" +
                    "____________________________________________________________";
            System.out.println(reply);
        } else if (input.startsWith("delete")) {
            int pos = Integer.parseInt(input.split(" ")[1]);
            //Duke.Task task = arr.get(pos-1);
            //arr.remove(pos-1);
            count--;
            String s = "";
            if (count > 1 && count != 0) {
                s = "s";
            }
            String reply = "____________________________________________________________\n" +
                    "Noted. I've removed this task:\n  " +
                    //task + "\n" +
                    "Now you have " + count + " task" + s + " in the list.\n" +
                    "____________________________________________________________";
            System.out.println(reply);
        } else if (input.startsWith("find")) {
            String task = input.split(" ")[1];
        } else {
            System.out.println(new DukeException("____________________________________________________________\n" +
                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "____________________________________________________________"));
        }
    }
}

//
