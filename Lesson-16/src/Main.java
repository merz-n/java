import java.lang.reflect.Array;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int m = 4;
        int n = 4;

        int[][] matrix = new int[m][n];

        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[1][0] = 1;
        matrix[1][3] = 1;
        matrix[2][0] = 1;
        matrix[3][1] = 1;
        matrix[3][3] = 1;

        System.out.println("Look map: ");
        printMatrix(matrix);

        System.out.println("_____________DFS_______________________");
        int[][] dfsMap= deepCopy(matrix);
        Voyager dfs = new DfsVoyager();
        int countIslandDfs = dfs.lookupIslands(dfsMap);
        printMatrix(dfsMap);
        System.out.println("Found island: " + countIslandDfs);

        System.out.println("_____________BFS_______________________");
        int[][] bfsMap= deepCopy(matrix);
        Voyager bfs = new BfsVoyager();
        int countIslandBfs = bfs.lookupIslands(bfsMap);
        printMatrix(bfsMap);
        System.out.println("Found island: " + countIslandBfs);




    }
    public static void printMatrix(int [][] map){
        for (int[] row : map) {
            System.out.println(Arrays.toString(row));
        }
    }
    public static int [][] deepCopy(int [][] matrix){
        int [][] copy = new int[matrix.length][];
        for (int i = 0; i < matrix.length;i++ ){
            copy[i] = Arrays.copyOf(matrix[i],matrix[i].length);
        }
        return copy;

    }
}