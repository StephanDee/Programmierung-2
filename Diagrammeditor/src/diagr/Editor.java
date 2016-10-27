package diagr;

import javax.swing.*;
import javax.swing.event.*;

import figuren.*;

import java.awt.*;
import java.awt.event.*;

public class Editor {
	
	private final DiagrammImpl _diagramm = _anfangsDiagramm();

	private final JButton _entmarkierenButton = new JButton("Entmarkieren");
	private final JButton _loeschenButton = new JButton("Löschen");
	private final JButton _druckenButton = new JButton("Drucken");
	private final String figurAuswahl[] = { "Rechteck", "Quadrat", "Ellipse",
			"Kreis", "Raute", "Strecke" };
	private final JList<String> figuren = new JList<String>(figurAuswahl);

	private Editor() {

		final JFrame jframe = new JFrame("DiagrammEditor");
		final JPanel jpanel = new JPanel(new GridBagLayout());
		final Container pane = jframe.getContentPane();
		final GridBagConstraints c = new GridBagConstraints();

		final ActionListener al = new AL();
		_entmarkierenButton.addActionListener(al);
		_loeschenButton.addActionListener(al);
		_druckenButton.addActionListener(al);
		figuren.addListSelectionListener(new AL());
		jframe.addMouseListener(new AL());

		// _diagramm.entmarkieren();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		jpanel.add(new JLabel("Erzeugen"), c);
		jpanel.add(figuren, c);
		jpanel.add(new JLabel("Bearbeiten"), c);
		jpanel.add(_entmarkierenButton, c);
		jpanel.add(_loeschenButton, c);
		jpanel.add(_druckenButton, c);

		final JComponent anzeige = _diagramm;

		pane.add(jpanel, BorderLayout.LINE_START);
		pane.add(anzeige, BorderLayout.CENTER);
		jframe.setSize(520, 300);
		jframe.setVisible(true);
		jframe.setResizable(false);
	}

	private DiagrammImpl _anfangsDiagramm() {

		final DiagrammImpl _diagramm = new DiagrammImpl();
		final Figur[] gesicht = FigurAnzeige.gesicht(400, 280);
		for (int i = 0; i < gesicht.length; i++)
			_diagramm.eintragen(gesicht[i]);
		_diagramm.setVisible(true);
		return _diagramm;
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Editor();
			}
		});
	}

	private class AL implements ListSelectionListener, ActionListener, MouseListener {

		@Override
		public void actionPerformed(final ActionEvent e) {
			// Holen der Ereignisquelle:
			final Object eventSource = e.getSource();
			try {
				if (eventSource == _entmarkierenButton) {
					_diagramm.entmarkieren();
				} else if (eventSource == _loeschenButton) {
					_diagramm.loeschen();
				} // else if (eventSource == _druckenButton) {
					// _diagramm.eintragen((Figur)figuren.getSelectedValue());
					// if (figuren.getSelectedValue().toString() == "Rechteck")
				_diagramm.eintragen(new Rechteck());
				if (figuren.getSelectedValue().toString() == "Quadrat")
					_diagramm.eintragen(new Quadrat());
				if (figuren.getSelectedValue().toString() == "Ellipse")
					_diagramm.eintragen(new Ellipse());
				if (figuren.getSelectedValue().toString() == "Kreis")
					_diagramm.eintragen(new Kreis());
				if (figuren.getSelectedValue().toString() == "Raute")
					_diagramm.eintragen(new Raute());
				if (figuren.getSelectedValue().toString() == "Strecke")
					_diagramm.eintragen(new Strecke());
			} catch (Exception ex) {
				multex.Swing.report(_diagramm, ex);
			}
			_diagramm.repaint();
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// if (!e.getValueIsAdjusting()) {
			if (figuren.getSelectedValue().toString() == "Rechteck")
				_diagramm.eintragen(new Rechteck());
			if (figuren.getSelectedValue().toString() == "Quadrat")
				_diagramm.eintragen(new Quadrat());
			if (figuren.getSelectedValue().toString() == "Ellipse")
				_diagramm.eintragen(new Ellipse());
			if (figuren.getSelectedValue().toString() == "Kreis")
				_diagramm.eintragen(new Kreis());
			if (figuren.getSelectedValue().toString() == "Raute")
				_diagramm.eintragen(new Raute());
			if (figuren.getSelectedValue().toString() == "Strecke")
				_diagramm.eintragen(new Strecke());
			// }
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			_diagramm.markieren(e.getX(), e.getY());
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}