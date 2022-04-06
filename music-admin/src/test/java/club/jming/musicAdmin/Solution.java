package club.jming.musicAdmin;

/**
 * 详细描述
 * 给定两个数字 (x, y)，允许以下两种计算：
 * <p>
 * 1.同时对两个数加1， 即 (x, y) -> (x+1, y+1)
 * <p>
 * 2.同时对两个数乘2，即 (x, y) -> (x*2, y*2)
 * <p>
 * 求要将 (x, y) 转换成 (X,Y)，至少需要多次计算，如果不能转换，返回-1
 * <p>
 * <p>
 * <p>
 * 注：x,y,X,Y > 0
 */
public class Solution {
    /**
     * Note: 类名、方法名、参数名已经指定，请勿修改
     * 要在M*M的土地上建造M个房子，为了保证房子的视野，要
     * 求每条横向街道上只能有一个房子，每条竖向街道上也只能
     * 有一个房子，且任意两个房子都不能在同一斜对角线上，
     * 问有多少种建房子的方法 ？比如4 * 4的土地一共有以下两种建造方法，如下：
     *
     * @param n int整型
     * @return int整型
     */
    int res = 0;
    public int buildHouses(int n) {
        // write code here
        dfs(new boolean[n][n],0);
        return res;
    }

    private void dfs(boolean[][] used,int curY){
        if (curY == used.length){
            res += 1;
            return;
        }
        for (int i = 0; i < used.length; i++) {
            if (isOK(used,i,curY)){
                used[i][curY] = true;
                dfs(used,curY+1);
                used[i][curY] = false;
            }
        }
    }

    int[][] dict = {{1,1},{1,-1},{-1,-1},{-1,1}};

    private boolean isOK(boolean[][] used,int x,int y){
        if (x<0 || y<0 || x>=used.length || y>=used.length){
            return false;
        }
        for (int i = 0; i < used.length; i++) {
            if (used[x][i] || used[i][y]){
                return false;
            }
        }
        for (int i = 1; i < used.length; i++) {
            for (int i1 = 0; i1 < dict.length; i1++) {
                int x1 = i*dict[i1][0];
                int y1 = i*dict[i1][1];
                if (x1<0 || y1<0 || x1>=used.length || y1>=used.length){
                    continue;
                }
                if (used[x1][y1]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.buildHouses(1));
    }
}