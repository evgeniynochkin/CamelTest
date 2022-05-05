package com.example.cameltest.processor;

import com.example.cameltest.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;

/**
 * Процессор обработки сообщений.
 * Преобразует сообщения из формата JSON в объект {@link User}
 *
 * @author Evgeniy Nochkin
 */
public class JsonProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = exchange.getIn().getBody(String.class);

        JSONObject inputJson = new JSONObject(json);
        JSONObject userFromJson = inputJson.getJSONObject("user");
        User user = objectMapper.readValue(userFromJson.toString(), User.class);

        exchange.getIn().setBody(user, User.class);
    }
}
