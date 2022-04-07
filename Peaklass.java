import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Peaklass {

    public static void main(String[] args) throws Exception {
        System.out.println("Mängureeglid: \n" +
                "1. Mängijate arv saab olla 1-4 mängijat. \n" +
                "2. Igal mängijal on 6 käiku. \n" +
                "3. Iga mängija saab ühe käigu ajal veeretada täringuid 3 korda. \n" +
                "4. Peale igat veeretamist saab mängija panna veeretatud täringutest kõrvale endale sobivad täringud. \n" +
                "5. Kõrvale pandud täringuid mängulauale ehk veeretamisele tagasi panna ei saa. \n" +
                "6. Peale käigu lõppemist saab mängija valida kõrvale pandud täringutest, millise silmadearvuga täringute summa lisatakse punktitabelisse. \n" +
                "   Näide: Mängija kõrvale pandud täringud on [6, 6, 2] ja mängija valib punktitabelisse lisatavaks silmade arvuga 6 täringud, \n" +
                "          siis punkti tabelisse lisatakse reale KUUED punktisummaks 12, kuna kõrvale pandud täringute hulgas on 2 täringut, mille silmade arv on 6 \n" +
                "          ehk 2 * 6 = 12. \n" +
                "7. Punktitabelisse ühele reale saab lisada punkte mängu jooksul vaid ühe korra. \n" +
                "   Näide: Kui mängija punktitabelis real KUUED on juba tulemus kirjas, siis sellele reale enam uut tulemust lisada ei saa. \n" +
                "8. Mängu võidab mängija, kelle lõppskoor kõikide tabeli punktide summana on kõige suurem. ");

        String mängijateArv = JOptionPane.showInputDialog(null, "Sisesta mängijate arv (1-4)", "Mängulaud",
                JOptionPane.QUESTION_MESSAGE);
        List<Mängija> mängijad = new ArrayList<>();
        int mängijaid = Integer.parseInt(mängijateArv);
        for (int i = 0; i < mängijaid; i++) {
            int jrkNumber = i + 1;
            String mängijaNimi = JOptionPane.showInputDialog(null, "Sisesta " + jrkNumber + ". mängija nimi: ", "Mängulaud",
                    JOptionPane.QUESTION_MESSAGE);
            mängijad.add(new Mängija(mängijaNimi));
        }
        Laud laud = new Laud();

        // Mängu põhikäik.
        for (int käik = 0; käik < 6; käik ++) {
            for (Mängija mängija : mängijad) {
                laud.veereta(mängija);
                laud.kuva(mängija);
                laud.vali(mängija);
                if (laud.kasOnVeelVeeretada(mängija)) {
                    laud.veereta(mängija);
                    laud.kuva(mängija);
                    laud.vali(mängija);
                    if (laud.kasOnVeelVeeretada(mängija)) {
                        laud.veereta(mängija);
                        laud.kuva(mängija);
                        laud.vali(mängija);
                    } else {
                        System.out.println("Oled kõik täringud kõrvale pannud!");
                    }
                }
                else {
                    System.out.println("Oled kõik täringud kõrvale pannud!");
                }
                laud.käiguLõpp(mängija);
                System.out.println();
                TimeUnit.SECONDS.sleep(1);
            }
        }
        laud.mänguLõpp(mängijad);
    }
}
