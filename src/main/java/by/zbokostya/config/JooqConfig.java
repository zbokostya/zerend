package by.zbokostya.config;

import lombok.*;
import org.jooq.*;
import org.jooq.conf.*;
import org.jooq.impl.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.*;

import javax.sql.*;

@Configuration
@RequiredArgsConstructor
public class JooqConfig {
    private final DataSource dataSource;

    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }

    @Bean
    @Primary
    DefaultDSLContext dsl() {
        return new DefaultDSLContext(configuration());
    }

    @Bean
    @Qualifier("optimistic")
    DSLContext dslContext() {
        return DSL.using(connectionProvider(), SQLDialect.POSTGRES, new Settings().withExecuteWithOptimisticLocking(true));
    }

    public DefaultConfiguration configuration() {
        DefaultConfiguration jooqDefaultConfiguration = new DefaultConfiguration();
        jooqDefaultConfiguration.setSQLDialect(SQLDialect.POSTGRES);
        jooqDefaultConfiguration.set(connectionProvider());
        return jooqDefaultConfiguration;
    }


}
