package com.greenmist.innkeeper.android.dao.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.greenmist.innkeeper.android.BR;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmModel;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by geoff.powell on 2/5/17.
 */
@RealmClass
public class HSCard implements RealmModel, Serializable, Observable {

    @PrimaryKey
    private String cardId;
    private String name;
    private String cardSet;
    private String type;
    private int health;
    private String img;
    private String imgGold;
    private String locale;
    private String faction;
    private String rarity;
    private int cost;
    private int attack;
    private String text;
    private String flavor;
    private String artist;
    private boolean collectible;
    private boolean elite;
    private String race;
    private Date timestamp;

    @Bindable
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
        notifyPropertyChanged(BR.cardId);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getCardSet() {
        return cardSet;
    }

    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
        notifyPropertyChanged(BR.cardSet);
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Bindable
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        notifyPropertyChanged(BR.health);
    }

    @Bindable
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
        notifyPropertyChanged(BR.img);
    }

    @Bindable
    public String getImgGold() {
        return imgGold;
    }

    public void setImgGold(String imgGold) {
        this.imgGold = imgGold;
        notifyPropertyChanged(BR.imgGold);
    }

    @Bindable
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
        notifyPropertyChanged(BR.locale);
    }

    @Bindable
    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
        notifyPropertyChanged(BR.faction);
    }

    @Bindable
    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
        notifyPropertyChanged(BR.rarity);
    }

    @Bindable
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
        notifyPropertyChanged(BR.cost);
    }

    @Bindable
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
        notifyPropertyChanged(BR.attack);
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    @Bindable
    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
        notifyPropertyChanged(BR.flavor);
    }

    @Bindable
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
        notifyPropertyChanged(BR.artist);
    }

    @Bindable
    public boolean isCollectible() {
        return collectible;
    }

    public void setCollectible(boolean collectible) {
        this.collectible = collectible;
        notifyPropertyChanged(BR.collectible);
    }

    @Bindable
    public boolean isElite() {
        return elite;
    }

    public void setElite(boolean elite) {
        this.elite = elite;
        notifyPropertyChanged(BR.elite);
    }

    @Bindable
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
        notifyPropertyChanged(BR.race);
    }

    @Bindable
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        notifyPropertyChanged(BR.timestamp);
    }


    /***
     * Code below from com.android.databinding.BaseObservable
     ***/

    @Ignore
    private transient PropertyChangeRegistry mCallbacks;

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (mCallbacks == null) {
            mCallbacks = new PropertyChangeRegistry();
        }
        mCallbacks.add(callback);
    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (mCallbacks != null) {
            mCallbacks.remove(callback);
        }
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public synchronized void notifyChange() {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, 0, null);
        }
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }
}
