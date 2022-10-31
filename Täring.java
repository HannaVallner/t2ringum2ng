public class Täring {
    private int silmadeArv;

    // Täringu isendi loomisel genereeritakse sellele suvaline silmade arv, lõigult [1,6].
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
