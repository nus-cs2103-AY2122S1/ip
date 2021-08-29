package dialog;

import command.InvalidTimeFormatException;

import parser.Parser;
import task.Task;
import task.TimeTask;
import task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TaskDialog extends Dialog {
    // This class is a child of dialog class which allow the user to interact with the task
    private TaskList taskList;

    private TaskDialog(ArrayList<String> sentences, TaskList taskList) {
        super(sentences);
        this.taskList = taskList;
    }

    public static Dialog generate(String id) {
        if (archive.containsKey(id)) {
            return archive.get(id);
        } else {
            final Dialog newDialog = new TaskDialog(new ArrayList<>(), new TaskList(new ArrayList<>()));
            archive.put(id, newDialog);
            return newDialog;
        }
    }

    public static Dialog generate(String id, TaskList taskList) {
        if (archive.containsKey(id)) {
            return archive.get(id);
        } else {
            final Dialog newDialog = new TaskDialog(new ArrayList<>(), taskList);
            archive.put(id, newDialog);
            return newDialog;
        }
    }

    public void addTask(Task task) throws DialogException {
        Dialog addDialog = Dialog.generate(Integer.toString(task.hashCode()));
        taskList.add(task);
        addDialog.add("Got it. I've added this task:");
        addDialog.add("  " + task);
        addDialog.add("Now you have " + this.taskList.length() + " tasks in the list.");
        System.out.println(addDialog);
    }

    public TaskDialog by(String deadline) throws InvalidTimeFormatException {
        LocalDate dlDate = Parser.parseTimeString(deadline);
        return new TaskDialog(new ArrayList<>(List.of("task.Deadline: " + dlDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))),
                new TaskList(new ArrayList<>(this.taskList.getTasks().stream().filter((task) -> {
            if (!task.getClass().getSuperclass().getName().equals("task.TimeTask")) {
                // return any task without time associated with it
                return true;
            } else {
                TimeTask timetask = (TimeTask) task;
                return timetask.getTime().isBefore(dlDate) || timetask.getTime().isEqual(dlDate);
            }
        }).collect(Collectors.toList()))));
    }

    public TaskDialog with(String keyword) {
        return new TaskDialog(new ArrayList<>(List.of("Find: " + keyword)),
                new TaskList(new ArrayList<>(this.taskList.getTasks().stream().filter(
                        (task) -> task.description().contains(keyword)).collect(Collectors.toList()))));
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public void markTaskAsDone(int index) throws IndexOutOfBoundsException, DialogException {
        if (index < 0 || index > this.taskList.length()) {
            throw new IndexOutOfBoundsException();
        } else {
            Task task = taskList.get(index);
            task.markAsDone();
            String id = "markAsDone" + task.hashCode();
            Dialog markAsDoneDialog = Dialog.generate(id);
            markAsDoneDialog.add("Nice! I've marked this task as done:");
            markAsDoneDialog.add("  " + task);
            System.out.println(markAsDoneDialog);
            // allow duplicates later
            Dialog.archive.remove(id);
        }
    }

    public void deleteTaskByIndex(int index) throws IndexOutOfBoundsException, DialogException {
        if (index < 0 || index > this.taskList.length()) {
            throw new IndexOutOfBoundsException();
        } else {
            Task task = taskList.get(index);
            taskList.remove(index);
            String id = "remove" + task.hashCode();
            Dialog removeDialog = Dialog.generate(id);
            removeDialog.add("Noted. I've removed this task:");
            removeDialog.add("  " + task);
            removeDialog.add("Now you have " + this.taskList.length() + " tasks in the list.");
            System.out.println(removeDialog);
            Dialog.archive.remove(id);
            Dialog.archive.remove(task.toString());
        }
    }



    @Override
    public String toString() {
        String dialogs = this.sentences.stream().reduce("    ", (s1, s2) -> s1 + s2 + "\n    ");

        return "    ____________________________________________________________\n" +
                dialogs +
                "Here are the tasks in your list:\n"+
                taskList +
                "    ____________________________________________________________";
    }
}
