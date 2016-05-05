package org.moo.framework.domain.action;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by moo on 15/12/11.
 * <p>
 * 工作用例基类
 */
public abstract class AbsAction implements IAction {
    /**
     * 执行工作的进程调度
     */
    protected Scheduler mJobScheduler;
    /**
     * 返回观察的进程调度
     */
    protected Scheduler mObserveOnScheduler;

    private AbsAction concatWithUseCase = null;

    protected Subscriber mSubscriber;
    protected Subscription mSubscription;// = Subscriptions.empty();

    public AbsAction() {
        this(ActionScheduler.mJobScheduler, ActionScheduler.mainThreadScheduler);
    }

    public AbsAction(Scheduler jobScheduler, Scheduler observeOnScheduler) {
        this.setScheduler(jobScheduler, observeOnScheduler);
    }

    public AbsAction setScheduler(Scheduler jobScheduler, Scheduler observeOnScheduler) {
        if (jobScheduler != null) {
            this.mJobScheduler = jobScheduler;
        }
        if (observeOnScheduler != null) {
            this.mObserveOnScheduler = observeOnScheduler;
        }
        return this;
    }

    private <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(mJobScheduler)
                .observeOn(mObserveOnScheduler);
    }

    protected abstract Observable buildAction();

    private Observable buildActionInternal() {
        Observable action = this.buildAction();
        if (this.concatWithUseCase != null) {
            action = action.concatWith(this.concatWithUseCase.buildActionInternal());
        }

        return action;
    }

    public AbsAction concatWith(AbsAction useCase) {
        this.concatWithUseCase = useCase;
        return this;
    }

    @SuppressWarnings("unchecked")
    private void execute(Subscriber subscriber) {
        this.mSubscription = buildActionInternal()
                .compose(applySchedulers())
                .subscribe(subscriber);
    }

    private void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    public AbsAction setSubscriber(Subscriber useCaseSubscriber) {
        this.mSubscriber = useCaseSubscriber;

        return this;
    }

    @Override
    public void execute() {
        if (this.mSubscriber == null) {
            this.mSubscriber = new DefaultSubscriber<>();
        }
        execute(this.mSubscriber);
    }

    @Override
    public void cancel() {
        this.unSubscribe();
    }
}
