📝 Spielidee
Unser Spiel ist ein klassischer 2D-Platformer, bei dem der Spieler eine Spielfigur durch eine Plattformwelt steuert. Die Figur kann sich nach links und rechts bewegen sowie springen. Ziel ist es, sich durch die Welt zu navigieren und dabei mit der Umgebung korrekt zu interagieren. Der Schwerpunkt liegt aktuell auf der technischen Umsetzung der Spielgrundlagen, um ein stabiles und erweiterbares Gerüst zu schaffen.

Die Spielwelt wird durch sogenannte Tilemaps dargestellt – das sind Textdateien, in denen die Position und Art jedes Blocks gespeichert ist. Auf Basis dieser Tiles wird das Level dann im Spiel gerendert. Auch die Spielfigur reagiert physikalisch korrekt auf diese Umgebung, etwa beim Landen, Laufen oder bei Kollisionen mit festen Blöcken.

Darüber hinaus verfügt das Spiel über eine saubere Trennung in verschiedene Zustände – etwa das aktive Spiel, ein Pausenmodus sowie das Hauptmenü. In den letzten Folgen lag der Fokus vor allem auf dem Aufbau eines funktionalen Pausenbildschirms, der sowohl visuell als auch steuerungstechnisch umgesetzt wurde.

🎮 Aktueller Funktionsumfang
Das Spiel beinhaltet derzeit folgende Funktionen:

Steuerbare Spielfigur: inklusive Laufen, Springen und Animation

Kollisionserkennung: korrekte Reaktion auf Kollision mit der Welt

Levelsystem mit Tilemaps: Level werden aus Textdateien geladen und dargestellt

Pausenmenü: vollständig funktional mit Auswahloptionen und Tastennavigation

Spielzustände: sauber getrennte Zustände (Menü, Spiel, Pause) für besseren Codeaufbau

Grundlegende Benutzeroberfläche: HUD in Planung (z. B. Anzeige von Zeit oder Levelname)

Features wie Leben, Gegner, Sound oder Levelziele sind für zukünftige Erweiterungen vorgesehen, aber derzeit noch nicht implementiert.

👥 Aufgabenverteilung
🧑‍💻 Mohamed Ishak
Spielersteuerung: Entwicklung der Spielerklasse mit vollständiger Bewegungslogik, Sprungverhalten und Kollisionserkennung mit der Umgebung

Tilemap-System: Umsetzung des Levelsystems inklusive Dateiimport und Zeichnen der Welt mithilfe von Tiles

Game Loop & State Management: Aufbau des zentralen Spielsystems mit Update-/Render-Zyklus sowie Zustandswechsel (Menü ↔ Spiel ↔ Pause)

Pause-Menü (Teil 2): Logik zur Auswahl und Navigation im Pausenbildschirm, inklusive Tasteneingaben und Auswahlanzeige

🧑‍💻 Emre Akpinar
Levelanzeige und Tilemap-Erstellung: Gestaltung erster spielbarer Level als Textdateien und grafische Umsetzung mit verschiedenen Blöcken

Pause-Menü (Teil 1): Aufbau des Grundlayouts und Integration in das bestehende State-System

Testing & Debugging: Laufende Kontrolle der Spielfunktionen, Fehlersuche und Feinschliff bei der Darstellung

Grafikverwaltung: Laden, Zuweisung und Einbindung der verwendeten Sprites (z. B. Spieleranimationen, Tileset)
