package org.splay.async;

import android.os.Handler;

/**
 * Created by jeffrey on 16-2-6.
 */
public interface AsyncCallback {

    public interface RemoteRequstCallback {
        public static final int REMOTE_REQUEST_ONSUCCESS = 0x10000;
        public static final int REMOTE_REQUEST_ONERROR = 0x10001;
        public static final int REMOTE_REQUEST_ONFINSHED = 0x10002;
        public static final int REMOTE_REQUEST_ONCACHE = 0x10003;

        public void registerForRequestOnSuccess(Handler h, int what, Object obj);
        public void unregisterForRequestOnSuccess(Handler h);
        public void notifyRequestOnSuccessRegistrants(AsyncResult ar);

        public void registerForRequestOnCache(Handler h, int what, Object obj);
        public void unregisterForRequestOnCache(Handler h);
        public void notifyRequestOnCacheRegistrants(AsyncResult ar);

        public void registerForRequestOnError(Handler h, int what, Object obj);
        public void unregisterForRequestOnError(Handler h);
        public void notifyRequestOnErrorRegistrants(AsyncResult ar);

        public void registerForRequestOnFinished(Handler h, int what, Object obj);
        public void unregisterForRequestOnFinished(Handler h);
        public void notifyRequestOnFinishedRegistrants(AsyncResult ar);
    }
}
