<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:p="http://primefaces.org/ui">

    <ui:composition template="/templates/main.xhtml">


        <ui:define name="title">
            <h:outputText value="${r"#{"}bundle.List${bo.javaName}Title}"></h:outputText>
        </ui:define>

        <ui:define name="body">
            <h:form id="${bo.javaName}ListForm">
                <p:panel header="${r"#{"}bundle.List${bo.javaName}Title}">
                    <p:dataTable id="datalist" value="${r"#{"}${bo.javaName}Controller.items}" var="item"
                                 selectionMode="single" selection="${r"#{"}${bo.javaName}Controller.selected}"
                                 paginator="true"
                                 rowKey="${r"#{"}item.uuid}"
                                 rows="10"
                                 rowsPerPageTemplate="10,20,30,40,50"
                                 >

                        <p:ajax event="rowSelect"   update="createButton viewButton editButton deleteButton"/>
                        <p:ajax event="rowUnselect" update="createButton viewButton editButton deleteButton"/>
 <#if bo.attributes??><#list bo.attributes as o>
                        <p:column>
                            <f:facet name="header">
                                <h:outputText value="${r"#{"}bundle.List${bo.javaName}Title_${o.javaName}}"/>
                            </f:facet>
                            <h:outputText value="${r"#{"}item.${o.javaName}}"/>
                        </p:column>
</#list></#if>
                        <f:facet name="footer">
                            <p:commandButton id="createButton" icon="ui-icon-plus"   value="${r"#{"}bundle.Create}" actionListener="${r"#{"}${bo.javaName}Controller.prepareCreate}" update=":${bo.javaName}CreateForm" oncomplete="PF('${bo.javaName}CreateDialog').show()"/>
                            <p:commandButton id="viewButton"   icon="ui-icon-search" value="${r"#{"}bundle.View}" update=":${bo.javaName}ViewForm" oncomplete="PF('${bo.javaName}ViewDialog').show()" disabled="${r"#{"}empty ${bo.javaName}Controller.selected}"/>
                            <p:commandButton id="editButton"   icon="ui-icon-pencil" value="${r"#{"}bundle.Edit}" update=":${bo.javaName}EditForm" oncomplete="PF('${bo.javaName}EditDialog').show()" disabled="${r"#{"}empty ${bo.javaName}Controller.selected}"/>
                            <p:commandButton id="deleteButton" icon="ui-icon-trash"  value="${r"#{"}bundle.Delete}" actionListener="${r"#{"}${bo.javaName}Controller.destroy}" update=":growl,datalist" disabled="${r"#{"}empty ${bo.javaName}Controller.selected}"/>
                        </f:facet>
                    </p:dataTable>
                </p:panel>
            </h:form>

            <ui:include src="Create.xhtml"/>
            <ui:include src="Edit.xhtml"/>
            <ui:include src="View.xhtml"/>
        </ui:define>
    </ui:composition>

</html>
