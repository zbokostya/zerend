package by.zbokostya.zerend.entity;

import java.time.Instant;
import java.util.UUID;

public class VerificationToken {
    private static final long EXPIRATION = 60 * 60 * 24;
    private UUID id;
    private String token;
    private UUID user;
    private final Instant expireTime;

    public VerificationToken() {
        this.expireTime = Instant.now().plusSeconds(EXPIRATION);
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public Instant getExpireTime() {
        return expireTime;
    }

}
