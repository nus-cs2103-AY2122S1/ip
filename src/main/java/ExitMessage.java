public class ExitMessage extends DukeMessage{
    public void display() {
        System.out.println("Bye user!");
        Duke.conversationState = false;
    }
}
