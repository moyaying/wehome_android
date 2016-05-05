package org.moo.wehome.data;

import com.f2prateek.rx.preferences.RxSharedPreferences;

import javax.inject.Inject;

/**
 * Created by moo on 16/4/19.
 */
public class Repository {
    private RxSharedPreferences rxSharedPreferences;

    private static final String KEY_MAIN_PAGE_DATA = "keyMainPageData";

    @Inject
    public Repository(RxSharedPreferences rxSharedPreferences) {
        this.rxSharedPreferences = rxSharedPreferences;
    }

//    public Observable<MainPageEntity> getMainPageData() {
//        return getMainPageCache().concatWith(refreshMainPageData());
//    }
//
//    public Observable<MainPageEntity> getMainPageCache() {
////        MainPageEntity entity = new MainPageEntity();
////        entity.setSelectstr("11111");
////        return Observable.just(null);
//        return Observable.just(this.rxSharedPreferences.getObject(KEY_MAIN_PAGE_DATA, new GsonPreferenceAdapter<>(MainPageEntity.class)).get());
////        return this.rxSharedPreferences.getObject(KEY_MAIN_PAGE_DATA, new GsonPreferenceAdapter<>(MainPageEntity.class)).asObservable();
//    }
//
//    public Observable<MainPageEntity> refreshMainPageData() {
////        MainPageEntity entity = new MainPageEntity();
////        entity.setSelectstr("abcd");
////        return Observable.just(entity);
//        return mainPageService.getMainPageData(100)
//                .doOnNext(mainPageEntity -> {
//                    rxSharedPreferences.getObject(KEY_MAIN_PAGE_DATA, new GsonPreferenceAdapter<>(MainPageEntity.class)).set(mainPageEntity);
//                });
//    }
}
