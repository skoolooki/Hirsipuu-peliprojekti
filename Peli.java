import java.util.Scanner;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Peli {
    
    public static void main(String [] args) {
        Scanner lukija = new Scanner(System.in, "UTF-8");

        // Tehdään sanoille lista
        // Arvatuille kirjaimille lista
        ArrayList<String> sanat = new ArrayList<>();
        ArrayList<Character> arvatutKirjaimet = new ArrayList<>();

        // luetaan tiedoston sanat läpi ja lisätään ne yksitellen listalle
        tiedostonLuku(sanat);

        // haetaan listalta random sana jota lähdetään arvaamaan
        Random rand = new Random();
        String sana = sanat.get(rand.nextInt(sanat.size()));

        System.out.println(sana);
        System.out.println();

        // Peli alkaa
        aloitusTeksti();

        // Tulostetaan sanan kirjainten määrän verran viivoja, jotta pelaaja näkee kirjainten määrän
        arvattavaSanaAlkuunViivoina(sana);
        
        System.out.println();
        System.out.println();

        int oikeatLaskuri = 0;
        int väärätLaskuri = 0;

        while (true) {
           
            //Kysytään pelaajalta kirjainta ja lisätään kirjain listalle
            System.out.println("Anna kirjain: ");
            String kirjain = lukija.nextLine();
            arvatutKirjaimet.add(kirjain.charAt(0));

            // Jos arvattu kirjain kuuluu sanaan, laitetaan se oikealla paikalleen ja jos taas ei kuulu tulostetaan alaviiva ja lisätään vääriin arvauksiin yksi piste lisää
            for (int i = 0; i < sana.length(); i++) {
                if (arvatutKirjaimet.contains(sana.charAt(i))) {
                    System.out.print(sana.charAt(i));
                    oikeatLaskuri++;
                } else {
                    System.out.print("_");
                }
            }

            // Tutkitaan arvaukset
            System.out.println();
            if (oikeatLaskuri == sana.length()) {
                System.out.println("Voitit! Arvasit sanan oikein.");
                System.out.println("Peli päättyi.");
                break;
            }
            if (sana.contains(kirjain)) {
                System.out.println("Yritä arvata koko sana:");
                String arvaus = lukija.nextLine();

                if (arvaus.equals(sana)) {
                    System.out.println("Voitit, arvasit koko sanan oikein!");
                    System.out.println("Peli päättyi.");
                    break;
                } else {
                    System.out.println("Väärin!");
                    System.out.println();
                }
            } else if (!sana.contains(kirjain)) {
                väärätLaskuri++;
            }
            
            // Tekee hirsipuuta väärien arvausten perusteella
            if (väärätLaskuri == 1) {
                Hirsipuu.piirraHirsipuu(1);
            }
            if (väärätLaskuri == 2) {
                Hirsipuu.piirraHirsipuu(2);
            }
            if (väärätLaskuri == 3) {
                Hirsipuu.piirraHirsipuu(3);
            }
            if (väärätLaskuri == 4) {
                Hirsipuu.piirraHirsipuu(4);
            }
            if (väärätLaskuri == 5) {
                Hirsipuu.piirraHirsipuu(5);
            }
            if (väärätLaskuri == 6) {
                Hirsipuu.piirraHirsipuu(6);
            }
            if (väärätLaskuri == 7) {
                Hirsipuu.piirraHirsipuu(7);
                System.out.println("Hävisit! Peli päättyi.");
                System.out.println("Oikea sana oli: " + sana);
                break;
            }
        }
    }

    private static void tiedostonLuku(ArrayList<String> sanat) {
        try (Scanner tiedostonLukija = new Scanner(Paths.get("sanalista.txt"), "UTF-8")) {
            while (tiedostonLukija.hasNextLine()) {
                sanat.add(tiedostonLukija.nextLine());

            } 
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }
    }

    private static void aloitusTeksti() {
        System.out.println("Tervetuloa pelaamaan hirsipuuta!");
        System.out.println();
        System.out.println();
    }

    private static void arvattavaSanaAlkuunViivoina(String sana) {
        for (int i = 0; i < sana.length(); i++) {
            System.out.print("_");
        }
    }
}
