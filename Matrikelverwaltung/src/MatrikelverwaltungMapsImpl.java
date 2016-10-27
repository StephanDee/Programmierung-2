import java.util.HashMap;

public class MatrikelverwaltungMapsImpl implements Matrikelverwaltung {

	private final HashMap<Integer, String> matrikelnummern = new HashMap<Integer, String>();
	private final HashMap<String, Integer> studenten = new HashMap<String, Integer>();
	private int matrikelnr = 0;

	@Override
	public int eintragen(final String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Kein Name eingegeben.");
		}
		if (matrikelnummern.size() == Integer.MAX_VALUE) {
			throw new Ueberlauf(Integer.MAX_VALUE);
		}
		++matrikelnr;

		matrikelnummern.put(matrikelnr, name);
		studenten.put(name, matrikelnr);

		return matrikelnr;
	}

	@Override
	public String name(final int matrikelnr) {

		if (!matrikelnummern.containsKey(matrikelnr)) {
			throw new MatrikelnrUnbekannt(matrikelnr);
		}
		return matrikelnummern.get(matrikelnr);
	}

	@Override
	public int matrikelnr(final String name) {

		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Kein Name eingegeben.");
		}
		if (!studenten.containsKey(name)) {
			throw new NameUnbekannt(name);
		}
		return studenten.get(name);
	}

	@Override
	public int letzteVergebeneMatrikelnr() {
		return matrikelnr;
	}

	@Override
	public void austragen(final int matrikelnr) {

		if (!matrikelnummern.containsKey(matrikelnr)) {
			throw new MatrikelnrUnbekannt(matrikelnr);
		}
		final String name = matrikelnummern.get(matrikelnr);
		matrikelnummern.remove(matrikelnr);
		studenten.remove(name);
	}
}