package org.moo.framework.data.preference;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.f2prateek.rx.preferences.Preference;
import com.google.gson.Gson;

/**
 * Created by moo on 16/4/16.
 */
public class GsonPreferenceAdapter<T> implements Preference.Adapter<T> {

    final Gson gson;
    private Class<T> clazz;

    public GsonPreferenceAdapter(Class<T> clazz) {
        this(null, clazz);
    }

    public GsonPreferenceAdapter(Gson gson, Class<T> clazz) {
        if (gson == null) {
            gson = new Gson();
        }
        this.gson = gson;
        this.clazz = clazz;
    }

    @Override
    public T get(@NonNull String key, @NonNull SharedPreferences preferences) {
        return gson.fromJson(preferences.getString(key, null), clazz);
    }

    @Override
    public void set(@NonNull String key, @NonNull T value, @NonNull SharedPreferences.Editor editor) {
        editor.putString(key, gson.toJson(value));
    }
}
