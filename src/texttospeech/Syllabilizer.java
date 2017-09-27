/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Kadek
 * This is a class for obtained the syllable of a word.
 */
public class Syllabilizer {
    // Syllable patterns.
    public static final ArrayList<String> SYLLABLES = new ArrayList<String>(Arrays.asList(
                                                    "CVC-VC-CV-CV-CV",	//	NOR-AS-WA-LI-ZA 
                                                    "CCV-CVC-CV-CV-VC",	//	SYARULNAZIAH                                         
                                                    "CV-CCV-CCCVC-CVC", //INFRASTRUKTUR	/	IN-FRA-STRUK-TUR
                                                    "CCVC-CCV-CVC-CVC",	//	SHIB-GHA-TUL-LA                                        
                                                    "CVCC-CV-CV-CVC",   //MENGGUNAKAN	/	MENG-GU-NA-KAN
                                                    "CVCC-CV-CV-VC",    //PENGGUNAAN	/	PENG-GU-NA-AN
                                                    "CCV-CVC-CV-CVC",	//	SYA-RIF-FA-NOR 
                                                    "CCV-CV-CVC-CVC",	// SYA-RI-FUD-DIN
                                                    "CV-CV-CVC-CVC",    //SAHIBUDDIN	/	SA-HI-BUD-DIN
                                                    "CVC-CVC-CV-CVC",	//	NOR-FAZ-LI-ZAH
                                                    "CVC-CVC-CV-CV",	//	FAH-RUL-RA-ZI 
                                                    "CVC-VCV-CV-CV",	//	NUR-IDA-WA-TI 
                                                    "CVC-CV-CV-CVC",	//	NOR-HI-DA-YAH 
                                                    "CVC-CV-CV-CV",	//	NORZIANI	/	NOR-ZI-HA-NI 22
                                                    "CVC-VC-CV-CV",	//	NORASMARA
                                                    "CVC-CV-CV-VC",     //PERKATAAN	/	PER-KA-TA-AN
                                                    "CCV-CV-CV-CV",     //DWIBAHASA	/	DWI-BA-HA-SA
                                                    "CVC-V-CCV-CVC",	//	NORASHIKIN 
                                                    "VC-CVC-CV-CVC",	//	AB-DUL-LA-SIM 
                                                    "CV-CVC-CV-VC",     //PEREMPUAN	/	PE-REM-PU-AN
                                                    "CV-CV-CV-CVC",     //SEMALAMAN	/	SE-MA-LA-MAN
                                                    "CV-CVV-CV-CV",     //BAGAIMANA	/	BA-GAI-MA-NA
                                                    "CVC-CV-CCV-V",     //MEMPUNYAI	/	MEM-PU-NYA-I
                                                    "CV-CV-CV-CV",      //MATAHARI	/	MA-TA-HA-RI
                                                    "CV-CV-CV-VC",      //KEMUDIAN	/	KE-MU-DI-AN
                                                    "VC-CV-CV-CV",      //APLIKASI	/	AP-LI-KA-SI
                                                    "CV-CV-V-CVC",      //KENAIKAN	/	KE-NA-I-KAN
                                                    "CV-CV-CC-CV",      //SEHINGGA	/	SE-HI-NG-GA
                                                    "CV-CV-V-CV",       //SESUATU	/	SE-SU-A-TU
                                                    "V-CVC-CV-CVC",     //	A-MIR-RU-DIN
                                                    "V-CV-V-CV",	//	U-ZA-I-NI
                                                    "V-CV-CV-CV",       //APABILA	/	A-PA-BI-LA
                                                    "CCVC-CV-CVC",      //PRAKTIKAL	/	PRAK-TI-KAL
                                                    "VCVV-CVV-CVC",	//	ABOO-BAI-DER 
                                                    "VCVV-CVV-CVC",	//	ABOO-BAI-DER 
                                                    "CCVC-CV-CV",	//	KHIL-WA-NI 
                                                    "CCVC-CV-CV",	//	KHAM-BA-RI 
                                                    "CVC-CV-CCVV",	//	ZOL-KA-FLEE 
                                                    "VCV-CVS-VCV",	//	EMA-LI-ANA 
                                                    "CVC-CV-CCV",	//	ZUL-KI-FLI
                                                    "CVV-CV-CVC",	//	ZAI-NU-DIN
                                                    "CCV-CV-CCV",	//	PRA-MU-DYA
                                                    "CVV-CV-VC",	//	FAU-ZI-AH
                                                    "VCC-CV-CV",	//	ASY-RA-NI 
                                                    "CVV-CV-CV",	//	FAUZURA		/	FAU-ZU-RA 22
                                                    "CVC-CV-CV",	//	TAR-MI-ZI
                                                    "CVC-CV-V-CV",      //SENTIASA	/	SEN-TI-A-SA
                                                    "CCV-CVC-CVC",      //PROFESSOR	/	PRO-FES-SOR
                                                    "CV-CVC-CVC",       //KEMASKAN	/	KE-MAS-KAN
                                                    "CVC-CV-CVC",       //MAKRIFAT	/	MAK-RI-FAT
                                                    "VC-CV-CVCC",       //INSURANS	/	IN-SU-RANS
                                                    "CCCV-CV-CV",       //STRATEGI	/	STRA-TE-GI
                                                    "CV-CV-CVCC",       //SEKARANG	/	SE-KA-RANG
                                                    "CCV-CV-CVC",       //PROFESOR	/	PRO-FE-SOR
                                                    "VC-VC-CVC",        //ARIFFIN	/	AR-IF-FIN
                                                    "CV-CCV-CV",        //MENGAPA	/	ME-NGA-PA
                                                    "CVC-CV-VC",        //MEMBUAT	/	MEM-BU-AT
                                                    "CV-CV-CVC",        //CABARAN	/	CA-BA-RAN
                                                    "CV-CV-CVV",        //SEBAGAI	/	SE-BA-GAI
                                                    "CV-CVC-CV",        //SEPERTI	/	SE-PER-TI
                                                    "CV-V-CCVC",        //KUINGIN	/	KU-I-NGIN
                                                    "CV-V-CVCC",        //SEORANG	/	SE-O-RANG
                                                    "CCV-CV-CV",        //PROFESI	/	PRO-FE-SI
                                                    "CV-CVV-CVV",	//	YU-ZAI-MIE 
                                                    "VC-CVC-CVC",	//	AB-DOL-LAH 
                                                    "VC-CVV-CVC",	//	AZ-LIA-NOR
                                                    "VC-CVC-CVC",	//	AB-DUL-LAH
                                                    "VC-CVC-CC",	//	AFFANDY		/	AF-FAN-DY 22
                                                    "CV-CVV-CV",	//	ZA-HEE-RA
                                                    "VC-CVC-CV",	//	AS-LIN-DA
                                                    "VC-CV-CVC",	// 	IBRAHIM
                                                    "CV-CV-VV",         //BELIAU	/	BE-LI-AU
                                                    "CV-CV-CV",         //LELAKI	/	LE-LA-KI
                                                    "CV-CV-VC",         //KELUAR	/	KE-LU-AR
                                                    "V-CV-CVC",         //ADALAH	/	A-DA-LAH
                                                    "CV-CV-V",          //SEMUA		/	SE-MU-A
                                                    "CV-V-CV",          //SUARA		/	SU-A-RA
                                                    "V-CVC-CV",	//	A-NAN-TA 
                                                    "V-CV-CV",          //UTAMA		/	U-TA-MA
                                                    "V-CV-VC",          //AKAID		/	A-KA-ID
                                                    "CCCVC-CVC",        //STRUKTUR	/	STRUK-TUR
                                                    "CCVC-CVC",         //PRAKTIK	/	PRAK-TIK
                                                    "CVC-CVCC",         //TENTANG       /	TEN-TANG
                                                    "CVCC-CVC",         //MUNGKIN	/	MUNG-KIN
                                                    "CCV-CVC",          //SYAKIB	/	SYA-KIB
                                                    "CCCV-CV",          //STRATA	/	STRA-TA
                                                    "CV-CVCC",          //KOSONG	/	KO-SONG
                                                    //"CV-CCVC",          //SANGAT	/	SA-NGAT
                                                    "CVC-CVC",          //DOKTER	/	DOK-TER
                                                    "VCC-CVC",		//	OTHMAN	/	OTH-MAN
                                                    "CCV-CVC",		//	DRAMAN  
                                                    "CVV-CVC",		//	FAI-SAL
                                                    "CVC-CVC",  	// 	KOS-NIN
                                                    "CCV-CVV",		//	SYU-RIA
                                                    "CCVC-CV",          //SYAHRI	/	SYAH-RI
                                                    "CVC-CV",           //HANTU		/	HAN-TU
                                                    "CVC-CC",		//	HEL-MY  /2208
                                                    "CVC-CC",		//	RAM-LY  /2208
                                                    "CV-CVC",           //MALAM		/	MA-LAM
                                                    "VC-CVC",           //ASPEK		/	AS-PEK
                                                    "VC-CCV",           //INFRA		/	IN-FRA
                                                    "V-CVCC",           //AKANG		/	A-KANG
                                                    "CV-CCV",           //HANYA		/	HA-NYA
                                                    "CV-CV",            //BELI		/	BE-LI
                                                    "CV-VC",            //BUAT		/	BU-AT
                                                    "VC-CV",            //ANDA		/	AN-DA
                                                    "VC-CVC", 		//	ASMAI   /2208
                                                    "VC-CV",		//	AZRI    /2208
                                                    "V-CVC",            //IKAN		/	I-KAN
                                                    "V-CVV",            //ATAU		/	A-TAU
                                                    "V-CV",             //INI		/	I-NI
                                                    "CCCVC",            //STRUK
                                                    "CCCV",             //STRA
                                                    "CCVC",             //PRAK
                                                    "CVCC",             //RANS
                                                    "CVC",              //CIK
                                                    "CCV",              //DWI
                                                    "CVV",              //DIA
                                                    "CV",               //DI
                                                    "VC",               //AN
                                                    "CC",               //NG
                                                    "VV",               //IA
                                                    "V",                //A
                                                    "C"                 //N
    ));
    // Remove "-" from the syllable patterns.
    public static final ArrayList<String> WORDPATTERNS = removeStrip(SYLLABLES);
    
    
    public static ArrayList<String> removeStrip(ArrayList<String> list) {
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i).replaceAll("-", ""));
        }
        
        return temp;
    }
    
    public static ArrayList<String> removeStrip(String syllable) {
        return new ArrayList<String>(Arrays.asList(syllable.split("-")));
    }
    
    /**
     * Convert real word into Consonant-Vowel format.
     */
    public static String getWordPattern(String word) {
        //word.replace("x","s").replace("ny", "ɲ").replace("ng", "ɳ").replace("kh", "x").replace("sy", "š").replace("gh", "ɣ").replace("th", "Ө");
        return word.replaceAll("(?![aiueo])[a-z]", "C").replaceAll("[aiueo]", "V");
    }
    
    /**
     * searchSyllable is a method to get syllable index.
     * If found, split the syllable and convert them to list.
     */
    public static ArrayList<String> searchSyllable(String word) {
        for (int i = 0; i < WORDPATTERNS.size(); i++) {
            if (WORDPATTERNS.get(i).equals(word))
                return new ArrayList<String>(Arrays.asList(SYLLABLES.get(i).split("-")));
        }
        
        return new ArrayList<String>();
    }
    
    /**
     * getSubSyllable is a method for search the longest match syllable pattern of a word.
     * This method is used when syllable patterns didn't contain pattern of the word.
     */
    public static ArrayList<String> getSubSyllable(String word) {
        for (int i = 0; i < WORDPATTERNS.size(); i++) {
            if (word.startsWith(WORDPATTERNS.get(i)))
                return new ArrayList<String>(Arrays.asList(SYLLABLES.get(i).split("-")));
        }
        
        return new ArrayList<String>();
    }

    public static int getSyllableLength(ArrayList<String> syllable) {
        return String.join("", syllable).length();
    }
    
    /**
     * Main method of this class.
     */
    public static ArrayList<String> getSyllable(String word) {
        ArrayList<String> syllable = new ArrayList<String>();
        // Get Consonant-Vowel format.
        String pattern = getWordPattern(word), currentPattern = pattern;
        System.out.println(pattern);
        
        // If pattern available.
        if (WORDPATTERNS.contains(pattern)) {
            return searchSyllable(pattern);
        } else {
            // While pattern string is not empty.
            while (!currentPattern.equals("")) {
                // Get the sub-syllable.
                ArrayList<String> subSyllable = getSubSyllable(currentPattern);
                //System.out.println(subSyllable);
                int syllableLength = getSyllableLength(subSyllable);
                // Update the current pattern string.
                currentPattern = currentPattern.substring(syllableLength);
                // Add sub-syllable to the list.
                syllable.addAll(subSyllable);
            }
        }
        
        return syllable;
    }
    
    /**
     * This is a method for convert syllable into word parts.
     * Example: CV-CVC --> Ka-dek
     */
    public static ArrayList<String> syllableToWords(ArrayList<String> syllable, String word) {
        ArrayList<String> newSyl = new ArrayList<String>();
        String currentWord = word;
        
        // For every sub-syllable.
        for (String syl : syllable) {
            // If sub-syllable length is 1, then add "_".
            // Example: "a" --> "a_"
            if (syl.length() == 1)
                newSyl.add(currentWord.substring(0, syl.length()) + "_");
            // Get substring of current word.
            else
                newSyl.add(currentWord.substring(0, syl.length()));
            // Update the current word.
            currentWord = currentWord.substring(syl.length());
        }
        
        return newSyl;
    }
}
