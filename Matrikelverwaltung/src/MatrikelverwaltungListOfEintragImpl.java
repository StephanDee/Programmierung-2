import java.util.ArrayList;

public class MatrikelverwaltungListOfEintragImpl implements Matrikelverwaltung {

	private class _Eintrag {

		private final int matrikelnr;
		private final String name;

		public _Eintrag(final int matrikelnr, final String name) {
			this.matrikelnr = matrikelnr;
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public int getMatrikelnr() {
			return this.matrikelnr;
		}
	}

	private final ArrayList<_Eintrag> studenten = new ArrayList<_Eintrag>();
	private int matrikelnr = 0;

	@Override
	public int eintragen(final String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Kein Name eingegeben.");
		}

		if (studenten.size() == Integer.MAX_VALUE) {
			throw new Ueberlauf(Integer.MAX_VALUE);
		}
		studenten.add(new _Eintrag(++matrikelnr, name));
		return matrikelnr;
	}

	@Override
	public String name(final int matrikelnr) {
		for (final _Eintrag student : studenten) {
			if (student.getMatrikelnr() == matrikelnr) {
				return student.getName();
			}
		}
		throw new MatrikelnrUnbekannt(matrikelnr);
	}

	@Override
	public int matrikelnr(final String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Kein Name eingegeben.");
		}
		for (final _Eintrag student : studenten) {
			if (student.getName().equals(name)) {
				return student.getMatrikelnr();
			}
		}
		throw new NameUnbekannt(name);
	}

	@Override
	public int letzteVergebeneMatrikelnr() {
		return matrikelnr;
	}

	@Override
	public void austragen(final int matrikelnr) {

		for (int i = 0; i < studenten.size(); i++) {
			if (studenten.get(i).getMatrikelnr() == matrikelnr) {
				studenten.remove(i);
				return;
			}
		}
		throw new MatrikelnrUnbekannt(matrikelnr);
	}
}