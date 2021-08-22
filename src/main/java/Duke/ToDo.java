package Duke;

 /**
  * <pre>
  * Child class Todo from Task Class
  * </pre>
  */
public class ToDo extends Task{

    /**
     * Defult constructor,
     * no need the date info so justput ramdom.
     */
    public ToDo(String actionName, boolean compleated, String type) {
        super(actionName, compleated, type, "1/1/2000 0000");
    }

    /**
     * Defult to String.
     * @return String representation of task
     */        
    @Override
    public String toString(){
        String check = "[ ]";
        if (this.compleated){
            check = "[X]";
        }
        return "[T] " + check + " " + this.actionName;
    }
}
