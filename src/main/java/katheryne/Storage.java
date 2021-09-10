package katheryne;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import katheryne.task.Task;

public class Storage {

    private ObjectMapper mapper;
    private ObjectWriter writer;

    public Storage() {
        mapper = new ObjectMapper().enableDefaultTyping();
        mapper.registerModule(new JavaTimeModule());
        
        // give object mapper the ability to write the properties of tasks to json
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        writer = mapper.writer(new DefaultPrettyPrinter());
    }
    
    void loadTasks(List lst, String pathName) {
        if (Files.isReadable(Paths.get("tasks.json"))) {
            try {
                lst.addAll(Arrays.asList(this.mapper.readValue(
                        Paths.get("tasks.json").toFile(),
                        Task[].class)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Tries to save the tasks to a file.
     * @param lst TaskList
     * @param pathName Location to store tasks.
     */
    void saveTasks(List lst, String pathName) throws KatheryneExceptions{
        try {
            Files.write(Paths.get(pathName), writer.writeValueAsString(lst).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new KatheryneExceptions("Oh wait! Traveller, I couldn't catch that... Your file wasn't saved");
        }
    }
}
