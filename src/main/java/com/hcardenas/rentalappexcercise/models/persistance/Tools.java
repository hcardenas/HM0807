package com.hcardenas.rentalappexcercise.models.persistance;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
public class Tools {


    @Id
    private String toolsCode;

    private String toolType;

    private String brand;

    @OneToOne(mappedBy = "tools")
    private ToolsAttributes toolsAttributes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tools tools = (Tools) o;
        return getToolsCode() != null && Objects.equals(getToolsCode(), tools.getToolsCode());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
