package duke.task;

public class Event extends Task {

  protected String at;

  public Event(String description, String at) {
    super(description);
    this.at = at;
  }

  public Event(String description, String at, int done) {
    super(description, done);
    this.at = at;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.at + ")";
  }
  
  @Override
  public String toStorageString(){
    int done = 0;
    if(this.isDone){
      done = 1;
    }
    return "E | " + done + " | " +  this.description + " | " + this.at + "\n";
  }
  
}