package com.greenmist.innkeeper.model.entities.hs;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

import com.greenmist.innkeeper.android.dao.model.HSCard;

/**
 * Created by geoff.powell on 2/8/17.
 */
public class HSEntity extends BaseObservable {

    private ObservableField<Integer> id;
    private ObservableField<String> cardId;
    private ObservableField<String> name;
    private ObservableField<HSCard> hsCard;
    private ObservableArrayMap<String, String> properties;

    public HSEntity() {
        id = new ObservableField<>();
        cardId = new ObservableField<>();
        name = new ObservableField<>();
        hsCard = new ObservableField<>();
        properties = new ObservableArrayMap<>();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCardId() {
        return cardId.get();
    }

    public void setCardId(String cardId) {
        this.cardId.set(cardId);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public HSCard getHsCard() {
        return hsCard.get();
    }

    public void setHsCard(HSCard hsCard) {
        this.hsCard.set(hsCard);
    }

    public ObservableArrayMap<String, String> getProperties() {
        return properties;
    }

    public void addProperty(String tag, String value) {
        properties.put(tag, value);
    }

}
