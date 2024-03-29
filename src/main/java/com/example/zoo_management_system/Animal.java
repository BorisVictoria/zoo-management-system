package com.example.zoo_management_system;

import java.sql.Date;

public class Animal
{
    private int anim_id;
    private String anim_name;
    private String anim_type;
    private int age;
    private String diet;
    private Date fed;
    private Date vetted;
    private String notes;
    private int enc_id;

    public Animal(int anim_id, String anim_name, String anim_type, int age, String diet, Date fed, Date vetted, String notes, int enc_id)
    {
        this.anim_id = anim_id;
        this.anim_name = anim_name;
        this.anim_type = anim_type;
        this.age = age;
        this.diet = diet;
        this.fed = fed;
        this.vetted = vetted;
        this.notes = notes;
        this.enc_id = enc_id;
    }

    public int getAnim_id() {
        return anim_id;
    }

    public void setAnim_id(int anim_id) {
        this.anim_id = anim_id;
    }

    public String getAnim_name() {
        return anim_name;
    }

    public void setAnim_name(String anim_name) {
        this.anim_name = anim_name;
    }

    public String getAnim_type() {
        return anim_type;
    }

    public void setAnim_type(String anim_type) {
        this.anim_type = anim_type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Date getFed() {
        return fed;
    }

    public void setFed(Date fed) {
        this.fed = fed;
    }

    public Date getVetted() {
        return vetted;
    }

    public void setVetted(Date vetted) {
        this.vetted = vetted;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getEnc_id() {
        return enc_id;
    }

    public void setEnc_id(int enc_id) {
        this.enc_id = enc_id;
    }
}
