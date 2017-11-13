package app.beelabs.com.codebase.support.rx;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by arysuryawan on 9/18/17.
 */

public class RxCompositeDisposableManager {
    private static CompositeDisposable compositeDisposable;

    public RxCompositeDisposableManager() {
    }

    public static void add(Disposable disposable) {
        getCompositeDisposable().add(disposable);
    }

    public static void dispose() {
        getCompositeDisposable().dispose();
    }

    public static CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }


    //     ------
    public static void doAction(OnProcess onProcess, final RxCallback rxCallback) {
        getCompositeDisposable().add(doObservable(onProcess)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {


                    @Override
                    public void onNext(String s) {
                        rxCallback.onNext();
                    }

                    @Override
                    public void onError(Throwable e) {
                        rxCallback.onError();
                    }

                    @Override
                    public void onComplete() {
                        rxCallback.onComplete();
                    }
                }));

    }

    static Observable<String> doObservable(final OnProcess onProcess) {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {

                onProcess.onCall();
                return Observable.just("one", "two", "three", "four", "five", "");
            }
        });
    }

    public static class RxCallback implements IRxCallback {

        @Override
        public void onNext() {

        }

        @Override
        public void onError() {

        }

        @Override
        public void onComplete() {
        }
    }

    public static class OnProcess {
        public void onCall() {
        }
    }
}
