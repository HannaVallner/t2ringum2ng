import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Laud {
    // Andmestruktuur kõrvalepandud hoiustab Täring-tüüpi isendeid, mis on antud käigu ajal Mängija poolt kõrvale pandud.
    // Andmestruktuur veeretamisel hoiustab Täring-tüüpi isendeid, mis genereeritakse igal veeretamiskorral uuesti.
    private List<Täring> kõrvalepandud;
    private List<Täring> veeretamisel;
    private int kordiVeeretatud;

    public Laud() {
        this.kõrvalepandud = new ArrayList<>();
        this.veeretamisel = new ArrayList<>();
        this.kordiVeeretatud = 0;
    }

    // Meetod veereta genereerib veeretamisel andmestruktuuri uued täringud.
    public void veereta(Mängija mängija) {
        veeretamisel = new ArrayList<>();
        // Eelnevalt kontrollime, kas veeretamise kordade arv on kolmest väiksem (lugemist alustame 0'st),
        // seega on veeretamiste võimalik suurim arv 3.
        if (kordiVeeretatud < 3) {
            // Genereerime veeretamisel andmestruktuuri sellise arvu täringuid, mis kõrvalepandud täringute arvuga liites
            // annaks tulemuseks 5.
            for (int i = 5 - kõrvalepandud.size(); i > 0; i--) {
                veeretamisel.add(new Täring());
            }
            kordiVeeretatud += 1;
            // Kui oleme mingi käigu esimese veeretamise juures,
            if (kordiVeeretatud == 1) {
                int mitmesKäik = 1;
                // leiame punktitabeli hetkeseisu
                int[] hetkeSeis = mängija.getPunktitabel2();
                // ja käigu järjekorranumbri.
                for (int i = 0; i < hetkeSeis.length; i++) {
                    if (hetkeSeis[i] != 0)
                        mitmesKäik += 1;
                }
                // Väljastame, millise mängija mitmenda käiguga algust tegime.
                System.out.println("Algas " + mängija.getNimi() + " " + mitmesKäik + ". käik!");
                // Väljastame punktitabeli, et mängija saaks enne käigu algust veenduda, et
                // ei hakka kogemata koguma juba punktiskoori lisatud silmade arvuga täringuid.
                System.out.println("Sinu punktitabeli hetkeseis: ");
                kuvaPunktitabel(mängija);
            }
            // Kui 3 korda on juba veeretatud, on vastav käik läbi, ja liigume edasi käigu lõpus vajalike tegevusteni.
        } else if (kordiVeeretatud == 3) {
            System.out.println("Käik on läbi!");
            käiguLõpp(mängija);
        }
    }

    // Meetodi käiguLõpp abil saab mängija käigu lõpus saab valida,
    // millise silmade arvuga täringud soovib ta oma punktitabelisse kanda.
    public void käiguLõpp(Mängija mängija) {
        System.out.println("Tulemusena saadud täringud: ");
        System.out.println(Arrays.toString(kõrvalepandud.toArray()));
        String tulemusValik = JOptionPane.showInputDialog(null, "Mitme silmaga täringute skoori soovid punktitabelisse lisada?", "Mängulaud",
                JOptionPane.QUESTION_MESSAGE);
        // Eeldame, et kasutaja valib korrektselt.
        int tulemusTäring = Integer.parseInt(tulemusValik);
        int kordi = 0;
        // Vaatame, mitu mängija valitud silmade arvuga täringut kõrvalepandud täringute hulgas leidub
        for (Täring täring : kõrvalepandud) {
            if (täring.getSilmadeArv() == tulemusTäring)
                kordi += 1;
        }
        // Kirjutame täringu silmade arvu ja esinemiste summa korrutise punktitabelisse
        int skoor = tulemusTäring * kordi;
        int[] tabel = mängija.getPunktitabel2();
        if (tabel[tulemusTäring - 1] == 0) {
            tabel[tulemusTäring - 1] = skoor;
            mängija.setPunktitabel2(tabel);
            // Väljastame mängija punktitabeli hetkeseisu,
            if (!(tabel[0] == 0 || tabel[1] == 0 || tabel[2] == 0 || tabel[3] == 0|| tabel[4] == 0|| tabel[5] == 0)) {
                System.out.println("Sinu lõppskoor: ");
            } else {
                System.out.println(mängija.getNimi() + " punktitabel: ");
            }
            // kasutades selleks ettenähtud meetodit.
            kuvaPunktitabel(mängija);
            tühjenda();
            // Kui tabelis on antud kohale juba nullist erinev tulemus sisestatud, palume kasutajal uuesti valida,
            // milleks kutsume funktsiooni uuesti rekursiivselt välja.
        } else {
            System.out.println("Tabelisse on juba antud silmadearvuga tulemus lisatud! Vali muu täring.");
            käiguLõpp(mängija);
        }
    }

    // Meetod tühjenda algväärtustab kõrvalepandud täringute andmestruktuuri ja veeretamiste kordade arvu.
    // Meetodit rakendame iga käigu lõpus.
    public void tühjenda() {
        kõrvalepandud = new ArrayList<>();
        kordiVeeretatud = 0;
    }

    // Meetod kuva väljastab veeretamise järgselt selle tulemuse ning juba eelnevalt kõrvalepandud täringud.
    public void kuva(Mängija mängija) {
        if (kõrvalepandud.size() != 0) {
            System.out.println("Kõrvalepandud täringud: ");
            System.out.println(Arrays.toString(kõrvalepandud.toArray()));
        }
        System.out.print(mängija.getNimi() + " ");
        System.out.println(kordiVeeretatud + ". veeretamise tulemus: ");
        System.out.println(Arrays.toString(veeretamisel.toArray()));
    }

    // Meetod vali võimaldab kasutajal peale iga veeretamist valida, milliseid täringuid ta kõrvale soovib jätta.
    public void vali(Mängija mängija) {
        String valik = JOptionPane.showInputDialog(null, "Kas soovid mängusolevaid täringuid kõrvale panna? JAH / EI", "Mängulaud",
                JOptionPane.QUESTION_MESSAGE);
        if (valik.equals("JAH")) {
            String täringuvalik = JOptionPane.showInputDialog(null, "Mitmenda täringu soovid mängulaualt kõrvale panna?", "Mängulaud",
                    JOptionPane.QUESTION_MESSAGE);
            // Eeldame, et mängija valib korrektselt.
            int mitmestäring = Integer.parseInt(täringuvalik) - 1;
            kõrvalepandud.add(veeretamisel.get(mitmestäring));
            System.out.println("Kõrvalepandud täringud: ");
            System.out.println(Arrays.toString(kõrvalepandud.toArray()));
            veeretamisel.remove(mitmestäring);
            if (veeretamisel.size() != 0) {
                System.out.println("Mängusolevad täringud: ");
                System.out.println(Arrays.toString(veeretamisel.toArray()));
                vali(mängija);
            }
        }
    }

    // Kuvab punktitabeli hetkeseisu.
    public void kuvaPunktitabel(Mängija mängija) {
        for (int i = 0; i < 6; i++) {
            System.out.print(mängija.getPunktitabel1()[i] + ": ");
            System.out.println(mängija.getPunktitabel2()[i]);
        }
    }

    // Väljastab mängu lõppedes kõikide mängijate tulemused ning võitja(d).
    // Kui tegemist on viigiga, teavitab meetod sellest ka kasutajat.
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

    // Tagastab tõeväärtusena, kas mängijal on veel antud käigu vältel mõtet veeretada
    // ehk kontrollib, ega kõik täringud juba kõrvalepandud pole.
    public boolean kasOnVeelVeeretada(Mängija mängija) {
        if (kõrvalepandud.size() == 5)
            return false;
        else {
            return true;
        }
    }
}
