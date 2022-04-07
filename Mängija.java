public class Mängija implements Comparable<Mängija> {
    // Mängija punktitabel on jaotatud kaheks:
    // punktitabel1 hoiustab endas võimalike tulemuste pealkirju sõnena.
    // punktitabel2 hoiustab endas saavutatud tulemusi.
    // Punktitabelite kindlatel indeksitel olevad pealkirjad ja nende tulemused on seatud ühtivaks.
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

    public int[] getPunktitabel2() {
        return punktitabel2;
    }

    public void setPunktitabel2(int[] punktitabel2) {
        this.punktitabel2 = punktitabel2;
    }

    public String getNimi() {
        return nimi;
    }

    // Arvutab Mängija isendi senini kogutud punktisumma kokku.
    public int arvutaPunktisumma() {
        int punktisumma = 0;
        for (int i = 0; i < 6; i++) {
            punktisumma += punktitabel2[i];
        }
        return punktisumma;
    }

    // Mängija isendite võrdlemine nende kogupunktisumma alusel,
    // mille rakendamisel sorteeritakse andmestruktuur mittekasvavalt järjestatuks,
    // kus kõrgema punktisummaga Mängija asub vähema punktisummaga Mängijast eespool.
    @Override
    public int compareTo(Mängija m) {
        if (this.arvutaPunktisumma() > m.arvutaPunktisumma())
            return -1;
        else if (this.arvutaPunktisumma() == m.arvutaPunktisumma())
            return 0;
        else
            return 1;
    }

    @Override
    public String toString() {
        return nimi;
    }
}
