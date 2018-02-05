package com.edu.hibernate.model.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "name",
                column = @Column(name = "WEIGHT_NAME")),
        @AttributeOverride(name = "symbol",
                column = @Column(name = "WEIGHT_SYMBOL"))
})
public class Weight extends Measurement {

    public static Weight kilograms(BigDecimal weight) {
        return new Weight("kilograms", "kg", weight);
    }

    public static Weight pounds(BigDecimal weight) {
        return new Weight("pounds", "lbs", weight);
    }

    @NotNull
    @Column(name = "WEIGHT")
    protected BigDecimal value;

    public Weight(String name, String symbol, BigDecimal weight) {
        super(name, symbol);
        this.value = weight;
    }

    public String toString() {
        return String.format("%s%s", this.value, this.symbol);
    }
}
