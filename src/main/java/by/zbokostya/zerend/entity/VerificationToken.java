package by.zbokostya.zerend.entity;

import java.time.Instant;
import java.util.UUID;

public class VerificationToken {
    private static final long EXPIRATION = 60 * 60 * 24;
    private UUID id;
    private String token;
    private UUID userId;
    private Instant expireTime;

    public Instant calcExpireTime() {
        return Instant.now().plusSeconds(EXPIRATION);
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Instant getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Instant expireTime) {
        this.expireTime = expireTime;
    }
}
