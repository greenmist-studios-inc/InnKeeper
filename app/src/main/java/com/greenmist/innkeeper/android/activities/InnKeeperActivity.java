package com.greenmist.innkeeper.android.activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.greenmist.innkeeper.android.R;
import com.greenmist.innkeeper.android.adapters.DebugGameLogAdapter;
import com.greenmist.innkeeper.model.entities.hs.HSEntity;
import com.greenmist.innkeeper.model.enums.Permission;
import com.greenmist.innkeeper.model.enums.hs.Zone;
import com.greenmist.innkeeper.model.enums.hs.ZoneType;
import com.greenmist.innkeeper.mvvm.MVVMBase;
import com.greenmist.innkeeper.mvvm.MVVMHSScan;
import com.greenmist.innkeeper.mvvm.viewmodel.InnKeeperViewModel;

import butterknife.BindView;

/**
 * Created by geoff.powell on 1/20/2017.
 */

public class InnKeeperActivity extends GMBaseDrawerActivity implements MVVMHSScan.View {

    @BindView(R.id.cards)           RecyclerView cardList;

    //TODO Store in shared preferences
    private boolean overlayActive = false;

    private MVVMHSScan.ViewModel viewModel;
    private DebugGameLogAdapter adapter;

    @Override
    protected void setupDependencies() {
        setToolbarTitle(R.string.innkeeper);

        viewModel = new InnKeeperViewModel(this, logger);
        cardList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new DebugGameLogAdapter(viewModel);
        cardList.setAdapter(adapter);
        cardList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        if (permissionUtils.checkPermission(this, Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)) {
            viewModel.onReadExternalStorageGranted();
            viewModel.onWriteExternalStorageGranted();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_inn_keeper;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.innkeeper_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.overlay);
        menuItem.setIcon(overlayActive ? R.drawable.ic_overlay_on : R.drawable.ic_overlay_off);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.overlay) {
            overlayActive = !overlayActive;
            item.setIcon(overlayActive ? R.drawable.ic_overlay_on : R.drawable.ic_overlay_off);
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public MVVMBase.ViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void notifyInsert(int index) {
    }

    @Override
    public void notifyRemoval(int index) {
    }

    @Override
    public void showZoneChange(HSEntity hsEntity, int playerId, Zone zoneFrom, ZoneType zoneFromType, Zone zoneTo, ZoneType zoneToType) {

    }
}
