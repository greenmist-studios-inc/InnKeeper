package com.greenmist.innkeeper.model.entities.hs;

/**
 * Created by geoff.powell on 2/8/17.
 */
public class HSTagChangeEvent {

    private HSEntityReference entityReference;
    private String tag;
    private String value;

    public HSEntityReference getEntityReference() {
        return entityReference;
    }

    public void setEntityReference(HSEntityReference entityReference) {
        this.entityReference = entityReference;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
