import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

enum Activity {
    TODO, DONE, EVENT, DELETE, DEADLINE, BYE, LIST, NORMAL
}

public class Duke {

    public static void main(String[] args) {

        final String INTRO = "Hello! I'm Duke\n" +
                "What can I do for you?";

        final String OUTRO = "Bye. Hope to see you again soon!";
        Scanner sc = new Scanner(System.in);

        final String FILE_NAME = "data/duke.txt";

        ArrayList<Task> lst = new ArrayList<>();

        String command;
        System.out.println(INTRO);

        try {
            lst = readFromFile(FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while (sc.hasNextLine()) {
            try {
                command = sc.nextLine();

                Activity activity;
                if (command.equals("bye")) {
                    activity = Activity.BYE;

                } else if (command.equals("list")) {
                    activity = Activity.LIST;

                } else if (command.startsWith("done")) {
                    activity = Activity.DONE;

                } else if (command.startsWith("todo")) {
                    activity = Activity.TODO;

                } else if (command.startsWith("event")) {
                    activity = Activity.EVENT;

                } else if (command.startsWith("deadline")) {
                    activity = Activity.DEADLINE;

                } else if (command.startsWith("delete")){
                    activity = Activity.DELETE;

                } else {
                    activity = Activity.NORMAL;
                }

                switch (activity) {
                case BYE: {
                    System.out.println(OUTRO);
                    return;
                }
                case DONE: {
                    int index = Integer.parseInt(command.substring(5)) - 1;
                    String subtext = "Nice! I've marked this task as done:\n";
                    Task currentTask = lst.get(index);
                    currentTask.setDone();
                    System.out.println(subtext + currentTask.getStatusAndDescription());
                    writeToFile(FILE_NAME, lst);
                    break;
                }
                case LIST: {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        Task currentTask = lst.get(i);
                        System.out.println((i + 1) + "." + currentTask.getStatusAndDescription());
                    }
                    writeToFile(FILE_NAME, lst);
                    break;
                }
                case TODO: {
                    String desc = command.substring(4);

                    if (desc.isEmpty()) {
                        throw new DukeException("todo");
                    }

                    ToDo toDo = new ToDo(command.substring(5));
                    lst.add(toDo);
                    System.out.format("Got it. I've added this task:\n" + toDo.getStatusAndDescription() + "\n"
                            + "Now you have %d tasks in this list.\n", lst.size());
                    writeToFile(FILE_NAME, lst);
                    break;
                }
                case EVENT: {
                    String desc = command.substring(5);

                    if (desc.isEmpty()) {
                        throw new DukeException("event");
                    }

                    int escapeIndex = command.lastIndexOf("/");
                    Deadline deadline = new Deadline(command.substring(6, escapeIndex - 1), command.substring(escapeIndex + 4));
                    lst.add(deadline);
                    System.out.format("Got it. I've added this task:\n" + deadline.getStatusAndDescription() + "\n"
                            + "Now you have %d tasks in this list.\n", lst.size());
                    writeToFile(FILE_NAME, lst);
                    break;
                }
                case DELETE: {
                    int index = Integer.parseInt(command.substring(7)) - 1;

                    if (index >= lst.size()) {
                        throw new DeleteException();
                    }

                    Task currentTask = lst.get(index);
                    lst.remove(index);
                    System.out.format("Noted. I've removed this task:\n" + currentTask.getStatusAndDescription() + "\nNow you have %d tasks in the list\n", lst.size());
                    writeToFile(FILE_NAME, lst);
                    break;
                }
                case NORMAL: {
                    lst.add(new Task(command));
                    System.out.println("added: " + command);
                    writeToFile(FILE_NAME, lst);
                    break;
                }
                case DEADLINE: {
                    String desc = command.substring(8);

                    if (desc.isEmpty()) {
                        throw new DukeException("deadline");
                    }

                    int escapeIndex = command.lastIndexOf("/");
                    Deadline deadline = new Deadline(command.substring(9, escapeIndex - 1), command.substring(escapeIndex + 4));
                    lst.add(deadline);
                    System.out.format("Got it. I've added this task:\n" + deadline.getStatusAndDescription() + "\n"
                            + "Now you have %d tasks in this list.\n", lst.size());
                    writeToFile(FILE_NAME, lst);
                    break;
                }
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means! Please fill in a valid command");
            } catch (DukeException | DeleteException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void writeToFile(String filePath, ArrayList<Task> lst) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            String status = task.isDone ? "1" : "0";
            String date = "";
            String activity = "";
            String information = "";
            if (task instanceof Event) {
                activity = "E";
                information = task.getDescription();
                date = ((Event) task).getDate();
                String desc = String.format("%s | %s | %s | %s\n",activity, status, information,date);
                fw.write(desc);
            } else if (task instanceof Deadline) {
                activity = "D";
                information = task.getDescription();
                date = ((Deadline) task).getDate();
                String desc = String.format("%s | %s | %s | %s\n",activity, status, information, date);
                fw.write(desc);
            } else if (task instanceof ToDo) {
                activity = "T";
                information = task.getDescription();
                String desc = String.format("%s | %s | %s\n",activity, status, information);
                fw.write(desc);
            }
        }
        fw.close();
    }

    private static ArrayList<Task> readFromFile(String filePath) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filePath));
        ArrayList<Task> lst = new ArrayList<>();
        while (s.hasNext()) {
            String text = s.nextLine();
            String activity = text.substring(0, 1);
            String status = text.substring(4, 5);
            String desc, date;

            if (activity.equals("T")) {
                desc = text.substring(8);
                ToDo todo = new ToDo(desc);
                if (status.equals("1")) {
                    todo.setDone();
                }
                lst.add(todo);
            } else {
                int thirdBarId = text.indexOf('|', 7);
                desc = text.substring(8, thirdBarId - 1);
                date = text.substring(thirdBarId + 2);

                if (activity.equals("D")) {
                    Deadline deadline = new Deadline(desc, date);
                    if (status.equals("1")) {
                        deadline.setDone();
                    }
                    lst.add(deadline);
                } else {
                    Event event = new Event(desc, date);
                    if (status.equals("1")) {
                        event.setDone();
                    }
                    lst.add(event);
                }
            }
        }
        System.out.println("Your file has been loaded");
        return lst;
    }
}
