package com.aayush.cli;

import com.aayush.cli.models.*;
import com.aayush.cli.services.IResourceService;
import com.aayush.cli.services.ResourceService;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ResourceCLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IResourceService service = new ResourceService();

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   RESOURCE MANAGEMENT SYSTEM (CLI)     ");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> listResources();
                case "2" -> addResource();
                case "3" -> updateResource();
                case "4" -> deleteResource();
                case "5" -> searchResources();
                case "0" -> {
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. List All Resources");
        System.out.println("2. Add New Resource");
        System.out.println("3. Update Resource");
        System.out.println("4. Delete Resource");
        System.out.println("5. Search Resources");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void listResources() {
        List<Resource> resources = service.getAllResources();
        if (resources.isEmpty()) {
            System.out.println("No resources found.");
            return;
        }
        printHeader();
        resources.forEach(System.out::println);
    }

    private static void printHeader() {
        System.out.println("\n" + String.format("%-10s | %-20s | %-12s | %-10s | %-15s | %-15s",
                "ID", "Name", "Type", "Status", "Allocated To", "Alloc. Type"));
        System.out.println("-".repeat(95));
    }

    private static void addResource() {
        System.out.println("\n--- Add New Resource ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.println("Type (LAPTOP, MONITOR, KEYBOARD, MOUSE, CHAIR, DESK, OTHER): ");
        ResourceType type = ResourceType.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Allocation (PERMANENT, TEMPORARY): ");
        AllocationType alloc = AllocationType.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Status (AVAILABLE, ALLOCATED, UNDER_MAINTENANCE, RETIRED): ");
        ResourceStatus status = ResourceStatus.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Allocated To (Leave blank if none): ");
        String allocatedTo = scanner.nextLine();
        if (allocatedTo.isBlank())
            allocatedTo = null;

        Resource r = new Resource(
                UUID.randomUUID().toString().substring(0, 8),
                name, type, alloc, status, allocatedTo, LocalDate.now(), LocalDate.now());

        service.addResource(r);
        System.out.println("Resource added successfully!");
    }

    private static void updateResource() {
        System.out.print("\nEnter ID of resource to update: ");
        String id = scanner.nextLine();
        service.getResourceById(id).ifPresentOrElse(r -> {
            System.out.print("New Name (current: " + r.getName() + ", leave blank to keep): ");
            String name = scanner.nextLine();
            if (!name.isBlank())
                r.setName(name);

            System.out.print("New Status (current: " + r.getStatus() + ", leave blank to keep): ");
            String statusStr = scanner.nextLine();
            if (!statusStr.isBlank())
                r.setStatus(ResourceStatus.valueOf(statusStr.toUpperCase()));

            System.out.print("New Allocated To (current: " + r.getAllocatedTo() + ", leave blank to keep): ");
            String allocatedTo = scanner.nextLine();
            if (!allocatedTo.isBlank())
                r.setAllocatedTo(allocatedTo);

            service.updateResource(id, r);
            System.out.println("Resource updated successfully!");
        }, () -> System.out.println("Resource not found."));
    }

    private static void deleteResource() {
        System.out.print("\nEnter ID of resource to delete: ");
        String id = scanner.nextLine();
        if (service.deleteResource(id)) {
            System.out.println("Resource deleted successfully.");
        } else {
            System.out.println("Resource not found.");
        }
    }

    private static void searchResources() {
        System.out.print("\nEnter search query (name or user): ");
        String query = scanner.nextLine();
        List<Resource> results = service.searchResources(query);
        if (results.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            printHeader();
            results.forEach(System.out::println);
        }
    }
}
