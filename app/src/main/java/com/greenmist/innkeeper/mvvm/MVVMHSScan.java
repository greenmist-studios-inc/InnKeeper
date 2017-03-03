package com.greenmist.innkeeper.mvvm;

import com.greenmist.innkeeper.model.entities.hs.HSEntity;
import com.greenmist.innkeeper.model.entities.hs.HSEntityReference;
import com.greenmist.innkeeper.model.entities.hs.HSEventBlock;
import com.greenmist.innkeeper.model.entities.hs.HSTagChangeEvent;
import com.greenmist.innkeeper.model.enums.hs.Zone;
import com.greenmist.innkeeper.model.enums.hs.ZoneType;

/**
 * Created by geoff.powell on 2/18/17.
 */
public interface MVVMHSScan {

    interface View extends MVVMBase.View {

        void notifyInsert(int index);

        void notifyRemoval(int index);

        void showZoneChange(HSEntity hsEntity, int playerId, Zone zoneFrom, ZoneType zoneFromType, Zone zoneTo, ZoneType zoneToType);
    }

    interface ViewModel extends MVVMBase.ViewModel {

        void onReadExternalStorageGranted();

        void onWriteExternalStorageGranted();

        void onGameCreated();

        void onGameEntityCreated(HSEntityReference entityReference);

        void onPlayerCreated(HSEntityReference entityReference);

        void onEventBlock(HSEventBlock eventBlock);

        void onEntityCreatedOrUpdated(HSEntityReference entityReference);

        void onEntityTagChanged(HSTagChangeEvent tagChangeEvent);

        void onEntityZoneChanged(HSEntityReference entityReference, Zone zoneFrom, ZoneType zoneFromType, Zone zoneTo, ZoneType zoneToType);

        int getEntityCount();

        int getHandCount(int playerId);

        int getDeckCount(int playerId);

        HSEntity getEntity(int position);

        HSEntity getEntityById(int id);

        HSEntity getEntityInHand(int playerId, int position);

        HSEntity getEntityInDeck(int playerId, int position);

    }
}
