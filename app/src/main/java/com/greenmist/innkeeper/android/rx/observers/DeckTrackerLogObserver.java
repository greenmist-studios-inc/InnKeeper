package com.greenmist.innkeeper.android.rx.observers;

import com.greenmist.innkeeper.android.log.HSLogMessage;
import com.greenmist.innkeeper.android.utils.IntegerUtils;
import com.greenmist.innkeeper.android.utils.StringUtils;
import com.greenmist.innkeeper.model.entities.hs.HSEntityReference;
import com.greenmist.innkeeper.model.entities.hs.HSTagChangeEvent;
import com.greenmist.innkeeper.mvvm.MVVMHSScan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by geoff.powell on 2/5/17.
 */
public class DeckTrackerLogObserver implements Observer<HSLogMessage> {

    private static final Pattern LOG_TYPE_PATTERN = Pattern.compile("(\\w+)(.*)");
    private static final Pattern CREATE_GAME_ENTITY_PATTERN = Pattern.compile("EntityID=(\\d+)");
    private static final Pattern CREATE_PLAYER_PATTERN = Pattern.compile("EntityID=(\\d+) PlayerID=(\\d+) .*");
    private static final Pattern CREATE_FULL_ENTITY_PATTERN = Pattern.compile(" - Creating ID=(\\d+) CardID=(.*)");
    private static final Pattern SHOW_ENTITY_BASE_PATTERN = Pattern.compile(" - Updating Entity=(.*) CardID=(.*)");
    private static final Pattern ENTITY_PATTERN = Pattern.compile("\\[name=(.*) id=(\\d+) zone=(\\w+) zonePos=(\\d+) cardId=(.*) player=(\\d+)]");
    private static final Pattern TAG_PATTERN = Pattern.compile("=(.+) value=(.+)");
    private static final Pattern TAG_CHANGE_ENTITY_PATTERN = Pattern.compile("Entity=(.*) tag=(.*) value=(.*)");

    private Disposable disposable;
    private HSEntityReference currentEntity;

    private MVVMHSScan.ViewModel eventView;

    public DeckTrackerLogObserver(MVVMHSScan.ViewModel viewModel) {
        this.eventView = viewModel;
    }

    public Disposable getDisposable() {
        return disposable;
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(HSLogMessage hsLogMessage) {
        Matcher typeMatcher = LOG_TYPE_PATTERN.matcher(hsLogMessage.getMessage());

        if (typeMatcher.find()) {
            String type = typeMatcher.group(1);
            String input = typeMatcher.group(2);

            switch (type) {
                case "CREATE_GAME":
                    eventView.onGameCreated();
                    break;

                case "GameEntity":
                    createGameEntity(input);
                    break;

                case "Player":
                    createPlayer(input);
                    break;

                case "FULL_ENTITY":
                    createFullEntity(input);
                    break;

                case "SHOW_ENTITY":
                    updateEntity(input);
                    break;

                case "tag":
                    updateTagForCurrentEntity(input);
                    break;

                case "TAG_CHANGE":
                    tagChangeForEntity(input);
                    break;

                default:
                    currentEntity = null;
                    break;
            }
        }
    }

    private HSEntityReference parseEntityReference(String entityInfo) {
        int id = 0;
        String name = null;
        String zone = null;
        int zonePos = 0;
        String cardId = null;
        int playerId = 0;

        Matcher entityMatcher = ENTITY_PATTERN.matcher(entityInfo);

        if (entityMatcher.find()) {
            name =  entityMatcher.group(1);
            id = Integer.parseInt(entityMatcher.group(2));
            zone = entityMatcher.group(3);
            zonePos = Integer.parseInt(entityMatcher.group(4));
            cardId = entityMatcher.group(5);
            playerId = Integer.parseInt(entityMatcher.group(6));
        } else if (IntegerUtils.isInteger(entityInfo)) {
            id = Integer.parseInt(entityInfo);
        } else {
            name = entityInfo;
        }

        if (id > 0 || StringUtils.isNotBlank(name)) {
            HSEntityReference entityReference = new HSEntityReference();
            if (entityReference.isValidName(name)) {
                entityReference.setEntityName(name);
            }
            entityReference.setEntityId(id);
            if (StringUtils.isNotBlank(name)) {
                entityReference.setCardId(cardId);
            }
            entityReference.setZone(zone);
            entityReference.setZonePos(zonePos);
            entityReference.setPlayerId(playerId);

            return entityReference;
        }
        return null;
    }

    private void createGameEntity(String input) {
        Matcher matcher = CREATE_GAME_ENTITY_PATTERN.matcher(input);
        if (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));

            currentEntity = new HSEntityReference();
            currentEntity.setEntityId(id);

            eventView.onGameEntityCreated(currentEntity);
        }
    }

    private void createPlayer(String input) {
        Matcher matcher = CREATE_PLAYER_PATTERN.matcher(input);
        if (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));
            int playerId = Integer.parseInt(matcher.group(2));

            currentEntity = new HSEntityReference();
            currentEntity.setEntityId(id);
            currentEntity.setPlayerId(playerId);

            eventView.onPlayerCreated(currentEntity);
        }
    }

    private void createFullEntity(String input) {
        Matcher matcher = CREATE_FULL_ENTITY_PATTERN.matcher(input);
        if (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));
            String cardId = matcher.group(2);

            currentEntity = new HSEntityReference();
            currentEntity.setEntityId(id);
            if (StringUtils.isNotBlank(cardId)) {
                currentEntity.setCardId(cardId);
            }

            eventView.onEntityCreatedOrUpdated(currentEntity);
        }
    }

    private void updateEntity(String input) {
        Matcher showEntity = SHOW_ENTITY_BASE_PATTERN.matcher(input);

        if (showEntity.find()) {
            String entityInfo = showEntity.group(1);
            String cardId = showEntity.group(2);

            currentEntity = parseEntityReference(entityInfo);
            if (currentEntity != null) {
                currentEntity.setCardId(cardId);
                eventView.onEntityCreatedOrUpdated(currentEntity);
            }
        }
    }

    private void updateTagForCurrentEntity(String input) {
        if (currentEntity != null) {
            Matcher tagMatcher = TAG_PATTERN.matcher(input);

            if (tagMatcher.find()) {
                String tag = tagMatcher.group(1);
                String value = tagMatcher.group(2);

                HSTagChangeEvent tagChangeEvent = new HSTagChangeEvent();
                tagChangeEvent.setEntityReference(currentEntity);
                tagChangeEvent.setTag(tag);
                tagChangeEvent.setValue(value);

                eventView.onEntityTagChanged(tagChangeEvent);
            }
        }
    }

    private void tagChangeForEntity(String input) {
        Matcher matcher = TAG_CHANGE_ENTITY_PATTERN.matcher(input);
        if (matcher.find()) {
            String entityInfo = matcher.group(1);
            String tag = matcher.group(2);
            String value = matcher.group(3);

            HSEntityReference entityReference = parseEntityReference(entityInfo);

            HSTagChangeEvent hsTagChangeEvent = new HSTagChangeEvent();
            hsTagChangeEvent.setEntityReference(entityReference);
            hsTagChangeEvent.setTag(tag);
            hsTagChangeEvent.setValue(value);

            eventView.onEntityTagChanged(hsTagChangeEvent);
        }
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {
    }

}
