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
 * @author Kadek & Anisa
 * This is a class for obtained the syllable of a word.
 */
public class Syllabilizer {
    // Syllable patterns. // "CVC-CCVCC", //gembleng
    public static final ArrayList<String> SYLLABLES = new ArrayList<String>(Arrays.asList(
                                                    //this one are additional, should be sort actually but tak laraaat, cz so a lot
                                                    //"CVC-CCVCC", //gembleng
                                                    "VC-CV-V-CV", //arguero
                                                    "V-CV-CVC-CV-CCVC",	//aminurrashid
                                                    "CV-VC-CVC-CV-CV-V",	//ganagambegai
                                                    "VC-CV-CV-CVC"	,	//afzanizam
                                                    "CV-CV-CVC-CV-CVC",	//puvanasvaran
                                                    "CV-CV-CCVC-CCVC",	//balakrishnan
                                                    "CVC-CVC-CV-VC",	//zolkarnain
                                                    "CCV-V-CVC-CCVC",	//khairulddin
                                                    "CV-CV-CCV-CV-CVC",	//norisharizal
                                                    "V-CV-VC-CC",		//arianty
                                                    "CCV-V-CVC-CV-VC",	//khairunnisa
                                                    "CCV-CV-CV-CVC",	//syahibudil
                                                    "CVC-CVC-CVC-CVC",	//hasrulnizzam
                                                    "V-CV-CV-CV-CV-VC",	//amiranurain
                                                    "V-V-CVC",               //aizad
                                                    "CVVC-VC-CV-CV",  	//nooraznina
                                                    "V-CV-CVC-CV", 		//idamurni
                                                    "CVVC-CV-C-CV-CV",	//noorbaizura
                                                    "CCV-V-CVC-CVC",	//khairuddin
                                                    "CVC-VC-CVC-CV",	//norazlinda
                                                    "CCV-V-CV-CVC",		//shaarizan
                                                    "CCV-V",		//frie
                                                    "CCV-CV",		//khiar
                                                    "CVCC-CVC-CV-CCV",	//fakhrulradhi
                                                    "V-CVC-CVC-CVC",	//amirruddin
                                                    "CV-V-CC",		//baity
                                                    "CV-CV-CVC-CV",		//nurulizwa
                                                    "CVC-CV",		//mahdi
                                                    "CVVC-V-V-CVC-CVC",	//nooraidillah
                                                    "CV-VC",		//daus
                                                    "CVC-CCV-CV",		//hardjono
                                                    "CVC-CCV-V-CV",		//mardhiana
                                                    "CV-V-V-CVC",		//saealal
                                                    "CV-CV-CVC-CV-CVC",	//kamarulzaman
                                                    "V-CVC-CV-CV",		//irastika
                                                    "CV-V-CVC-CVC-CVC",	//hairulhisham
                                                    "CVC-CVC-CCV-V",	//norfadzlia
                                                    "CCVC-CV-CCVC",		//dzulhisham
                                                    "CV-V-CV-CV-CCV",	//zairulazha
                                                    "V-CV-CVC-CV-CVC",	//abedalrahim
                                                    "CVCC-CCVC",		//fahdzril
                                                    "CVC-CV-CVC-CV",	//normalisza
                                                    "CV-CVC-CV-V-V-CVC",	//nurulfaiezah
                                                    "V-V-CVC",		//aisah
                                                    "CV-CV-CVC-CV-CVC",	//kamarolzaman
                                                    "CCV-V-CV-CVC",		//shaidatul
                                                    "CCV-CV-CV-CVC",	//syafidatul
                                                    "CV-CVC-CC",		//mawardy
                                                    "CCVC-CVC-VCV",		//shamsuddin
                                                    "CV-CV-CVC-CV-CV",	//munirahwati
                                                    "CV-CV-V-CCV",		//suhaizri
                                                    "CV-CVC",		//karim
                                                    "V-V-CVC-CVC",		//ainuddin
                                                    "CVC-CVC-CCV-CV-CV",	//nurfadhliyana
                                                    "CV-CVC-CV-CV-V-VC",	//bazilyadaain
                                                    "V-CV-CV-V",		//azalia
                                                    "V-CV-CV-VC",		//adawiah
                                                    "VC-CV-CV-CV-CV",	//azlinawati
                                                    "CV-V-VC",		//muaat
                                                    "CVC-CV-CV-CV-V",	//zulkifilie
                                                    "CV-V-CC",		//laily
                                                    "CCV-CCV-V-V",		//khashiie
                                                    "CCV-CV-VC",		//shafuad
                                                    "V-V-CV-CV",		//auzani
                                                    "VCC-CVCC",		//ashraff
                                                    "CV-CV-CV-V",		//warusia
                                                    "CV-VC-CVC",		//moallaf
                                                    "VC-CV-CV-CCV",		//ezrizikri
                                                    "CV-CV-CV-CV-CVC",	//kamarazaman
                                                    "CVC-VC-CVC-CC",	//noreffendy
                                                    "CV-CVC-CC",		//hamiddy
                                                    "VVCV-CVC-CVC-CV",	//shahrulfadli
                                                    "V-CV-V-CV-CVC",	//edeerozey
                                                    "CVC-CCV-V-CV",		//saffreena
                                                    "CVC-CCV-V-VCV",	//norshuadah
                                                    "CVC-CV-CV-CV-CV",	//norlizawati
                                                    "CVC-VCV",		//zahrah
                                                    "VC-CV-CV-V-CC",	//arjunaidy
                                                    "CV-V-VC",		//faiez
                                                    "V-CV-CV-CV-CV-VC",	//alihanafiah
                                                    "CVC-V-V-CCVC",		//nuraishah
                                                    "CVC-CCV-CV-CV-CV",	//norshafarina
                                                    "CVC-CCV-VC",		//firdhaus
                                                    "CVC-CCV-V-CV",		//zulkhaini
                                                    "V-CV-CV-VC",		//asaroon
                                                    "VC-CVC-CVC-CV-VC",	//iqmallullail
                                                    "CV-CV-CVC-CV-VC",	//zahirulzaim
                                                    "V-CVC-CCVC",		//izamshah
                                                    "CCVCC",		//shriff
                                                    "CV-CV-CV-CVC", 	//kamarudin
                                                    "VC-CVC",		//abbId
                                                    "CVC-CV-V-CV-CV-CV-CV",	//fitrianasuhani
                                                    "CVC-CV-V-CV-V",	//fadliedie
                                                    "VC-CV-CVC-CV",		//ernihazra
                                                    "CV-CV",   		//qais
                                                    "CV-CV-V-CVC",		//azlianor
                                                    "CV-CV-CV-CV-CVC",	//kamaruzaman
                                                    "V-CV-V-CV",		//aziean
                                                    "CVCC-CVC-CV-VC",	//fakhrulnaim
                                                    "CV-CV-V-VC",		//naquiah
                                                    "V-V-CV-CVC-CV",	//aidayanti
                                                    "CCV-CV-CV-VC",		//shahaneen
                                                    "CVC-CV-CV-CV-CV",	//norhazilina
                                                    "CVC-CV-V-CV-CV",	//nurbaizura
                                                    "CV-V-CV-V",		//fauzee
                                                    "CVVC-CVC-CV-CV",	//noorhaslina
                                                    "V-CV-CVC-CCVC",	//adiyansyah
                                                    "CVC-CCV-CV-CVC",	//kanchimalay
                                                    "CVC-CVC-CCV",		//zulfadhli
                                                    "CCV-V-CVC-CV-CVC",	//khairulnizam
                                                    "VC-CV-CC",		//aswany
                                                    "CCV-CV-VC",		//shazeey
                                                    "CCV-CV-CV-CV-CVC",	//shaharuzaman
                                                    "V-V-CVC",		//aiman
                                                    "VC-CVCC",		//albert
                                                    "CCVC-CV-CCV",		//dzulkifli
                                                    "CVC-CVC-CV-VC",	//zulkalnain
                                                    "CVC-CV-CVC-CV-CV",	//lindaherdani
                                                    "VC-CCV-CVC",		//idzdihar
                                                    "V-V-CV-CV",		//aeliza
                                                    "CV-VC",		//hail
                                                    "CV-V-CVC-CV",		//saifulza
                                                    "V-V-CV-CV-CC",		//aidawaty
                                                    "CCV-VC",		//chuan
                                                    "CCVC-CV-CCV",		//dzulkipli
                                                    "CCV-VCC",		//cheong
                                                    "CVVC-VC-CV-CV",	//noorazlina
                                                    "CV-CV-CVC-CV-CVC",	//kamaruzzaman
                                                    "VC-CV-CV-CV-CV",	//adzilawati
                                                    "CV-CCV-CVC",		//insyirah
                                                    "VC-CV-CVC-CVC",	//alsayaydeh
                                                    "CVC-CVC-CVC-CV",	//norhaslinda
                                                    "CVC-CV-CV-CV-VC",	//subramaniam
                                                    "CVC-CV-CV-CCV",	//gannapathI
                                                    "CCV-V-CVC-CVC",	//khaeruddin
                                                    "CVC-CV-CV-CV-VC",	//subramonian
                                                    "V-V-V-CV-V",		//eiisaa
                                                    "CV-CVC-CVC-CV",	//nurussalwa
                                                    "CVC-CV-CV-CV-CV",	//masmuliyadi
                                                    "CCVC-CV-CV-V",		//syalwanie
                                                    "CCVC-CV-CV-CCVC",	//dharmalingam
                                                    "CVC-CVC-CVC",		//ruztamrIn
                                                    "CV-V-CV-CV-V",		//zainaria
                                                    "CVC-CV-CV-CV-V-CV",	//wardatulaina
                                                    "CV-V-V-CVC",		//faaizah
                                                    "CCV-CV-V",		//rhafaI
                                                    "CVVC-CV-CVC",		//noornahar
                                                    "VC-CV-CV-V",		//erdalia
                                                    "CCV-CVC",		//ghafar
                                                    "V-CV-CV-CVC-CV",	//ifayefunmi
                                                    "CVC-CVC-CV-VC",	//zulkarnain
                                                    "CVC-CCV",		//rosslI
                                                    "CVCC-CVC-CVC",		//fakhrullah
                                                    "V-CV-V-VCV",		//izaidin
                                                    "CVVC-CVC-CVC-CVC",	//noorhasyuddin
                                                    "CCV-CVC",		//draman
                                                    "CV-CV-V-CVC",		//rabeatul
                                                    "CCV-CV-VC",		//shazuan
                                                    "CCVC-CCVC",		//shamshul
                                                    "CCV-CCV",		//shukrI
                                                    "CVC-V-CCV-CV-VC",	//nurashikeen
                                                    "CVC-CV-CVC-CV-CVC",	//nursabillilah
                                                    "CVC-CV-CV-CVC-CV",	//norfaradilla
                                                    "CV-CVV",		//siang
                                                    "CCV-V-CV-VCV",		//khairudin
                                                    "CV-V-CCCV",		//soufhwI
                                                    "CVVC-V-CV-V-CV",	//nooraziana
                                                    "CCV-V-CV",		//khairI
                                                    "VC-CVC-CV",		//aswanzi
                                                    "CCV-VC",		//thien
                                                    "CVC-CV-CV-CVC-CV",	//norzuriyahni
                                                    "CV-CVC-CVC-CV-CVC",	//kamalrulzaman
                                                    "V-CV-CV-CV",		//amaniah
                                                    "CCV-V-CV-CV-CVC",	//shaifulizam
                                                    "V-V-CV-CVC",		//aidatul
                                                    "CVC-CCV-V-CV",		//massheila
                                                    "CCV-VC",		//chiew
                                                    "CVVC-CVC",		//noordin
                                                    // until here
                                                    //5
                                                    "CCVV-CVC-CV-CVV-CV",	// khai rul hu sai ni	1309
                                                    "CVVC-VC-V-CV-CVC",	//	noor-za-i-li-nah	1509         
                                                    "CVC-CV-CV-V-CVC",	// rat-na-sa-e-rah	1309
                                                    "CVC-VC-CV-CV-CV",	//	NOR-AS-WA-LI-ZA 
                                                    "CCV-CVC-CV-CV-VC",	//	SYARULNAZIAH                                         
                                                    "CCV-V-CVC-CV-CCV", //kha-i-run-ni-sha
                                                    "CV-CCV-CCCVC-CVC", //INFRASTRUKTUR	/	IN-FRA-STRUK-TUR
                                                    "V-CV-VC-CV-CVC",	//	a-nu-ar-ru-din		1509
                                                    //4
                                                    "CVVC-CV-CV-CV",	// noor ha ya ti	1309
                                                    "CVVC-CV-V-CVC",	//	noor-ha-i-zah
                                                    "CVVC-V-CV-CVC",    //	noor-a-zi-lah	13091509
                                                    "CCVC-CCV-CVC-CVC",	//	SHIB-GHA-TUL-LA                                        
                                                    "CVCC-CV-CV-CVC",   //MENGGUNAKAN	/	MENG-GU-NA-KAN
                                                    "CVCC-CV-CV-VC",    //PENGGUNAAN	/	PENG-GU-NA-AN
                                                    "CVC-CCVC-CV-CVC",    //nurshamsinar
                                                    "CCV-CV-CVC-CV",	//khu wa riz mi	1309
                                                    "CCV-CV-V-CV", //	shu-ha-i-mi	1309
                                                    "CVC-CVC-CV-CVC",	//	NOR-FAZ-LI-ZAH
                                                    "CVC-CVC-CV-CV",	//	FAH-RUL-RA-ZI 
                                                    "CVC-VCV-CV-CV",	//	NUR-IDA-WA-TI 
                                                    "CVC-CV-CV-V",	//	nor-su-ri-a		1509
                                                    "CVC-CV-V-CVC",	//nor-fa-e-zah	1309
                                                    "CCV-CV-V-CVC",	//	khu-za-i-pah		1509
                                                    "CCV-CVC-CV-CVC",	//	SYA-RIF-FA-NOR 
                                                    "CVC-CCV-CV-CV", //nur-sha-hi-ra
                                                    "CVC-CV-CVC-CVC",	//	BURHANUDDIN		/	BUR-HA-NUD-DIN	*NEW
                                                    "CCV-CV-CVC-CVC",	// SYA-RI-FUD-DIN
                                                    "CV-CV-CVC-CVC",    //SAHIBUDDIN	/	SA-HI-BUD-DIN
                                                    "CVC-CVC-CV-CVC",	//	NOR-FAZ-LI-ZAH
                                                    "CVC-CVC-CV-CV",	//	FAH-RUL-RA-ZI 
                                                    "CVC-V-CV-CV-CV",	//	NUR-I-DA-WA-TI 
                                                    "CVC-CV-CV-CVC",	//	NOR-HI-DA-YAH 
                                                    "CVC-CV-CV-CV",	//	NORZIHANI	/	NOR-ZI-HA-NI 22
                                                    "CVC-VC-CV-CV",	//	NORASMARA
                                                    "CVC-CV-CV-VC",     //PERKATAAN	/	PER-KA-TA-AN
                                                    "CCV-CV-CV-CV",     //DWIBAHASA	/	DWI-BA-HA-SA
                                                    "CVC-V-CCV-CVC",	//	NORASHIKIN 
                                                    "CCV-V-CV-CV",	//sha-a-ra-ni	1309
                                                    "VC-CVC-CV-CVC",	//	AB-DUL-LA-SIM 
                                                    "CV-CVC-CV-VC",     //PEREMPUAN	/	PE-REM-PU-AN
                                                    "CV-CV-CV-CVC",     //SEMALAMAN	/	SE-MA-LA-MAN
                                                    "CV-CVV-CV-CV",     //BAGAIMANA	/	BA-GAI-MA-NA
                                                    "VC-CV-CVC-CC",    //AR-DI-YAN-TY
                                                    "CVC-CV-CCV-V",     //MEMPUNYAI	/	MEM-PU-NYA-I
                                                    "CV-CV-CVC-CV-CV",  //munirahwati
                                                    "CV-CV-CV-CV",      //MATAHARI	/	MA-TA-HA-RI
                                                    "CV-CV-CV-VC",      //KEMUDIAN	/	KE-MU-DI-AN
                                                    "VC-CV-CV-CV",      //APLIKASI	/	AP-LI-KA-SI
                                                    "CV-CV-V-CVC",      //KENAIKAN	/	KE-NA-I-KAN
                                                    "CV-CVCC-CV",      //SEHINGGA	/	SE-HING-GA
                                                    "CV-CV-V-CV",       //SESUATU	/	SE-SU-A-TU
                                                    "CV-CV-CVC-CCVC",	//	SA-ZA-LIN-SYAH 
                                                    "CV-CV-CVC-CC",	//su ri yan ty	1309
                                                    "VC-CV-CV-C",	//	er-da-li-a		1509
                                                    "CV-CV-V-CC",	//	ma-ri-a-ty		1509
                                                    "CV-V-CVC-CVC",	//	za-i-far-han		1509
                                                    "V-CV-CV-CVC",	//a wa lu din	1309
                                                    "V-CVC-CV-CVC",     //	A-MIR-RU-DIN
                                                    "V-CV-V-CV",	//	U-ZA-I-NI
                                                    "V-CV-CV-CV",       //APABILA	/	A-PA-BI-LA
                                                    "CCVC-CV-CVC",      //PRAKTIKAL	/	PRAK-TI-KAL
                                                    "V-CVV-CV-V-CVC",	//	ABOO-BAI-DER 
                                                    
                                                    "CCVC-CV-CV",	//	KHIL-WA-NI  
                                                    "CVC-CV-CCV-V",	//	ZOL-KA-FLE-E 
                                                    "V-CV-CV-V-CV",	//	E-MA-LI-A-NA 
                                                    "CVC-CV-CCV",	//	ZUL-KI-FLI
                                                    "CV-V-CV-CVC",	//	ZAI-NU-DIN
                                                    "CCV-CV-CCV",	//	PRA-MU-DYA
                                                    "CV-V-CV-VC",	//	FAU-ZI-AH
                                                    "CV-CV-CCV-CVCC",      //      bapuprakash
                                                    "VCC-CV-CV",	//	ASY-RA-NI 
                                                    "CV-V-CV-CV",	//	FAUZURA		/	FAU-ZU-RA 22
                                                    "CVC-CV-CV",	//	TAR-MI-ZI
                                                    "CVC-CV-V-CV",      //SENTIASA	/	SEN-TI-A-SA
                                                    "V-CV-CV-CV-CV", //ayunazura
                                                    //3
                                                    "CVC-CV-CCC",	//zul ki fly	1309
                                                    "CVV-CV-CVCC",	//mai za tull	1309
                                                    "CCV-V-CV",	//	kha-i-ri		1509
                                                    "CCV-CVC-CVC",      //PROFESSOR	/	PRO-FES-SOR
                                                    "CV-CVC-CVC",       //KEMASKAN	/	KE-MAS-KAN
                                                    "CVC-CV-CVC",       //MAKRIFAT	/	MAK-RI-FAT
                                                    "VC-CV-CVCC",       //INSURANS	/	IN-SU-RANS
                                                    "CCCV-CV-CV",       //STRATEGI	/	STRA-TE-GI
                                                    "CV-CV-CVCC",       //SEKARANG	/	SE-KA-RANG
                                                    "CCV-CV-CVC",       //PROFESOR	/	PRO-FE-SOR
                                                    "CCVC-CV-VC",	//	sham si ah	1309
                                                    "CVC-CVC-CVC",	//	naj mud din	1309
                                                    "CVC-CCV-CVC",	//	mas-dza-rif	1309
                                                    "V-CVC-CVC",        //ARIFFIN	/	AR-IF-FIN
                                                    "CV-CCV-CV",        //MENGAPA	/	ME-NGA-PA
                                                    "CVC-CV-VC",        //MEMBUAT	/	MEM-BU-AT
                                                    "CV-CV-CVC",        //CABARAN	/	CA-BA-RAN
                                                    "CV-CV-CV-V",        //SEBAGAI	/	SE-BA-GAI
                                                    "CV-CVC-CV",        //SEPERTI	/	SE-PER-TI
                                                    "CV-V-CCVC",        //KUINGIN	/	KU-I-NGIN
                                                    "CV-V-CVCC",        //SEORANG	/	SE-O-RANG
                                                    "CCV-CV-CV",        //PROFESI	/	PRO-FE-SI
                                                    "CV-CV-V-CV-V",	//	YU-ZAI-MIE 
                                                    "VC-CVC-CVC",	//	AB-DOL-LAH 
                                                    "VC-CV-V-CVC",	//	AZ-LI-A-NOR
                                                    "VC-CVC-CVC",	//	AB-DUL-LAH
                                                    "VC-CVC-CC",	//	AFFANDY		/	AF-FAN-DY 22
                                                    "CV-CVV-CV",	//	ZA-HEE-RA
                                                    "VC-CVC-CV",	//	AS-LIN-DA
                                                    "VC-CV-CVC",	// 	IBRAHIM
                                                    "CV-CV-V-V",         //BELIAU	/	BE-LI-AU
                                                    "CV-CV-CV",         //LELAKI	/	LE-LA-KI
                                                    "CV-CV-VC",         //KELUAR	/	KE-LU-AR
                                                    "VC-CV-VCV",	//	AR-DI-ANA	##
                                                    "VC-CV-VC",	//	IS-MA-IL	##
                                                    "CV-CC-CV",	//ha fy fa	1309
                                                    "VC-CV-CV",	//er da li
                                                    "VC-CV-CV",	//az-li-na	1309
                                                    "V-CV-CVC",         //ADALAH	/	A-DA-LAH
                                                    "CV-CV-V",          //SEMUA		/	SE-MU-A
                                                    "CV-V-CV",          //SUARA		/	SU-A-RA
                                                    "V-CVC-CV",	//	A-NAN-TA 
                                                    "V-CV-CV",          //UTAMA		/	U-TA-MA
                                                    "V-CV-VC",          //AKAID		/	A-KA-ID
                                                    "V-V-CCVC",	//	a-i-shah		1509
                                                    "V-V-CCV",	//	a-i-sya		1309

                                                    //2
                                                    "CCCVC-CVC",        //STRUKTUR	/	STRUK-TUR
                                                    "CCVC-CVC",         //PRAKTIK	/	PRAK-TIK
                                                    "CVC-CVCC",         //TENTANG       /	TEN-TANG
                                                    "CVCC-CVC",         //MUNGKIN	/	MUNG-KIN
                                                    "CCV-CVC",          //SYAKIB	/	SYA-KIB
                                                    "CCCV-CV",          //STRATA	/	STRA-TA
                                                    "CCVV-CVC",	//khairul		1309
                                                    "CVCC-CV",		//teng ku		1309
                                                    "CCVC-CV",		//	syah-mi			1509
                                                    "CV-CVCC",          //KOSONG	/	KO-SONG
                                                    //"CV-CCVC",          //SANGAT	/	SA-NGAT
                                                    "CCV-CVC",		//	DRAMAN	*********
                                                    "CV-V-CVC",	//	FAI-SAL *********
                                                    "CVC-CC",	//	RAM-LY *********
                                                    "CVC-CCV",	//	yas zli		1309
                                                    "CCV-CVCC",	//sha-riff	1309
                                                    "CCV-CCV",		//	sha-fri			1509
                                                    "CVC-CVC",          //DOKTER	/	DOK-TER
                                                    "VCC-CVC",		//	OTHMAN	/	OTH-MAN
                                                    "CCV-CVC",		//	DRAMAN  
                                                    "CV-V-CVC",		//	FA-I-SAL
                                                    "CVC-CVC",  	// 	KOS-NIN
                                                    "CCV-CCV",  //sya-sya
                                                    "CCV-CVV",		//	SYU-RIA
                                                    "CCVC-CV",          //SYAHRI	/	SYAH-RI
                                                    "CVC-CV",           //HANTU		/	HAN-TU
                                                    "CCV-CV",           //GHA-NI
                                                    //"CVC-CC",		//	HEL-MY  /2208
                                                    "CVC-CC",		//	RAM-LY  /2208
                                                    "CV-CVC",           //MALAM		/	MA-LAM
                                                    "VC-CVC",           //ASPEK		/	AS-PEK
                                                    "VC-CCV",           //INFRA		/	IN-FRA
                                                    "V-CVCC",           //AKANG		/	A-KANG
                                                    "CV-CCV",           //HANYA		/	HA-NYA
                                                    "CV-CV",            //BELI		/	BE-LI
                                                    "CV-VC",            //BUAT		/	BU-AT
                                                    "VC-CV",            //ANDA		/	AN-DA
                                                    "VC-CV-C", 		//	ASMAI   /2208
                                                    "VC-CV",		//	AZRI    /2208
                                                    "V-CVC",            //IKAN		/	I-KAN
                                                    "V-CV-V",            //ATAU		/	A-TAU
                                                    "V-CV",             //INI		/	I-NI
                                                    "V-VC",             //AAK
                                                    "CCCVC",            //STRUK
                                                    "CCCV",             //STRA
                                                    "CCVC",             //PRAK
                                                    "CVCC",             //RANS
                                                    "CVC",              //CIK
                                                    "CCV",              //DWI
                                                    "CV-V",              //DIA
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
