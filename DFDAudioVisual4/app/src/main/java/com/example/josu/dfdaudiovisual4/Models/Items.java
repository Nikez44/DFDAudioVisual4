package com.example.josu.dfdaudiovisual4.Models;

/**
 * Created by Josué on 31/01/2015.
 */
public class Items {

    private int id;
    private boolean instruction;
    private String description;
    private boolean printer;
    private String allocator;
    private String assigned_to;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInstruction() {
        return instruction;
    }

    public void setInstruction(boolean instruction) {
        this.instruction = instruction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrinter() {
        return printer;
    }

    public void setPrinter(boolean printer) {
        this.printer = printer;
    }

    public String getAllocator() {
        return allocator;
    }

    public void setAllocator(String allocator) {
        this.allocator = allocator;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }
}




