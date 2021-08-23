import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Bern {
    public static boolean isANumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String ifDone(String input, ArrayList<Task> arListTask) throws BernException {
        if (input.length() == 4 || (input.length() == 5 && input.substring(4, 5).equals(" "))) {
            throw new EmptyDescriptionException("done");
        } else if (!isANumber(input.substring(5))) {
            throw new InvalidCommandException(input);
        } else if (Integer.parseInt(input.substring(5)) > arListTask.size()) {
            throw new IndexException(input);
        }
        int index = Integer.parseInt(input.substring(5)) - 1;
        arListTask.get(index).markAsDone();
        return "Good job! I've marked this task as done:\n"
                + arListTask.get(index).toString();
    }

    public static String ifDeadline(String input, ArrayList<Task> arListTask) throws BernException {
        if (input.length() == 8 || (input.length() == 9 && input.substring(8, 9).equals(" "))) {
            throw new EmptyDescriptionException("deadline");
        }
        // TODO Haven't handled if there is no /by
        String task = input.substring(9, input.indexOf('/') - 1);
        String by = input.substring(input.indexOf('/') + 4);
        arListTask.add(new Deadline(task, by));
        return "Got it. I've added this task:\n"
                + arListTask.get(arListTask.size() - 1).toString() + "\n"
                + "Now you have " + String.valueOf(arListTask.size())
                + (arListTask.size() == 1 ? " task in the list" : " tasks in the list");
    }

    public static String ifEvent(String input, ArrayList<Task> arListTask) throws BernException {
        if (input.length() == 5 || (input.length() == 6 && input.substring(5, 6).equals(" "))) {
            throw new EmptyDescriptionException("event");
        }
        // TODO Haven't handled if there is no /at
        String task = input.substring(6, input.indexOf('/') - 1);
        String at = input.substring(input.indexOf('/') + 4);
        arListTask.add(new Event(task, at));
        return "Got it. I've added this task:\n"
                + arListTask.get(arListTask.size() - 1).toString() + "\n"
                + "Now you have " + String.valueOf(arListTask.size())
                + (arListTask.size() == 1 ? " task in the list" : " tasks in the list")
        ;
    }

    public static String ifToDo(String input, ArrayList<Task> arListTask) throws BernException {
        if (input.length() == 4 || (input.length() == 5 && input.substring(4, 5).equals(" "))) {
            throw new EmptyDescriptionException("todo");
        }
        arListTask.add(new ToDo(input.substring(5)));
        return "Got it. I've added this task:\n"
                + arListTask.get(arListTask.size() - 1).toString() + "\n"
                + "Now you have " + String.valueOf(arListTask.size())
                + (arListTask.size() == 1 ? " task in the list" : " tasks in the list")
        ;
    }

    public static String ifBye(String input, ArrayList<Task> arListTask) throws BernException {
        return "Bye. Hope to see you soon and hope you found my service useful!";
    }

    public static String ifList(String input, ArrayList<Task> arListTask) throws BernException {
        String result = "";

        for (int i = 0; i < arListTask.size(); i++) {
            result += String.valueOf(i + 1)
                    + ". "
                    + arListTask.get(i).toString()
                    + (i == arListTask.size() - 1 ? "" : "\n");
        }
        if (arListTask.size() == 0) {
            result = "There is no task.";
        }

        return result;
    }

    public static String ifDelete(String input, ArrayList<Task> arListTask) throws BernException {
        if (input.length() == 6 || (input.length() == 7 && input.substring(6, 7).equals(" "))) {
            throw new EmptyDescriptionException("done");
        } else if (!isANumber(input.substring(7))) {
            throw new InvalidCommandException(input);
        } else if (Integer.parseInt(input.substring(7)) > arListTask.size()) {
            throw new IndexException(input);
        }
        int index = Integer.parseInt(input.substring(7)) - 1;
        String temp = arListTask.get(index).toString();
        arListTask.remove(index);
        return "Noted! I've removed this task:\n"
                + temp + "\n"
                + "Now you have " + String.valueOf(arListTask.size())
                + (arListTask.size() == 1 ? " task in the list" : " tasks in the list");
    }

    public enum Command {
        DONE, DEADLINE, EVENT, TODO, BYE, LIST, DELETE, INVALID
    }

    public static String getReply(Command c, String input, ArrayList<Task> arListTask) throws BernException {
        switch (c) {
            case DONE:
                return ifDone(input, arListTask);
            case DEADLINE:
                return ifDeadline(input, arListTask);
            case EVENT:
                return ifEvent(input, arListTask);
            case TODO:
                return ifToDo(input, arListTask);
            case BYE:
                return ifBye(input, arListTask);
            case LIST:
                return ifList(input, arListTask);
            case DELETE:
                return ifDelete(input, arListTask);
            case INVALID:
                throw new InvalidCommandException(input);
        }
        return "";
    }

    public static void reinitialiseFile() {
        try {
            File file = new File("savedList.txt");
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeIntoFile(ArrayList<Task> arListTask) {
        try {
            reinitialiseFile();
            FileWriter fw = new FileWriter("savedList.txt");
            fw.write(getReply(Command.LIST, "", arListTask));
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (BernException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ToDo toDoFromString(String s) {
        boolean isDone = s.substring(4, 5).equals("X");
        String desc = s.substring(7);
        ToDo ans = new ToDo(desc);
        if (isDone) {
            ans.markAsDone();
        }
        return ans;
    }

    public static Deadline deadlineFromString(String s) {
        boolean isDone = s.substring(4, 5).equals("X");
        String desc = s.substring(7, s.indexOf("(by: ") - 1);
        String by = s.substring(s.indexOf("(by: ") + 5, s.length() - 1);
        Deadline ans = new Deadline(desc, by);
        if (isDone) {
            ans.markAsDone();
        }
        return ans;
    }

    public static Event eventFromString(String s) {
        boolean isDone = s.substring(4, 5).equals("X");
        String desc = s.substring(7, s.indexOf("(at: ") - 1);
        String at = s.substring(s.indexOf("(at: ") + 5, s.length() - 1);
        Event ans = new Event(desc, at);
        if (isDone) {
            ans.markAsDone();
        }
        return ans;
    }

    public static Task taskFromString(String s) {
        String cat = s.substring(1, 2);
        return cat.equals("T")
                ? toDoFromString(s)
                : cat.equals("D")
                ? deadlineFromString(s)
                : eventFromString(s);
    }

    public static void initialiseArListTask(ArrayList<Task> arListTask) {
        File file = new File("savedList.txt");
        if (!file.exists()) {
            return;
        } else {
            try {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String taskStr = s.nextLine();
                    taskStr = taskStr.substring(taskStr.indexOf("["));
                    Task temp = taskFromString(taskStr);
                    arListTask.add(temp);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        ArrayList<Task> arListTask = new ArrayList<>();

        initialiseArListTask(arListTask);

        System.out.println("Hi! I'm Bern, your trustworthy chatbot.\nWhat can I do for you?");

        while (true) {
            String input = myObj.nextLine();
            try {
                if (input.length() >= 4 && input.substring(0, 4).equals("done")){
                    System.out.println(getReply(Command.DONE, input, arListTask));
                    writeIntoFile(arListTask);
                } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                    System.out.println(getReply(Command.DEADLINE, input, arListTask));
                    writeIntoFile(arListTask);
                } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                    System.out.println(getReply(Command.EVENT, input, arListTask));
                    writeIntoFile(arListTask);
                } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
                    System.out.println(getReply(Command.TODO, input, arListTask));
                    writeIntoFile(arListTask);
                } else if (input.equals("bye")){
                    System.out.println(getReply(Command.BYE, input, arListTask));
                    break;
                } else if (input.equals("list")){
                    System.out.println(getReply(Command.LIST, input, arListTask));
                    writeIntoFile(arListTask);
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")){
                    System.out.println(getReply(Command.DELETE, input, arListTask));
                    writeIntoFile(arListTask);
                } else {
                    getReply(Command.INVALID, input, arListTask);
                }
            } catch (BernException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
