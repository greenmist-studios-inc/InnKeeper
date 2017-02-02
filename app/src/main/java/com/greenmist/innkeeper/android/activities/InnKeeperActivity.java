package com.greenmist.innkeeper.android.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.MenuItem;

import com.greenmist.innkeeper.android.R;
import com.greenmist.innkeeper.model.enums.Permission;
import com.greenmist.innkeeper.model.enums.RequestCode;

import static android.app.Activity.RESULT_OK;

/**
 * Created by geoff.powell on 1/20/2017.
 */

public class InnKeeperActivity extends GMBaseDrawerActivity {

    //TODO Store in shared preferences
    private boolean overlayActive = false;

    @Override
    protected void setupDependencies() {
        setToolbarTitle(R.string.innkeeper);

        if (permissionUtils.checkPermission(this, Permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            onReadExternalStorageGranted();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode.PERMISSION_READ_EXTERNAL.getCode() && resultCode == RESULT_OK) {
            onReadExternalStorageGranted();
        }
    }

    private void onReadExternalStorageGranted() {
        //TODO Add functionality when permissions are granted
    }
}
