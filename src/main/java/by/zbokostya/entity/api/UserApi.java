package by.zbokostya.entity.api;


import lombok.Data;
import org.jooq.JSON;
import java.util.UUID;

@Data
public class UserApi {

    private UUID uuid;

    private JSON json;
}
