/*
 * Copyright (c) 2013 Fizz Buzz LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.moo.framework.di.modules;

import android.app.Service;

import org.moo.framework.di.ForService;

import dagger.Module;
import dagger.Provides;


@Module
public class BaseServiceModule {
    private Service mService;

    /**
     * Class constructor.
     *
     * @param service the Service with which this module is associated.
     */
    public BaseServiceModule(Service service) {
        mService = service;
    }

    /**
     * Provides the Service
     *
     * @return the Service
     */
    @Provides
    @ForService
    public Service provideService() {
        return mService;
    }

}
