package app.beelabs.com.codebase.support.rx;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxTimer {

    public static void doTimer(long delay, boolean repeat, final RxTimer callback){
        Observable<Long> obs = Observable.timer(delay, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io());

                if(repeat) obs = obs.repeat();
                obs.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        callback.onCallback(aLong);

                        return;
                    }

                });
    }


    public void onCallback(Long along){

    }
}
