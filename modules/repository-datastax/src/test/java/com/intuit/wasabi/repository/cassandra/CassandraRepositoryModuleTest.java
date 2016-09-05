/*******************************************************************************
 * Copyright 2016 Intuit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.intuit.wasabi.repository.cassandra;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.intuit.wasabi.cassandra.datastax.CassandraDriver;
import com.intuit.wasabi.repository.AuthorizationRepository;
import com.intuit.wasabi.repository.FeedbackRepository;
import com.intuit.wasabi.repository.PagesRepository;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class CassandraRepositoryModuleTest {

    @Test
    public void testConfigure() throws Exception {
        Injector injector = Guice.createInjector(new CassandraRepositoryModule());
        injector.getInstance(Key.get(String.class, Names.named("CassandraInstanceName")));

        assertThat(injector.getInstance(CassandraDriver.class), is(not(nullValue())));
        assertThat(injector.getInstance(CassandraDriver.class).isKeyspaceInitialized(), is(true));

        assertThat(injector.getInstance(AuthorizationRepository.class), is(not(nullValue())));
        assertThat(injector.getInstance(FeedbackRepository.class), is(not(nullValue())));
        assertThat(injector.getInstance(PagesRepository.class), is(not(nullValue())));
    }
}