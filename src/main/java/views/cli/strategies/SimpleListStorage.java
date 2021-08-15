package views.cli.strategies;

import java.util.ArrayList;
import java.util.List;

// Level-2
public class SimpleListStorage extends RespondWith {
    private String listCommand = "list";
    private List<String> userText;

    public SimpleListStorage() {
        userText = new ArrayList<>();
    }

    private String listString() {
        String result = "";

        for (int i = 0; i < userText.size(); i++) {
            result += String.format("%d.%s%s", (i + 1), userText.get(i), System.lineSeparator());
        }
        return result;
    }

    @Override
    public String respond(String query) {
        if (query.equals(listCommand)) {
            return listString();
        }
        userText.add(query);
        return "added: " + query + System.lineSeparator();
    }

}
