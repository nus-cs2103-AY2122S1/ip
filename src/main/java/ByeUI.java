public class ByeUI extends SaberCommandUI {
    protected final String successMessage = "      Am I ... no longer needed, Master?\n" +
            "      I understand. I shall excuse myself.\n";

    public void showSuccess() {
        System.out.println(successMessage);
    }
}
