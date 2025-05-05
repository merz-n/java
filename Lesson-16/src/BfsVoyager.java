import java.util.*;
public class BfsVoyager implements Voyager{
    @Override
    public int lookupIslands(int[][] map) {
        int m = map.length;
        int n = map[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j< n;j++ ) {
                if(map[i][j] == 1){
                    bfs(map, i, j);
                    count++;
                }
            }
        }


        return count;
    }

    public void bfs(int [][] map, int i,int j){
        int m = map.length;
        int n = map[0].length;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i,j));

        while(!queue.isEmpty()){
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;

            if(x < 0 || x >= m || y < 0 || y >= n) continue;
            if(map[x][y] != 1) continue;

            map[x][y] = 0;

            queue.add(new Point(x,y-1));
            queue.add(new Point(x,y+1));
            queue.add(new Point(x-1,y));
            queue.add(new Point(x+1,y));

        }
    }
}
