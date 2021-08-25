public class Deadline extends Task {

  protected String by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  public Deadline(String description, String by, int done) {
    super(description, done);
    this.by = by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.by + ")";
  }

  @Override
  public String toStorageString(){
    int done = 0;
    if(this.isDone){
      done = 1;
    }
    return "D | " + done +" | " +  this.description + " | " + this.by + "\n";
  }
}