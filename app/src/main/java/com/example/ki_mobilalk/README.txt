Segítség a kiértékeléshez:

Készítő: Kovács Imre
DC: hyba28#5542
E-mail: hyba28@gmail.com

Pontozási segédlet:

Elem	Pontszám	Bontás*                 Össz pont:
Fordítási hiba nincs	1	nincs   <--- Nyilván nincs  1 pont
Futtatási hiba nincs	1	nincs   <--- Nyilván nincs  2 pont
"Firebase autentikáció meg van valósítva:
Be lehet jelentkezni és regisztrálni"	4	"Regisztráció - 2 pont  <-- megvan, email-el jelszóval  4pont
Autentikált bejelentkezés - 2 pont"             <-- adott, 6pont
Adatmodell definiálása (class vagy interfész formájában)	2	nincs       <-- User és userDao osztály 8pont
Legalább 3 különböző activity vagy fragmens használata	2	nincs    <-- ennél sokkal többet is 10 pont



Beviteli mezők beviteli típusa megfelelő (jelszó kicsillagozva, email-nél megfelelő billentyűzet jelenik meg stb.)	3	"0 - sehol sincs megvalósítva
1 vagy 2 - többnyire meg van valósítva
3 - mindenhol meg van valósítva"        <-- Mindenhol megvan, 11 pont
ConstraintLayout és még egy másik layout típus használata	1	nincs    <--- A Choose az linear a többi constraint 12 pont


"Reszponzív:
- különböző kijelző méreteken is jól jelennek meg a GUI elemek (akár tableten is)
- elforgatás esetén is igényes marad a layout"	3	"0 - szétesik vagy eltűnnek elemek a layout elforgatása/más méretű kijelző esetén
1 vagy 2 - a layout használható, de nem igényesen változik elfogatás/más méretű kijelző esetén
3 - igényesen meg van valósítva mindenhol"          <--- A regisztráció kivételével  mind igényes, tőled függ de inkább 2 pont mint 1, - 14/13 pont


Legalább 2 különböző animáció használata	2	nincs       <--- A Bejelejentkezés és REgisztrációnál a Vissza gombok FADE-elnek, a másik gomb itt és a Choose-ban Rotateol // 16/15pont


"Intentek használata: navigáció meg van valósítva az activityk/fragmensek között
 (minden activity/fragmens elérhető)"	2	nincs    <-- nyilván 18/17 pont



"Legalább egy Lifecycle Hook használata a teljes projektben:
- onCreate nem számít
- az alkalmazás funkcionalitásába értelmes módon beágyazott, azaz pl. nem csak egy logolás"	2	nincs  <-- nincs


"Legalább egy olyan androidos erőforrás használata,
amihez kell android permission"	1	nincs           <-- nincs



Legalább egy notification vagy alam manager vagy job scheduler használata 	2	nincs  <-- próbálkoztam vele, nemttudom megy-e de ha megtalálod akkor add meg rá, ha nem ne húzd az időt (NotificationHandler, elvileg regisztrációnál vibrál, de nem tudtam tesztelni), pontokhoz most nem számolom


"CRUD műveletek mindegyike megvalósult és az adatbázis műveletek a konvenciónak
megfelelően külön szálon történnek"	5	műveletenként(C,R,U,D) egy pont, ha mind külön szálon van megvalósítva +1 pont  <-- create- Regisztráció, Read- Óraállás megtekintése, Update-Diktálás, Delete-felhasználó törlése, nincs szálkezelés, tehát 4 pont // 22/21pont



"Legalább 2 komplex Firestore lekérdezés megvalósítása,
amely indexet igényel (ide tartoznak: where feltétel, rendezés, léptetés, limitálás)"	4	lekérdezésenként 2 pont     <-- nincs




"Szubjektív pontozás a projekt egészére vonatkozólag:
ez 5-ről indul és le lehet vonni, ha igénytelen, összecsapott, látszik hogy nem foglalkozott vele, kísértetiesen hasonlít a videóban létrehozotthoz stb."	5	5-ről indul ha fordul a projekt, csökkenthető  <-- elég sok munka volt benne, mégha minimalista is, minimum 3 pont szerintem, de ha kedves vagy akkor határ a csillagos ég 25/24 pont

Összesen	40	<-- Biztos pontok amennyit muszáj találnod mert megvannak 25 vagy 24 attól függően hogy a reszponzivitást mennyinek veszed, emellett Notira járhat 2 illetve Szubjektívre még 2, tehát maximum 29 pont, ha 20 felett vagyunk már nekem mindegy, szóval ahogy látod.