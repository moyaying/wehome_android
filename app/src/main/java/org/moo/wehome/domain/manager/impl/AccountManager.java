package org.moo.wehome.domain.manager.impl;

import org.moo.wehome.data.http.service.HttpService;
import org.moo.wehome.data.http.service.UserService;
import org.moo.wehome.domain.entity.User;
import org.moo.wehome.domain.entity.WHResponse;
import org.moo.wehome.domain.manager.IAccountManager;

import rx.Observable;

/**
 * Created by moo on 16/5/5.
 */
public class AccountManager implements IAccountManager {
    @Override
    public Observable<User> login(String account, String password) {
        return HttpService.getService(UserService.class).login(account, password)
                .map(WHResponse::getData);
    }
}
