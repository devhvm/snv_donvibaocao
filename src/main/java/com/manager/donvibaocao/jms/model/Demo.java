package com.manager.donvibaocao.jms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Demo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7695072041105901329L;
    private String from;
    private String to;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Demo() {
    }

    public Demo(String from, String to, BigDecimal amount, LocalDateTime timestamp) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{serialVersionUID=" + serialVersionUID + ", from='" + from + '\'' + ", to='" + to + '\''
            + ", amount=" + amount + ", timestamp=" + timestamp + '}';
    }
}