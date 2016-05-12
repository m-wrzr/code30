import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    /*
      R rows and C columns. Each of the cells in this grid might be blank,
      or it might be labeled with an arrow pointing in one of four possible directions: up, right, down, or left.
      */
    private String solve(int ncase, int r, int c, char[][] grid) {

        ArrayList<Triplet> arrows = new ArrayList<>();

        HashMap<Integer, ArrayList<Triplet>> row = new HashMap<>();
        HashMap<Integer, ArrayList<Triplet>> col = new HashMap<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] != '.') {

                    if (!row.containsKey(i)) {
                        row.put(i, new ArrayList<>());
                    }
                    row.get(i).add(new Triplet(i, j, grid[i][j]));

                    if (!col.containsKey(j)) {
                        col.put(j, new ArrayList<>());
                    }
                    col.get(j).add(new Triplet(i, j, grid[i][j]));

                    arrows.add(new Triplet(i, j, grid[i][j]));
                }
            }
        }

        int changes = 0;

        for (Triplet arr : arrows) {

            ArrayList<Triplet> tripR = row.get(arr.getI());
            ArrayList<Triplet> tripC = col.get(arr.getJ());

            if (tripR.size() == 1 && tripC.size() == 1) {
                return "Case #" + ncase + ": IMPOSSIBLE";
            }

            // change if no arrow in line
            if ((arr.getArrow() == '<' || arr.getArrow() == '>')
                    && !hasInLine(arr, row.get(arr.getI()))) {
                changes++;
            } else if ((arr.getArrow() == '^' || arr.getArrow() == 'v')
                    && !hasInLine(arr, col.get(arr.getJ()))) {
                changes++;
            }
        }

        return "Case #" + ncase + ": " + changes;
    }

    private static boolean hasInLine(Triplet t, ArrayList<Triplet> rc) {

        switch (t.getArrow()) {
            case '<':
                for (Triplet tmp : rc) {
                    if (tmp.getJ() < t.getJ()) {
                        return true;
                    }
                }
                break;
            case '>':
                for (Triplet tmp : rc) {
                    if (tmp.getJ() > t.getJ()) {
                        return true;
                    }
                }
                break;
            case '^':
                for (Triplet tmp : rc) {
                    if (tmp.getI() < t.getI()) {
                        return true;
                    }
                }
                break;
            case 'v':
                for (Triplet tmp : rc) {
                    if (tmp.getI() > t.getI()) {
                        return true;
                    }
                }
                break;
        }

        return false;
    }

    public static void main(String[] args) {

        String inputSize = "large";

        try {

            BufferedReader br = new BufferedReader(new FileReader("A-" + inputSize + "-practice.in"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("A-" + inputSize + "-out.txt"));

            StringBuilder res = new StringBuilder();

            int n = Integer.valueOf(br.readLine());
            for (int ncase = 1; ncase <= n; ncase++) {

                int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int r = input[0];
                int c = input[1];

                char[][] grid = new char[r][c];
                for (int i = 0; i < r; i++) {
                    grid[i] = br.readLine().toCharArray();
                }

                res.append(new Solution().solve(ncase, r, c, grid)).append("\n");
            }
            bw.write(res.toString());

            br.close();
            bw.close();

            System.out.println(res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // helper class
    private class Triplet {
        int i;
        int j;
        char arrow;

        Triplet(int i, int j, char arrow) {
            this.i = i;
            this.j = j;
            this.arrow = arrow;
        }

        int getI() {
            return i;
        }

        int getJ() {
            return j;
        }

        char getArrow() {
            return arrow;
        }

        @Override
        public String toString() {
            return "(" + i + "," + j + "," + arrow + ")";
        }
    }
}
