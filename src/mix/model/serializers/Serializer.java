package mix.model.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Serializer {
    ObjectMapper objectMapper;

    protected String objectToString(Object object){
        String objectToString = null;
        try{
            objectToString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objectToString;
    }
}
