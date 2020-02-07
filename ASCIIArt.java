import java.util.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class ASCIIArt extends JFrame implements MouseListener, WindowListener, KeyListener, MouseMotionListener, TableCellRenderer {

    ////////// PARTE DA MODIFICARE
	public static void main(String[] args) {
		canvas = new Canvas(50, 100, new Stack<Canvas>());
		toolbox = new Toolbox(canvas);
		toolbox.aggiungi(new Segmento(canvas), 's');
		toolbox.attiva('s');

		new ASCIIArt(canvas, toolbox);
	}

	private static final long serialVersionUID = 1L;


	private static Toolbox toolbox;
	private static Canvas canvas;
	private JTextArea textArea;
	private double charWidth;
	private double charHeight;
	private boolean dragging;
	private JFrame toolBar;
	private int dragFromX;
	private int dragFromY;
	private boolean dragRight;
	private boolean dragStartLaunched;

	public ASCIIArt(Canvas c, Toolbox t) {
		super("ASCII Art");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Text area
		setupTextArea(c.getR(), c.getC());
		add(textArea);
		pack();
		setResizable(false);
		setVisible(true);

		// Tool bar
		setupToolBar(t);

		// Listeners
		addWindowListener(this);
		textArea.addMouseListener(this);
		textArea.addMouseMotionListener(this);
		textArea.addKeyListener(this);
		setAlwaysOnTop(true);
		toFront();
	}

	private void setupTextArea(int rows, int cols) {
		textArea = new JTextArea(rows, cols);
		Font font = new Font("Courier", Font.PLAIN, 10);
		textArea.setFont(font);
		textArea.setEditable(false);
		textArea.setHighlighter(null);
		charWidth = getFontMetrics(font).getStringBounds("A", null).getWidth();
		charHeight = getFontMetrics(font).getStringBounds("A", null).getHeight();
	}

	private void setupToolBar(Toolbox t) {
		toolBar = new JFrame("Tools");
		toolBar.setLayout(new GridLayout(1, 1));
		Object[][] tableContent = new Object[t.strumenti().size()][2];
		int i = 0;
		for (Character car: t.strumenti().keySet()) {
				tableContent[i][0] = car;
				Strumento strumento = t.strumenti().get(car);
				String prefix = (strumento == t.attivo())? "*" : "";
				tableContent[i++][1] = prefix + strumento.getClass().getSimpleName();
		}
		JTable table = new JTable(tableContent, new String[] {"Key", "Tool"});
		TableCellRenderer cellRenderer = this;
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.setGridColor(Color.BLACK);
		table.setShowGrid(true);
		toolBar.add(table);
		toolBar.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		toolBar.setLocation(this.getLocation().x + this.getWidth() + 50, this.getLocation().y);
		toolBar.pack();
		toolBar.setVisible(true);
	}

	public int getX(MouseEvent e) {
		return Math.min(Math.max((int)(e.getX() / charWidth), 0), canvas.getC() - 1);
	}

	public int getY(MouseEvent e) {
		return canvas.getR() - Math.min(Math.max((int)(e.getY() / charHeight), 0), canvas.getR() - 1);
	}


	public void update() {
		textArea.setText(canvas.toString());
		repaint();
		toolBar.invalidate();
		toolBar.revalidate();
		toolBar.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		toolbox.ricevi(new MouseClick(getX(e), getY(e), e.getButton() == 3, e.getClickCount() == 2));
		update();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (dragging) {
			if (dragStartLaunched) toolbox.ricevi(new DragEnd(getX(e), getY(e)));
			else mouseClicked(e); // false dragging
			update();
			dragging = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		toolbox.ricevi(new EventoDiTastiera(e.getKeyChar()));
		update();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!dragging) {
			dragFromX = getX(e);
			dragFromY = getY(e);
			dragRight = e.getButton() == 3;
			dragStartLaunched = false;
			dragging = true;
		} else if (!dragStartLaunched && (getX(e) != dragFromX || getY(e) != dragFromY)) mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		toolbox.ricevi(new MouseMove(getX(e), getY(e)));
		if (dragging & !dragStartLaunched) {
			toolbox.ricevi(new DragStart(dragFromX, dragFromY, dragRight));
			dragStartLaunched = true;
		}
		update();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object v, boolean isSelected, boolean hasFocus, int row, int column) {
		String value = v.toString();
		if (value.startsWith("*")) value = value.substring(1);
		String prefix = (toolbox.attivo() != null && toolbox.attivo().getClass().getSimpleName().equals(value))? "*" : "";
		JTextField jtf = new JTextField(prefix + value);
		jtf.setFont(new Font("Times", Font.PLAIN, 10));
		jtf.setHorizontalAlignment(JTextField.CENTER);
		jtf.setSize(new Dimension(jtf.getWidth() , jtf.getHeight() + 10));
		jtf.setBorder(BorderFactory.createEmptyBorder());
		return jtf;
	}

}
