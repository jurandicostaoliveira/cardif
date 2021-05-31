package com.cardif.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@SuppressWarnings("serial")
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int positionId;

    @Column(length = 50)
    private String positionName;

    private boolean isBoss = false;

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

}
