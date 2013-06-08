/* IcosaMapper - an rpg map editor based on equilateral triangles that form an icosahedron
 * Copyright (C) 2013  Ville Jokela
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

package org.penny_craal.icosamapper.map;

/**
 * An ArrayTriangleContainer of size 20, that is, an icosahedron.
 * @author Ville Jokela
 */
public class ArrayIcosahedron extends ArrayTriangleContainer {
    static final int size = 20;
    
    /**
     * Constructs an ArrayIcosahedron with <code>init</code> as the initial value for all elements.
     * @param init the initial value for elements
     */
    public ArrayIcosahedron(byte init) {
        super(init);
    }
    
    @Override
    public int getSize() {
        return size;
    }
}