package com.aayush.cli.services;

import com.aayush.cli.exceptions.ResourceNotFoundException;
import com.aayush.cli.models.Resource;
import com.aayush.cli.storage.ResourceStore;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResourceService implements IResourceService {
    private final ResourceStore store;
    private List<Resource> resources;

    public ResourceService() {
        this.store = new ResourceStore();
        this.resources = store.loadResources();
    }

    public List<Resource> getAllResources() {
        return resources;
    }

    public List<Resource> searchResources(String query) {
        return resources.stream()
                .filter(r -> r.getName().toLowerCase().contains(query.toLowerCase()) ||
                        (r.getAllocatedTo() != null && r.getAllocatedTo().toLowerCase().contains(query.toLowerCase())))
                .collect(Collectors.toList());
    }

    public void addResource(Resource resource) {
        resources.add(resource);
        store.saveResources(resources);
    }

    public void updateResource(String id, Resource updated) {
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).getId().equals(id)) {
                resources.set(i, updated);
                store.saveResources(resources);
            }
        }
        throw new ResourceNotFoundException(id);
    }

    public void deleteResource(String id) {
        boolean removed = resources.removeIf(r -> r.getId().equals(id));
        if (removed) {
            store.saveResources(resources);
        }
        throw new ResourceNotFoundException(id);
    }

    public Optional<Resource> getResourceById(String id) {
        return resources.stream().filter(r -> r.getId().equals(id)).findFirst();
    }
}
