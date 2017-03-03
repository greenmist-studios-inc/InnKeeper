package com.greenmist.innkeeper.mvvm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by geoff.powell on 2/18/17.
 */
public interface MVVMBase {

    interface View {

        void startActivity(Intent intent);

        void startActivity(Intent intent, Bundle bundle);

        Context getContext();

    }

    interface ViewModel {

        void onCreate(Bundle savedInstanceState);

        void onResume();

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

        void onPause();

        void onDestroy();

        boolean onBackPressed();

    }

    interface Model {

    }
}
