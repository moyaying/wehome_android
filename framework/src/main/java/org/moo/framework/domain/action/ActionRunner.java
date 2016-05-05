package org.moo.framework.domain.action;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by moo on 16/4/15.
 */
public class ActionRunner {

    public static IAction run(Observable observable) {
        return run(observable, new DefaultSubscriber<>());
    }

    public static IAction run(Observable observable, Subscriber subscriber) {
        ExeAction exeAction = new ExeAction(observable);
        exeAction.setSubscriber(subscriber);
        exeAction.execute();
        return exeAction;
    }
}
