public abstract class Task {
    private final String description;
    private boolean isDone;
    private final String time;

    private Task(String description, String time){
        this.description = description;
        this.isDone = false;
        this.time = time;
    }

    public abstract String checkStatus();

    public String showDescription(){
        return this.description;
    }

    public String showTime(){
        return this.time;
    }

    public void markDone(){
        isDone = true;
    }

    public class ToDo extends Task{
        public ToDo(String description){
            super(description, "");
        }

        @Override
        public String checkStatus(){
            return (isDone ? "[T]" : "[]" + " " + this.showDescription());
        }
    }

    public class Deadline extends Task{
        public Deadline(String description, String time){
            super(description, time);
        }

        @Override
        public String checkStatus(){
            return (isDone ? "[D]" : "[]" + " " + this.showDescription());
        }
    }

    public class Event extends Task{
        public Event(String description, String time){
            super(description, time);
        }

        @Override
        public String checkStatus(){
            return (isDone ? "[E]" : "[]" + " " + this.showDescription());
        }
    }

}
