package com.greenmist.innkeeper.model.entities.hs;

/**
 * Created by geoff.powell on 2/8/17.
 */
public class HSEventBlock {

    private String blockType;
    private HSEntityReference entityReference;
    private String effectCardId;
    private int effectIndex;
    private int target;

    public String getBlockType() {
        return blockType;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public HSEntityReference getEntityReference() {
        return entityReference;
    }

    public void setEntityReference(HSEntityReference entityReference) {
        this.entityReference = entityReference;
    }

    public String getEffectCardId() {
        return effectCardId;
    }

    public void setEffectCardId(String effectCardId) {
        this.effectCardId = effectCardId;
    }

    public int getEffectIndex() {
        return effectIndex;
    }

    public void setEffectIndex(int effectIndex) {
        this.effectIndex = effectIndex;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
