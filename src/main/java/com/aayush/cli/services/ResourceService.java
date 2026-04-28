package com.aayush.cli.services;

import com.aayush.cli.exceptions.ResourceNotFoundException;
import com.aayush.cli.models.Resource;
import com.aayush.cli.storage.ResourceStore;

import java.util.*;
import java.util.stream.Collectors;

public class ResourceService implements IResourceService {
    private final ResourceStore store;
    private List<Resource> resources;
    private final Map<String, Resource> resourceMap = new HashMap<>();
    public ResourceService() {
        this.store = ResourceStore.getInstance();
        this.resources = store.loadResources();
        for (Resource r : resources) {
            resourceMap.put(r.getId(), r);
        }
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
        resourceMap.put(resource.getId(), resource);
        store.saveResources(resources);
    }

    public void updateResource(String id, Resource updated) {
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).getId().equals(id)) {
                resources.set(i, updated);
                resourceMap.put(id, updated);
                store.saveResources(resources);
                return ;
            }
        }
        throw new ResourceNotFoundException(id);
    }

    public void deleteResource(String id) {
        boolean removed = resources.removeIf(r -> r.getId().equals(id));
        if (removed) {
            store.saveResources(resources);
            resourceMap.remove(id);
            return ;
        }
        throw new ResourceNotFoundException(id);
    }

    public Optional<Resource> getResourceById(String id) {
        return Optional.ofNullable(resourceMap.get(id));
    }
    public List<Resource> getSortedResources(String field) {
        Comparator<Resource> comparator = switch (field) {
            case "name" -> Comparator.comparing(Resource::getName);
            case "type" -> Comparator.comparing(r -> r.getType().name());
            case "status" -> Comparator.comparing(r -> r.getStatus().name());
            default -> Comparator.comparing(Resource::getId);
        };
        return resources.stream().sorted(comparator).collect(Collectors.toList());
    }
}
