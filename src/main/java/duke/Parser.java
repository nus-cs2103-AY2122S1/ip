package duke;

public class Parser {
    public static Integer parseDelete(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }
    public static Integer parseDone(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }
    public static String parseEvent(String input) {
        return input.split("event ")[1];
    }
    public static String parseTodo(String input) {
        return input.split("todo ")[1];
    }
    public static String parseDeadline(String input) {
        return input.split("deadline ")[1];
    }

}
//if(input.equals("bye")) {
//        System.out.println("Bye. Hope to see you again soon!");
//        break;
//    } else if(input.split(" ")[0].equals("delete")) {
//        duke.Task taskToBeDeleted = taskList.get(Integer.parseInt(input.split(" ")[1]) - 1);
//        deleteTask(Integer.parseInt(input.split(" ")[1]) - 1);
//        System.out.println("Noted. I've removed this task: \n" +
//                "  " + taskToBeDeleted.toString() +"\n" +
//                "Now you have " + index + " tasks in the list.");
//        saveTask();
//    } else if (input.equals("list")) {
//        System.out.println("Here are the tasks in your list:");
//        for(int i = 0; i < index; i++){
//            System.out.println((i + 1) + ". " + taskList.get(i).toString());
//        }
//    }else if(input.split(" ")[0].equals("done")){
//        int taskIndex = Integer.parseInt(input.split(" ")[1]);
//        taskList.get(taskIndex - 1).markCompleted();
//        System.out.println("Nice! I've marked this task as done:\n" + taskList.get(taskIndex - 1).toString());
//        saveTask();
//    } else if(input.split("todo").length == 0) {
//        try {
//            throw new duke.DukeException.emptyToDoDescriptionException();
//        } catch (duke.DukeException.emptyToDoDescriptionException e) {
//            e.exceptionMessage();
//        }
//    }else if(input.split("deadline").length == 0) {
//        try {
//            throw new duke.DukeException.emptyDeadlineDescriptionException();
//        } catch (duke.DukeException.emptyDeadlineDescriptionException e) {
//            e.exceptionMessage();
//        }
//    }else if(input.split("event").length == 0) {
//        try {
//            throw new duke.DukeException.emptyEventDescriptionException();
//        } catch (duke.DukeException.emptyEventDescriptionException e) {
//            e.exceptionMessage();
//        }
//    } else if(input.split("event")[0].equals("")) {
//        String taskContent = input.split("event ")[1];
//        duke.Task newEvent = new Event(taskContent);
//        addTask(newEvent);
//        System.out.println("Got it. I've added this task: \n" +
//                "  " + newEvent.toString() + "\n" +
//                "Now you have " + index + " tasks in the list.");
//        saveTask();
//    } else if(input.split("deadline")[0].equals("")) {
//        String taskContent = input.split("deadline ")[1];
//        duke.Task newEvent = new duke.Deadline(taskContent);
//        addTask(newEvent);
//        System.out.println(" Got it. I've added this task: \n" +
//                "  " + newEvent.toString() + "\n" +
//                "Now you have " + index + " tasks in the list.");
//        saveTask();
//    } else if(input.split("todo")[0].equals("")) {
//
//        String taskContent = input.split("todo ")[1];
//        duke.Task newEvent = new duke.ToDo(taskContent);
//        addTask(newEvent);
//        System.out.println("Got it. I've added this task: \n" +
//                "  " + newEvent.toString() + "\n" +
//                "Now you have " + index + " tasks in the list.");
//        saveTask();
//    }
//            else {
//        try{
//            throw new duke.DukeException.invalidInputException();
//        } catch (duke.DukeException.invalidInputException e) {
//            e.exceptionMessage();
//        }
//    }
//}