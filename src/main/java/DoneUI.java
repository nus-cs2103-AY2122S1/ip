public class DoneUI extends SaberCommandUI {
    protected String successMessage;

    protected final String unableToFindTaskError = "      I'm really sorry, Master.\n"
            + "      I'm ... not sure which task you want me\n"
            + "      to mark as done...\n";

    protected final String argumentError =  "      I'm really sorry, Master.\n"
            + "      I'm unable to find the task that\n"
            + "      you specified...\n";

    public DoneUI() {
        this.successMessage = "";
    }

    public void setSuccessMessage(Task task){
        this.successMessage = "      Understand, Master.\n" + "      I'll mark this done right away.\n"
                + "\n        " + task + "\n";
    }

    public void showSuccess() {
        System.out.println(successMessage);
    }

    public void showUnableToFindTaskError() {
        System.out.println(unableToFindTaskError);
    }

    public void showArgumentError() {
        System.out.println(argumentError);
    }
}
