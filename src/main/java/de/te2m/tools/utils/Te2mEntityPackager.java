/*
 * Te2mEntityPackager.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-templates project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.utils;

import de.te2m.report.api.model.GeneratorUtils;
import de.te2m.tools.generator.engine.SimpleGenerator;
import de.te2m.tools.generator.engine.impl.freemarker.FreeMarkerProcessor;
import de.te2m.tools.generator.engine.impl.result.zip.ZipResultProcessor;
import de.te2m.report.api.model.GeneratorMetaData;
import de.te2m.report.api.model.GeneratorTarget;
import de.te2m.report.api.model.Report;
import de.te2m.report.api.model.TemplateType;
import de.te2m.report.api.model.dev.java.JavaClassDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * The Class ProjectSpecPackager.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class Te2mEntityPackager implements Te2mTemplates {

    /**
     * The Constant EXPR_FILENAME_JAVA_MAIN_ENTITY.
     */
    public static final String EXPR_FILENAME_JAVA_MAIN_ENTITY = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/java/').concat(bo.packageName).replace('.','/').concat('/entity/').concat(bo.javaName).concat('.java')";

    /**
     * The Constant EXPR_FILENAME_JAVA_TEST_ENTITY.
     */
    public static final String EXPR_FILENAME_JAVA_TEST_ENTITY = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/test/java/').concat(bo.packageName).replace('.','/').concat('/entity/Simple').concat(bo.javaName).concat('PersistenceTest.java')";

    /**
     * The Constant EXPR_FILENAME_JAVA_MAIN_FACADE.
     */
    public static final String EXPR_FILENAME_JAVA_MAIN_FACADE = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/java/').concat(bo.packageName).replace('.','/').concat('/boundary/').concat(bo.javaName).concat('Facade.java')";

    /**
     * The Constant EXPR_FILENAME_JAVA_TEST_FACADE.
     */
    public static final String EXPR_FILENAME_JAVA_TEST_FACADE = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/test/java/').concat(bo.packageName).replace('.','/').concat('/boundary/').concat(bo.javaName).concat('FacadeTest.java')";

    /**
     * The Constant EXPR_FILENAME_JAVA_MAIN_CONTROLLER.
     */
    public static final String EXPR_FILENAME_JAVA_MAIN_CONTROLLER = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/java/').concat(bo.packageName).replace('.','/').concat('/controller/').concat(bo.javaName).concat('Controller.java')";

    /**
     * The Constant EXPR_FILENAME_JSF_COMPONENT_SIMPLE_VIEW.
     */
    public static final String EXPR_FILENAME_JSF_COMPONENT_SIMPLE_VIEW = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/resources/META-INF/resources/components/').concat(bo.javaName).concat('/View.xhtml')";

    /**
     * The Constant EXPR_FILENAME_JSF_COMPONENT_TAGLIB.
     */
    public static final String EXPR_FILENAME_JSF_COMPONENT_TAGLIB = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/resources/META-INF/').concat(bo.javaName).concat('.taglib.xml')";

    /**
     * The Constant EXPR_FILENAME_JSF_FACES_CONFIG.
     */
    public static final String EXPR_FILENAME_JSF_FACES_CONFIG = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/resources/META-INF/faces-config.xml')";

    /**
     * The Constant EXPR_FILENAME_JSF_PAGE_CREATE.
     */
    public static final String EXPR_FILENAME_JSF_PAGE_CREATE = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/resources/META-INF/resources/pages/').concat(bo.javaName).concat('/Create.xhtml')";

    /**
     * The Constant EXPR_FILENAME_JSF_PAGE_EDIT.
     */
    public static final String EXPR_FILENAME_JSF_PAGE_EDIT = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/resources/META-INF/resources/pages/').concat(bo.javaName).concat('/Edit.xhtml')";

    /**
     * The Constant EXPR_FILENAME_JSF_PAGE_LIST.
     */
    public static final String EXPR_FILENAME_JSF_PAGE_LIST = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/resources/META-INF/resources/pages/').concat(bo.javaName).concat('/List.xhtml')";

    /**
     * The Constant EXPR_FILENAME_JSF_PAGE_VIEW.
     */
    public static final String EXPR_FILENAME_JSF_PAGE_VIEW = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/resources/META-INF/resources/pages/').concat(bo.javaName).concat('/View.xhtml')";

    /**
     * The Constant EXPR_FILENAME_JSF_RESOURCE_BUNDLE.
     */
    public static final String EXPR_FILENAME_JSF_RESOURCE_BUNDLE = "''.concat(bo.javaName).concat('/').concat(SUB_PROJECT_NAME).concat('/src/main/resources/META-INF/resources/tmp').concat(bo.javaName).concat('Bundle.properties')";

    /**
     * The Constant EXPR_FILENAME_MAVEN_POM_API.
     */
    public static final String EXPR_FILENAME_MAVEN_POM_API = "''.concat(bo.javaName).concat('/api/pom.xml')";

    /**
     * The Constant EXPR_FILENAME_MAVEN_POM_COMMON.
     */
    public static final String EXPR_FILENAME_MAVEN_POM_COMMON = "''.concat(bo.javaName).concat('/common/pom.xml')";

    /**
     * The Constant EXPR_FILENAME_MAVEN_POM_MASTER.
     */
    public static final String EXPR_FILENAME_MAVEN_POM_MASTER = "''.concat(bo.javaName).concat('/pom.xml')";

    /**
     * The Constant EXPR_FILENAME_MAVEN_POM_MGMT.
     */
    public static final String EXPR_FILENAME_MAVEN_POM_MGMT = "''.concat(bo.javaName).concat('/mgmt/pom.xml')";

    /**
     * The Constant EXPR_FILENAME_MAVEN_POM_REST.
     */
    public static final String EXPR_FILENAME_MAVEN_POM_REST = "''.concat(bo.javaName).concat('/rest/pom.xml')";

    /**
     * Creates the generator info.
     *
     * @return the generator meta data
     */
    public static GeneratorMetaData createGeneratorInfo() {
        GeneratorMetaData info = new GeneratorMetaData();
        info.setCopyrightInfo("(c) te2m.de 2013");
        info.setAuthor("frank@te2m.de");
        info.setVersion("1.0");
        return info;
    }

    /**
     * Creates the test object.
     *
     * @return the java class descriptor
     */
    public static JavaClassDescriptor createTestObject() {
        JavaClassDescriptor jcd = JavaClassDescriptor.builder()
                .withName("Persona")
                .withPackage("de.te2m.services.project")
                .withDescription("Persona")
                .withMember(JavaClassDescriptor.builder()
                        .withName("name")
                        .withDescription("The name of the persona")
                        .withType("String")
                        .withPackage("java.lang")
                        .build())
                .withMember(JavaClassDescriptor.builder()
                        .withName("description")
                        .withDescription("the description")
                        .withType("String")
                        .withPackage("java.lang")
                        .build())
                .build();

        return jcd;
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        Te2mEntityPackager spp = new Te2mEntityPackager();
        spp.process();
    }

    /**
     * Process.
     */
    public void process() {
        GeneratorMetaData info = createGeneratorInfo();
        JavaClassDescriptor jcd = createTestObject();
        File file = new File("target/temp/Te2mWeb.xml");
        FileOutputStream fos = null;

        try {

            fos = new FileOutputStream(file);

            Report newReport = Report
                    .builder()
                    .withName("te2m Web Entity")
                    .withDescription("Entity including JSF stuff")
                    .withCopyrightInfo("(c) 2013 - 2015 te2m.de")
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_JSF_CREATE)))
                            .withName(EXPR_FILENAME_JSF_PAGE_CREATE)
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_JSF_COMMON)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_JSF_EDIT)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_JSF_COMMON)
                            .withName(EXPR_FILENAME_JSF_PAGE_EDIT)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_JSF_LIST)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_JSF_COMMON)
                            .withName(EXPR_FILENAME_JSF_PAGE_LIST)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_JSF_VIEW)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_JSF_COMMON)
                            .withName(
                                    EXPR_FILENAME_JSF_PAGE_VIEW)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_JSF_FACES_CFG)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_JSF_COMMON)
                            .withName(EXPR_FILENAME_JSF_FACES_CONFIG)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(TEMPLATE_MODEL_TE2M_JSF_CC_SIMPLE_VIEW)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_JSF_COMMON)
                            .withName(
                                    EXPR_FILENAME_JSF_COMPONENT_SIMPLE_VIEW)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_JSF_RESOURCE_BUNDLE)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_JSF_MGMT)
                            .withName(EXPR_FILENAME_JSF_RESOURCE_BUNDLE)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_ENTITY_CLASS)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_API)
                            .withName(EXPR_FILENAME_JAVA_MAIN_ENTITY)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_FACADE)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_API)
                            .withName(EXPR_FILENAME_JAVA_MAIN_FACADE)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_JSF_CC_TAGLIB)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_JSF_COMMON)
                            .withName(
                                    EXPR_FILENAME_JSF_COMPONENT_TAGLIB)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_ENTITY_CLASS_TEST)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_API)
                            .withName(EXPR_FILENAME_JAVA_TEST_ENTITY)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_FACADE_TEST)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_API)
                            .withName(EXPR_FILENAME_JAVA_TEST_FACADE)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_CONTROLLER)))
                            .withConfig(KEY_SUB_PROJECT_NAME, PRJ_JSF_COMMON)
                            .withName(EXPR_FILENAME_JAVA_MAIN_CONTROLLER)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_MAVEN_POM_MASTER)))
                            .withName(EXPR_FILENAME_MAVEN_POM_MASTER)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_MAVEN_POM_API)))
                            .withName(EXPR_FILENAME_MAVEN_POM_API)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_MAVEN_POM_COMMON)))
                            .withName(EXPR_FILENAME_MAVEN_POM_COMMON)
                            .build())
                    .withTarget(GeneratorTarget
                            .builder()
                            .withTemplateType(
                                    TemplateType.FREEMARKER.toString())
                            .withTemplate(
                                    GeneratorUtils
                                    .readString(this
                                            .getClass()
                                            .getResourceAsStream(
                                                    TEMPLATE_MODEL_TE2M_MAVEN_POM_MGMT)))
                            .withName(EXPR_FILENAME_MAVEN_POM_MGMT)
                            .build())
                    .build();

            JAXBContext context;

            context = JAXBContext.newInstance(Report.class);

            //StringWriter sw = new StringWriter();
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(newReport, fos);

            FileOutputStream zfos = null;
            try {
                zfos = new FileOutputStream("target/temp/persona.zip");
                ZipResultProcessor zrp = new ZipResultProcessor(zfos);
                FreeMarkerProcessor fmp = new FreeMarkerProcessor();
                SimpleGenerator g = new SimpleGenerator(newReport, fmp, zrp);
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("bo", jcd);
                params.put("info", info);
                g.generate(params);
                fos.flush();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass()
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Te2mEntityPackager.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (null != fos) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Te2mEntityPackager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
