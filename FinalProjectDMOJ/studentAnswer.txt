import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Problem: State which problem you are tackling
 * @author Jackie Zou
 */
public class StudentCode  {
    private static final int[][] moves = {
        {-2, -1}, {-1, -2}, {1, -2}, {2, -1}, 
        {2, 1}, {1, 2}, {-1, 2}, {-2, 1}
    };
      
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Input starting position
        //System.out.print("startXY: ");
        int startX = sc.nextInt();
        int startY = sc.nextInt();
        
        //System.out.print("endXY: ");
        // Input target position
        int endX = sc.nextInt();
        int endY = sc.nextInt();
        
         boolean[][] visited = new boolean[8][8];

        // Output the minimum number of moves
         System.out.println(minKnightMoves(startX, startY, endX, endY));
        
    }
     public static int minKnightMoves(int startX, int startY, int endX, int endY) {
        // If the starting and ending positions are the same
        if (startX == endX && startY == endY) {
            return 0;
        }

        // Create a queue for BFS
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[8][8];

        // Add the starting position to the queue
        queue.offer(new int[]{startX, startY, 0}); // {x, y, moves}
        visited[startX - 1][startY - 1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int movesCount = current[2];

            // Try all possible knight moves
            for (int[] m : moves) {
                int newX = x + m[0];
                int newY = y + m[1];

                // Check if the new position is within bounds and not visited
                if (newX >= 1 && newX <= 8 && newY >= 1 && newY <= 8 && !visited[newX - 1][newY - 1]) {
                    // If we reach the target position, return the moves count
                    if (newX == endX && newY == endY) {
                        return movesCount + 1;
                    }

                    // Mark the position as visited and add it to the queue
                    visited[newX - 1][newY - 1] = true;
                    queue.offer(new int[]{newX, newY, movesCount + 1});
                }
            }
        }

        // If no solution is found (should not happen in a chessboard)
        return -1;
    }
}
