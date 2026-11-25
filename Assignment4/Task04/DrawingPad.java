import javax.swing.*; import java.awt.*; import java.awt.event.*; import java.util.*;
public class DrawingPad extends JPanel {
  private final java.util.List<Point> points=new ArrayList<>(); private final JLabel count=new JLabel("Clicks: 0");
  DrawingPad(){
    setPreferredSize(new Dimension(500,350));add(count);
    addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent e){points.add(e.getPoint());count.setText("Clicks: "+points.size());repaint();}});
  }
  protected void paintComponent(Graphics g){super.paintComponent(g);g.setColor(Color.BLUE);for(Point p:points)g.fillOval(p.x-4,p.y-4,8,8);}
  public static void main(String[] a){SwingUtilities.invokeLater(()->{JFrame f=new JFrame("Drawing Pad");f.add(new DrawingPad());f.pack();f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);});}
}
