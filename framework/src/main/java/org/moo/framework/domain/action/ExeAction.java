package org.moo.framework.domain.action;

import rx.Observable;

/**
 * Created by moo on 16/4/15.
 */
class ExeAction extends AbsAction {
    Observable observable;

    public ExeAction(Observable observable) {
        this.observable = observable;
    }

    @Override
    protected Observable buildAction() {
        return this.observable;
    }
}
