package com.greenmist.innkeeper.model.entities.hs;

import com.greenmist.innkeeper.android.utils.StringUtils;

/**
 * Created by geoff.powell on 2/8/17.
 */
public class HSEntityReference {

    private int entityId;
    private String entityName;
    private String zone;
    private int zonePos;
    private String cardId;
    private int playerId;

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getZonePos() {
        return zonePos;
    }

    public void setZonePos(int zonePos) {
        this.zonePos = zonePos;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public boolean isValidName(String name) {
        return StringUtils.isNotBlank(name) && !name.contains("UNKNOWN ENTITY");
    }

    @Override
    public String toString() {
        return "HSEntityReference{" +
                "entityId=" + entityId +
                ", entityName='" + entityName + '\'' +
                ", zone='" + zone + '\'' +
                ", zonePos=" + zonePos +
                ", cardId='" + cardId + '\'' +
                ", playerId=" + playerId +
                '}';
    }
}
