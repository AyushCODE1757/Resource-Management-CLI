package com.aayush.cli.storage;

import com.aayush.cli.models.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResourceStore {
    private static final String FILE_PATH = "resources.txt";
    private static ResourceStore instance;
    private ResourceStore(){}
    public static ResourceStore getInstance() {  // add this
        if (instance == null) {
            instance = new ResourceStore();
        }
        return instance;
    }
    public void saveResources(List<Resource> resources) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Resource r : resources) {
                writer.println(serialize(r));
            }
        } catch (IOException e) {
            System.err.println("Error saving resources: " + e.getMessage());
        }
    }

    public List<Resource> loadResources() {
        List<Resource> resources = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists())
            return resources;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resources.add(deserialize(line));
            }
        } catch (IOException e) {
            System.err.println("Error loading resources: " + e.getMessage());
        }
        return resources;
    }

    private String serialize(Resource r) {
        return String.join("|",
                r.getId(),
                r.getName(),
                r.getType().name(),
                r.getAllocationType().name(),
                r.getStatus().name(),
                r.getAllocatedTo() != null ? r.getAllocatedTo() : "NULL",
                r.getLastMaintenance() != null ? r.getLastMaintenance().toString() : "NULL",
                r.getPurchaseDate() != null ? r.getPurchaseDate().toString() : "NULL");
    }

    private Resource deserialize(String line) {
        String[] parts = line.split("\\|");
        ResourceType type = ResourceType.valueOf(parts[2]);

        Resource r;
        if (type == ResourceType.CHAIR || type == ResourceType.DESK) {
            r = new FurnitureResource(parts[0], parts[1], type,
                    AllocationType.valueOf(parts[3]),
                    ResourceStatus.valueOf(parts[4]),
                    parts[5].equals("NULL") ? null : parts[5],
                    parts[6].equals("NULL") ? null : LocalDate.parse(parts[6]),
                    parts[7].equals("NULL") ? null : LocalDate.parse(parts[7]),
                    "Unknown");
        } else {
            r = new DigitalResource(parts[0], parts[1], type,
                    AllocationType.valueOf(parts[3]),
                    ResourceStatus.valueOf(parts[4]),
                    parts[5].equals("NULL") ? null : parts[5],
                    parts[6].equals("NULL") ? null : LocalDate.parse(parts[6]),
                    parts[7].equals("NULL") ? null : LocalDate.parse(parts[7]),
                    "Unknown");
        }
        return r;
    }
}
