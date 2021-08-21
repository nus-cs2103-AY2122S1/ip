import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        List<Task> savedTasks = new ArrayList<>();
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File saveFile = new File("data/duke.txt");
        try {
            if (saveFile.createNewFile()) {
                System.out.println("Save file created!");
            } else {
                System.out.println("Save file found!");
            }
        } catch (IOException e) {
            System.out.println("Error loading save file :(");
            e.printStackTrace();
        }
        try {
            Scanner sc2 = new Scanner(saveFile);
            while (sc2.hasNextLine()) {
                String nextLine = sc2.nextLine();
                String[] splitLine = nextLine.split("[|]");
                if (splitLine.length == 3) {
                    if (splitLine[1].equals("0")) {
                        savedTasks.add(new Todo(splitLine[2], false));
                    }
                    else {
                        savedTasks.add(new Todo(splitLine[2], true));
                    }

                }
                else if (splitLine.length == 4){
                    String[] dateParameters = splitLine[3].split("-");
                    int day = Integer.parseInt(dateParameters[2]);
                    int month = Integer.parseInt(dateParameters[1]);
                    int year = Integer.parseInt(dateParameters[0]);
                    LocalDate localDate = LocalDate.of(year, month, day);
                    if (splitLine[0].equals("D")) {
                        if (splitLine[1].equals("0")) {
                            savedTasks.add(new Deadline(splitLine[2],
                                    false,
                                    localDate));
                        }
                        else {
                            savedTasks.add(new Deadline(splitLine[2],
                                    true,
                                    localDate));
                        }
                    }
                    else {
                        if (splitLine[1].equals("0")) {
                            savedTasks.add(new Event(splitLine[2],
                                    false,
                                    localDate));
                        }
                        else {
                            savedTasks.add(new Event(splitLine[2],
                                    true,
                                    localDate));
                        }
                    }
                }
            }
            sc2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\nWhat can I do for you today?");

        while (sc.hasNextLine()) {
            String userInput = sc.next();
            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            }
            else if (userInput.toLowerCase().equals("list")) {
                if (savedTasks.size() == 0) {
                    System.out.println("No tasks in your list! Add one using todo, deadline or event!");
                }
                else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < savedTasks.size(); i++) {
                        Task currTask = savedTasks.get(i);
                        int index = i + 1;
                        System.out.println(index + ". " + currTask);
                    }
                }
            }
            else if (userInput.toLowerCase().equals("done")) {
                int nextNum = sc.nextInt();
                if (nextNum > savedTasks.size() || nextNum < 1) {
                    DukeException exception = new DukeException("Number out of range!");
                    System.out.println(exception);
                }
                else {
                    Task oldTask = savedTasks.get(nextNum-1);
                    Task newTask = oldTask.setDone();
                    savedTasks.set(nextNum-1, newTask);
                    System.out.println("Nice! I've marked this task as done:\n" + newTask);
                }

            }
            else if (userInput.toLowerCase().equals("todo")) {
                String restOfLine = sc.nextLine();
                if (restOfLine.equals("")) {
                    DukeException exception = new DukeException("Description of todo cannot be empty :(");
                    System.out.println(exception);
                }
                else {
                    Task newTask = new Todo(restOfLine);
                    savedTasks.add(newTask);
                    System.out.println("I've added this task:\n" + newTask);
                    System.out.println("Now you have " + savedTasks.size() + " tasks in the list!");
                }

            }
            else if (userInput.toLowerCase().equals("deadline")) {
                String body = "";
                String deadline = "";
                int counter = 0;
                String restOfLine = sc.nextLine();
                String[] words = restOfLine.split(" ");
                for (int i = 1; i < words.length; i++) {
                    if (words[i].equals("/by")) {
                        counter = i+1;
                        break;
                    }
                    else {
                        body += " " + words[i];
                    }
                }
                if (body.equals("")) {
                    DukeException exception = new DukeException("Description of deadline cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                if (counter == 0) {
                    DukeException exception = new DukeException("Deadline of deadline cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                for (int i = counter; i < words.length; i ++) {
                    deadline += " " + words[i];
                }
                if (deadline.equals("")) {
                    DukeException exception = new DukeException("Deadline of deadline cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                if (deadline.length() != 11) {
                    DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                    System.out.println(exception);
                    continue;
                }
                String[] deadlineArray = deadline.substring(1).split("-");
                int year = Integer.parseInt(deadlineArray[0]);
                int month = Integer.parseInt(deadlineArray[1]);
                int day = Integer.parseInt(deadlineArray[2]);
                if (month > 12 || day > 31) {
                    DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                    System.out.println(exception);
                    continue;
                }
                LocalDate newDeadline = LocalDate.of(year, month, day);
                Task newTask = new Deadline(body, newDeadline);
                savedTasks.add(newTask);
                System.out.println("I've added this task:\n" + newTask);
                System.out.println("Now you have " + savedTasks.size() + " tasks in the list!");
            }
            else if (userInput.toLowerCase().equals("event")) {
                String body = "";
                String deadline = "";
                int counter = 0;
                String restOfLine = sc.nextLine();
                String[] words = restOfLine.split(" ");
                for (int i = 1; i < words.length; i++) {
                    if (words[i].equals("/at")) {
                        counter = i+1;
                        break;
                    }
                    else {
                        body += " " + words[i];
                    }
                }
                if (body.equals("")) {
                    DukeException exception = new DukeException("Description of event cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                if (counter == 0) {
                    DukeException exception = new DukeException("Date of event cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                for (int i = counter; i < words.length; i ++) {
                    deadline += " " + words[i];
                }
                if (deadline.equals("")) {
                    DukeException exception = new DukeException("Date of event cannot be empty :(");
                    System.out.println(exception);
                    continue;
                }
                if (deadline.length() != 11) {
                    DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                    System.out.println(exception);
                    continue;
                }
                String[] deadlineArray = deadline.substring(1).split("-");
                int year = Integer.parseInt(deadlineArray[0]);
                int month = Integer.parseInt(deadlineArray[1]);
                int day = Integer.parseInt(deadlineArray[2]);
                if (month > 12 || day > 31) {
                    DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                    System.out.println(exception);
                    continue;
                }
                LocalDate newDeadline = LocalDate.of(year, month, day);
                Task newTask = new Event(body, newDeadline);
                savedTasks.add(newTask);
                System.out.println("I've added this task:\n" + newTask);
                System.out.println("Now you have " + savedTasks.size() + " tasks in the list!");
            }
            else if (userInput.toLowerCase().equals("delete")) {
                int nextNum = sc.nextInt();
                if (nextNum > savedTasks.size() || nextNum < 1) {
                    DukeException exception = new DukeException("Number out of range!");
                    System.out.println(exception);
                }
                else {
                    int index = nextNum-1;
                    Task removedTask = savedTasks.get(index);
                    savedTasks.remove(index);
                    System.out.println("Alright! I've removed this task:\n" + removedTask);
                    System.out.println(String.format("Now you have %d tasks left in the list!", savedTasks.size()));
                }
            }
            else if (userInput.toLowerCase().equals("get")) {
                String date = sc.next();
                if (date.length() != 10) {
                    DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                    System.out.println(exception);
                    continue;
                }
                String[] dateArray = date.split("-");
                int year = Integer.parseInt(dateArray[0]);
                int month = Integer.parseInt(dateArray[1]);
                int day = Integer.parseInt(dateArray[2]);
                if (month > 12 || day > 31) {
                    DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                    System.out.println(exception);
                    continue;
                }
                LocalDate localDate = LocalDate.of(year, month, day);
                int counter = 0;
                for (Task i : savedTasks) {
                    if (i instanceof Event) {
                        if (((Event) i).getDate().equals(localDate)) {
                           System.out.println(i);
                           counter++;
                        }
                    }
                    if (i instanceof Deadline) {
                        if (((Deadline) i).getDeadline().equals(localDate)) {
                            System.out.println(i);
                            counter++;
                        }
                    }
                }
                if (counter == 0) {
                    System.out.println("No tasks found!");
                }
            }
            else {
                DukeException exception = new DukeException("I'm sorry but I don't understand what that means :(");
                System.out.println(exception);
                continue;
            }
            try {
                updateFile(savedTasks);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void updateFile (List<Task> updatedTasks) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task i : updatedTasks) {
            String taskBody = i.getBody();
            boolean isDone = i.getDone();
            if (i instanceof Todo) {
                if (!isDone) {
                    fw.write("T|0|" + taskBody + System.lineSeparator());
                }
                else {
                    fw.write("T|1|" + taskBody + System.lineSeparator());
                }
            }
            else if (i instanceof Deadline) {
                LocalDate taskDeadline = ((Deadline) i).getDeadline();
                if (!isDone) {
                    fw.write("D|0|" + taskBody + "|" + taskDeadline + System.lineSeparator());
                }
                else {
                    fw.write("D|1|" + taskBody + "|" + taskDeadline + System.lineSeparator());
                }
            }
            else {
                LocalDate taskDate = ((Event) i).getDate();
                if (!isDone) {
                    fw.write("E|0|" + taskBody + "|" + taskDate + System.lineSeparator());
                }
                else {
                    fw.write("E|1|" + taskBody + "|" + taskDate + System.lineSeparator());
                }
            }
        }
        fw.close();
    }
}
