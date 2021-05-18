package KDT5;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
/**********************************************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr /5b/
 * Teema:
 *       Programm mis võtab sisendiks teksi ja väljastab tekstis kõige tihedamini kasutusel olevad sõnad
 *
 *
 * Autor: Kristjan Pühvel
 *
 * Ajapuuduse tõttu jäi mul väljastuse formeerimine ebakorrektne.
 *
 ***********************************************************************************************************/


public class sagedasedsonad {
    public static void main(String[] args) throws IOException {
        java.io.File fail1 = new java.io.File("KuldninagaMees_est.txt");
        java.io.File fail2 = new java.io.File("Korboja_sisu_puhastekst.txt");
        ArrayList<Sona> sonelist = new ArrayList<>();
        boolean arvestauppercase = false; //Kas suure algustähega sõnu arvestatakse või ei


        BufferedReader lugeja = new BufferedReader(new InputStreamReader(new FileInputStream(fail1), "windows-1252"));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = lugeja.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }
        String uhestringina = content.toString();
        uhestringina = uhestringina.replaceAll("[-+.^:,\"»;!«?1234567890]", ""); //Tekstis korjatakse kõik sümbolid välja
        uhestringina = uhestringina.replace("\r", "").replace("\n", " ");//Reavahetuse korjatakse välja
        uhestringina = uhestringina.strip();
        String[] linelist = uhestringina.split(" ");
        for (int i = 0; i < linelist.length; i++) {
            if (linelist[i].length() < 5) {
                linelist[i] = " ";
            }
            if (!arvestauppercase) {
                char[] a = linelist[i].toCharArray();
                if (Character.isUpperCase(a[0])) {
                    linelist[i] = " ";
                }
            }
        }
        LinkedHashSet<String> unikaalsed = new LinkedHashSet<>(Arrays.asList(linelist)); //Hashset korjab välja kõik unikaalsed elemendid
        ArrayList<String> unikaalsedsoned = new ArrayList<>(unikaalsed);
        unikaalsedsoned.remove(" ");
        for (int i = 0; i < unikaalsedsoned.size(); i++) {
            sonelist.add(new Sona(unikaalsedsoned.get(i), 0));
        }
        for (int i = 0; i < linelist.length; i++) {
            for (int j = 0; j < sonelist.size(); j++) {
                if (linelist[i].equals(sonelist.get(j).getSone())) {
                    sonelist.get(j).setArv(sonelist.get(j).getArv() + 1);
                }

            }
        }
        sonelist.sort(Comparator.comparing(Sona::getArv));
        Collections.reverse(sonelist);
        System.out.println("Kodutöö nr 4a."+" ".repeat(49)+"Programmi väljund");
        System.out.println("=".repeat(80)+":");
        System.out.println();
        System.out.println("Sagedasemad 5-tähelised sõnad tekstifailis "+fail1 +" / "+fail2);
        System.out.println();
        for (int i = 0; i <20; i++) {
            System.out.println(sonelist.get(i).toString());

        }
        System.out.println("=".repeat(80)+".");
        System.out.println("Kristjan Pühvel"+" ".repeat(42)+new java.sql.Timestamp(System.currentTimeMillis()));
    }


}

