package diagr; //DiagrammDialog.java:

import java.awt.Container;
import javax.swing.JComponent;

//Änderungshistorie:
//2013-11-18  Knabe  Umgestellt auf Scala
//2008-12-08  Knabe  Zwei Zähne mit in die Folge aufgenommen.
//2008-12-08  Knabe  Ohne Parameternamenspräfixe i_, io_, o_
//1999-11-17  Knabe  Button und andere Variablen objektweit privat in
//                   DiagrammTest, objektweiter ActionListener mit
//                   einheitlicher Fehlerbehandlung
//1999-02-23  Knabe  Informatoren ohne Namenspraefix 'i_', Mutatoren mit 'mut_'
//1999-02-15  Knabe  Alle Figuren als aeussere Klassen in Paket figuren
//1998-11-23  Knabe  Hilfsmeldungen auf System.err auskommentiert
//1998-11-20  Knabe  umgestellt auf Markieren mittels Maus-Koordinaten
//1998-11-05  Knabe  erstellt

object DiagrammDialog extends App {
  //Schedule a job for the event-dispatching thread: creating and showing this application's GUI.
  javax.swing.SwingUtilities.invokeLater(new Runnable {
    def run(){
      new DiagrammDialog
    }
  })
}

/**
 * Zweck: Diagramm aufbauen und Aenderungen schrittweise anzeigen. Den jeweils
 * nächsten Testschritt löst man durch Klicken des Weiter-Buttons aus.
 */
class DiagrammDialog extends/*Java hier: implements*/ java.awt.event.ActionListener {

    private val _figuren = FigurAnzeige.gesicht(368, 273); // Breite,Höhe
    private val (_diagramm, _zeichenbereich) = _sichtenAufteilen(new DiagrammImpl)
    private val _frame = new javax.swing.JFrame(getClass.getName)
    private val _weiterButton = new javax.swing.JButton("Weiter")
    private var _schrittNr = -1
    
    _frame.setSize(500, 300)
    val contentPane: Container = _frame.getContentPane
    contentPane.add(_zeichenbereich, java.awt.BorderLayout.CENTER) // möglichst groß
    // System.err.println("_frame: " + _frame.getBounds());
    // System.err.println("_diagramm: " + _diagramm.getBounds());

    _weiterButton.addActionListener(this)
    contentPane.add(_weiterButton, java.awt.BorderLayout.WEST)

    _frame.addWindowListener(new java.awt.event.WindowAdapter {
        override def windowClosing(ev: java.awt.event.WindowEvent) {
            System.exit(0)
        }
    })

    _frame.setVisible(true)

    /**
     * Wenn der Weiter-Button gedrückt wird, soll der nächste Testschritt
     * durchgeführt werden. Dabei auftretende Exceptions werden in einem eigenen
     * Fenster gemeldet.
     */
    def actionPerformed(ev: java.awt.event.ActionEvent) {
        val eventSource: Object = ev.getSource
        try {
            if (eventSource == _weiterButton) {
                _schrittNr += 1
                print(_schrittNr + ". ")
                _schritte(_schrittNr).apply()
            }else {
                throw new IllegalArgumentException("Illegal event source " + eventSource + " of event " + ev);
            }
        } catch {
          case ex: Exception => // jede Ausnahme melden
            _report(ex)
        }
        _zeichenbereich.repaint()
    }

    /** Reports exception to a message box owned by _frame */
    private def _report(exception: Exception) {
        multex.Swing.report(_frame, exception)
    }

    /**Liefert 2 Sichten auf die Diagramm-Implementierung: als Diagramm und als JComponent.*/
    private def _sichtenAufteilen(impl: DiagrammImpl): (Diagramm, JComponent) =(impl, impl)

    /**Ein Test-Schritt ist eine parameterlose Funktion, die nichts liefert.*/
    type Schritt = ()=>Unit
    
    /**
     * Ansatz: Alle Test-Schritt-Objekte sind im Feld '_schritte' eingetragen.
     * Dies ermöglicht einfach die Umstellung ihrer Reihenfolge:
     */
    private val _schritte: Array[Schritt] = Array(
        () => _eintragen("Kopf"),
        () => _eintragen("linkes Auge"),
        () => _eintragen("rechtes Auge"),
        () => _eintragen("Nase"),
        () => _eintragen("Mund"),
        () => _eintragen("linker Zahn"),
        () => _eintragen("rechter Zahn"),
        () => _eintragen("linkes Ohr"),
        () => _eintragen("rechtes Ohr"),
        () => _markieren(70, 77, "linkes Auge", true),
        () => _markieren(114, 115, "linkes Auge", false),
        () => _markieren(245, 79, "rechtes Auge", true),
        () => _markieren(295, 118, "rechtes Auge", false),
        () => _markieren(157, 106, "Nase", true),
        () => _markieren(207, 159, "Nase", false),
        () => {
          println("entmarkieren: Nase")
          _diagramm.entmarkieren()
        },
        () => _markieren(115, 180, "Mund", true),
        () => _markieren(252, 204, "Mund", false),
        () => _markieren(33, 29, "Kopf", true),
        () => _markieren(326, 245, "Kopf", false),
        () => {
            println("loeschen: Kopf")
            _diagramm.loeschen()
        },
        () => {
            println("loeschen: unmarkierte Figur")
            _diagramm.loeschen()
        },
        () => {
            println("Ende")
            System.exit(0)
        }
    )

    /**
     * Trägt _figuren[_schrittNr] in _diagramm ein. Kündigt diese Aktion davor
     * auf der Standardausgabe an.
     */
    private def _eintragen(figurRolle: String) {
        val figur = _figuren(_schrittNr)
        println("eintragen: " + figur.getClass.getName + " als " + figurRolle)
        _diagramm.eintragen(figur)
    }

    /**
     * Markiert die Sensitivecke und ihre Figur aus _diagramm, die den Koordinaten
     * x,y am nächsten liegt. Kündigt diese Aktion und den beabsichtigten Markierungszweck
     * davor auf der Standardausgabe an.
     *
     * @throws FalschExc
     *             _diagramm.zwecksVerschieben() hat einen anderen
     *             Markierungszweck ermittelt als in zwecksVerschiebenBeabsichtigt
     *             angegeben.
     */
    private def _markieren(
        x: Int, y: Int,
        figurName: String, zwecksVerschiebenBeabsichtigt: Boolean 
    ){
        val beabsichtigtZweck = _markierungsZweck(zwecksVerschiebenBeabsichtigt)
        println("markieren(" + x + "," + y + "): " + figurName + " zwecks " + beabsichtigtZweck)
        _diagramm.markieren(x, y);
        val zwecksVerschiebenBewirkt = _diagramm.isZwecksVerschieben
		if (zwecksVerschiebenBeabsichtigt != zwecksVerschiebenBewirkt) {
	        val bewirktZweck = _markierungsZweck(zwecksVerschiebenBewirkt)
            throw new _FalschExc(x, y, beabsichtigtZweck, bewirktZweck)
        }
    }
    
    /**Liefert die String-Darstellung des Markierungszwecks.*/
    private def _markierungsZweck(zwecksVerschieben: Boolean): String = {
    	if(zwecksVerschieben) "Verschieben" else "Skalieren"
    }

    /** Bewirkter Markierungszweck abweichend vom beabsichtigten */
    private class _FalschExc(val x: Int, val y: Int, val beabsichtigtZweck: String, val bewirktZweck: String) 
    extends multex.Exc(
       	"Mit Klick auf Koordinaten {0},{1} war Markierungszweck \"{2}\" beabsichtigt; bewirkt wurde aber {3}."
       	, x.asInstanceOf[AnyRef], y.asInstanceOf[AnyRef], beabsichtigtZweck, bewirktZweck
    )
    

} // DiagrammDialog
