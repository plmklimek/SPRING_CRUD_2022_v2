package com.example.demo.models;

public enum StatusInvitation {
    PENDING, ACCEPTED, REJECTED;

    public boolean isPending() {
        return this == PENDING;
    }

    public boolean iAccepted() {
        return this == ACCEPTED;
    }

    public boolean isRejected() {
        return this == REJECTED;
    }
}
