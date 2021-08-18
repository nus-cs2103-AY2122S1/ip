public class Task {
    private String name;
    private static String breakline = "____________________________________________________________";
    private boolean done;

    Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setStatus(boolean status) {
        this.done = status;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        System.out.println(breakline);
    }

    public static Task parseStringIntoTask(String input, String split, String taskType) {
        try {
            if (input.equals("")) {
                String errorMsg = String.format("Oops!!! %s cannot be empty", taskType);
                throw new DukeException(errorMsg);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(breakline);
            return null;
        }

        try {
            if (split.equals("")) {
                return new Todo(input, false);
            } else {
                if (!input.contains(split)) {
                    String errorMsg = String.format("Oops!!! %s cannot be found in %s.", split, taskType);
                    throw new DukeException(errorMsg);
                }
                String[] nameNTime = input.split(split);
                String name = nameNTime[0];
                String time = nameNTime[1];

                Task task = null;

                if(taskType.equals("DEADLINE")) {
                    task = new Deadline(name, false, time);
                } else if (taskType.equals("EVENT")) {
                    task = new Event(name, false, time);
                } else {
                    throw new DukeException("TaskType cannot be found");
                }
                return task;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(breakline);
            return null;
        }

    }

    @Override
    public String toString() {
        return this.name;
    }


}
