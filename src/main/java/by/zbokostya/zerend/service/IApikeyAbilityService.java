package by.zbokostya.zerend.service;

import by.zbokostya.zerend.entity.Ability;

import java.util.UUID;

public interface IApikeyAbilityService {

    Ability getById(UUID apikeyId, UUID projectId, UUID abilityId);
    Ability insert(UUID apikeyId, UUID projectId, Ability ability);
}
