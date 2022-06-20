package com.doordash;

public class Streamer {
        private int[] values;
        private int numSeconds;

        public Streamer(int numSeconds) {
            this.numSeconds = numSeconds;
            this.values = new int[numSeconds];
        }

        public void setValue(int timestamp, int value) {
            values[timestamp % numSeconds] = value;
        }

        public int getMaxValue(int timestamp) {
            int endIndex = timestamp % numSeconds;
            int maxValue = Integer.MIN_VALUE;

            for (int i = 0; i < numSeconds; i++) {
                if (endIndex < 0) {
                    endIndex = values.length - 1;
                }
                maxValue = Math.max(maxValue, values[endIndex]);

                endIndex--;
            }

            return maxValue;
        }
    }