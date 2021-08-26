import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Duke {
    public static void main(String[] args) {
        try {
            File taskList = new File("C:\\Users\\Rickie\\Documents\\University\\Year 2\\Semester 1\\CS2103T\\week 2\\ip\\Data\\taskList.txt");
            ArrayList<Task> list = new ArrayList<>();
            int listSize = 0;
            if (!taskList.createNewFile()) {
                Scanner fileReader = new Scanner(taskList);
                while(fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] splitString = line.split(" \\| ");
                    if (splitString[0].equals("T")) {
                        Todo t = new Todo(line.substring(8));
                        if (line.substring(4,5).equals("1")) {
                            t.markAsDone();
                        }
                        list.add(t);
                    } else if (splitString[0].equals("D")) {
                        Deadline d = new Deadline(splitString[2], splitString[3]);
                        if (splitString[1].equals("1")) {
                            d.markAsDone();
                        }
                        list.add(d);
                    } else {
                        Event e = new Event(splitString[2], splitString[3]);
                        if (splitString[1].equals("1")) {
                            e.markAsDone();
                        }
                        list.add(e);
                    }
                }
            }
            Scanner in = new Scanner(System.in);
            System.out.println("    ____________________________________________________________");
            System.out.println("     Hello! I'm Duke");
            System.out.println("     What can I do for you?");
            System.out.println("    ____________________________________________________________");
            String input = in.nextLine();
            while(!input.replaceAll("\\s","").toLowerCase().equals("bye")) {
                try {
                    String inputLower = input.toLowerCase();
                    if (inputLower.replaceAll("\\s", "").equals("list")) {
                        if (list.size() == 0) {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("     You have no tasks in your list!");
                        } else {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Here are the tasks in your list:");
                            for (int i = 0; i < list.size(); i++) {
                                Task item = list.get(i);
                                System.out.println(String.format("     %d. %s", (i + 1), item.toString()));
                            }
                        }
                    } else if (input.length() > 3 && inputLower.substring(0, 4).equals("done")) {
                        if (input.replaceAll("\\s", "").length() == 4) {
                            throw new DukeException(DukeException.Type.EmptyDone);
                        } else {
                            String modInput = input.replaceAll("\\s", "");
                            int index = Integer.parseInt(modInput.substring(modInput.length() - 1)) - 1;
                            if (list.size() <= index) {
                                throw new DukeException(DukeException.Type.InvalidTaskNumber);
                            } else {
                                Task item = list.get(index);
                                item.markAsDone();
                                System.out.println("    ____________________________________________________________");
                                System.out.println("     Nice! I've marked this task as done:");
                                System.out.println(String.format("       %s", item.toString()));
                            }
                        }
                    } else if (input.length() > 5 && inputLower.substring(0, 6).equals("delete")) {
                        if (input.replaceAll("\\s", "").length() == 6) {
                            throw new DukeException(DukeException.Type.EmptyDelete);
                        } else {
                            String modInput = input.replaceAll("\\s", "");
                            int index = Integer.parseInt(modInput.substring(modInput.length() - 1)) - 1;
                            if (list.size() <= index) {
                                throw new DukeException(DukeException.Type.InvalidTaskNumber);
                            } else {
                                Task item = list.remove(index);
                                System.out.println("    ____________________________________________________________");
                                System.out.println("     Noted. I've removed this task:");
                                System.out.println(String.format("       %s", item.toString()));
                                System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                            }
                        }
                    } else if (input.length() >= 4 && inputLower.substring(0, 4).equals("todo")) {
                        if (input.replaceAll("\\s", "").length() == 4) {
                            throw new DukeException(DukeException.Type.EmptyTodo);
                        } else {
                            Todo t = new Todo(input.substring(5));
                            System.out.println();
                            list.add(t);
                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Got it. I've added this task:");
                            System.out.println("       " + t.toString());
                            System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                        }
                    } else if (input.length() >= 5 && inputLower.substring(0, 5).equals("event")) {
                        if (input.replaceAll("\\s", "").length() == 5) {
                            throw new DukeException(DukeException.Type.EmptyEvent);
                        } else {
                            String[] splitString = input.substring(6).split(" /at ", 2);
                            Event e = new Event(splitString[0], splitString[1]);
                            list.add(e);
                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Got it. I've added this task:");
                            System.out.println("       " + e.toString());
                            System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                        }
                    } else if (input.length() >= 8 && inputLower.substring(0, 8).equals("deadline")) {
                        if (input.replaceAll("\\s", "").length() == 8) {
                            throw new DukeException(DukeException.Type.EmptyDeadline);
                        } else {
                            String[] splitString = input.substring(9).split(" /by ", 2);
                            Deadline d = new Deadline(splitString[0], splitString[1]);
                            list.add(d);
                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Got it. I've added this task:");
                            System.out.println("       " + d.toString());
                            System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                        }
                    } else {
                        throw new DukeException(DukeException.Type.InvalidCommand);
                    }
                    System.out.println("    ____________________________________________________________");
                    input = in.nextLine();
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    input = in.nextLine();
                }
            }
            taskList.delete();
            FileWriter writer = new FileWriter("C:\\Users\\Rickie\\Documents\\University\\Year 2\\Semester 1\\CS2103T\\week 2\\ip\\Data\\taskList.txt");
            for (int i = 0; i < list.size(); i++) {
                File tasks = new File("C:\\Users\\Rickie\\Documents\\University\\Year 2\\Semester 1\\CS2103T\\week 2\\ip\\Data\\taskList.txt");
                Task t = list.get(i);
                String isDone = t.isDone ? "1" : "0";
                if (t instanceof Todo) {
                    String line = "T | " + isDone + " | " + t.description;
                    writer.write(line + "\n");
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    String line = "D | " + isDone + " | " + t.description + " | " + d.by;
                    writer.write(line + "\n");
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    String line = "E | " + isDone + " | " + t.description + " | " + e.at;
                    writer.write(line + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
