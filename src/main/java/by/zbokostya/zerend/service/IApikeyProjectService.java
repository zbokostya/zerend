package by.zbokostya.zerend.service;

import by.zbokostya.zerend.entity.Apikey;
import by.zbokostya.zerend.entity.Project;

import java.util.UUID;

public interface IApikeyProjectService {
    Project findByApikey(UUID apikeyId);

    Apikey findByUsername(String login);
}
