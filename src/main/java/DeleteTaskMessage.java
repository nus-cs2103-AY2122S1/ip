public class DeleteTaskMessage extends DukeMessage{
    private Task deletedTask;
    private int taskIndex;

    public DeleteTaskMessage(int index) {
        this.taskIndex = index;
        this.deletedTask = TaskList.getTaskList().getTasks().remove(taskIndex);
    }
    public void display() {
        System.out.println("Theek h bhai... ye task hata diya");
        System.out.println(deletedTask.getTaskString());
        System.out.println("Ab " + TaskList.getTaskList().getSize() + " tasks bache list mein.");
        Duke.conversationState = true;
    }
}
