/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.moo.framework.domain.action;

import android.util.Log;

import rx.Subscriber;

/**
 * Default subscriber base class to be used whenever you want default error handling.
 */
public class DefaultSubscriber<T> extends Subscriber<T> {
    private static final String TAG = DefaultSubscriber.class.getSimpleName();

    protected boolean completed;
    protected boolean canceled;
    protected boolean error;

    protected T lastItem;

    public DefaultSubscriber(){
        this.add(new SubscriptionForCancel() {
            @Override
            protected void onUnsubscribe() {
                DefaultSubscriber.this.onUnsubscribe();
            }
        });
    }

    private void onUnsubscribe(){
        if(!isCompleted()) {
            Log.i(TAG, "onUnsubscribe");
            this.canceled = true;

            onCancel();
        }
    }

    protected void onCancel(){
    }

    protected void onCompleted(T data){
    }

    @Override
    public void onCompleted() {
        Log.i(TAG, "onCompleted");
        this.completed = true;
        onCompleted(lastItem);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        error = true;
    }

    @Override
    public void onNext(T t) {
        Log.i(TAG, "onNext:" + t);
        lastItem = t;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public boolean isError() {
        return error;
    }
}
