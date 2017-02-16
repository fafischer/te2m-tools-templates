/*
* PomInfo.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-templates project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.templates.pom;

/**
 * The Class PomInfo.
 *
 * @author ffischer
 */
public class PomInfo {
 
    /**
     * The artifact id.
     */
    private String artifactID;
    
    /**
     * The group id.
     */
    private String groupID;
    
    /**
     * The package name.
     */
    private String packageName;
    
    /**
     * The url.
     */
    private String url;
    
    /**
     * The name.
     */
    private String name;

    /**
     * Gets the artifact id.
     *
     * @return the artifact id
     */
    public String getArtifactID() {
        return artifactID;
    }

    /**
     * Sets the artifact id.
     *
     * @param artifactID the new artifact id
     */
    public void setArtifactID(String artifactID) {
        this.artifactID = artifactID;
    }

    /**
     * Gets the group id.
     *
     * @return the group id
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * Sets the group id.
     *
     * @param groupID the new group id
     */
    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    /**
     * Gets the package name.
     *
     * @return the package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets the package name.
     *
     * @param packageName the new package name
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     *
     * @param url the new url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
}
