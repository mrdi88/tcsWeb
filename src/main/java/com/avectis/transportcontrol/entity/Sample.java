/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author DPoplauski
 */
@Entity
@Table(name="sample")
public class Sample {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment",strategy="increment")
    private long id;
    private String name;
    private float weediness;
    private float gluten;
    private float humidity;
    private String nomenclature;
    private String cultureClass;
    
    public Sample() {
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getCultureClass() {
        return cultureClass;
    }

    public void setCultureClass(String cultureClass) {
        this.cultureClass = cultureClass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeediness() {
        return weediness;
    }

    public void setWeediness(float weediness) {
        this.weediness = weediness;
    }

    public float getGluten() {
        return gluten;
    }

    public void setGluten(float gluten) {
        this.gluten = gluten;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Sample{" + "id=" + id + ", name=" + name + ", weediness=" + weediness + ", gluten=" + gluten + ", humidity=" + humidity + ", nomenclature=" + nomenclature + ", cultureClass=" + cultureClass + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 47 * hash + Objects.hashCode(this.getName());
        hash = 47 * hash + Float.floatToIntBits(this.getWeediness());
        hash = 47 * hash + Float.floatToIntBits(this.getGluten());
        hash = 47 * hash + Float.floatToIntBits(this.getHumidity());
        hash = 47 * hash + Objects.hashCode(this.getCultureClass());
        hash = 47 * hash + Objects.hashCode(this.getNomenclature());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Sample)) {
            return false;
        }
        final Sample other = (Sample) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getCultureClass(), other.getCultureClass())) {
            return false;
        }
        if (!Objects.equals(this.getNomenclature(), other.getNomenclature())) {
            return false;
        }
        if (Float.floatToIntBits(this.getWeediness()) != Float.floatToIntBits(other.getWeediness())) {
            return false;
        }
        if (Float.floatToIntBits(this.getGluten()) != Float.floatToIntBits(other.getGluten())) {
            return false;
        }
        if (Float.floatToIntBits(this.getHumidity()) != Float.floatToIntBits(other.getHumidity())) {
            return false;
        }
        return true;
    }

    
}
