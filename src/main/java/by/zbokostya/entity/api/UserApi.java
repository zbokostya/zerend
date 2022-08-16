package by.zbokostya.entity.api;


import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jooq.JSON;

import javax.persistence.*;
import java.util.UUID;


//@Entity
//@TypeDef(
//        name = "json",
//        typeClass = JsonType.class
//)
//@Table(name = "\"api\"")

@Data
public class UserApi {

    private UUID uuid;

    private JSON json;
}
