/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.Sample;
import java.util.Objects;

/**
 *
 * @author DPoplauski
 */

public class SampleView {

    private long id;
    private String name;
    private float weediness;
    private float gluten;
    private float humidity;
    private String nomenclature;
    private String cultureClass;
    
    public SampleView() {
    }
    public SampleView(Sample sample) {
        if (sample!=null){
            this.id=sample.getId();
            this.name=sample.getName();
            this.weediness=sample.getWeediness();
            this.gluten=sample.getGluten();
            this.humidity=sample.getHumidity();
            this.nomenclature=sample.getNomenclature();
            this.cultureClass=sample.getCultureClass();
        }
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
        return "SampleView{" + "id=" + id + ", name=" + name + ", weediness=" + weediness + ", gluten=" + gluten + ", humidity=" + humidity + ", nomenclature=" + nomenclature + ", cultureClass=" + cultureClass + '}';
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SampleView other = (SampleView) obj;
        if (this.id != other.id) {
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
        if (Float.floatToIntBits(this.weediness) != Float.floatToIntBits(other.weediness)) {
            return false;
        }
        if (Float.floatToIntBits(this.gluten) != Float.floatToIntBits(other.gluten)) {
            return false;
        }
        if (Float.floatToIntBits(this.humidity) != Float.floatToIntBits(other.humidity)) {
            return false;
        }
        return true;
    }
}
