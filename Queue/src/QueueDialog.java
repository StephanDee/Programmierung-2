import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Im SS 13 in Pr1: Aufgabe 9, in WS 08 in IN3: Aufgabe 6
//2008-11-08  Knabe  Umstellung auf Dialogtest von Queue
//1998-05-21  Knabe  Erstellung

/**Dialogtesttreiber für die ausnahmegesicherte, größenbeschränkte Warteschlange Queue.*/
class QueueDialog {


    public static final int maxAnz = 5;

    /** Liest Zeilen in eine Warteschlange ein und gibt sie bei Eingabeende in derselben Reihenfolge wieder aus. */
    public static void main(final String[] args) throws Exception {
        new QueueDialog().benutzen();
    }//main

    /** Liest Zeilen in eine Warteschlange ein und gibt sie bei Eingabeende in derselben Reihenfolge wieder aus. */
	public void benutzen() throws IOException, Queue.Overflow, Queue.Underflow {
		final Queue queue = new ScalaQueue(maxAnz);
        _einlesen(queue);
        _ausgeben(queue);
	}

	private void _einlesen(final Queue queue) throws IOException, Queue.Overflow {
        System.out.println("Geben Sie eine Reihe von Zeilen ein und beenden Sie durch Eingabe von <Strg/Z><Eingabe> auf Windows bzw. <Strg/D><Eingabe> auf Unix-Derivaten.");
    	final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for(;;){
            System.out.print("Zeile ? ");
            final String line = in.readLine();
            if(line==null){break;}
            queue.insert(line);
        }
        in.close();
	}

	private void _ausgeben(final Queue queue) throws Queue.Underflow {
        System.out.println();
        System.out.println("Die Schlange liefert in folgender Reihenfolge:");
        for(;;){
            final String zeile;
            try{
            	zeile = queue.read();
            }catch(final Queue.Underflow ex){
            	break;
            }
			System.out.println(zeile);
        	queue.delete();
        }
	}


}//QueueDialog
