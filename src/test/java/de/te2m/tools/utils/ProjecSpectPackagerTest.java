/*
 * ProjecSpectPackagerTest.java
 *   
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-templates project which is a sub project of temtools 
 * (http://temtools.sf.net).
 * 
 */
package de.te2m.tools.utils;

import de.te2m.tools.generator.dsl.GeneratorTest;
import de.te2m.tools.generator.engine.ELUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * The Class ProjecSpectPackagerTest.
 *
 * @author ffischer
 */
public class ProjecSpectPackagerTest {

    /**
     * Test of process method, of class ProjecSpectPackagerTest.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
        try {
            Files.createDirectories(Paths.get("./target/temp"));
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        ProjectSpecPackager instance = new ProjectSpecPackager();
        instance.process();
    }

    /**
     * Test for evaluate f name service component.
     */
    @Test
    public void testEvaluateFNameServiceProject() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("bo", ProjectSpecPackager.createTestObject());
        String result = (String) ELUtils.evalExpression(params, ProjectSpecPackager.EXPR_FILENAME_SERVICE_DOC);
        System.out.println(result);
        assertNotNull(result);
    }

}
