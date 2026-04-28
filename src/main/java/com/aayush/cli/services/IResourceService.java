package com.aayush.cli.services;

import com.aayush.cli.models.Resource;

import java.util.List;
import java.util.Optional;

public interface IResourceService {
    List<Resource> getAllResources();
    void addResource(Resource resource);
    void updateResource(String id, Resource updated);
    void deleteResource(String id);
    Optional<Resource> getResourceById(String id);
    List<Resource> searchResources(String query);
}
