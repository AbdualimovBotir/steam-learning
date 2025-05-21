package uz.steam_learning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.steam_learning.dtos.ResourceDto;
import uz.steam_learning.models.Resource;
import uz.steam_learning.services.ResourceService;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        return resourceService.getResourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resource> createResource(@RequestBody ResourceDto resourceDto) {
        try {
            Resource resource = resourceService.createResource(resourceDto);
            return ResponseEntity.ok(resource);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable Long id, @RequestBody ResourceDto resourceDto) {
        try {
            Resource resource = resourceService.updateResource(id, resourceDto);
            return ResponseEntity.ok(resource);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        boolean deleted = resourceService.deleteResource(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
