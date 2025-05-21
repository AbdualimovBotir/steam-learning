package uz.steam_learning.services;

import uz.steam_learning.dtos.ResourceDto;
import uz.steam_learning.models.Resource;

import java.util.List;
import java.util.Optional;

public interface ResourceService {
    List<Resource> getAllResources();
    Optional<Resource> getResourceById(Long id);
    Resource createResource(ResourceDto resourceDto);
    Resource updateResource(Long id, ResourceDto resourceDto);
    boolean deleteResource(Long id);
}
