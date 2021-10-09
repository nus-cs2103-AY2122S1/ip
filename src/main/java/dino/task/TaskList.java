package dino.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import dino.command.AddTaskCommand;
import dino.exception.*;
import dino.util.Parser;

public class TaskList {

    private final List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a new task to the task list
     *
     * @param task the task to be added
     * @return the output message after execution
     */
    public String addTask(Task task) {
        assert(task != null); //the task to be added is not empty
        taskList.add(task);
        int size = taskList.size();
        return "Got it. I've added this task: \n"
                + "  " + taskList.get(size - 1) + "\n" +
                "Now you have " + size +
                (size > 1 ? " tasks" : " task") + " in the list.";
    }

    /**
     * Prints out the task list in console, prefixed with index
     *
     * @return the output message after execution
     */
    public String printTaskList() {
        try {
            if (taskList.isEmpty()) {
                throw new EmptyListException();
            }
            StringBuilder list = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                list.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
            return "Here are the tasks in your list: \n" + list;
        } catch (EmptyListException e) {
            return e.getMessage();
        }

    }

    /**
     * Marks the task indicated by the given index as done
     *
     * @param index the index of the task as indicated by the task list
     * @return the output message after execution
     */
    public String markTaskDone(int index) {
        try {
            if (index < 1 || index > taskList.size()) {
                throw new InvalidIndexException();
            } else {
                Task t = taskList.get(index - 1);
                assert(t != null); //the task we fetched is not null
                if (t.getStatus()) {
                    throw new TaskAlreadyDoneException();
                }
                assert(!t.getStatus()); //the task to be marked as done is not done yet
                t.setDone();
                return "Nice! I've marked this task as done: \n" + t;
            }
        } catch (TaskAlreadyDoneException | InvalidIndexException e) {
            return e.getMessage();
        }

    }

    /**
     * Deletes the task indicated by the index from the task list
     *
     * @param index the index of the task in the task list
     * @return the output message after execution
     * @throws InvalidIndexException if the index entered is out of bounds
     */
    public String deleteTask(int index) throws InvalidIndexException {
        try {
            if (index < 1 || index > taskList.size()) {
                throw new InvalidIndexException();
            } else {
                Task t = taskList.remove(index - 1);
                assert (t != null); //the task that we removed is not null
                int size = taskList.size();
                return "Noted. I've removed this task: \n" + t + "\n"
                        + "Now you have " + size +
                        (size > 1 ? " tasks" : " task") + " in the list.";
            }
        } catch (InvalidIndexException e) {
            return e.getMessage();
        }
    }

    /**
     * Prints out the task(s) that contains the input keyword(s) in description
     *
     * @param keywords a list of keywords for searching tasks
     * @return the list of task that contains the keyword
     */
    public String searchTaskFromKeyword(String ...keywords) {
        List<Task> matchingTasksList = new ArrayList<>();
        Stream<Task> matchingTasksStream;
        for (String keyword : keywords) {
            matchingTasksStream = taskList.stream().filter(task -> task.getDescription().contains(keyword));
            matchingTasksStream.forEach(task -> matchingTasksList.add(task));
        }
        try {
            if (matchingTasksList.isEmpty()) {
                throw new TaskNotFoundException();
            } else {
                StringBuilder list = new StringBuilder();
                for (int i = 0; i < matchingTasksList.size(); i++) {
                    list.append(i + 1).append(". ").append(matchingTasksList.get(i)).append("\n");
                }
                return "Here are the matching tasks in your list:\n" + list;
            }
        } catch (TaskNotFoundException e) {
            return e.getMessage();
        }
    }

    /**
     * Edits the task at the given index to the new task
     *
     * @param newTask the new task after edition
     * @param index the index of the task to be edited
     * @return the output message after edition
     */
    public String editTask(String newTask, int index) {
        try {
            if (index < 1 || index > taskList.size()) {
                throw new InvalidIndexException();
            }
            Task task = taskList.get(index - 1);
            Parser.CMDTYPE taskType = Parser.parse(newTask);
            String newDescription = AddTaskCommand.getTaskDescription(newTask, taskType);
            assert(newDescription != null);
            task.editDescription(newDescription);
            if (taskType.equals(Parser.CMDTYPE.DEADLINE) || taskType.equals(Parser.CMDTYPE.EVENT)) {
                LocalDate newTime = AddTaskCommand.getTaskTime(newTask);
                task.editTime(newTime);
            }
            return "Your task has been successfully changed to:\n" + task;
        } catch (DinoException e) {
            return e.getMessage();
        }
    }

}
