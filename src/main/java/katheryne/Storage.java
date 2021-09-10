package katheryne;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

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
    
    void loadTasks(TaskList lst, String pathName) throws KatheryneException{
        if (Files.isReadable(Paths.get("tasks.json"))) {
            try {
                lst.addAll(Arrays.asList(this.mapper.readValue(
                        Paths.get("tasks.json").toFile(),
                        Task[].class)));
            } catch (IOException e) {
                throw new KatheryneException("I can't seem to find your files... Let's start over afresh.");
            }
        }
    }

    /**
     * Tries to save the tasks to a file.
     * @param lst TaskList
     * @param pathName Location to store tasks.
     */
    void saveTasks(TaskList lst, String pathName) throws KatheryneException {
        try {
            Files.write(Paths.get(pathName), writer.writeValueAsString(lst.getList()).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new KatheryneException("Oh wait! Traveller, I couldn't catch that... Your file wasn't saved");
        }
    }
}
