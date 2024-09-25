package dat.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dat.dtos.PoemDTO;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class PoemService {
    String jsonFilePath = "/Users/jeppekoch/Downloads/poems.json";

    ObjectMapper objectMapper = new ObjectMapper();


    public List<PoemDTO> loadPoem() {
        try {
            return Collections.singletonList(objectMapper.readValue(new File(jsonFilePath), PoemDTO.class));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}


