package com.doordash;

public class MaxAreaWithSameNumber {
    private static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static int longestIncreasingPath(int[][] arr) {
        int longestPath = 1;
        int n = arr.length, m = arr[0].length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                boolean[][] visited = new boolean[arr.length][arr[0].length];
                longestPath = Math.max(longestPath, dfs(arr, visited, row, col, arr[row][col]));
            }
        }

        return longestPath;
    }

    public static int dfs(int[][] arr, boolean[][] visited, int row, int col, int target) {

        int area = 1;

        visited[row][col] = true;

        for(int[] d : dirs) {
            int newX = row + d[0];
            int newY = col + d[1];

            if (newX < 0 || newY < 0 || newX >= arr.length || newY >= arr[0].length ||
                    visited[newX][newY] || arr[newX][newY] != target) continue;

            area += dfs(arr, visited, newX, newY, target);
        }

        return area;
    }
}
