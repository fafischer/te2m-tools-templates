/*
 * JaxWSServiceProjectPackagerTest.java
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
 * The Class JaxWSServiceProjectPackagerTest.
 *
 * @author ffischer
 */
public class JaxWSServiceProjectPackagerTest {

    /**
     * Test of process method, of class ServiceProjectPackager.
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
        JaxWSServiceProjectPackager instance = new JaxWSServiceProjectPackager();
        instance.process();
    }

    /**
     * Test for evaluate f name service component.
     */
    @Test
    public void testEvaluateFNameServiceProject() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("bo", JaxWSServiceProjectPackager.createTestObject());
        String result = (String) ELUtils.evalExpression(params, JaxWSServiceProjectPackager.EXPR_FILENAME_JAXWS_BEAN);
        System.out.println(result);
        assertNotNull(result);
    }

    /**
     * Test for evaluate f name service component.
     */
    @Test
    public void testEvaluateFNamePackageInfo() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("bo", JaxWSServiceProjectPackager.createTestObject());
        String result = (String) ELUtils.evalExpression(params, JaxWSServiceProjectPackager.EXPR_FILENAME_PACKAGE_INFO);
        System.out.println(result);
        assertNotNull(result);
    }

}
