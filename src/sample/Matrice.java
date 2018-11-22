package sample;

public class Matrice {
        private int row = 3;
        private int column = 3;
        private int tabMat[][] = new int[row][column];

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public int[][] getTabMat() {
            return tabMat;
        }

        public void setTabMat(int[][] tabMat) {
            this.tabMat = tabMat;
        }
    }
