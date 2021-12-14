package org.iesfm.bank;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Account {

    @Id
    @Column(name = "iban")
    private String accountIban;
    @OneToMany
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private int ownerId;
    private double balance;
    @Column(name = "open_date")
    private Date openDate;

    public String getAccountIban() {
        return accountIban;
    }

    public void setAccountIban(String accountIban) {
        this.accountIban = accountIban;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return ownerId == account.ownerId && Double.compare(account.balance, balance) == 0 && Objects.equals(accountIban, account.accountIban) && Objects.equals(openDate, account.openDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountIban, ownerId, balance, openDate);
    }
}
