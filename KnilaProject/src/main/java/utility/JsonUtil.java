package utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    public static <T> T fromJson(String jsonString, Class<T> className) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, className);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse JSON to object: " + jsonString);
        }
    }

    /**
     * Gets the object.
     *
     * @param <T> the
     * @param jsonString the json string
     * @param className the class name
     * @return the object
     */
    public static <T> T getObject(String jsonString, Class<T> className) {

        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, className);
        } catch (NullPointerException ex) {

            System.out.println(jsonString == null ? "The File for the environment is not loaded" : "The required data " + jsonString
                    + " is not in property file");
        } catch (IOException e) {
            System.out.println("Exception in JSON parsing. Cause: " + e);
        }
        return null;
    }
}
