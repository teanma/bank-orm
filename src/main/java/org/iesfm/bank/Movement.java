package org.iesfm.bank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Movement {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int movementId;
    @Column(name = "iban")
    private String movementIban;
    @Column(name = "movement_date")
    private Date movementDate;
    private double amount;
    private String description;

    public int getMovementId() {
        return movementId;
    }

    public void setMovementId(int movementId) {
        this.movementId = movementId;
    }

    public String getMovementIban() {
        return movementIban;
    }

    public void setMovementIban(String movementIban) {
        this.movementIban = movementIban;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return movementId == movement.movementId && Double.compare(movement.amount, amount) == 0 && Objects.equals(movementIban, movement.movementIban) && Objects.equals(movementDate, movement.movementDate) && Objects.equals(description, movement.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movementId, movementIban, movementDate, amount, description);
    }
}
