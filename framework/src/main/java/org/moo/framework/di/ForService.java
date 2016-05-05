package org.moo.framework.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by moo on 15/9/18.
 */
@Scope
@Retention(RUNTIME)
public @interface ForService {}