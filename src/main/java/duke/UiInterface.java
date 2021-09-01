package duke;

public interface UiInterface {
    void print(String message);
    void showWelcome(String welcomeMessage);
    void sayBye(String endMessage);
    void showLoadingError(String error);
    void showError(String error);
    String readCommand();
}
