package com.greenmist.innkeeper.android.rx.observers;

import com.greenmist.innkeeper.android.log.HSLogMessage;
import com.greenmist.innkeeper.model.entities.hs.HSEntityReference;
import com.greenmist.innkeeper.model.enums.hs.Zone;
import com.greenmist.innkeeper.model.enums.hs.ZoneType;
import com.greenmist.innkeeper.mvvm.MVVMHSScan;

import org.joda.time.LocalTime;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by geoff.powell on 2/5/17.
 */
public class ZoneLogObserver implements Observer<HSLogMessage> {

    private static final String ZONE_CHANGE_REGEX = "^id=\\d* local=.* \\[name=(.*) id=(\\d*) zone=.* zonePos=\\d* cardId=(.*) player=(\\d)\\] zone from ?(FRIENDLY|OPPOSING)? ?(.*)? -> ?(FRIENDLY|OPPOSING)? ?(.*)?$";
    private static final Pattern zoneChangePattern = Pattern.compile(ZONE_CHANGE_REGEX);

    private MVVMHSScan.ViewModel viewModel;
    private LocalTime localTime;
    private Disposable disposable;
    private Timer timer = new Timer();

    public ZoneLogObserver(MVVMHSScan.ViewModel viewModel) {
        this.viewModel = viewModel;
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
        if (hsLogMessage.getLoggingMethod().contains("ZoneChangeList.ProcessChanges")) {

            Matcher matcher = zoneChangePattern.matcher(hsLogMessage.getMessage());

            if (matcher.find()) {
                String name = matcher.group(1);
                int id = Integer.parseInt(matcher.group(2));
                String cardId = matcher.group(3);
                int player = Integer.parseInt(matcher.group(4));

                ZoneType zoneFromType = ZoneType.parse(matcher.group(5));
                Zone zoneFrom = Zone.parse(matcher.group(6));
                ZoneType zoneToType = ZoneType.parse(matcher.group(7));
                Zone zoneTo = Zone.parse(matcher.group(8));

                HSEntityReference entityReference = new HSEntityReference();

                if (entityReference.isValidName(name)) {
                    entityReference.setEntityName(name);
                }

                entityReference.setEntityId(id);
                entityReference.setCardId(cardId);
                entityReference.setPlayerId(player);

                if (localTime == null) {
                    localTime = hsLogMessage.getTimeStamp();
                    viewModel.onEntityZoneChanged(entityReference, zoneFrom, zoneFromType, zoneTo, zoneToType);
                } else {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            viewModel.onEntityZoneChanged(entityReference, zoneFrom, zoneFromType, zoneTo, zoneToType);
                        }
                    }, hsLogMessage.getTimeStamp().getMillisOfDay() - localTime.getMillisOfDay());
                }
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        timer.cancel();
    }

    @Override
    public void onComplete() {
    }

}
