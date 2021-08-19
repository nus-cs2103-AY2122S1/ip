/**
 * Represents a Task object that can be added
 * to users' task list.
 * A Task can be a ToDo, Deadline or Event.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Task {
    protected boolean isDone;
    protected String task;

    protected Task(String task) {
        this.isDone = false;
        this.task = task;
    }

    /**
     * Creates a new Task object
     *
     * @param task String description of the task
     */
    public static Task createTask(String task) throws DukeException  {
        String[] taskArr = task.split(" ", 2);
        String firstWord = taskArr[0];
       if (firstWord.equals("todo")) {
           if (taskArr.length < 2) {
               throw new DukeMissingTaskDescription(new ToDo(" "), new IllegalArgumentException());
           }
           return new ToDo(taskArr[1]);
        } else if (firstWord.equals("deadline")) {
           if (taskArr.length < 2) {
               String[] emptyString = new String[2];
               throw new DukeMissingTaskDescription(new Deadline(emptyString), new IllegalArgumentException());
           } else if (taskArr[1].split("/by", 2).length < 2){
               String[] emptyString = new String[2];
               throw new DukeIncorrectTaskDescription(new Deadline(emptyString), new IllegalArgumentException());
           }
           return new Deadline(taskArr[1].split("/by", 2));
        } else if (firstWord.equals("event")) {
           if (taskArr.length < 2) {
               String[] emptyString = new String[2];
               throw new DukeMissingTaskDescription(new Event(emptyString), new IllegalArgumentException());
           } else if (taskArr[1].split("/at", 2).length < 2){
               String[] emptyString = new String[2];
               throw new DukeIncorrectTaskDescription(new Event(emptyString), new IllegalArgumentException());
           }
           return new Event(taskArr[1].split("/at", 2));
        } else {
            throw new DukeIncorrectCommandWord(new IllegalArgumentException());
        }
    }

    /**
     * Sets the task to be done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * String representation of the Task object
     */
    @Override
    public String toString() {
        String checker = this.isDone ? "X" : " ";
        return "[" + checker + "]" + " " + this.task;
    }
}
