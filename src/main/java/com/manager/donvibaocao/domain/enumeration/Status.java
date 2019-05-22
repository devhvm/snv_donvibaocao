package com.manager.donvibaocao.domain.enumeration;

/**
 * The Status enumeration.
 */
public enum Status {
    NEW, ACTIVE, SIGNED, FEEDBACK, DELETED;

    public boolean isCanEditable(){
        return this == NEW || this == FEEDBACK || this == DELETED;
    }

    public boolean isFited(){
        return this == ACTIVE || this == SIGNED;
    }

    public boolean isNew(){
        return this == NEW;
    }
}
