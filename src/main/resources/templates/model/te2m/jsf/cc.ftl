<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:cc="http://java.sun.com/jsf/composite"
      xmlns:p="http://primefaces.org/ui"
      xmlns:h="http://java.sun.com/jsf/html">
    <!-- INTERFACE -->
    <cc:interface>
        <cc:attribute name="header"/>
        <cc:attribute name="entity" type="${bo.packageName}.entity.${bo.javaName}"/>
    </cc:interface>

    <!-- IMPLEMENTATION -->
    <cc:implementation>                            
        
        <p:panel header="${r"#{"}cc.attrs.header}">
            <p:tabView>
                <p:tab title="${r"#{"}bundle.Tab_Header_Main}">
                    <p:panelGrid columns="2" rendered="${r"#{"}cc.attrs.entity != null}">
<#if bo.attributes??><#list bo.attributes as o>
                        <h:outputText value="${r"#{"}bundle.View${bo.javaName}Label_${o.javaName}}"/>
                        <h:outputText value="${r"#{"}cc.attrs.entity.${o.javaName}}" title="${r"#{"}bundle.View${bo.javaName}Title_${o.javaName}}"/>
</#list></#if>
                    </p:panelGrid>
                </p:tab>
                <p:tab title="${r"#{"}bundle.Tab_Header_Misc}">
                    <!-- technical information -->
                    <h:outputText value="${r"#{"}bundle.CreationDate}"/>
                    <h:outputText value="${r"#{"}cc.attrs.entity.creationDate}" title="${r"#{"}bundle.CreationDate}"/>
                    <h:outputText value="${r"#{"}bundle.Creator}"/>
                    <h:outputText value="${r"#{"}cc.attrs.entity.creator.name}" title="${r"#{"}bundle.Creator}"/>
                    <h:outputText value="${r"#{"}bundle.LastModifiedDate}"/>
                    <h:outputText value="${r"#{"}cc.attrs.entity.lastModifiedDate}" title="${r"#{"}bundle.LastModifiedDate}"/>
                    <h:outputText value="${r"#{"}bundle.LastEditor}"/>
                    <h:outputText value="${r"#{"}cc.attrs.entity.lastEditor.name}" title="${r"#{"}bundle.LastEditor}"/>
                    <h:outputText value="${r"#{"}bundle.ID}"/>
                    <h:outputText value="${r"#{"}cc.attrs.entity.id" title="${r"#{"}bundle.ID}"/>
                    <h:outputText value="${r"#{"}bundle.Version}"/>
                    <h:outputText value="${r"#{"}cc.attrs.entity.version}" title="${r"#{"}bundle.Version}"/>
                </p:tab>
            </p:tabView>
                    
        </p:panel>

    </cc:implementation>
</html>