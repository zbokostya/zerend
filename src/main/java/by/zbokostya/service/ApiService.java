package by.zbokostya.service;

import by.zbokostya.domain.Tables;
import by.zbokostya.entity.api.UserApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.DSLContext;
import org.jooq.JSON;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    private final DSLContext dsl;

    public ApiService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void saveWithText(String login, UserApi userApi) {
        List<UUID> apisUuids = dsl.select(Tables.USER_API.API_ID)
                .from(Tables.USER_API)
                .join(Tables.USER)
                .on(Tables.USER.ID.eq(Tables.USER_API.USER_ID))
                .where(Tables.USER.LOGIN.eq(login))
                .fetch(Tables.USER_API.API_ID);

        logger.info("userApis:" + apisUuids);
        if (apisUuids.contains(userApi.getUuid()))
            dsl.insertInto(Tables.API)
                    .values(userApi.getUuid(), userApi.getJson())
                    .onDuplicateKeyUpdate()
                    .set(Tables.API.INFO, userApi.getJson())
                    .execute();
        else throw new RuntimeException();
    }

    public String parseJSON(UUID api) throws ParseException {
        JSON values = dsl.selectFrom(Tables.API)
                .where(Tables.API.ID.eq(api))
                .fetchOne(Tables.API.INFO);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(values.data());

        if(jsonObject.get("type").toString().equals("text")) {
            return jsonObject.get("text").toString();
        }
        else return "error";

    }


}
