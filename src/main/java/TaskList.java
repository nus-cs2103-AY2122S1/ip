public class TaskList {
    Task[] list;
    int curSize;
    public TaskList(int size){
        this.list = new Task[size];
        this.curSize = 0;
    }

    public void addTask(Task task){
        this.list[this.curSize] = task;
        this.curSize++;
        String item = "\n" +
        "   ____________________________________________________________\n" +
        "   added: " + task +  "\n" +
        "   ____________________________________________________________";
        System.out.println(item);

    }

    public void done(int num){
        Task task = this.list[num  - 1];
        task.doneTask();
        this.list[num  - 1] = task;
        String item = "\n" +
        "   ____________________________________________________________\n" +
        "   nice Nice! I've marked this task as done: \n" +
        "       " + task.toString() + "\n" + 
        "   ____________________________________________________________";
        System.out.println(item);

    }

    public String toString(){
        String res = "";
        res += "    ____________________________________________________________\n";
        for (int i = 0; i < curSize; i++){
            Task eachTask = this.list[i];
            String each = String.valueOf(i + 1) + ". " + eachTask.toString();
            res += each + "\n";
        }
        res += "    ____________________________________________________________";
        return res;
    }

}
