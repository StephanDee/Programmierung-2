//Im SS 13 in Pr1: Aufgabe 9, in WS 08 in IN3: Aufgabe 6
//1999-06-03  Knabe  javadoc-Kommentare
//1998-06-18  Knabe  Absicherung durch Ausnahmen
//1998-06-04  Knabe  Erstellung

/** Implementiert den Trait Queue (analog Java-Interface) in Java. */
public class JavaQueue implements Queue {

	/**
	 * Das Feld, in welchem die Elemente abgelegt werden. Das älteste hat immer
	 * den Index 0.
	 */
	private final String _inhalt[];

	/** Anzahl eingetragener Elemente */
	private int _anzahl = 0;

	/** Erzeugt eine neue Queue mit Platz fuer maximal size Elemente */
	public JavaQueue(final int size) {
		_inhalt = new String[size];
	}

	/**
	 * Hängt element an die Warteschlange an (Mutator).
	 * 
	 * @throws Overflow
	 *             kein Platz mehr in der Schlange
	 */
	public void insert(final String element) {
		if (_anzahl >= _inhalt.length) {
			throw new Queue.Overflow(_anzahl);
		}
		_inhalt[_anzahl] = element;
		_anzahl++;
	}

	/**
	 * Liefert das älteste, noch in der Warteschlange enthaltene Element
	 * (Informator).
	 * 
	 * @throws Underflow
	 *             Schlange leer
	 */
	public String read() throws Underflow {
		_checkUnderflow();
		return _inhalt[0];
	}

	/**
	 * Löscht das älteste Element aus der Warteschlange (Mutator)
	 * 
	 * @throws Underflow
	 *             Schlange leer
	 */
	public void delete() throws Underflow {
		_checkUnderflow();
		for (int i = 1; i < _anzahl; i++) {
			_inhalt[i - 1] = _inhalt[i];
		}
		_anzahl--;
		_inhalt[_anzahl] = null;
	}

	private void _checkUnderflow() throws Underflow {
		if (_anzahl <= 0) {
			throw new Underflow();
		}
	}

}
