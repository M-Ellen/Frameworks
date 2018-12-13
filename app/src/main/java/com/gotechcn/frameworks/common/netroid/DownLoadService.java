package com.gotechcn.frameworks.common.netroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownLoadService extends Service {
    
    
    public static final String TAG = "DownLoadService";

    public static final int	DOWNLOAD_START	= 1;
    public static final int	DOWNLOAD_PAUSE	= 2;
    public static final int	DOWNLOAD_RESUME	= 3;
    public static final int	DOWNLOAD_DELETE	= 4;
    
    public DownLoadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        
        switch (intent.getFlags()) {
            case DOWNLOAD_START :
//                startDownload(programInfoData);
//                break;
//            case DOWNLOAD_PAUSE :
//                pauseDownload(programInfoData);
//                break;
//            case DOWNLOAD_RESUME :
//                resumeDownload(programInfoData);
//                break;
//            case DOWNLOAD_DELETE :
//                deleteDownload(programInfoData);
//                break;
            default:
                break;
        }
        
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
