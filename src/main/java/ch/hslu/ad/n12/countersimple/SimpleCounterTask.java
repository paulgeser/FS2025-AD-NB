/*
 * Copyright 2025 Hochschule Luzern - Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.ad.n12.countersimple;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Ein Task für einen einfachen Zähler.
 */
public class SimpleCounterTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleCounterTask.class);
    private final SimpleCounter counter;
    private static final Set<Integer> set = new HashSet<>();

    /**
     * Erzeugt einen SimpleCounter Task für die SimpleCounter Demo.
     *
     * @param counter SimpleCounter.
     */
    public SimpleCounterTask(final SimpleCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int value = counter.increment();
            if (SimpleCounterTask.set.add(value)) {
                LOG.info("{}{}", Thread.currentThread().getName(), value);
            } else {
                LOG.info("{}{} exists!!!", Thread.currentThread().getName(), value);
            }
        }
    }
}
