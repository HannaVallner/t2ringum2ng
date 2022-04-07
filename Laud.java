import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Laud {
    private List<Täring> kõrvalepandud;
    private List<Täring> veeretamisel;
    private int kordiVeeretatud;

    public Laud() {
        this.kõrvalepandud = new ArrayList<>();
        this.veeretamisel = new ArrayList<>();
        this.kordiVeeretatud = 0;
    }

    public List<Täring> getKõrvalepandud() {
        return kõrvalepandud;
    }

    public void setKõrvalepandud(List<Täring> kõrvalepandud) {
        this.kõrvalepandud = kõrvalepandud;
    }


    public void setVeeretamisel(List<Täring> veeretamisel) {
        this.veeretamisel = veeretamisel;
    }

    public void setKordiVeeretatud(int kordiVeeretatud) {
        this.kordiVeeretatud = kordiVeeretatud;
    }

    public void veereta(Mängija mängija) {
        veeretamisel = new ArrayList<>();
        if (kordiVeeretatud < 3) {
            for (int i = 5 - kõrvalepandud.size(); i > 0; i--) {
                veeretamisel.add(new Täring());
            }
            kordiVeeretatud += 1;
            if (kordiVeeretatud == 1) {
                int mitmesKäik = 1;
                int[] hetkeSeis = mängija.getPunktitabel2();
                for (int i = 0; i < hetkeSeis.length; i++) {
                    if (hetkeSeis[i] != 0)
                        mitmesKäik += 1;
                }
                System.out.println("Algas " + mängija.getNimi() + " " + mitmesKäik + ". käik!");
                // Väljastame punktitabeli, et mängija saaks enne käigu algust veenduda, et
                // ei hakka kogemata koguma juba punktiskoori lisatud silmade arvuga täringuid.
                System.out.println("Sinu punktitabeli hetkeseis: ");
                kuvaPunktitabel(mängija);
            }
        } else if (kordiVeeretatud == 3) {
            System.out.println("Käik on läbi!");
            käiguLõpp(mängija);
        }
    }

    public boolean kasKäigudOtsad(Mängija mängija) {
        int mitmesKäik = 0;
        int[] punktitabel = mängija.getPunktitabel2();
        for (int i = 0; i < punktitabel.length; i++) {
            if (punktitabel[i] != 0)
                mitmesKäik += 1;
        }
        if (mitmesKäik == 6)
            return true;
        else
            return false;
    }

    public void käiguLõpp(Mängija mängija) {
        System.out.println("Tulemusena saadud täringud: ");
        System.out.println(Arrays.toString(kõrvalepandud.toArray()));
        String tulemusValik = JOptionPane.showInputDialog(null, "Mitme silmaga täringute skoori soovid punktitabelisse lisada?", "Mängulaud",
                JOptionPane.QUESTION_MESSAGE);
        int tulemusTäring = Integer.parseInt(tulemusValik);
        int kordi = 0;
        for (Täring täring : kõrvalepandud) {
            if (täring.getSilmadeArv() == tulemusTäring)
                kordi += 1;
        }
        int skoor = tulemusTäring * kordi;
        int[] tabel = mängija.getPunktitabel2();
        if (tabel[tulemusTäring - 1] == 0) {
            tabel[tulemusTäring - 1] = skoor;
            mängija.setPunktitabel2(tabel);
            if (!(tabel[0] == 0 || tabel[1] == 0 || tabel[2] == 0 || tabel[3] == 0|| tabel[4] == 0|| tabel[5] == 0)) {
                System.out.println("Sinu lõppskoor: ");
            } else {
                System.out.println(mängija.getNimi() + " punktitabel: ");
            }
            kuvaPunktitabel(mängija);
            tühjenda();
        } else {
            System.out.println("Tabelisse on juba antud silmadearvuga tulemus lisatud! Vali muu täring.");
            käiguLõpp(mängija);
        }
    }

    public void tühjenda() {
        kõrvalepandud = new ArrayList<>();
        kordiVeeretatud = 0;
    }

    public void kuva(Mängija mängija) {
        if (kõrvalepandud.size() != 0) {
            System.out.println("Kõrvalepandud täringud: ");
            System.out.println(Arrays.toString(kõrvalepandud.toArray()));
        }
        System.out.print(mängija.getNimi() + " ");
        System.out.println(kordiVeeretatud + ". veeretamise tulemus: ");
        System.out.println(Arrays.toString(veeretamisel.toArray()));
    }

    public void vali(Mängija mängija) {
        String valik = JOptionPane.showInputDialog(null, "Kas soovid mängusolevaid täringuid kõrvale panna? JAH / EI", "Mängulaud",
                JOptionPane.QUESTION_MESSAGE);
        if (valik.equals("JAH")) {
            String täringuvalik = JOptionPane.showInputDialog(null, "Mitmenda täringu soovid mängulaualt kõrvale panna?", "Mängulaud",
                    JOptionPane.QUESTION_MESSAGE);
            // Eeldame, et mängija valib õigesti
            int mitmestäring = Integer.parseInt(täringuvalik) - 1;
            kõrvalepandud.add(veeretamisel.get(mitmestäring));
            System.out.println("Kõrvalepandud täringud: ");
            System.out.println(Arrays.toString(kõrvalepandud.toArray()));
            veeretamisel.remove(mitmestäring);
            System.out.println("Mängusolevad täringud: ");
            System.out.println(Arrays.toString(veeretamisel.toArray()));
            vali(mängija);
        }
    }

    public void kuvaPunktitabel(Mängija mängija) {
        for (int i = 0; i < 6; i++) {
            System.out.print(mängija.getPunktitabel1()[i] + ": ");
            System.out.println(mängija.getPunktitabel2()[i]);
        }
    }

    public void mänguLõpp(List<Mängija> mängijad) {
        Collections.sort(mängijad);
        List<Mängija> viigisVõitjad = new ArrayList<>();
        System.out.println("Mängu tulemus: ");
        int võitjaSumma = 0;
        for (Mängija mängija : mängijad) {
            if (mängija == mängijad.get(0)) {
                võitjaSumma = mängija.arvutaPunktisumma();
                System.out.println(mängija + ": " + võitjaSumma);
            } else {
                int punktisumma = mängija.arvutaPunktisumma();
                System.out.println(mängija + ": " + punktisumma);
                if (punktisumma == võitjaSumma) {
                    viigisVõitjad.add(mängijad.get(0));
                    viigisVõitjad.add(mängija);
                }
            }
        }
        if (viigisVõitjad.size() == 0) {
            System.out.println("Mängu võitis " + mängijad.get(0) + ".");
        } else {
            System.out.print("Mäng jäi viiki! Võitjad: ");
            for (Mängija mängija : viigisVõitjad) {
                if (mängija == viigisVõitjad.get(viigisVõitjad.size() - 1))
                    System.out.println(mängija + ".");
                else
                    System.out.print(mängija + ", ");
            }
        }
    }
}
