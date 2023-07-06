package za.co.mie.model;

import java.util.Objects;

public class Allergy {
    private String allergName;

    public Allergy() {
    }

    public Allergy(String allergName) {
        this.allergName = allergName;
    }

    public String getAllergName() {
        return allergName;
    }

    public void setAllergName(String allergName) {
        this.allergName = allergName;
    }

    @Override
    public String toString() {
        return "Allergy{" + "allergName=" + allergName + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.allergName);
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
        final Allergy other = (Allergy) obj;
        if (!Objects.equals(this.allergName, other.allergName)) {
            return false;
        }
        return true;
    }
    
}
