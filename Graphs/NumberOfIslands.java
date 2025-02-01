import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NumberOfIslands {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[m][n];

        for(int i = 0; i < arr.length; i++) {
            String parts = br.readLine();
            for(int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(parts.split(" ")[j]);
            }
        }

        // Find Number Of Islands
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] == 0 && visited[i][j] == false) {
                    groupConnectedTogether(arr, i, j, visited);
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    public static void groupConnectedTogether(int[][] arr, int i, int j, boolean[][] visited) {
        if(i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || visited[i][j] == true || arr[i][j] == 1) {
            return;
        }
        
        visited[i][j] = true;

        groupConnectedTogether(arr, i - 1, j, visited);
        groupConnectedTogether(arr, i, j + 1, visited);
        groupConnectedTogether(arr, i + 1, j, visited);
        groupConnectedTogether(arr, i, j - 1, visited);
    }
}