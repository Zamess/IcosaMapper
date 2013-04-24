package org.penny_craal.icosamapper.ui;

/**
 *
 * @author Ville Jokela & James Pearce
 */
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import org.penny_craal.icosamapper.map.AccessPath;
import org.penny_craal.icosamapper.map.BadPathException;
import org.penny_craal.icosamapper.map.GreyscaleLR;
import org.penny_craal.icosamapper.map.Layer;

public class UI extends JFrame {

    public UI() {
        initUI();
    }

    public final void initUI() {
        //creating the main window
        setTitle("IcosaMapper");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
         //creating menubar and menus
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu layer = new JMenu("Layers");
        JMenu view = new JMenu("View");
        //adding the menubar
        setJMenuBar(menubar);
        
        //creating components for the menus
        JMenuItem newF = new JMenuItem("New");

        JMenuItem open = new JMenuItem("Open");

        JMenuItem save = new JMenuItem("Save");

        JMenuItem saveas = new JMenuItem("Save As");

        JMenuItem quit = new JMenuItem("Exit");
        quit.setToolTipText("Exit application");
        //adding menus into menubar
        menubar.add(file);
        menubar.add(layer);
        menubar.add(view);
        //adding to the file menu components
        file.add(newF);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(quit);
        
        //Status Bar
        JPanel statusbar = new JPanel();
        statusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusbar, BorderLayout.SOUTH);
        statusbar.setLayout(new BoxLayout(statusbar, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusbar.add(statusLabel);
        
        Layer l = new Layer("test-layer", new GreyscaleLR(), (byte) 0);
        byte[] ap1 = {1};
        byte[] ap2 = {1,2};
        try {
            l.setAtPath(new AccessPath(ap1), (byte) 128);
            l.subdivide(new AccessPath(ap1));
            l.setAtPath(new AccessPath(ap2), (byte) 250);
        } catch (BadPathException ex) {
            throw new RuntimeException("OH NOES");
        }
        int renderDepth = 1;
        MapPanel map = new MapPanel(l, renderDepth);
        statusLabel.setText("Render depth: " + renderDepth);
        add(map, BorderLayout.CENTER);
        
        JPanel tools = new JPanel();
        tools.setLayout(new BorderLayout());
        add(tools, BorderLayout.WEST);
        
        JPanel paint = new JPanel();
        paint.setLayout(new BorderLayout());
        tools.add(paint, BorderLayout.NORTH);
        
        JPanel layers = new JPanel();
        layers.setLayout(new BorderLayout());
        tools.add(layers, BorderLayout.CENTER);

        //creating the toolbars
        JToolBar paintbar = new JToolBar();
        paintbar.setOrientation(JToolBar.VERTICAL);
        paint.add(paintbar, BorderLayout.EAST);
        paintbar.setFloatable(false);
        
        //giving every button meaning
        JButton neww = new JButton(new ImageIcon("gfx/new.png"));
        neww.setToolTipText("Adds a new thing");

        JButton delete = new JButton(new ImageIcon("gfx/delete.png"));
        delete.setToolTipText("Deletes a thing");

        JButton draw = new JButton(new ImageIcon("gfx/draw.png"));
        draw.setToolTipText("Draws a thing");

        JButton duplicate = new JButton(new ImageIcon("gfx/duplicate.png"));
        duplicate.setToolTipText("Dublicates a thing");

        JButton fill = new JButton(new ImageIcon("gfx/fill.png"));
        fill.setToolTipText("Fills a thing");

        JButton properties = new JButton(new ImageIcon("gfx/properties.png"));
        properties.setToolTipText("Shows the properties of a thing");

        JButton rename = new JButton(new ImageIcon("gfx/rename.png"));
        rename.setToolTipText("Renames a thing");

        JButton subdivide = new JButton(new ImageIcon("gfx/subdivide.png"));
        subdivide.setToolTipText("Subdivides a thing");

        JButton underlay = new JButton(new ImageIcon("gfx/underlay.png"));
        underlay.setToolTipText("to be added");//TODO

        JButton unite = new JButton(new ImageIcon("gfx/unite.png"));
        unite.setToolTipText("Unites a thing");

        JButton zoomin = new JButton(new ImageIcon("gfx/zoom-in.png"));
        zoomin.setToolTipText("Zooms in");

        JButton zoomout = new JButton(new ImageIcon("gfx/zoom-out.png"));
        zoomout.setToolTipText("zooms out");
        //adding all the buttons to the toolbar
        paintbar.add(draw);
        paintbar.add(fill);
        paintbar.add(subdivide);
        paintbar.add(unite);
        paintbar.add(zoomin);
        paintbar.add(zoomout);

        JToolBar layerbar = new JToolBar();
        layerbar.setOrientation(JToolBar.VERTICAL);
        layers.add(layerbar, BorderLayout.EAST);
        layerbar.setFloatable(false);
        
        JLabel layerLabel = new JLabel("Layers");
        layers.add(layerLabel, BorderLayout.NORTH);
        
        JList layerList = new JList();
        layerList.setBorder(new BevelBorder(BevelBorder.LOWERED));
        layers.add(layerList, BorderLayout.CENTER);
        
        layerbar.add(neww);
        layerbar.add(duplicate);
        layerbar.add(rename);
        layerbar.add(properties);
        layerbar.add(delete);
        layerbar.add(underlay);

        JPanel opSize = new JPanel();
        paint.add(opSize, BorderLayout.SOUTH);
        
        JLabel sizeLabel = new JLabel("Operating size");
        opSize.add(sizeLabel);
        JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));//(initial value, minimum value, maximum value, step)
        opSize.add(sizeSpinner);
        
        ColourPicker colour = new ColourPicker(new GreyscaleLR());
        paint.add(colour, BorderLayout.CENTER);
    }   
}