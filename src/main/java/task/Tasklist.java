package task;

import java.util.ArrayList;

import duke.Duke;
import duke.DukeException;
import ui.LogMessage;
import ui.Ui;

/**
 * Class that contains the initialization of a list to store the tasks
 *
 * @author: Wei Yangken
 */
public class Tasklist {
    private ArrayList<Task> taskList;

    /**
     * Constructor to create a new taskList to store tasks
     */
    public Tasklist(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds task to list and notifies the user if task has not been added properly
     * @param task Task to be added
     */
    public void add(Task task, LogMessage msg) {
        try {
            if (task == null) {
                throw new DukeException.TaskNotAddedException("Task has not been added successfully.");
            }
        } catch (DukeException e) {
            msg.add(e.getMessage());
            System.out.println(e.getMessage());
            Ui.printBreakline();
            return;
        }

        this.taskList.add(task);
        String addMsg = String.format("Got it. I've added this task:\n%s", task);
        String counterMsg = String.format("Now you have %d tasks in the list.", taskList.size());
        msg.add(addMsg);
        msg.add(counterMsg);
        msg.printAllMessages();
        Ui.printBreakline();
    }

    /**
     * Add task without outputting messages
     * @param task
     */
    public void add(Task task) {
        try {
            if (task == null) {
                throw new DukeException.TaskNotAddedException("Task has not been added successfully.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return;
        }
        this.taskList.add(task);
    }

    /**
     * Lists out the tasks present in tasklist.
     * Checks for the scenario where list is empty.
     * @return
     */
    public LogMessage list() {
        LogMessage returnMsg = new LogMessage();
        try {
            if (this.taskList.size() == 0) {
                String emptyListErrMsg = String.format("List is empty :(");
                throw new DukeException.EmptyListException(emptyListErrMsg);
            }
        } catch (DukeException e) {
            returnMsg.add(e.getMessage());
            Ui.printBreakline();
            return returnMsg;
        }

        String listTaskMsg = "Here are the tasks in your list:";
        returnMsg.add(listTaskMsg);
        for (int i = 0; i < taskList.size(); i++) {
            String taskMsg = String.format("%d. %s", i + 1, this.taskList.get(i).toString());
            returnMsg.add(taskMsg);
        }
        returnMsg.printAllMessages();
        Ui.printBreakline();
        return returnMsg;
    }

    /**
     * Deletes the indicated task from tasklist.
     * Accounts for situations where task could not be found.
     * @param idx Ranking of task to be deleted
     */
    public LogMessage delete(int idx) {
        LogMessage msg = new LogMessage();
        Task removedTask;
        try {
            removedTask = this.taskList.remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            String taskNotFoundMsg = String.format("Task number %d not found.", idx);
            msg.add(taskNotFoundMsg);
            msg.printAllMessages();
            Ui.printBreakline();
            return msg;
        }
        String removeMsg = String.format("Noted. I've removed this task: %s", removedTask.toString());
        String tasksLeftMsg = String.format("Now you have %d task(s) in the list.", taskList.size());
        msg.add(removeMsg);
        msg.add(tasksLeftMsg);
        msg.printAllMessages();
        Ui.printBreakline();
        return msg;
    }

    /**
     * Check if string is found in tasklist
     * @param keyword key to search for
     */
    public LogMessage findString(String keyword) {
        assert(keyword != "");
        Tasklist filteredList = new Tasklist(new ArrayList<>());
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getName().contains(keyword)) {
                filteredList.add(task);
            }
        }
        LogMessage msg = filteredList.list();
        return msg;
    }

    public LogMessage findTag(String keyword) {
        Tasklist filteredList = new Tasklist(new ArrayList<>());
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            ArrayList<Tag> tags = task.getTagList();
            for(int j = 0; j < tags.size(); j++) {
                if(tags.get(j).compareTagName(keyword)) {
                    filteredList.add(task);
                    break;
                }
            }
        }
        LogMessage msg = filteredList.list();
        msg.printAllMessages();
        return msg;
    }

    public LogMessage addTagToTask(String input) {
        String[] inputs = input.split(" ");
        LogMessage msg = new LogMessage();
        try {
            if (inputs.length != 3) {
                throw new DukeException("Wrong number of fields");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            msg.add(e.getMessage());
            return msg;
        }

        int idx = Integer.parseInt(inputs[1]) - 1;
        String tagName = inputs[2];
        Task task = taskList.get(idx);
        task.addTag(tagName);
        msg.add(String.format("Tag %s was added to task %d", tagName.toString(), idx));
        return msg;
    }

    public LogMessage viewTagOfTask(String input) {
        String[] inputs = input.split(" ");
        LogMessage msg = new LogMessage();
        try {
            if (inputs.length != 2) {
                throw new DukeException("Wrong number of fields");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            msg.add(e.getMessage());
            return msg;
        }

        int idx = Integer.parseInt(inputs[1]) - 1;
        Task task = taskList.get(idx);
        msg.add(task.viewTags());
        msg.printAllMessages();
        Ui.printBreakline();
        return msg;
    }

    /**
     * Returns the task of a particular index in arraylist
     * @param idx Index in the arraylist
     * @return Task of a particular index
     */
    public Task getTask(int idx) throws DukeException.TaskNotFoundException {
        Task task = null;
        try {
            task = taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            String errMsg = String.format("Task number %d not found.", idx + 1);
            throw new DukeException.TaskNotFoundException(errMsg);
        }
        return task;
    }

    /**
     * Sets task to completed
     *
     * @param idx Index of task set to compeleted
     */
    public LogMessage setToCompleted(int idx) {
        LogMessage returnMsg = new LogMessage();
        try {
            Task task = this.getTask(idx);
            String addTaskMsg = String.format("Nice! I've marked this task as done:\n + %s", task);
            returnMsg.add(addTaskMsg);
            task.setToCompleted();
        } catch (DukeException.TaskNotFoundException e) {
            returnMsg.add(e.getMessage());
            System.out.println(e.getMessage());
            Ui.printBreakline();
        }
        return returnMsg;
    }

    /**
     * @return size of todo list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * @param idx Index of list
     * @return Object of specified index on list
     */
    public Task get(int idx) {
        return taskList.get(idx);
    }

    /**
     * @param o List to be compared to
     * @return Whether the two list share the same tasks
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Tasklist)) {
            return false;
        }
        Tasklist tasklist = (Tasklist) o;
        if (tasklist.size() != this.size()) {
            return false;
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (!tasklist.get(i).equals(this.get(i))) {
                    System.out.printf("different %d", i);
                    return false;
                }
            }
            return true;
        }
    }
}


