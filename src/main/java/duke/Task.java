package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    String actionName;
    boolean compleated;
    String type;
    Date date;

    public Task(String var1, boolean var2, String var3, String var4) {
        this.actionName = var1;
        this.compleated = var2;
        this.type = var3;
        SimpleDateFormat var5 = new SimpleDateFormat("dd/MM/yyyy HHmm");

        try {
            this.date = var5.parse(var4);
        } catch (ParseException var7) {
            var7.printStackTrace();
        }

    }

    public void doneTask() {
        this.compleated = true;
    }

    public String getActionName() {
        return this.actionName;
    }

    public boolean getCompleted() {
        return this.compleated;
    }

    public String getType() {
        return this.type;
    }

    public Date getDate() {
        return this.date;
    }

    public String toString() {
        String var1 = "[ ]";
        if (this.compleated) {
            var1 = "[X]";
        }

        return var1 + " " + this.actionName;
    }

    /**
     * <pre>
     * Child class Todo from Task Class
     * </pre>
     */
    public static class ToDo extends Task{

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
}
