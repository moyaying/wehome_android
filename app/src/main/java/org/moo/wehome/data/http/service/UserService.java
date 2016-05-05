package org.moo.wehome.data.http.service;

import org.moo.wehome.domain.entity.User;
import org.moo.wehome.domain.entity.WHResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by moo on 16/5/5.
 */
public interface UserService {

    @GET("user/login")
    Observable<WHResponse<User>> login(@Query("account") String account, @Query("password") String password);

}
