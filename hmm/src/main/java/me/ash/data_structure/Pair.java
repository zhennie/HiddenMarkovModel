package me.ash.data_structure;

/**
 * Created by me.ash on 10/18/15.
 */
public class Pair<L,R> {
    private L l;
    private R r;

    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public L getL() {
        return l;
    }

    public R getR() {
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (l != null ? !l.equals(pair.l) : pair.l != null) return false;
        if (r != null ? !r.equals(pair.r) : pair.r != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = l != null ? l.hashCode() : 0;
        result = 31 * result + (r != null ? r.hashCode() : 0);
        return result;
    }
}
