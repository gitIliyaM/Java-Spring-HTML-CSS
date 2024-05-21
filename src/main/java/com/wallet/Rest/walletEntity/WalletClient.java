package com.wallet.Rest.walletEntity;

import jakarta.persistence.*;

@Entity
@Table()
public class WalletClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long UUID;

    @Column()
    private String login;

    @Column()
    private String password;

    @Column()
    private int amount;

    @Column()
    private String operation;

    public WalletClient() {
    }

    public WalletClient(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public WalletClient(long UUID, int amount, String login, String password) {
        this.UUID = UUID;
        this.amount = amount;
        this.login = login;
        this.password = password;
    }

    public long getUUID() {return UUID;}

    public int getAmount() {return amount;}

    public String getLogin() {return login;}

    public String getPassword() {return password;}

    public String getOperation() {return operation;}

    public void setUUID(long UUID) {this.UUID = UUID;}

    public void setAmount(int amount) { this.amount = amount;}

    public void setLogin(String login) { this.login = login;}

    public void setPassword(String password) {this.password = password;}

    public void setOperation(String operation) {this.operation = operation;}

}
