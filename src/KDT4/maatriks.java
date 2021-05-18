package KDT4;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**********************************************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr /4a/
 * Teema:
 *       Programm mis võtab 2018 ja 2019 aasta vältel E-ilmajaama temperatuuri andmeid ja väljastab need
 *       maatriksis, tuues välja keskmise temperatuuri, suurima temperatuuri, vähima temperatuuri ja suurima
 *       ja vähima temperatuuri vahe aasta vältel.
 *
 *
 * Autor: Kristjan Pühvel
 *
 ***********************************************************************************************************/



public class maatriks {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Double> temperatuurid = new ArrayList<>();
        ArrayList<Double> keskmised = new ArrayList<>();
        double keskmistekeskmine = 0; //Muutuja kuu keskmiste keskmise arvutamiseks.
        String vanakp = null; //Muutuja kuhu salvestatakse eelmises reas loetud kuu arv.
        int iteraator = 0;
        String[] kuud = {"jaanuar", "veebruar", "märts", "aprill", "mai", "juuni", "juuli", "august", "september", "oktoober", "november", "detsember"};
        String[][] maatriks = new String[12][5]; //väljastusmaatriks
        java.io.File fail1 = new java.io.File("temperatuurid2018.txt");
        java.io.File fail2 = new java.io.File("temperatuurid2019.txt");
        DecimalFormat df = new DecimalFormat("0.000");//Ujukomaarvude formeerimine
        System.out.println("Kodutöö nr 4a."+" ".repeat(49)+"Programmi väljund");
        System.out.println("=".repeat(80)+":");
        System.out.printf("%60s","AASTA 2018 TEMPERATUURIDE TABEL KUUDE LÕIKES"+"\n");
        System.out.printf("%63s","E-ilmajaama (Tartus füüsikahoone katusel) andmetel"+"\n".repeat(2));
        System.out.printf("%15s%14s%15s%15s%15s\n","","keskmine","suurim","vähim","vahe");
        try (java.util.Scanner sisu = new java.util.Scanner(fail1, "UTF-8")) {
            while (sisu.hasNextLine()) {
                String andmed = sisu.nextLine();
                System.out.println(andmed);
                if (andmed.startsWith("20")) { //Rida loetakse ainult kui see algab "20"-ga
                    String[] osad = andmed.split(" ");//Rea eraldamine massiiviks
                    String[] kposad = osad[0].split("-"); // Rea kuupäeva osa eraldamine massiiviks
                    if (vanakp != null) { //Konditsioon millega kinni püüda hetk kus kahte erinevat rida ei ole sisse loetud
                        if (vanakp.equals(kposad[1]) && sisu.hasNextLine()) { //Kui kuupäevade kuu arvud klapivad
                            temperatuurid.add(Double.parseDouble(osad[2]));//Lisatakse temperatuuri andmed järjendisse
                        } else {//Kui kuupäevade kuu arvud enam ei klapi siis on ühe terve kuu andmed sisse loetud
                            if (!sisu.hasNextLine()) {//Konditsioon millega kinni püüda tekstifaili kõige viimane rida
                                temperatuurid.add(Double.parseDouble(osad[2]));
                            }
                            vanakp = kposad[1];/*Praeguse kuu arvu väärtus loetakse muutuja vanakp alla, et seda järgmise
                            kuu arvuga võrrelda tsükli jätkumisel
                            */
                            double vahim = vahim(temperatuurid);
                            double suurim = suurim(temperatuurid);
                            double keskmine = keskmine(temperatuurid);
                            //Maatriksisse andmete sisse kandmine
                            maatriks[iteraator][0] = kuud[iteraator];
                            maatriks[iteraator][1] = String.valueOf(df.format(keskmine));
                            maatriks[iteraator][2] = String.valueOf(df.format(vahim));
                            maatriks[iteraator][3] = String.valueOf(df.format(suurim));
                            maatriks[iteraator][4] = String.valueOf(df.format(suurim - vahim));
                            keskmised.add(keskmine);
                            keskmistekeskmine += keskmine;
                            iteraator++;
                            temperatuurid.clear();//Kui ühe kuu andmed on tervikuna töödeldud siis järjend puhastatakse
                            /*
                            Lisatakse see väärtus ka listi mis kuupäevade kuu arvu muutumisel esialgse if konditsiooni
                            alla ei lähe aga kuulub siiski järgmise kuu andmete alla
                             */
                            temperatuurid.add(Double.parseDouble(osad[2]));

                        }
                    } else {//Esimese rea lugemise else konditsioon
                        temperatuurid.add(Double.parseDouble(osad[2]));
                        vanakp = kposad[1];
                    }
                }
            }
            valjasta_tabel(maatriks);
        }
        System.out.println("-".repeat(75)+"\n");
        System.out.println("Keskmiste keskmine: " + df.format(keskmistekeskmine / kuud.length));
        Double[] vahed = new Double[kuud.length];
        for (int i = 0; i < kuud.length; i++) {
            vahed[i] = Math.abs(keskmised.get(i) - keskmistekeskmine/kuud.length);
            //Massiivi "vahed" abil leitakse kõige sarnasema keskmisega kuu keskmiste keskmisele.
        }
        //Võetakse massiivi minimaalse elemendi indeks ja tolle abil väljastatakse millise kuuga on tegu
        System.out.println("Sellele lähima keskmisega kuu on "+kuud[Arrays.asList(vahed).indexOf(Collections.min(Arrays.asList(vahed)))]+"\n");
        System.out.println("=".repeat(80)+".");
        System.out.println("Kristjan Pühvel"+" ".repeat(42)+new java.sql.Timestamp(System.currentTimeMillis()));
    }
    /**
     * Meetod mille abil määratakse aasta temperatuuride suurim kuu vältel
     * Rakendamine: Meetodile antakse ette järjend kuu jooksul mõõdetud temperatuuridest
     * millest ta tagastab suurima.
     * @return  tagastab suurima elemendi järjendis
     */
        public static double suurim(ArrayList<Double> jarjend){
        return Collections.max(jarjend);
        }
    /**
     * Meetod mille abil määratakse aasta temperatuuride väikseim kuu vältel
     * Rakendamine: Meetodile antakse ette järjend kuu jooksul mõõdetud temperatuuridest
     * millest ta tagastab väikseima.
     * @return  tagastab väikseima elemendi järjendis
     */
        public static double vahim(ArrayList<Double> jarjend){
        return Collections.min(jarjend);
        }
    /**
     * Meetod mille abil määratakse aasta temperatuuride aritmeetiline keskmine
     * Rakendamine: Meetodile antakse ette järjend kuu jooksul mõõdetud temperatuuridest
     * millest tagastab nende aritmeetilise keskmise
     * @return  tagastab järjendi aritmeetilise keskmise
     */
        public static double keskmine(ArrayList<Double> jarjend){
        double summa=0;
            for (int i = 0; i < jarjend.size(); i++) {
                summa+=jarjend.get(i);
            }
            return summa/jarjend.size();
        }
    /**
     * Meetod mille abil väljastatakse moodustatud maatriks. Meetod on võetud loengu abifailidest ja väljastust on muudetud.
     * Rakendamine: Meetodile antakse ette maatriks ja see väljastab selle
     * @return väljastab etteantud maatriksi
     */
    public static void valjasta_tabel(String[][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++)
                System.out.printf("%15s",a[i][j]+" ");
            System.out.println();
        }
        System.out.println();
    }
}
