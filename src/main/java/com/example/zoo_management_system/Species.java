package com.example.zoo_management_system;

public class Species
{
    String species;
    String enclosure;
    int count;
    int min;
    int ave;
    int max;

    public Species(String enclosure, String species, int count, int min, int ave, int max) {
        this.species = species;
        this.enclosure = enclosure;
        this.count = count;
        this.min = min;
        this.ave = ave;
        this.max = max;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getAve() {
        return ave;
    }

    public void setAve(int ave) {
        this.ave = ave;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
