package org.moo.wehome.domain.manager;

import org.moo.wehome.domain.entity.User;

import rx.Observable;

/**
 * Created by moo on 16/5/5.
 */
public interface IAccountManager {
    Observable<User> login(String account, String password);
}
