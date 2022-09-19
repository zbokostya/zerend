package by.zbokostya.zerend.dao;

import by.zbokostya.zerend.entity.Apikey;

import java.util.UUID;

public interface IApikeyDao {
    Apikey findByUsername(String username);
    Apikey find(UUID uuid);
}
