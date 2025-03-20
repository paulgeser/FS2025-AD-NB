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
package ch.hslu.ad.exercise.n1.balls;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Demonstration von Bällen.
 */
public final class DemoBalls {

    private static Random random = new Random();


    /**
     * Privater Konstruktor.
     */
    private DemoBalls() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {

        Canvas canvas = Canvas.getCanvas();
        final String[] colors = {"red", "black", "blue", "yellow", "green", "magenta"};

        List<Ball> balls = new ArrayList<>();
        List<Thread> threadsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String color = colors[getRandomNumber(0, 5)];
            int radius = getRandomNumber(20, 50);
            int xPos = getRandomNumber(0, canvas.getWidth() - radius);
            int yPos = getRandomNumber(0, (canvas.getHeight() - radius) / 2);
            Ball newBall = new Ball(radius, xPos, yPos, color);
            balls.add(newBall);
            threadsList.add(new Thread(newBall, "ball-" + i));
        }
        // Conventional threads here
        /*threadsList.forEach(Thread::start);*/

        // Virtual threads here
        balls.forEach(Thread::startVirtualThread);
    }

    private static int getRandomNumber(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
