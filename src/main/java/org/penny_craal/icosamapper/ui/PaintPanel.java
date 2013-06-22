/* IcosaMapper - an rpg map editor based on equilateral triangles that form an icosahedron
 * Copyright (C) 2013  Ville Jokela, James Pearce
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * contact me <ville.jokela@penny-craal.org>
 */

package org.penny_craal.icosamapper.ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.penny_craal.icosamapper.map.GreyscaleLR;
import org.penny_craal.icosamapper.ui.events.IMEvent;
import org.penny_craal.icosamapper.ui.events.IMEventHelper;
import org.penny_craal.icosamapper.ui.events.IMEventListener;
import org.penny_craal.icosamapper.ui.events.IMEventSource;

import static org.penny_craal.icosamapper.map.Constants.*;
/**
 *
 * @author Ville Jokela
 */
@SuppressWarnings("serial")
public class PaintPanel extends JPanel implements IMEventSource {
    private JPanel opSize;
    private JSpinner opSizeSpinner;
    private PaintBar paintBar;
    private ColourPicker colourPicker;
    
    public PaintPanel() {
        Listener listener = new Listener();
        opSize = new JPanel();
        opSize.add(new JLabel("Operating size"));
        opSizeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        opSizeSpinner.addChangeListener(listener);
        opSize.add(opSizeSpinner);
        paintBar = new PaintBar();
        paintBar.addChangeListener(listener);
        colourPicker = new ColourPicker(new GreyscaleLR(), (byte) MIN_VALUE);
        colourPicker.addChangeListener(listener);
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Paint"));
        setLayout(new BorderLayout());
        
        add(colourPicker,   BorderLayout.CENTER);
        add(paintBar,       BorderLayout.LINE_END);
        add(opSize,         BorderLayout.PAGE_END);
    }
    
    public byte getValue() {
        return colourPicker.getValue();
    }
    
    public PaintBar.Tool getTool() {
        return paintBar.getTool();
    }
    
      ///////////////////
     // Listener crap //
    ///////////////////
    
    @Override
    public void addIMEventListener(IMEventListener imel) {
        IMEventHelper.addListener(listenerList, imel);
    }
    
    @Override
    public void removeIMEventListener(IMEventListener imel) {
        IMEventHelper.removeListener(listenerList, imel);
    }
    
    protected void fireEvent(IMEvent ime) {
        IMEventHelper.fireEvent(listenerList, ime);
    }
    
    private class Listener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent ce) {
            if (ce.getSource() == opSizeSpinner) {
                fireEvent(new IMEvent(this, IMEvent.Type.OPSIZE_SELECTED));
            } else if (ce.getSource() == paintBar) {
                fireEvent(new IMEvent(this, IMEvent.Type.TOOL_SELECTED));
            } else if (ce.getSource() == colourPicker) {
                fireEvent(new IMEvent(this, IMEvent.Type.COLOUR_SELECTED));
            } else {
                throw new RuntimeException("Unrecognized event source");
            }
        }
    }
}