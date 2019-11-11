package com.example.elab.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class InternetConnectionService extends Service {
    public InternetConnectionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
