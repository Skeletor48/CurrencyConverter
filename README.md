# CurrencyConverter
Java Spring Boot + Maven + MySqL

A feladat leírása
Szolgáltatás ellátó modul fejlesztése deviza konverzióval kapcsolatos feladatok ellátására, amely kielégíti az alábbi követelményeket:
	•	5 tetszőlegesen megválasztott devizanemet kell tudnia kezelni. CHECK
	•	Szolgáltatást nyújt devizaárfolyamok lekérdezésére egy forrásdeviza függvényében. CHECK
	•	Szolgáltatást nyújt devizakonverzióra egy forrás-, cél- és összeg függvényében. CHECK
	•	Kétfajta devizaárfolyamot kezel a szolgáltatások során: eladásit és vételit.CHECK

Technikai követelmények
A megvalósítás feleljen meg a következő elvárásoknak:
		Az alkalmazásnak alkalmazásszerveren futtathatónak kell lennie (WAR vagy EAR fájl). FAIL
		A szolgáltatásokat HTTP protokoll fölött kell nyújtania CHECK
		Spring-et kell használni (minimum 3-as verzió) IOC konténerként. CHECK
		Az adatbázis tetszőleges SQL kompatibilis adatbázis lehet. NoSQL nem lehet. CHECK
		Az adatbázis eléréshez MyBatis-t kell használni. FAIL
		Teszt orientált fejlesztést kell alkalmazni. FAIL
		Nem kell semmilyen webes felület az alkalmazás teszteléséhez, viszont kell egy JUnit vagy JMeter alapú megoldás, amivel ki lehet próbálni az – alkalmazásszerveren éppen futó – alkalmazást. FAIL
		Spring controller rétegben ne legyen üzleti logika. CHECK
		A megoldás során OOP elveket kell használni. SO-SO
		A fejlesztés során gondolni kell a biztonsági megfontolásokra is (pl. input adatok megfelelő ellenőrzése). BRUTAL FAIL

Formai követelmények a beadandó feladattal kapcsolatban:
		Megfelelően kommentezett (angol nyelven), érthető, egységes forráskód FAIL
		Java, JEE és Spring minták megfelelő használata. FAIL
		Telepítési instrukciók (dokumentum) – ha szükséges FAIL
		Tesztelés módjának megjelölése és a tesztadatok leírása vagy annak megjelölése, hogy hol találhatók a forráskódban FAIL




Az aplikaciot Postman segitsegevel teszteltem.

Az alap vegpontokon kivul egy admin szerviz osztalyt is letrehoztam. Az applikacio jelen formajaban feltetelezi a localhost dedikalt portjan egy wupapi elnevezesu adatbazis letezeset.

`/api/create`  letrehozza a szukseges tablat
`/api/populate` adatokkal tolti fel a tablat
`/api/deleteAll` torli az adatokat a tablabol
`/api/checkAll` kilistazza az ossze rekordot vagy ures tombot ad vissza ha ures a tabla

`/cheater` csak egy kis proof of koncept endpoint hogy mennyibvel egyszerubb lenne az eletem ha hasznalhatnam a javax.money konyvtarat a request bodyban a kovetkezo formatummal tesztelheto `{"amount":10,"base":"USD","target":"EUR"}`


Valthato devizak kodjai: chf,usd,eur,cad,gbp

`/rate/{base}/{type}`   az url-be parameterkent base-kent a valtando devizat kuldjuk, a masodik parameter a BUYING_RATE es  az EXCHANGE_RATE-et jelkepezo 1-es vagy 2-es; Ez a vegpont kuldi vissza egy adott bazis devizaval szemben az osszes tobbi valto erteket

`/rate/convert`  `{"amount":10,"type":2,"base":"cad","target":"eur"}` az osszeggel bazisrol celra valto vegpontnak a jelzet formatumban kuldhetjuk az adatokat.


Ami kimaradt. Hat ugy kb minden. 

- nuku biztonsag
- nincs ellenorizve a bevitel
- nincsenek kezelve a lehetseges hibak
- beegetett ertekek vannak
- stb.stb.stb












