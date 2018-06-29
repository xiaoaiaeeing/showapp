package com.ident.validator.common.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//import com.zhiku.common.util.T;

/**
 * @author sky 2016/6/16 12:13
 */
public class RxUtils {
    /**
     * {@link Observable.Transformer} that transforms the source observable to subscribe in the
     * io thread and observe on the Android's UI thread.
     */
    private static Observable.Transformer ioToMainThreadSchedulerTransformer;


    static {
        ioToMainThreadSchedulerTransformer = createIOToMainThreadScheduler();
    }


    /**
     * Get {@link Observable.Transformer} that transforms the source observable to subscribe in
     * the io thread and observe on the Android's UI thread.
     * <p/>
     * Because it doesn't interact with the emitted items it's safe ignore the unchecked casts.
     *
     * @return {@link Observable.Transformer}
     */
    @SuppressWarnings("unchecked")
    private static <T> Observable.Transformer<T, T> createIOToMainThreadScheduler() {
//        return tObservable -> tObservable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(
//                        Schedulers.computation()) // TODO: remove when https://github.com/square/okhttp/issues/1592 is fixed
//                .observeOn(AndroidSchedulers.mainThread());
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    @SuppressWarnings("unchecked")
    public static <T> Observable.Transformer<T, T> applyIOToMainThreadSchedulers() {
        return ioToMainThreadSchedulerTransformer;
    }

//    private static Observable.Transformer<T, T> trans = new Observable.Transformer<T, T>() {
//        @Override
//        public Observable<T> call(Observable<T> tObservable) {
//            return tObservable.flatMap(new Func1<T, Observable<T>>() {
//                @Override
//                public Observable<T> call(T t) {
//                    if (t instanceof HttpResponse) {
//                        HttpResponse response = (HttpResponse) t;
//                        if (response.isSuccess()) {
//                            return (T) Observable.just(response.data);
//                        } else {
//                            return (T) Observable.error(new AppException(response.status, response.message));
//                        }
//                    }
//                    return (T) Observable.empty();
//                }
//            });
//        }
//    };
}
