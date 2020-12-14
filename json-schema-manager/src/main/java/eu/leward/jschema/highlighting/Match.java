package eu.leward.jschema.highlighting;

public class Match implements Comparable<Match> {
    public final String kind;
    public final int start;
    public final int end;

    public Match(String kind, int start, int end) {
        this.kind = kind;
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Match o) {
        return Integer.compare(start, o.start);
    }

    @Override
    public String toString() {
        return "Match{" +
            "kind='" + kind + '\'' +
            ", start=" + start +
            ", end=" + end +
            '}';
    }
}
