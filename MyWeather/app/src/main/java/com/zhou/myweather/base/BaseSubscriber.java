package com.zhou.myweather.base;

import rx.Subscriber;

/**
 * Created by 周利杰 on 2017/9/1.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
