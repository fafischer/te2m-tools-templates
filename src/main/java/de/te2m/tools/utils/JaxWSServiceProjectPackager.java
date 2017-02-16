/*
* JaxWSServiceProjectPackager.java
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
import de.te2m.report.api.model.dev.OperationDescriptor;
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
 * The Class JaxWSServiceProjectPackager.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class JaxWSServiceProjectPackager implements BaseTemplates {

    /**
     * The Constant EXPR_FILENAME_JAXWS_BEAN.
     */
    public static final String EXPR_FILENAME_JAXWS_BEAN = "'src/main/java/'.concat(bo.packageName).replace('.','/').concat('/').concat(bo.javaName).concat('.java')";

    /**
     * The Constant EXPR_FILENAME_PACKAGE_INFO.
     */
    public static final String EXPR_FILENAME_PACKAGE_INFO = "'src/main/java/'.concat(bo.packageName).replace('.','/').concat('/').concat('package-info.java')";

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        JaxWSServiceProjectPackager spp = new JaxWSServiceProjectPackager();
        spp.process();
    }

    /**
     * Process.
     */
    public void process() {
        GeneratorMetaData info = createGeneratorInfo();
        JavaClassDescriptor jcd = createTestObject();

        System.out.println("generatePOM");
        File file = new File("target/temp/Te2mJaxWSService.xml");
        FileOutputStream fos = null;

        try {

            fos = new FileOutputStream(file);

            Report r = new Report();
            r.setName("Te2mJaxWSService");
            r.setDescription("JaxWS based webservice");
            r.setCopyrightInfo("(c) 2013 te2m.de");

            GeneratorTarget t1 = new GeneratorTarget();
            t1.setTemplateType(TemplateType.FREEMARKER.toString());
            t1.setTemplate(GeneratorUtils.readString(this.getClass().getResourceAsStream(BaseTemplates.TEMPLATE_MODEL_JAXWS)));
            t1.setName(EXPR_FILENAME_JAXWS_BEAN);
            r.addTarget(t1);

            t1 = new GeneratorTarget();
            t1.setTemplateType(TemplateType.FREEMARKER.toString());
            t1.setTemplate(GeneratorUtils.readString(this.getClass().getResourceAsStream(BaseTemplates.TEMPLATE_MODEL_PACKAGE_INFO)));
            t1.setName(EXPR_FILENAME_PACKAGE_INFO);
            r.addTarget(t1);

            JAXBContext context;

            context = JAXBContext.newInstance(Report.class);

            //StringWriter sw = new StringWriter();
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(r, fos);

            FileOutputStream zfos = null;
            try {
                zfos = new FileOutputStream("target/temp/myproject.zip");
                ZipResultProcessor zrp = new ZipResultProcessor(zfos);
                FreeMarkerProcessor fmp = new FreeMarkerProcessor();
                SimpleGenerator g = new SimpleGenerator(r, fmp, zrp);
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
            Logger.getLogger(JaxWSServiceProjectPackager.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (null != fos) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(JaxWSServiceProjectPackager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

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
     * Creates the test object.
     *
     * @return the java class descriptor
     */
    public static JavaClassDescriptor createTestObject() {
        JavaClassDescriptor jcd = JavaClassDescriptor.builder()
                .withName("Profile")
                .withPackage("de.te2m.temp.service")
                .withDescription("JAX-WS Service Test")
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

        return jcd;
    }
}
