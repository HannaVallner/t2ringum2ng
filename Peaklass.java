import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Peaklass {

    // Mängija saab endale nime valida
    // Mängureeglid väljastada
    // Võimaldame valida mängijate arvu

    public static void main(String[] args) throws Exception {
        System.out.println("Mängureeglid: ");
        System.out.println();
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
        for (int käik = 0; käik < 6; käik ++) {
            for (Mängija mängija : mängijad) {
                laud.veereta(mängija);
                laud.kuva(mängija);
                laud.vali(mängija);
                laud.veereta(mängija);
                laud.kuva(mängija);
                laud.vali(mängija);
                laud.veereta(mängija);
                laud.kuva(mängija);
                laud.vali(mängija);
                if (mängija == mängijad.get(mängijad.size() - 1) && laud.kasKäigudOtsad(mängija)) {
                    laud.mänguLõpp(mängijad);
                    break;
                }
                else {
                laud.veereta(mängija);
                System.out.println();
                TimeUnit.SECONDS.sleep(1);
                }
            }
        }
    }

}
