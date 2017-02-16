/*
 * Te2mTemplateTest.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-templates project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator;

import de.te2m.report.api.model.GeneratorUtils;
import de.te2m.tools.generator.engine.impl.freemarker.FreemarkerUtils;
import de.te2m.report.api.model.GeneratorMetaData;
import de.te2m.report.api.model.dev.java.JavaClassDescriptor;
import de.te2m.tools.utils.Te2mTemplates;
import java.io.InputStream;
import java.util.HashMap;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains several tests for validating the templates (basic tests
 * only).
 *
 * @author ffi
 */
public class Te2mTemplateTest implements Te2mTemplates {

    /**
     * The Constant AUTHOR_STRING.
     */
    public static final String AUTHOR_STRING = "user@domain.tld";

    /**
     * The info.
     */
    private GeneratorMetaData info;

    /**
     * The bo.
     */
    private JavaClassDescriptor bo;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        this.info = new GeneratorMetaData();
        info.setAuthor("user@domain.tld");
        info.setCopyrightInfo("(c) Project 2013");
        info.setVersion("1.0.0.0");
        this.bo = JavaClassDescriptor.builder()
                .withName("Profile")
                .withPackage("de.te2m.temp.entity")
                .withDescription("te2m Entity Test")
                .withProperty("jaxrs.path", "/api/profile")
                .withMember(JavaClassDescriptor.builder()
                        .withName("attribute # 1")
                        .withDescription("a funny Description")
                        .withType("String")
                        .withPackage("java.lang")
                        .build())
                .withMember(JavaClassDescriptor.builder()
                        .withName("make some funny noise")
                        .withDescription("attribute containing how to make some funny noise")
                        .withType("String")
                        .withPackage("java.lang")
                        .build())
                .build();

    }

    /**
     * Test of generate method, of class FreemarkerGenerator.
     */
    @Test
    public void testGenerateEntity() {
        System.out.println("generateEntity");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_ENTITY_CLASS);

        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate entity test.
     */
    @Test
    public void testGenerateEntityTest() {
        System.out.println("generateEntityTest");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_ENTITY_CLASS_TEST);

        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test of generate method, of class FreemarkerGenerator.
     */
    @Test
    public void testGenerateFacade() {
        System.out.println("generateFacade");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_FACADE);

        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate controller.
     */
    @Test
    public void testGenerateController() {
        System.out.println("generateController");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_CONTROLLER);

        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate jsf view page.
     */
    @Test
    public void testGenerateJSFViewPage() {
        System.out.println("testGenerateJSFViewPage");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_JSF_VIEW);

        String expResult = "http://primefaces.org/ui";
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate jsf edit page.
     */
    @Test
    public void testGenerateJSFEditPage() {
        System.out.println("testGenerateJSFEditPage");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_JSF_EDIT);

        String expResult = "http://primefaces.org/ui";
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate jsf create page.
     */
    @Test
    public void testGenerateJSFCreatePage() {
        System.out.println("testGenerateJSFCreatePage");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_JSF_CREATE);

        String expResult = "http://primefaces.org/ui";
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate jsf list page.
     */
    @Test
    public void testGenerateJSFListPage() {
        System.out.println("testGenerateJSFListPage");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_JSF_LIST);

        String expResult = "http://primefaces.org/ui";
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate jsfrb.
     */
    @Test
    public void testGenerateJSFRB() {
        System.out.println("testGenerateJSFRB");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_JSF_RESOURCE_BUNDLE);

        String expResult = bo.getJavaName();
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate jsf view cc.
     */
    @Test
    public void testGenerateJSFViewCC() {
        System.out.println("testGenerateJSFViewCC");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_JSF_CC_SIMPLE_VIEW);

        String expResult = bo.getJavaName();
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate jsfcc tld.
     */
    @Test
    public void testGenerateJSFCCTld() {
        System.out.println("testGenerateJSFCCTld");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put(KEY_SUB_PROJECT_NAME, PRJ_API);
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_MODEL_TE2M_JSF_CC_TAGLIB);

        String expResult = bo.getJavaName().toLowerCase();
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }
}
