import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final List<Task> list;

    public Duke(){
        this.list = new ArrayList<>(100);
    }

    private void begin() {
        try {
            readTasks(list);
        } catch (Exception e) {
            System.out.println("Could not read the data file: " + e.getMessage());
        }

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    listTasks();
                } else if (input.startsWith("done")) {
                    try {
                        int listIndex = Integer.parseInt(input.substring(5));
                        if (listIndex <= list.size() && listIndex >= 1) {
                            completeTask(listIndex);
                            writeTasks();
                        } else {
                            throw new DukeException("Couldn't find that task in the list! Try again.");
                        }
                    } catch (NumberFormatException e) { //If 'done' is followed by a non-integer
                        throw new DukeException("Please make sure only a number follows the command 'done'. Try again.");
                    } catch (StringIndexOutOfBoundsException e) { //If 'done' is not followed by anything
                        throw new DukeException("Please add a number after the command 'done'. Try again.");
                    }
                } else if (input.startsWith("deadline")) {
                    if (!input.contains("/by")) {
                        throw new DukeException("Please state the deadline for this task with /by! Try again.");
                    } else {
                        try {
                            int deadlineIndex = input.indexOf("/by") + 4;
                            String deadline = input.substring(deadlineIndex);
                            String firstTrimmed = input.substring(9);
                            String lastTrimmed = firstTrimmed.substring(0, firstTrimmed.indexOf("/by"));
                            Deadline createdDeadlineTask = new Deadline(lastTrimmed, false, deadline);
                            addToList(createdDeadlineTask);
                            System.out.println("Got it. I've added this task:\n" + "  " + createdDeadlineTask + "\n"
                                    + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                                    " in the list.");
                            writeTasks();
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeException("Please ensure that there is a task description and deadline. " +
                                    "Try again.");
                        }
                    }
                } else if (input.startsWith("todo")) {
                    try {
                        String trimmed = input.substring(5);
                        Todo createdTodoTask = new Todo(trimmed, false);
                        addToList(createdTodoTask);
                        System.out.println("Got it. I've added this task:\n" + "  " + createdTodoTask + "\n"
                                + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                                " in the list.");
                        writeTasks();
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeException("Please add the task information. Try again.");
                    }
                } else if (input.startsWith("event")) {
                    if (!input.contains("/at")) {
                        throw new DukeException("Please state when the event will be held with /at! Try again.");
                    } else {
                        try {
                            int atIndex = input.indexOf("/at") + 4;
                            String at = input.substring(atIndex);
                            String firstTrimmed = input.substring(6);
                            String lastTrimmed = firstTrimmed.substring(0, firstTrimmed.indexOf("/at"));
                            Event createdEventTask = new Event(lastTrimmed, false, at);
                            addToList(createdEventTask);
                            System.out.println("Got it. I've added this task:\n" + "  " + createdEventTask + "\n"
                                    + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                                    " in the list.");
                            writeTasks();
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeException("Please ensure that there is an event time. Try again.");
                        }
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int toDeleteIndex = Integer.parseInt(input.substring(7));
                        deleteTask(toDeleteIndex);
                        writeTasks();
                    } catch (NumberFormatException e){
                        throw new DukeException("Please make sure only a number follows the command 'delete'. " +
                                "Try again.");
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeException("Please add a number after the command 'delete'. Try again.");
                    }
                } else {
                    throw new DukeException("I didn't quite get what you meant. To add a task, begin with " +
                            "'deadline', 'event' or 'todo'.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void addToList(Task task) {
        this.list.add(task);
    }

    private void listTasks() throws DukeException {
        if (list.size() < 1) {
            throw new DukeException("You haven't added anything to the list yet! Try adding something.");
        } else {
            if (list.size() == 1) {
                System.out.println("Here is the sole task in your list:");
            } else {
                System.out.println("Here are the tasks in your list:");
            }
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
        }
    }

    private void deleteTask(int index) throws DukeException {
        if (list.size() < 1) {
            throw new DukeException("You haven't added anything to the list yet! Try adding something before " +
                    "deleting.");
        } else if (index <= list.size() && index >= 1) {
            Task toDelete = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n" + "  " + toDelete + "\n" + "Now you have " +
                    list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
        } else {
            throw new DukeException("Couldn't find that task in the list! Try again.");
        }
    }

    private void completeTask(int index) {
        Task task = list.get(index - 1);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + "  " + task);
    }

    private void writeTasks() throws DukeException{
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./data/duke.txt"));
            for (Task task : list) {
                bw.write(task.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Couldn't write the tasks!");
        }
    }

    private void readTasks(List<Task> list) throws DukeException {
        File file = new File("./data/duke.txt");
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating data file: " + e.getMessage());
            }
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/duke.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(" ");

                switch(splitLine[0].charAt(1)) {
                case 'T':
                    String todoName = splitLine[1];
                    boolean todoStatus = (splitLine[0].charAt(4) == 'X');
                    list.add(new Todo(todoName, todoStatus));
                    break;
                case 'D':
                    String deadlineName = splitLine[1] + " ";
                    boolean deadlineStatus = (splitLine[0].charAt(4) == 'X');
                    String deadlineByWithBracket = line.substring(line.lastIndexOf("(by: ") + 5);
                    String deadlineBy = deadlineByWithBracket.substring(0, deadlineByWithBracket.length() - 1);
                    list.add(new Deadline(deadlineName, deadlineStatus, deadlineBy));
                    break;
                case 'E':
                    String eventName = splitLine[1] + " ";
                    boolean eventStatus = (splitLine[0].charAt(4) == 'X');
                    String eventAtWithBracket = line.substring(line.lastIndexOf("(at: ") + 5);
                    String eventAt = eventAtWithBracket.substring(0, eventAtWithBracket.length() - 1);
                    list.add(new Event(eventName, eventStatus, eventAt));
                    break;
                }
                }
            br.close();
            } catch (IOException e) {
            throw new DukeException("Couldn't read the tasks!");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.begin();
    }
}
