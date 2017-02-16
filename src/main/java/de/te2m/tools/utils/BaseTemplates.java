/*
 * BaseTemplates.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-templates project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */ 
package de.te2m.tools.utils;

/**
 * The Interface BaseTemplates.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public interface BaseTemplates {

    /**
     * The Constant TEMPLATE_MODEL_CLASS.
     */
    public static final String TEMPLATE_MODEL_CLASS = "/templates/model/java/class.ftl";

    /**
     * The Constant TEMPLATE_MODEL_INTERFACE.
     */
    public static final String TEMPLATE_MODEL_INTERFACE = "/templates/model/java/interface.ftl";

    /**
     * The Constant TEMPLATE_MODEL_POM.
     */
    public static final String TEMPLATE_MODEL_POM = "/templates/misc/maven/pom/jaxws/pom.ftl";

    /**
     * The Constant TEMPLATE_MODEL_PACKAGE_INFO.
     */
    public static final String TEMPLATE_MODEL_PACKAGE_INFO = "/templates/model/java/package-info.ftl";

    /**
     * The Constant TEMPLATE_MODEL_JAXWS.
     */
    public static final String TEMPLATE_MODEL_JAXWS = "/templates/model/java/jaxws/jaxwsbean.ftl";

    /**
     * The Constant TEMPLATE_MODEL_JAXRS.
     */
    public static final String TEMPLATE_MODEL_JAXRS = "/templates/model/java/jaxrs/jaxrsbean.ftl";

    /**
     * The Constant TEMPLATE_MODEL_JSF_FACES_CFG.
     */
    public static final String TEMPLATE_MODEL_JSF_FACES_CFG = "/templates/model/jsf/faces-config.ftl";

    /**
     * The Constant TEMPLATE_BO_DOC.
     */
    public static final String TEMPLATE_BO_DOC = "/templates/misc/doc/bo_html.ftl";

    /**
     * The Constant TEMPLATE_SERVICE_DOC_W_ESTIMATION.
     */
    public static final String TEMPLATE_SERVICE_DOC_W_ESTIMATION = "/templates/misc/doc/service_tasks_html.ftl";

    /**
     * The Constant TEMPLATE_MAVEN_SERVICE_SITE_XML.
     */
    public static final String TEMPLATE_MAVEN_SERVICE_SITE_XML = "/templates/misc/maven/site/site_xml.ftl";

    /**
     * The Constant TEMPLATE_MAVEN_POM_REST_SERVICE.
     */
    public static final String TEMPLATE_MAVEN_POM_REST_SERVICE = "/templates/misc/maven/pom/jaxrs/service_pom.ftl";

}
