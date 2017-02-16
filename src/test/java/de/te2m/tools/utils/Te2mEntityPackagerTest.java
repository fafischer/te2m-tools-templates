/*
 * Te2mEntityPackagerTest.java
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
import static de.te2m.tools.utils.Te2mTemplates.KEY_SUB_PROJECT_NAME;
import static de.te2m.tools.utils.Te2mTemplates.PRJ_API;
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
public class Te2mEntityPackagerTest {

    /**
     * Test for evaluate f name service component.
     */
    @Test
    public void testEvaluateFNameCreate() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("bo", Te2mEntityPackager.createTestObject());
        params.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        String result = (String) ELUtils.evalExpression(params, Te2mEntityPackager.EXPR_FILENAME_JSF_PAGE_CREATE);
        System.out.println(result);
        assertNotNull(result);
    }

    /**
     * Test for evaluate f name edit.
     */
    @Test
    public void testEvaluateFNameEdit() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("bo", Te2mEntityPackager.createTestObject());
        params.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        String result = (String) ELUtils.evalExpression(params, Te2mEntityPackager.EXPR_FILENAME_JSF_PAGE_EDIT);
        System.out.println(result);
        assertNotNull(result);
    }

    /**
     * Test for evaluate f name list.
     */
    @Test
    public void testEvaluateFNameList() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("bo", Te2mEntityPackager.createTestObject());
        params.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        String result = (String) ELUtils.evalExpression(params, Te2mEntityPackager.EXPR_FILENAME_JSF_PAGE_LIST);
        System.out.println(result);
        assertNotNull(result);
    }

    /**
     * Test for evaluate f name view.
     */
    @Test
    public void testEvaluateFNameView() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("bo", Te2mEntityPackager.createTestObject());
        params.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        String result = (String) ELUtils.evalExpression(params, Te2mEntityPackager.EXPR_FILENAME_JSF_PAGE_VIEW);
        System.out.println(result);
        assertNotNull(result);
    }

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
        Te2mEntityPackager instance = new Te2mEntityPackager();
        instance.process();
    }

}
