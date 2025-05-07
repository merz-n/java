import java.util.*;
public class DfsVoyager implements Voyager{

    @Override
    public int lookupIslands(int[][] map) {

        int m = map.length;
        int n = map[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j< n;j++ ) {
                if(map[i][j] == 1){
                    dfs(map, i, j);
                    count++;
                }

                // System.out.println(Arrays.toString(map[i]));
            }
        }


        return count;
    }
    /* private void dfs(int [][] map, int i,int j){
                if(i < 0 || i >= map.length || j < 0 || j >= map[0].length ){
                    return;
                }
                if(map[i][j] != 1){
                   return;
                } map[i][j] = 0;
        dfs(map, i,j-1);
        dfs(map, i-1, j);
        dfs(map,i+1, j);
        dfs(map,i,j+1);

    }*/
    private void dfs(int [][] map, int i,int j){
        int m = map.length;
        int n = map[0].length;

        Stack<Point> stack = new Stack<>();
        stack.push(new Point(i,j));

        while (!stack.isEmpty()){
            Point p = stack.pop();
            int x = p.x;
            int y = p.y;

            if(x < 0 || x >= m || y < 0 || y >= n )continue;
            if(map[x][y] != 1)continue;

            map[x][y] = 0;

            stack.push(new Point(x,y-1));
            stack.push(new Point(x,y+1));
            stack.push(new Point(x-1,y));
            stack.push(new Point(x+1,y));
        }

    }
}