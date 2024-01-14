package com.example.trainticketsystem_hashmapbeatstherest.object;

public class Transaction {
    private String transactionID;
    private String transactionType;
    private String transactionAmount;
    private String transactionTime;



    public Transaction(String transactionID, String transactionType, String transactionAmount, String transactionTime) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionTime = transactionTime;
    }
    public Transaction() {
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }
}
