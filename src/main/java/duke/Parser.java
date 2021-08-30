package duke;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    public static void parse(String input, TaskList t, Storage s, File file) {
        if (input.equals("list")) {
            for (int i = 0; i < t.size(); i++) {
                System.out.println(i + 1 + "." + t.get(i).toString());
            }
        } else if (input.startsWith("done") && Character.isDigit(input.charAt(input.length() - 1))
                && input.length() <= 8 && !Character.isAlphabetic(input.charAt(input.length() - 2))
                && Character.isDigit(input.charAt(5))) {
            int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            t.get(value - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("[X] " + t.get(value-1).description);
            s.appendListToFile(t);
        } else if (input.startsWith("todo")) {
            if (input.length() < 6) {
                System.out.println(new NullTaskError().getMsg("todo"));
            } else {
                String firstTodo = input.substring(5);
                t.addTodo(firstTodo);
                System.out.println("Got it. I've added this task: ");
                System.out.println(new Todo(firstTodo));
                System.out.println("Now you have " + t.size() + " tasks in the list");
                s.appendListToFile(t);
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() < 10) {
                System.out.println(new NullTaskError().getMsg("deadline"));
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
                String dateForObject = date1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                t.addDeadline(firstDeadline, date1);
                System.out.println("Got it. I've added this task: ");
                System.out.println(new Deadline(firstDeadline, date1));
                System.out.println("Now you have " + t.size() + " tasks in the list");
                s.appendListToFile(t);
            }
        } else if (input.startsWith("event")) {
            if (input.length() < 7) {
                System.out.println(new NullTaskError().getMsg("event"));
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
                System.out.println(date1);
                t.addEvent(firstEvent, date1);
                System.out.println("Got it. I've added this task: ");
                System.out.println(new Event(firstEvent, date1));
                System.out.println("Now you have " + t.size() + " tasks in the list");
                s.appendListToFile(t);
            }
        } else if (input.startsWith("delete") && input.length() < 11) {
            int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            Task removedTask = t.get(value - 1);
            t.delete(value - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask.toString());
            System.out.println("Now you have " + t.size() + " tasks in the list.");
            s.appendListToFile(t);
        } else if (input.startsWith("find")) {
            String keyword = input.substring(5);
            ArrayList<Task> output = new ArrayList<>();
            int count = 0;
            for (Task task: t.getTaskList()) {
                String desc = task.description.substring(0,task.description.length()-4);
                String[] splitDesc = desc.split(" ");
                for (String str: splitDesc) {
                    if (str.equals(keyword)) {
                        System.out.println(task.toString());
                        count = count + 1;
                    }
                }
            }
            if (count == 0) {
                System.out.println("OOPS! The task does not exist");
            }
        } else {
            DukeException e = new NonExistentKeyword();
            System.out.println(e.getMsg());
        }
    }
}

/**
    if (this.input.equals("bye")) {
        System.out.println("It's sad to see you go :(");
        System.out.println("Goodbye, hope to you another day!");
        break;
    } else if (input.equals("list")) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + "." + list.get(i).toString());
        }
    } else if (input.startsWith("done") && input.length() < 9) {
        int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        list.get(value - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[X] " + list.get(value-1).description);
    } else if (input.startsWith("todo")) {
        if (input.length() < 6) {
            System.out.println(new duke.NullTaskError().getMsg("todo"));
        } else {
            String firstTodo = input.substring(5);
            list.add(new duke.Todo(firstTodo));
            System.out.println("Got it. I've added this task: ");
            System.out.println(new duke.Todo(firstTodo));
            System.out.println("Now you have " + list.size() + " tasks in the list");
        }
    } else if (input.startsWith("deadline")) {
        if (input.length() < 10) {
            System.out.println(new duke.NullTaskError().getMsg("deadline"));
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
            String dateForObject = date1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            tasks.addDeadline(firstDeadline, date1);
            System.out.println("Got it. I've added this task: ");
            System.out.println(new duke.Deadline(firstDeadline, date1));
            System.out.println("Now you have " + list.size() + " tasks in the list");
        }
    } else if (input.startsWith("event")) {
        if (input.length() < 7) {
            System.out.println(new duke.NullTaskError().getMsg("event"));
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
            System.out.println(date1);
            tasks.addEvent(firstEvent, date1, list);
            list.add(new duke.Event(firstEvent, date1));
            System.out.println("Got it. I've added this task: ");
            System.out.println(new duke.Event(firstEvent, date1));
            System.out.println("Now you have " + list.size() + " tasks in the list");
        }
    } else if (input.startsWith("delete") && input.length() < 11) {
        int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        duke.Task removedTask = list.get(value - 1);
        list.remove(value - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    } else {
        duke.DukeException e = new duke.NonExistentKeyword();
        System.out.println(e.getMsg());
    }
    appendListToFile(list);
}
*/