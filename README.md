# SpringAPI


## Programmieraufgabe

Es soll ein Prototyp einer Verlagsverwaltung in Java erstellt werden.
Falls die Anforderungen präzisiert werden müssen, bitte nachfragen!
Dabei ist auf
* sauberen Code
* relevante Dokumentation
* Robustheit
* Modularität
  zu achten.
  Abschließend soll die Lösung kurz präsentiert werden.

Kundenanforderungen
Ein Verlag hat mehrere Bücher und jedem Buch ist ein Autor zugeordnet. Ziel ist es, dass
der Prototyp folgende Funktionen zur Verfügung stellt:
* Alle Verlage auflisten
* Alle Bücher auflisten
* Alle Autoren auflisten
* Ein Verlag mit allen Büchern und zugehörigen Autoren auflisten
  Die Daten sind (vorerst) in Textdateien abgelegt, die wie folgt aufgebaut sind:
  Verlag.txt
  verlagID;Verlagname
  Buch.txt
  buchID;Titel;Preis;ISBN;verlagID
  Autor.txt
  autorID; Vorname;Name;buchID
  Weitere Hinweise:
* Die spätere Anwendung soll als Microservice betrieben werden (z.B.: JEE oder
  Spring Boot). Bei der Implementierung des Prototyps soll das entsprechend
  berücksichtigt werden.
* Die Implementierung soll auf Basis von Spring Boot erfolgen.
* Des Weiteren soll der Prototyp es erlauben, die Anwendung später auf eine
  Persistierung mittels einer Datenbank statt auf Textdateibasis umzustellen.
* Es muss sichergestellt sein, dass sich durch spätere Anpassungen an der
  Implementierung keine Fehler einschleichen