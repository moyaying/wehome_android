package org.moo.framework.domain.action;

import java.util.concurrent.atomic.AtomicBoolean;

import rx.Subscription;

/**
 * Created by moo on 16/1/15.
 */
public abstract class SubscriptionForCancel implements Subscription {

    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    @Override
    public void unsubscribe() {
        if (this.unsubscribed.compareAndSet(false, true)) {
            this.onUnsubscribe();
        }
    }

    @Override
    public boolean isUnsubscribed() {
        return this.unsubscribed.get();
    }

    protected abstract void onUnsubscribe();
}
