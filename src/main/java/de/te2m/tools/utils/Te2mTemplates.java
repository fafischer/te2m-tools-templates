/*
 * Te2mTemplates.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-templates project which is a sub project of temtools
 * (http://temtools.sf.net).
 * 
 */
package de.te2m.tools.utils;

/**
 * The Interface Te2mTemplates.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public interface Te2mTemplates extends BaseTemplates {

    /**
     * The Constant TEMPLATE_MODEL_TE2M_ENTITY_CLASS.
     */
    public static final String TEMPLATE_MODEL_TE2M_ENTITY_CLASS = "/templates/model/te2m/java/entity.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_ENTITY_CLASS_TEST.
     */
    public static final String TEMPLATE_MODEL_TE2M_ENTITY_CLASS_TEST = "/templates/model/te2m/java/test/entity.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_FACADE.
     */
    public static final String TEMPLATE_MODEL_TE2M_FACADE = "/templates/model/te2m/java/facade.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_FACADE_TEST.
     */
    public static final String TEMPLATE_MODEL_TE2M_FACADE_TEST = "/templates/model/te2m/java/test/facade.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_CONTROLLER.
     */
    public static final String TEMPLATE_MODEL_TE2M_CONTROLLER = "/templates/model/te2m/java/controller.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_MAVEN_POM_API.
     */
    public static final String TEMPLATE_MODEL_TE2M_MAVEN_POM_API = "/templates/model/te2m/maven/api-pom.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_MAVEN_POM_MASTER.
     */
    public static final String TEMPLATE_MODEL_TE2M_MAVEN_POM_MASTER = "/templates/model/te2m/maven/master-pom.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_MAVEN_POM_COMMON.
     */
    public static final String TEMPLATE_MODEL_TE2M_MAVEN_POM_COMMON = "/templates/model/te2m/maven/common-pom.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_MAVEN_POM_MGMT.
     */
    public static final String TEMPLATE_MODEL_TE2M_MAVEN_POM_MGMT = "/templates/model/te2m/maven/mgmt-pom.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_MAVEN_POM_REST.
     */
    public static final String TEMPLATE_MODEL_TE2M_MAVEN_POM_REST = "/templates/model/te2m/maven/rest-pom.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_JSF_VIEW.
     */
    public static final String TEMPLATE_MODEL_TE2M_JSF_VIEW = "/templates/model/te2m/jsf/View.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_JSF_CREATE.
     */
    public static final String TEMPLATE_MODEL_TE2M_JSF_CREATE = "/templates/model/te2m/jsf/Create.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_JSF_EDIT.
     */
    public static final String TEMPLATE_MODEL_TE2M_JSF_EDIT = "/templates/model/te2m/jsf/Edit.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_JSF_LIST.
     */
    public static final String TEMPLATE_MODEL_TE2M_JSF_LIST = "/templates/model/te2m/jsf/List.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_JSF_CC_SIMPLE_VIEW.
     */
    public static final String TEMPLATE_MODEL_TE2M_JSF_CC_SIMPLE_VIEW = "/templates/model/te2m/jsf/cc.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_JSF_CC_TAGLIB.
     */
    public static final String TEMPLATE_MODEL_TE2M_JSF_CC_TAGLIB = "/templates/model/te2m/jsf/cc-taglib.ftl";

    /**
     * The Constant TEMPLATE_MODEL_TE2M_JSF_RESOURCE_BUNDLE.
     */
    public static final String TEMPLATE_MODEL_TE2M_JSF_RESOURCE_BUNDLE = "/templates/model/te2m/jsf/bundle.ftl";

    /**
     * The Constant KEY_SUB_PROJECT_NAME.
     */
    public static final String KEY_SUB_PROJECT_NAME = "SUB_PROJECT_NAME";
    
    /**
     * The Constant PRJ_API.
     */
    public static final String PRJ_API = "'api'";
    
    /**
     * The Constant PRJ_JSF_COMMON.
     */
    public static final String PRJ_JSF_COMMON = "'common'";
    
    /**
     * The Constant PRJ_JSF_MGMT.
     */
    public static final String PRJ_JSF_MGMT = "'mgmt'";
}
