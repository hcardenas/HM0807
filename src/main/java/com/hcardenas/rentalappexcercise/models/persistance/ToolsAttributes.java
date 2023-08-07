package com.hcardenas.rentalappexcercise.models.persistance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ToolsAttributes {

    @Id
    private String toolType;

    private Long  dailyCharge;
    private Boolean weekdayCharge;
    private Boolean weekendCharge;
    private Boolean holidayCharge;

    @OneToOne
    private Tools tools;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ToolsAttributes that = (ToolsAttributes) o;
        return getToolType() != null && Objects.equals(getToolType(), that.getToolType());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
