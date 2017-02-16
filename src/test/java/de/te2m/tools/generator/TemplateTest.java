/*
 * TemplateTest.java
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
import de.te2m.report.api.model.GeneratorTarget;
import de.te2m.report.api.model.Report;
import de.te2m.report.api.model.TemplateType;
import de.te2m.report.api.model.dev.OperationDescriptor;
import de.te2m.report.api.model.dev.java.JavaClassDescriptor;
import de.te2m.tools.templates.pom.PomInfo;
import de.te2m.tools.utils.BaseTemplates;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains several tests for validating the templates (basic tests
 * only).
 *
 * @author ffi
 */
public class TemplateTest {

    /**
     * The Constant AUTHOR_STRING.
     */
    public static final String AUTHOR_STRING = "user@domain.tld";

    /**
     * The bo.
     */
    private JavaClassDescriptor bo;

    private JavaClassDescriptor boXML;
    /**
     * The info.
     */
    private GeneratorMetaData info;

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
                .withPackage("de.te2m.temp.service")
                .withType("ProfileBO")
                .withDescription("JAX-WS Service Test")
                .withProperty("jaxrs.path", "/api/profile")
                .withOperation(OperationDescriptor.builder()
                        .withName("validateSession")
                        .withDescription("Validate something")
                        .withParameter(JavaClassDescriptor.builder()
                                .withName("token")
                                .withDescription("some token")
                                .withType("String")
                                .withPackage("java.lang")
                                .build())
                        .withReturnValue(JavaClassDescriptor.builder()
                                .withName("ID")
                                .withDescription("An ID")
                                .withType("String")
                                .withPackage("java.lang")
                                .build())
                        .withProperty("jaxrs.path", "/api/profile")
                        .withProperty("jaxrs.produces", "application/json")
                        .withProperty("jaxrs.consumes", "application/json")
                        .withProperty("jaxrs.method", "@POST")
                        .build())
                .build();

        this.boXML = JavaClassDescriptor.builder()
                .withName("ProfileXML")
                .withPackage("de.te2m.temp.service")
                .withDescription("JAX-WS Service Test")
                .withProperty("jaxb.generate", "true")
                .withType("SuperProfileXML")
                .withMember(bo)
                .withOperation(OperationDescriptor.builder()
                        .withName("validateSession")
                        .withDescription("Validate something")
                        .withParameter(JavaClassDescriptor.builder()
                                .withName("token")
                                .withDescription("some token")
                                .withType("String")
                                .withPackage("java.lang")
                                .build())
                        .withReturnValue(JavaClassDescriptor.builder()
                                .withName("ID")
                                .withDescription("An ID")
                                .withType("String")
                                .withPackage("java.lang")
                                .build())
                        .build())
                .build();

    }

    /**
     * Test of generate method, of class FreemarkerGenerator.
     */
    @Test
    public void testGenerateClass() {
        System.out.println("generateClass");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);

        InputStream is = this.getClass().getResourceAsStream("/templates/model/java/class.ftl");

        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    @Test
    public void testGenerateClassWithXML() {
        System.out.println("generateClassXML");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", boXML);

        InputStream is = this.getClass().getResourceAsStream("/templates/model/java/class.ftl");

        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
        assertTrue(result.contains("@XmlRootElement"));
        assertTrue(result.contains("@XmlElement"));
    }

    @Test
    public void testEmptyProps() {
        System.out.println("generateEmptyProps");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);

        JavaClassDescriptor jcd = JavaClassDescriptor.builder()
                .withName("Test")
                .withPackage("de.te2m.temp.service")
                .withType("TestBO")
                .withDescription("JAX-WS Service Test")
                .withProperties(new Properties())
                .build();
        InputStream is = this.getClass().getResourceAsStream("/templates/model/java/class.ftl");
        hashMap.put("bo", jcd);
        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    @Test
    public void testNullProps() {
        System.out.println("generateNullProps");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);

        JavaClassDescriptor jcd = JavaClassDescriptor.builder()
                .withName("Test")
                .withPackage("de.te2m.temp.service")
                .withType("TestBO")
                .withDescription("JAX-WS Service Test")
                .withProperties(null)
                .build();
        InputStream is = this.getClass().getResourceAsStream("/templates/model/java/class.ftl");
        hashMap.put("bo", jcd);
        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate doc html.
     */
    @Test
    public void testGenerateDocHTML() {
        System.out.println("generateDocHTML");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);

        InputStream is = this.getClass().getResourceAsStream(BaseTemplates.TEMPLATE_BO_DOC);

        String expResult = "<html>";
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate faces cfg.
     */
    @Test
    public void testGenerateFacesCfg() {
        System.out.println("GenerateFacesCfg");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);

        InputStream is = this.getClass().getResourceAsStream(BaseTemplates.TEMPLATE_MODEL_JSF_FACES_CFG);

        String expResult = "faces-config";
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test of generate method, of class FreemarkerGenerator.
     */
    @Test
    public void testGenerateInterface() {
        System.out.println("generateInterface");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        InputStream is = this.getClass().getResourceAsStream("/templates/model/java/interface.ftl");

        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate jax ws bean.
     */
    @Test
    public void testGenerateJaxWsBean() {
        System.out.println("generateJaxWsBean");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);

        InputStream is = this.getClass().getResourceAsStream("/templates/model/java/jaxws/jaxwsbean.ftl");

        assertNotNull(is);

        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test of POM template.
     */
    @Test
    public void testGeneratePOM() {
        System.out.println("generatePOM");
        HashMap hashMap = new HashMap();

        PomInfo pi = new PomInfo();
        pi.setArtifactID("test");
        pi.setGroupID("test-test");
        pi.setPackageName("a.b.c.d.e.f");
        pi.setUrl("http://domain.tld");
        pi.setName("Yeah-Name");
        hashMap.put("info", info);
        hashMap.put("bo", bo);
        hashMap.put("pomInfo", pi);
        InputStream is = this.getClass().getResourceAsStream("/templates/misc/maven/pom/jaxws/pom.ftl");

        String expResult = "<url>http://domain.tld</url>";
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test of POM template.
     */
    @Test
    public void testGeneratePOMReport() {
        System.out.println("generatePOM");
        try {
            Files.createDirectories(Paths.get("./target/temp"));
        } catch (IOException ex) {
            Logger.getLogger(TemplateTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        File file = new File("./target/temp/MVN-JAXWS-POM.xml");
        FileOutputStream fos = null;

        try {

            fos = new FileOutputStream(file);

            Report r = new Report();
            r.setName("MVN-JAXWS-POM");
            r.setDescription("Maven project for JAX-WS based webservice clients.");
            r.setCopyrightInfo("(c) 2013 te2m.de");

            GeneratorTarget t1 = new GeneratorTarget();
            t1.setTemplateType(TemplateType.FREEMARKER.toString());
            t1.setTemplate(GeneratorUtils.readString(this.getClass().getResourceAsStream("/templates/misc/maven/pom/jaxws/pom.ftl")));
            t1.setName("'/pom.xml'");
            r.addTarget(t1);

            t1 = new GeneratorTarget();
            t1.setTemplateType(TemplateType.FREEMARKER.toString());
            t1.setTemplate(GeneratorUtils.readString(this.getClass().getResourceAsStream("/templates/model/java/interface.ftl")));
            t1.setName("'/src/main/java/'.concat('').concat('').concat('.java')");
            r.addTarget(t1);

            t1 = new GeneratorTarget();
            t1.setTemplateType(TemplateType.FREEMARKER.toString());
            t1.setTemplate(GeneratorUtils.readString(this.getClass().getResourceAsStream("/templates/model/java/class.ftl")));
            t1.setName("'/src/main/java/'.concat(current.packageToPath).concat(current.nameToFileName).concat('.java')");
            r.addTarget(t1);

            t1 = new GeneratorTarget();
            t1.setTemplateType(TemplateType.RAW_CONTENT.toString());
            t1.setForEach("ContentDescriptor.content");
            t1.setName("/src/main/wsdl/'.concat(ContentDescriptor.fileName)");
            r.addTarget(t1);

            JAXBContext context;

            context = JAXBContext.newInstance(Report.class);

            //StringWriter sw = new StringWriter();
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(r, fos);

        } catch (Exception ex) {
            fail(ex.getMessage());

        } finally {
            if (null != fos) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(TemplateTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    /**
     * Test of generate method, of class FreemarkerGenerator.
     */
    @Test
    public void testGeneratePackageInfo() {
        System.out.println("generatePackageInfo");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);

        InputStream is = this.getClass().getResourceAsStream("/templates/model/java/package-info.ftl");

        String expResult = "@author " + AUTHOR_STRING;
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    /**
     * Test for generate site xml.
     */
    @Test
    public void testGenerateSiteXML() {
        System.out.println("generateSiteXML");
        HashMap hashMap = new HashMap();
        hashMap.put("info", info);
        hashMap.put("bo", bo);

        InputStream is = this.getClass().getResourceAsStream(BaseTemplates.TEMPLATE_MAVEN_SERVICE_SITE_XML);

        String expResult = "</project>";
        String result = FreemarkerUtils.generate(hashMap, GeneratorUtils.readString(is));

        System.out.println(result);
        assertTrue(result.contains(expResult));
        assertTrue(result.contains(bo.getJavaName()));
    }
}
