package com.resistence.network.application.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonUtil {

    private JsonUtil(){
    }

    public static byte[] convertObjectToJsonBytes(final Object obj) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(obj);
    }

}