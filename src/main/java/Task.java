public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }
  
  public Task(String description, int done){
    this.description = description;
    if(done == 1){
      this.isDone = true;
    } else {
      this.isDone = false;
    }
  }

  public String getDescription() {
    return this.description;
  }

  public String getStatusIcon() {
    return (isDone ? "X" : " "); // mark done task with X
  }

  public void markAsDone(){
    this.isDone = true;
  }

  public String toString(){
    return "[" + this.getStatusIcon() + "] " + this.getDescription() ;
  }
  
  public String toStorageString(){
    int done = 0;
    if(this.isDone){
      done = 1;
    }
    return "T | " + done +  " | " +  this.description + "\n";
  }

}
