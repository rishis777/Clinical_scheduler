package com.clinic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doctor extends Person {
    private String specialization;
    private Map<String, List<String>> bookedSlots;

    public Doctor(String id, String name, String contactInfo, String specialization) {
        super(id, name, contactInfo);
        this.specialization = specialization;
        this.bookedSlots = new HashMap<>();
    }

    public String getSpecialization() { return specialization; }

    public boolean isAvailable(String date, String time) {
        if (bookedSlots.containsKey(date)) {
            return !bookedSlots.get(date).contains(time);
        }
        return true;
    }

    public void bookSlot(String date, String time) {
        bookedSlots.computeIfAbsent(date, k -> new ArrayList<>()).add(time);
    }

    public void releaseSlot(String date, String time) {
        if (bookedSlots.containsKey(date)) {
            bookedSlots.get(date).remove(time);
        }
    }

    @Override
    public String displayInfo() {
        return "Doctor ID: " + getId() + ", Dr. " + name + ", Specialization: " + specialization;
    }
}
