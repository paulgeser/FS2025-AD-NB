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
package ch.hslu.ad.n11.stoppable.interrupt;

import java.math.BigInteger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Nebenläufige Aufgabe, die unterbrochen werden kann.
 */
public final class StoppableTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(StoppableTask.class);
    private volatile Thread runThread;

    /**
     * Erzeugt einen Task, der per Interrupt gestoppt werden kann.
     */
    public StoppableTask() {
    }

    @Override
    public void run() {
        runThread = Thread.currentThread();
        final int max = 10000;
        int n = 0;
        while (n < max && !Thread.currentThread().isInterrupted()) {
            BigInteger bigInteger = new BigInteger(Integer.toString(n));
            if (bigInteger.isProbablePrime(Integer.MAX_VALUE)) {
                System.out.print(".");
            }
            n++;
        }
        System.out.println();
        if (max == n) {
            LOG.info("finished: " + runThread.getName());
        } else {
            LOG.info("stopped: " + runThread.getName());
        }
    }
}
