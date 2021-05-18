package KDT2;

public class kingavalik { //Peaklass


    /**********************************************************************************************************
     * Programmeerimisharjutused. LTAT.03.007
     * 2019/2020 kevadsemester
     *
     * Kodutöö. Ülesanne nr /2a/
     * Teema:
     *            Erak lahkub kodust visates kulli ja kirja, et millisest uksest minna. Majal on kaks ust
     *            ning nende kahe ukse taga on kolm paari kingi. Iga kord kui erak tagasi tuleb, viskab
     *            ta uuesti kulli ja kirja, et näha millisest uksest sisse minna jättes selle sama
     *            ukse taha need kingad millega ta väljus. Kui välja minnes ukse taga kingad puuduvad,
     *            siis erak väljab majast paljajalu.
     *
     *            Ülesandeks on välja uurida mitu korda erak väljub majast paljajalu ning protsentuaalselt
     *            välja arvutada see kõikidest kordadest.
     *
     * Autor: Kristjan Pühvel
     *
     ***********************************************************************************************************/

    /**
     * Meetod mille abil juhuslikult otsustatakse kumma ukse erak valib.
     * Rakendamine: Juhuslikult määratakse kumma ukse erak valib.
     * @return Tagastab täisarvud 1 või 0.
     */
    public static int valiuks() {
        return (int) Math.round(Math.random());
    }



    public static void main(String[] args) { //Peameetod
        int esimeseuksekingad = 3; //Muutuja mis tähistab kingi esimese ukse juures.
        int teiseuksekingad = 3;//Muutuja mis tähistab kingi teise ukse juures.
        int samm = 100; //Muutuja mis näitab ära millise sammuga tulemusi väljastatakse.
        int valjumisikokku = 2000; // Muutuja mis tähistab mitu korda erak kokku väljub.
        int paljajalu = 0; //Muutuja mis tähistab eraku paljajalu väljutud kordi.
        System.out.format("%1s%87s", "Kodutöö nr 2a.", "Programmi väljund\n"); //Väljundi vormistus
        System.out.println("=".repeat(100) + ":");//Väljundi vormistus
        System.out.format("%1s%30s%30s", "Väljumisi kokku", "Paljajalu väljumisi", "Paljajalu väljumiste % \n");
        //Väljundi vormistus

        for (int i = 0; i <= valjumisikokku; i++) {/*For tsükkel millega loetakse üles ja mis
        peatub kui lugeja on väiksem võrdne kokku väljumiste muutujaga */

            //Tõeväärtusmuutuja mis aitab määrata kas erak lahkus paljajalu. Alguses eeldame, et see on väär
            boolean lakspaljajalu = false;
            // Muutuja kuhu talletatakse kas meetodi 'valiuks' abilvaliti esimene või teine uks.
            int valik = valiuks();


            if (valik == 0) {
                //Kui esimese ukse juures kingi ei ole siis erak lahkub paljajalu.
                if (esimeseuksekingad == 0) {
                    paljajalu += 1;
                    lakspaljajalu = true;
                //Kui valitakse esimene uks siis võetakse esimese ukse juurest üks kingapaar vähemaks.
                } else {
                    esimeseuksekingad -= 1;


                }
            }//Teise ukse valiku puhul toimitakse täpselt samamoodi.
            else {
                if (teiseuksekingad == 0) {
                    paljajalu += 1;

                    lakspaljajalu = true;
                } else {
                    teiseuksekingad -= 1;

                }
            }
            /*Siin kontrollitakse kas erak lahkus majast paljajalu või ei. Kui jah siis ta ei too ühtegi kingapaari
            ühegi ukse juurde tagasi, seega pole oluline milliest uksest ta siseneb.
             */

            if (!lakspaljajalu) {
                valik = valiuks();
                if (valik == 0) {
                    esimeseuksekingad += 1; //Kui tuleb esimesest uksest tagai siis toodi esimese ukse juurde kingapaar.


                } else {
                    teiseuksekingad += 1;//Kui tuleb teisest uksest tagai siis toodi teise ukse juurde kingapaar.

                }
            }
            //If tingimus väljastab jääki kontrollides iga kord tulemused kui samm on saavutatud.
            if (i != 0 && i % samm == 0) {
                double protsent = ((double) paljajalu / (double) i) * 100;
                System.out.format("%6d%30d%30s", i, paljajalu, String.format("%.2f", protsent) + "%\n"); //vormistus.



            }
        }
        System.out.println("\n" + "=".repeat(100) + ".");
        System.out.format("%1s%85s", "Kristjan Pühvel", new java.sql.Timestamp(System.currentTimeMillis()));



    }
}
