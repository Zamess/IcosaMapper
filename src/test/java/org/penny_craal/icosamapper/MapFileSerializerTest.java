package org.penny_craal.icosamapper;

import java.io.File;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import org.penny_craal.icosamapper.map.GreyscaleLR;
import org.penny_craal.icosamapper.map.InvalidPathException;
import org.penny_craal.icosamapper.map.Layer;
import org.penny_craal.icosamapper.map.Map;
import org.penny_craal.icosamapper.map.Path;




/**
 *
 * @author Ville Jokela
 */
public class MapFileSerializerTest {
    private Map map;
    
    public MapFileSerializerTest() {
    }
    
    @Before
    public void setUp() throws InvalidPathException {
        byte[] ap = {1};
        map = new Map();
        map.addLayer(new Layer("test-layer", new GreyscaleLR(), (byte) 0));
        map.divide("test-layer", new Path(ap));
    }
    
    @After
    public void tearDown() {
        map = null;
    }

    /**
     * Test of save method, of class MapSerializer.
     */
    @Test
    public void save() throws Exception {
        File f = File.createTempFile("MapSerializerTest.save", ".imm");
        f.deleteOnExit();
        MapFileSerializer ms = new MapFileSerializer(f);
        ms.save(map);
    }

    /**
     * Test of load method, of class MapSerializer.
     */
    @Test
    public void load() throws Exception {
        File f = File.createTempFile("MapSerializerTest.load", ".imm");
        f.deleteOnExit();
        MapFileSerializer ms = new MapFileSerializer(f);
        ms.save(map);
        Map map2 = ms.load();
        assertEquals(map2, map);
    }
}
