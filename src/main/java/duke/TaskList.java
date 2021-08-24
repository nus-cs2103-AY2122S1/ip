package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    private int numOfTask;

    TaskList() {
        this.taskList = new ArrayList<Task>();
        this.numOfTask = 0;
    }

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.numOfTask = taskList.size();
    }

    public int getNumOfTask() {
        return this.numOfTask;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void list() {
        if (this.numOfTask == 0) {
            System.out.println("List is empty!");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.numOfTask; i++) {
            Task temp = this.taskList.get(i);
            System.out.printf("%s. %s\n", i + 1, temp);
        }
        System.out.println();
    }

    public void markAsDone(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index > this.numOfTask - 1) {
                if (this.numOfTask > 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There are only %s tasks in your list.", taskNumber, this.numOfTask));
                } else if (this.numOfTask == 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is only %s task in your list.", taskNumber, this.numOfTask));
                } else {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is no task in your list.", taskNumber));
                }
            } else if (index < 0) {
                throw new DukeException(String.format("There is no task %s.", taskNumber));
            }
            this.taskList.get(index).markAsDone();
            System.out.println();
        } catch (NumberFormatException ex) {
            System.out.println("Task must be an integer!");
            System.out.println();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public void addTask(String task) {
        try {
            if (task.startsWith("todo")) {
                if (task.substring(4).equals("") || task.substring(4).equals(" ")) {
                    throw new DukeException("The description of todo cannot be empty");
                }
                this.taskList.add(new ToDo(task.substring(5)));
            } else if (task.startsWith("event")) {
                int index = task.indexOf("/at ");
                if (index == -1) {
                    throw new DukeException("You must specify the time for an event.");
                }
                String description = task.substring(6, index - 1);
                String time = task.substring(index + 4);
                this.taskList.add(new Event(description, time));
            } else if (task.startsWith("deadline")) {
                int index = task.indexOf("/by ");
                if (index == -1) {
                    throw new DukeException("You must specify the deadline.");
                }
                String description = task.substring(9, index - 1);
                String time = task.substring(index + 4);
                this.taskList.add(new Deadline(description, time));
            } else {
                throw new DukeException("Sorry I don't understand what that means:(");
            }
            System.out.println("Got it! I have added this task:");
            System.out.println(this.taskList.get(this.numOfTask));
            this.numOfTask = this.numOfTask + 1;
            if (this.numOfTask > 1) {
                System.out.printf("Now you have %s tasks in your list.\n", this.numOfTask);
            } else if (this.numOfTask == 1) {
                System.out.printf("Now you have 1 task in your list.\n");
            } else {
                System.out.println("Your list is empty!");
            }
            System.out.println();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public void deleteTask(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index > this.numOfTask - 1) {
                if (this.numOfTask > 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There are only %s tasks in your list.", taskNumber, this.numOfTask));
                } else if (this.numOfTask == 1) {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is only 1 task in your list.", taskNumber));
                } else {
                    throw new DukeException(String.format("Cannot find task %s." +
                            "There is no task in your list.", taskNumber));
                }
            } else if (index < 0) {
                throw new DukeException(String.format("There is no task %s.", taskNumber));
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(this.taskList.get(index));
            this.taskList.remove(index);
            this.numOfTask = this.numOfTask - 1;
            if (this.numOfTask > 1) {
                System.out.printf("You have %s tasks left on your list.\n", this.numOfTask);
            } else {
                System.out.printf("You have %s task left on your list.\n", this.numOfTask);
            }
            System.out.println();
        } catch (NumberFormatException ex) {
            System.out.println("Task must be an integer!");
            System.out.println();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

}
