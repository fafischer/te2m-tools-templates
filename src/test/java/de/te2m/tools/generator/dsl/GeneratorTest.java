/*
* GeneratorTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-templates project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.dsl;

import de.te2m.report.api.model.GeneratorUtils;
import de.te2m.report.api.model.Report;
import de.te2m.tools.generator.engine.impl.result.zip.ZipResultProcessor;
import de.te2m.report.api.model.GeneratorMetaData;
import de.te2m.report.api.model.GeneratorTarget;
import de.te2m.report.api.model.TemplateType;
import de.te2m.report.api.model.dev.OperationDescriptor;
import de.te2m.report.api.model.dev.java.JavaClassDescriptor;
import de.te2m.report.api.model.mgmt.TaskDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * The Class GeneratorTest.
 *
 * @author ffischer
 */
public class GeneratorTest {

    /**
     * The Constant EXPR_FILENAME_SERVICE_DOC.
     */
    public static final String EXPR_FILENAME_SERVICE_DOC = "'src/site/resources/'.concat(bo.javaName).concat('_doc.html')";

    /**
     * The Constant TEMPLATE_SERVICE_DOC_W_ESTIMATION.
     */
    public static final String TEMPLATE_SERVICE_DOC_W_ESTIMATION = "/templates/misc/doc/service_tasks_html.ftl";

    /**
     * Instantiates a new generator test.
     */
    public GeneratorTest() {
    }

    /**
     * Test of builder method, of class Generator.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Generator.Builder expResult = null;
        ZipResultProcessor zrp;
        try {
            try {
                Files.createDirectories(Paths.get("./target/temp"));
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                fail(ex.getMessage());
            }
            zrp = new ZipResultProcessor(new FileOutputStream("./target/temp/out.zip"));

            Generator.builder().
                    withResultProcessor(zrp)
                    .withDefaultProcessors()
                    .withDictionaryValue("bo", createTestObject())
                    .withDictionaryValue("info", createGeneratorInfo())
                    .withTemplate(createReport())
                    .build();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
    }

    /**
     * Creates the test object.
     *
     * @return the java class descriptor
     */
    public static JavaClassDescriptor createTestObject() {
        JavaClassDescriptor jcd = JavaClassDescriptor.builder()
                .withName("Profile")
                .withPackage("de.te2m.temp.service")
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
                .withTask(TaskDescriptor.builder()
                        .withName("Service initialization")
                        .withDescription("Create all required data in order to setup the service")
                        .withEstimation(1, TaskDescriptor.EstimationUnit.SD)
                        .build())
                .withTask(TaskDescriptor.builder()
                        .withName("Implement Service")
                        .withDescription("Implement the service. This includes the service itself but also the data mapping.")
                        .withEstimation(3, TaskDescriptor.EstimationUnit.SD)
                        .build())
                .withTask(TaskDescriptor.builder()
                        .withName("Documentattion")
                        .withDescription("Create documentation")
                        .withEstimation(0.5f, TaskDescriptor.EstimationUnit.SD)
                        .build())
                .withTask(TaskDescriptor.builder()
                        .withName("Test coverage")
                        .withDescription("Create automated tests")
                        .withEstimation(0.5f, TaskDescriptor.EstimationUnit.SD)
                        .build())
                .build();

        return jcd;
    }

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
     * Creates the report.
     *
     * @return the de.te2m.tools.generator.model. report
     */
    public static Report createReport() {
        Report r = new Report();
        r.setName("ProjectSpec");
        r.setDescription("Simple project specification for an service project");
        r.setCopyrightInfo("(c) 2014, 2015 te2m.de");

        GeneratorTarget t1 = new GeneratorTarget();
        t1.setTemplateType(TemplateType.FREEMARKER.toString());
        t1.setTemplate(GeneratorUtils.readString(GeneratorTest.class.getResourceAsStream(TEMPLATE_SERVICE_DOC_W_ESTIMATION)));
        t1.setName(EXPR_FILENAME_SERVICE_DOC);
        r.addTarget(t1);
        return r;
    }
}
