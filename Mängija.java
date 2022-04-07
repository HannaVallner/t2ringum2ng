
public class Mängija implements Comparable<Mängija> {
    private String[] punktitabel1;
    private int[] punktitabel2;
    private String nimi;

    public Mängija(String nimi) {
        this.punktitabel1 = new String[] {"ÜHED", "KAHED", "KOLMED", "NELJAD", "VIIED", "KUUED"};
        this.punktitabel2 = new int[6];
        this.nimi = nimi;
    }

    public String[] getPunktitabel1() {
        return punktitabel1;
    }

    public void setPunktitabel1(String[] punktitabel1) {
        this.punktitabel1 = punktitabel1;
    }

    public int[] getPunktitabel2() {
        return punktitabel2;
    }

    public void setPunktitabel2(int[] punktitabel2) {
        this.punktitabel2 = punktitabel2;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int arvutaPunktisumma() {
        int punktisumma = 0;
        for (int i = 0; i < 6; i++) {
            punktisumma += punktitabel2[i];
        }
        return punktisumma;
    }

    @Override
    public int compareTo(Mängija m) {
        if (this.arvutaPunktisumma() > m.arvutaPunktisumma())
            return 1;
        else if (this.arvutaPunktisumma() == m.arvutaPunktisumma())
            return 0;
        else
            return -1;
    }
}
