package uz.steam_learning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.steam_learning.dtos.ResourceDto;
import uz.steam_learning.models.Resource;
import uz.steam_learning.models.Users;
import uz.steam_learning.repositories.ResourceRepository;
import uz.steam_learning.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserRepository usersRepository;

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Optional<Resource> getResourceById(Long id) {
        return resourceRepository.findById(id);
    }

    @Override
    public Resource createResource(ResourceDto dto) {
        Users creator = usersRepository.findById(dto.getCreatorId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + dto.getCreatorId()));

        Resource resource = new Resource();
        resource.setTitle(dto.getTitle());
        resource.setDescription(dto.getDescription());
        resource.setType(dto.getType());
        resource.setContentUrl(dto.getContentUrl());
        resource.setCreator(creator);

        return resourceRepository.save(resource);
    }

    @Override
    public Resource updateResource(Long id, ResourceDto dto) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found with ID: " + id));

        Users creator = usersRepository.findById(dto.getCreatorId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + dto.getCreatorId()));

        resource.setTitle(dto.getTitle());
        resource.setDescription(dto.getDescription());
        resource.setType(dto.getType());
        resource.setContentUrl(dto.getContentUrl());
        resource.setCreator(creator);

        return resourceRepository.save(resource);
    }

    @Override
    public boolean deleteResource(Long id) {
        if (!resourceRepository.existsById(id)) return false;
        resourceRepository.deleteById(id);
        return true;
    }
}
