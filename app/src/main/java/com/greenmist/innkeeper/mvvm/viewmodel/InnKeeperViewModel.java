package com.greenmist.innkeeper.mvvm.viewmodel;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.greenmist.innkeeper.android.dao.model.HSCard;
import com.greenmist.innkeeper.android.manager.HSLogManager;
import com.greenmist.innkeeper.android.rx.observers.DeckTrackerLogObserver;
import com.greenmist.innkeeper.android.rx.observers.ZoneLogObserver;
import com.greenmist.innkeeper.android.utils.Constants;
import com.greenmist.innkeeper.android.utils.IKLogger;
import com.greenmist.innkeeper.android.utils.StringUtils;
import com.greenmist.innkeeper.model.entities.hs.HSEntity;
import com.greenmist.innkeeper.model.entities.hs.HSEntityReference;
import com.greenmist.innkeeper.model.entities.hs.HSEventBlock;
import com.greenmist.innkeeper.model.entities.hs.HSTagChangeEvent;
import com.greenmist.innkeeper.model.enums.Permission;
import com.greenmist.innkeeper.model.enums.RequestCode;
import com.greenmist.innkeeper.model.enums.hs.Zone;
import com.greenmist.innkeeper.model.enums.hs.ZoneType;
import com.greenmist.innkeeper.mvvm.MVVMHSScan;
import com.greenmist.innkeeper.rest.hs.HSWebClient;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by geoff.powell on 2/18/17.
 */
public class InnKeeperViewModel implements MVVMHSScan.ViewModel {

    private MVVMHSScan.View view;
    private IKLogger logger;
    private Map<Integer, HSEntity> entityMap = new HashMap<>();
    private Map<String, Integer> entityNameIdMap = new HashMap<>();
    private Map<String, Integer> entityCardIdMap = new HashMap<>();
    private Map<Integer, ArrayList<Integer>> entityIdHandMap = new ArrayMap<>();
    private Map<Integer, ArrayList<Integer>> entityIdPlayMap = new ArrayMap<>();
    private Map<Integer, ArrayList<Integer>> entityIdDeckMap = new ArrayMap<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public InnKeeperViewModel(MVVMHSScan.View view, IKLogger logger) {
        this.view = view;
        this.logger = logger;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RequestCode.MULTIPLE_PERMISSIONS.getCode()) {
            for (int i = 0; i < permissions.length; i++) {
                if (Permission.READ_EXTERNAL_STORAGE.getPermission().equals(permissions[i]) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    onReadExternalStorageGranted();
                } else if (Permission.WRITE_EXTERNAL_STORAGE.getPermission().equals(permissions[i]) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    onWriteExternalStorageGranted();
                }
            }
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        if (!compositeDisposable.isDisposed() && compositeDisposable.size() > 0) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void onReadExternalStorageGranted() {
        //TODO Add functionality when permissions are granted
        DeckTrackerLogObserver deckTrackerLogObserver = new DeckTrackerLogObserver(this);

        HSLogManager.startLogScanService(Constants.POWER_LOG_FILENAME, logger)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(deckTrackerLogObserver);

        compositeDisposable.add(deckTrackerLogObserver.getDisposable());
    }

    @Override
    public void onWriteExternalStorageGranted() {
        //TODO Add functionality when permissions are granted
        HSLogManager.createLogConfig(view.getContext(), logger);
    }

    @Override
    public void onGameCreated() {
        entityMap.clear();
        entityCardIdMap.clear();
        entityNameIdMap.clear();
        entityIdHandMap.clear();
        entityIdDeckMap.clear();

        ZoneLogObserver zoneLogObserver = new ZoneLogObserver(this);

        HSLogManager.startLogScanService(Constants.ZONE_LOG_FILENAME, logger)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .delay(1500, TimeUnit.MILLISECONDS)
                .subscribe(zoneLogObserver);

        compositeDisposable.add(zoneLogObserver.getDisposable());
    }

    @Override
    public void onGameEntityCreated(HSEntityReference entityReference) {
        HSEntity hsEntity = getOrCreateEntity(entityReference);
    }

    @Override
    public void onPlayerCreated(HSEntityReference entityReference) {
        HSEntity hsEntity = getOrCreateEntity(entityReference);

        entityIdHandMap.put(entityReference.getPlayerId(), new ArrayList<>());
        entityIdDeckMap.put(entityReference.getPlayerId(), new ArrayList<>());
        entityIdPlayMap.put(entityReference.getPlayerId(), new ArrayList<>());
    }

    @Override
    public void onEventBlock(HSEventBlock eventBlock) {

    }

    @Override
    public void onEntityCreatedOrUpdated(HSEntityReference entityReference) {
        HSEntity hsEntity = getOrCreateEntity(entityReference);
        //TODO More Logic
    }

    private HSEntity getOrCreateEntity(HSEntityReference entityReference) {
        HSEntity hsEntity = entityMap.get(entityReference.getEntityId());

        if (hsEntity == null) {
            hsEntity = new HSEntity();
            hsEntity.setId(entityReference.getEntityId());

            entityMap.put(entityReference.getEntityId(), hsEntity);
        }

        if (StringUtils.isNotBlank(entityReference.getEntityName())) {
            hsEntity.setName(entityReference.getEntityName());
            entityNameIdMap.put(hsEntity.getName(), hsEntity.getId());
        }

        if (StringUtils.isNotBlank(entityReference.getCardId())) {
            entityCardIdMap.put(hsEntity.getCardId(), hsEntity.getId());
            hsEntity.setCardId(entityReference.getCardId());
        }

        if (StringUtils.isNotBlank(hsEntity.getCardId()) && hsEntity.getHsCard() == null) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            HSCard card = realm.where(HSCard.class)
                    .equalTo("cardId", hsEntity.getCardId())
                    .findFirst();
            realm.commitTransaction();

            if (card == null || card.getTimestamp().before(DateTime.now().minusDays(3).toDate())) {
                getHsCard(hsEntity.getCardId());
            } else {
                hsEntity.setHsCard(realm.copyFromRealm(card));
            }
        }

        return hsEntity;
    }

    private void getHsCard(String cardId) {
        Disposable disposable = HSWebClient.getInstance().getCard(cardId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<List<HSCard>>() {
                    @Override
                    public boolean test(List<HSCard> hsCards) throws Exception {
                        for (HSCard card : hsCards) {
                            card.setTimestamp(new Date());
                        }

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(new RealmList<>(hsCards.toArray(new HSCard[hsCards.size()])));
                        realm.commitTransaction();

                        return true;
                    }
                })
                .subscribe(new Consumer<List<HSCard>>() {

                    @Override
                    public void accept(List<HSCard> hsCards) throws Exception {

                        for (HSCard card : hsCards) {
                            HSEntity entity = entityMap.get(entityCardIdMap.get(card.getCardId()));
                            if (entity != null) {
                                entity.setHsCard(hsCards.get(0));
                            }
                        }
                    }
                });
    }

    @Override
    public void onEntityTagChanged(HSTagChangeEvent tagChangeEvent) {
        HSEntityReference entityReference = tagChangeEvent.getEntityReference();
        HSEntity hsEntity = null;

        if (entityReference.getEntityId() > 0) {
            hsEntity = entityMap.get(entityReference.getEntityId());

            if (hsEntity != null && StringUtils.isNotBlank(entityReference.getEntityName()) && !entityNameIdMap.containsKey(entityReference.getEntityName())) {
                hsEntity.setName(entityReference.getEntityName());
                entityNameIdMap.put(entityReference.getEntityName(), entityReference.getEntityId());
            }
        } else if (StringUtils.isNotBlank(entityReference.getEntityName())) {
            Integer id = entityNameIdMap.get(entityReference.getEntityName());
            if (id != null) {
                hsEntity = entityMap.get(id);
            }
        }

        if (hsEntity != null) {
            hsEntity.addProperty(tagChangeEvent.getTag(), tagChangeEvent.getValue());
        }
    }

    @Override
    public int getEntityCount() {
        return entityMap.size();
    }

    @Override
    public int getHandCount(int playerId) {
        ArrayList<Integer> handIds = entityIdHandMap.get(playerId);
        if (handIds == null) {
            return 0;
        }
        return entityIdHandMap.get(playerId).size();
    }

    @Override
    public int getDeckCount(int playerId) {
        ArrayList<Integer> deckIds = entityIdDeckMap.get(playerId);
        if (deckIds == null) {
            return 0;
        }
        return deckIds.size();
    }

    private int getPlayerIdFromZoneType(ZoneType zoneType) {
        if (zoneType == ZoneType.FRIENDLY) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void onEntityZoneChanged(HSEntityReference entityReference, Zone zoneFrom, ZoneType zoneFromType, Zone zoneTo, ZoneType zoneToType) {
        int playerIdFrom = getPlayerIdFromZoneType(zoneFromType);
        int playerIdTo = getPlayerIdFromZoneType(zoneToType);

        switch (zoneFrom) {
            case DECK:
                ArrayList<Integer> deckIds = entityIdDeckMap.get(playerIdFrom);
                deckIds.remove((Integer) entityReference.getEntityId());
                break;

            case HAND:
                ArrayList<Integer> handIds = entityIdHandMap.get(playerIdFrom);
                handIds.remove((Integer) entityReference.getEntityId());
                break;

            case PLAY:
                ArrayList<Integer> playIds = entityIdPlayMap.get(playerIdTo);
                playIds.remove((Integer) entityReference.getEntityId());
                break;
        }

        switch (zoneTo) {
            case DECK:
                ArrayList<Integer> deckIds = entityIdDeckMap.get(playerIdTo);
                deckIds.add(entityReference.getEntityId());
                break;

            case HAND:
                ArrayList<Integer> handIds = entityIdHandMap.get(playerIdTo);
                handIds.add(entityReference.getEntityId());
                break;

            case PLAY:
                ArrayList<Integer> playIds = entityIdPlayMap.get(playerIdTo);
                playIds.add(entityReference.getEntityId());
                break;
        }

        Log.d("T", entityReference.getEntityId() + " " + entityReference.getEntityName() + " " + zoneFromType + " " + zoneFrom + " -> " + zoneToType + " " + zoneTo);
        view.showZoneChange(getOrCreateEntity(entityReference), entityReference.getPlayerId(), zoneFrom, zoneFromType, zoneTo, zoneToType);
    }

    @Override
    public HSEntity getEntity(int position) {
        List<Integer> keyList = new ArrayList<>(entityMap.keySet());
        Collections.sort(keyList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        return entityMap.get(keyList.get(position));
    }

    @Override
    public HSEntity getEntityById(int id) {
        return entityMap.get(id);
    }

    @Override
    public HSEntity getEntityInHand(int playerId, int position) {
        return entityMap.get(entityIdHandMap.get(playerId).get(position));
    }

    @Override
    public HSEntity getEntityInDeck(int playerId, int position) {
        return entityMap.get(entityIdDeckMap.get(playerId).get(position));
    }
}
