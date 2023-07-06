package za.co.mie.model;

import java.util.Objects;

public class Unit {
    private String unit_name;
    private int unitId;

    public Unit() {
    }

    public Unit(String unit_name, int unitId) {
        this.unit_name = unit_name;
        this.unitId = unitId;
    }

    public Unit(String unit_name) {
        this.unit_name = unit_name;
        this.unitId = unitId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.unit_name);
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
        final Unit other = (Unit) obj;
        if (!Objects.equals(this.unit_name, other.unit_name)) {
            return false;
        }
        return true;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    @Override
    public String toString() {
        return "Unit{" + "unit_name=" + unit_name + ", unitId=" + unitId + '}';
    }
    
    
}
