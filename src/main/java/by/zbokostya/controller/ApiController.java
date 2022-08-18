package by.zbokostya.controller;


import by.zbokostya.entity.api.UserApi;
import by.zbokostya.security.SecurityUtils;
import by.zbokostya.service.ApiService;
import by.zbokostya.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jooq.JSON;
import org.jooq.tools.json.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.UUID;

@Controller
@RequestMapping("/api/api")
public class ApiController {

    private final ApiService apiService;

    private final UserService userService;

    public ApiController(ApiService apiService, UserService userService) {
        this.apiService = apiService;
        this.userService = userService;
    }


//    @GetMapping("/{api}")
//    public ResponseEntity<String> info(@PathVariable String api){
//
//    }

    @PostMapping("/{api}")
    public ResponseEntity<String> setText(@PathVariable String api, @RequestBody String text) throws URISyntaxException {
        if (api.length() != 32) throw new RuntimeException();
        UUID uuid = parseAPI(api);
        String login = SecurityUtils.getCurrentUserLogin();

        UserApi userTextApi = new UserApi();
        userTextApi.setUuid(uuid);

        userTextApi.setJson(JSON.valueOf(text));

        apiService.saveWithText(login, userTextApi);
        return ResponseEntity.ok(text);
    }

    @GetMapping("/{api}")
    public ResponseEntity<String> getText(@PathVariable String api) throws ParseException {

        return ResponseEntity.ok(apiService.parseJSON(parseAPI(api)));
    }

    private UUID parseAPI(String api) {
        return UUID.fromString(
                api.replaceFirst(
                        "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
                        "$1-$2-$3-$4-$5"
                ));
    }

}
