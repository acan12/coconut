package app.beelabs.com.codebase.support.rx;

/**
 * Created by arysuryawan on 9/18/17.
 */

interface IRxCallback {
    public void onNext();
    public void onError();
    public void onComplete();
}
