public class Dir {
    private int r;
    private int c;

    //    1. ↓
    //    2. ↑
    //    3. →
    //    4. ←
    //    5. ↘
    //    6. ↖
    //    7. ↗
    //    8. ↙
    public Dir(int type) {
        switch (type) {
            case 1:
                r = 1;
                c = 0;
                break;
            case 2:
                r = -1;
                c = 0;
                break;
            case 3:
                r = 0;
                c = 1;
                break;
            case 4:
                r = 0;
                c = -1;
                break;
            case 5:
                r = 1;
                c = 1;
                break;
            case 6:
                r = -1;
                c = -1;
                break;
            case 7:
                r = -1;
                c = 1;
                break;
            case 8:
                r = 1;
                c = -1;
                break;

        }
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public static int[] getType(int r1, int c1, int r2, int c2) {
        int dr = r2 - r1;
        int dc = c2 - c1;
        int[] type = {0, 0};
        if (dr == 0 || dc == 0 || dr == dc || dr == -dc) {
            if (dr == 0 && dc == 0)
                return null;
            if (dr > 0)
                type[0] = 1;
            if (dr < 0)
                type[0] = -1;
            if (dc > 0)
                type[1] = 1;
            if (dc < 0)
                type[1] = -1;
            return type;
        }
        return null;
    }
}
