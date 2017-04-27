<h1>Bugsy</h1>

<h2>Opis zadatka:</h2>

<p>
Kreirajte aplikaciju koja omogućuje prikaz vijesti iz RSS feed-a na
http://www.bug.hr/rss/vijesti/ te omogućuje prikaz po kategorijama. Potrebno je
prikazati sliku, vijest, vrijeme objave i omogućiti čitanje vijesti klikom na element liste.
</p>

<h2>Rješavanje zadatka i problemi:</h2>

<p>
<h4>Osvrt na zadatak i korištena rješenja:</h4>
Napravljena aplikacija Bugsy služi za čitanje rss feed-a sa stranice www.bug.hr. Za realizaciju aplikacije napravljene su 3 klase, MainActivity,
Reader(za prikaz svih elemenata) i ReaderAdapter(adapter reycler view-a). Ova zadaća je osnovana na aplikaciji koja već postoji[2], 
ali traženej po kategorijama, prikaz slika i ostale stvari su dodane.<br>
Klikom na element liste otvara se implicitni intent koji vodi na link odabranog članka. Također je moguće osvježiti sadržaj povlačenjem
početka recycler view-a i osvježavanje pritiskom na gumb search kada ništa nije upisano. Ukoliko ne postoji internet veza ispisuje se Toast poruka.
<h4>Testiranje:</h4>
Testiranje je izvršeno na Meizu MX3 i Noa VivoSe uređajima.
</p>

<p>
<h4>Problemi:</h4>
Prvi problemi su se pojavili prilikom dodavanja slike, ali to je rješeno korištenjem linka[3]. Nakon toga nije bilo moguće otvoriti intent 
van main activity-a dok nije dodana zastavica[4] i bio je problem sa referenciranjem jer nije bila odabrana instanca objekta, već sam objekt.
Za kraj dodana je različita boja za svaki drugi elemnt liste.[6]
</p>

<h1>Sources</h1>
 <ul type="none">
  <li>[1] Predlošci za laboratorijske vježbe</li>
  <li>[2] http://www.androidauthority.com/simple-rss-reader-full-tutorial-733245/</li>
  <li>[3] https://www.101apps.co.za/index.php/articles/android-recyclerview-and-picasso-tutorial.html</li>
  <li>[4] http://stackoverflow.com/questions/3918517/calling-startactivity-from-outside-of-an-activity-context</li>
  <li>[5] http://stackoverflow.com/questions/2559527/non-static-variable-cannot-be-referenced-from-a-static-context</li>
  <li>[6] http://stackoverflow.com/questions/35681065/change-color-of-every-second-element-in-recyclerview</li>
</ul> 
