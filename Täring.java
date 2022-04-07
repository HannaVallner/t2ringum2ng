public class Täring {
    private int silmadeArv;

    public Täring() {
        this.silmadeArv = (int) (1 + (Math.random() * 6));
    }

    @Override
    public String toString() {
        return Integer.toString(silmadeArv);
    }

    public int getSilmadeArv() {
        return silmadeArv;
    }
}
