package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Method that adds a Deadline task onto TaskList
     *
     * @param input The string that contains the user input
     * @param t The TaskList that contains the tasks to be added
     * @param s The Storage that handles the reading and writing to a text file
     *
     * @return void
     */
    public static String parse(String input, TaskList t, Storage s) {
        if (input.equals("list")) {
            String returnString = "";
            for (int i = 0; i < t.size(); i++) {
                returnString = returnString + String.valueOf(i + 1) + "." + t.get(i).toString() + "\n";
            }
            return returnString;
        } else if (input.startsWith("done") && Character.isDigit(input.charAt(input.length() - 1))
                && input.length() <= 8 && !Character.isAlphabetic(input.charAt(input.length() - 2))
                && Character.isDigit(input.charAt(5))) {
            String returnString = "";
            int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            assert value > 0 : "Task to be marked done must have index greater than 0";
            t.get(value - 1).markAsDone();
            System.out.println(t.get(value - 1).toString());
            s.appendListToFile(t);
            returnString = returnString + "Nice! I've marked this task as done: " + "\n";
            returnString = returnString + "[X] " + t.get(value - 1).description + "\n";
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("[X] " + t.get(value - 1).description);
            return returnString;
        } else if (input.startsWith("todo")) {
            String returnString = "";
            if (input.length() < 6) {
                System.out.println(new NullTaskError().getMsg("todo"));
                return new NullTaskError().getMsg("todo");
            } else {
                String firstTodo = input.substring(5);
                assert firstTodo.length() > 0: "task description cannot be empty";
                t.addTodo(firstTodo);
                s.appendListToFile(t);
                System.out.println("Got it. I've added this task: ");
                System.out.println(new Todo(firstTodo));
                System.out.println("Now you have " + t.size() + " tasks in the list");
                returnString = returnString + "Got it. I've added this task: " + "\n";
                returnString = returnString + new Todo(firstTodo).toString() + "\n";
                returnString = returnString + "Now you have " + t.size() + " tasks in the list" + "\n";
                return returnString;
            }
        } else if (input.startsWith("deadline")) {
            String returnString = "";
            if (input.length() < 10) {
                System.out.println(new NullTaskError().getMsg("deadline"));
                return new NullTaskError().getMsg("deadline");
            } else {
                String[] temp = input.split("/by");
                String firstDeadline = temp[0].substring(9);
                String[] splitDate = temp[1].split(" ");
                String date = splitDate[1];
                String[] breakingDate = date.split("/");
                String year = breakingDate[2];
                String month = breakingDate[1];
                String currentDate = breakingDate[0];
                int i = Integer.parseInt(currentDate);
                if (i < 10) {
                    currentDate = "0" + currentDate;
                }
                String finalDateFormat = year + "-" + month + "-" + currentDate;
                LocalDate date1 = LocalDate.parse(finalDateFormat);
                t.addDeadline(firstDeadline, date1);
                s.appendListToFile(t);
                returnString = returnString + "Got it. I've added this task: " + "\n";
                returnString = returnString + new Deadline(firstDeadline, date1).toString() + "\n";
                returnString = returnString + "Now you have " + t.size() + " tasks in the list" + "\n";
                System.out.println("Got it. I've added this task: ");
                System.out.println(new Deadline(firstDeadline, date1));
                System.out.println("Now you have " + t.size() + " tasks in the list");
                return returnString;
            }
        } else if (input.startsWith("event")) {
            String returnString = "";
            if (input.length() < 7) {
                System.out.println(new NullTaskError().getMsg("event"));
                return new NullTaskError().getMsg("event");
            } else {
                String[] tempEvent = input.split("/at");
                String firstEvent = tempEvent[0].substring(6);
                System.out.println(firstEvent);
                String[] splitDate = tempEvent[1].split(" ");
                String date = splitDate[1];
                String[] breakingDate = date.split("/");
                String year = breakingDate[2];
                String month = breakingDate[1];
                String currentDate = breakingDate[0];
                int i = Integer.parseInt(currentDate);
                if (i < 10) {
                    currentDate = "0" + currentDate;
                }
                String finalDateFormat = year + "-" + month + "-" + currentDate;
                LocalDate date1 = LocalDate.parse(finalDateFormat);
                t.addEvent(firstEvent, date1);
                System.out.println("Got it. I've added this task: ");
                System.out.println(new Event(firstEvent, date1));
                System.out.println("Now you have " + t.size() + " tasks in the list");
                returnString = returnString + "Got it. I've added this task: " + "\n";
                returnString = returnString + new Event(firstEvent, date1).toString() + "\n";
                returnString = returnString + "Now you have " + t.size() + " tasks in the list" + "\n";
                s.appendListToFile(t);
                return returnString;
            }
        } else if (input.startsWith("delete") && input.length() < 11) {
            String returnString = "";
            int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            assert value > 0 : "task to be deleted must have index > 0";
            Task removedTask = t.get(value - 1);
            t.delete(value - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask.toString());
            System.out.println("Now you have " + t.size() + " tasks in the list.");
            returnString = returnString + "Noted. I've removed this task: " + "\n";
            returnString = returnString + removedTask.toString() + "\n";
            returnString = returnString + "Now you have " + t.size() + " tasks in the list" + "\n";
            s.appendListToFile(t);
            return returnString;
        } else if (input.startsWith("find")) {
            String keyword = input.substring(5);
            String returnString = "";
            int count = 0;
            for (Task task : t.getTaskList()) {
                String desc = task.description.substring(0, task.description.length() - 4);
                String[] splitDesc = desc.split(" ");
                for (String str : splitDesc) {
                    if (str.equals(keyword)) {
                        System.out.println(task.toString());
                        returnString = returnString + task.toString() + "\n";
                        count = count + 1;
                    }
                }
            }
            if (count == 0) {
                System.out.println("OOPS! The task does not exist");
                return returnString + "\n" + "OOPS! The task does not exist";
            } else {
                return returnString;
            }
        } else {
            DukeException e = new NonExistentKeyword();
            System.out.println(e.getMsg());
            return e.getMsg();
        }
    }
}
