<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:${bo.javaName?lower_case}="http://te2m.de/jsf/components/${bo.javaName?lower_case}"
      xmlns:p="http://primefaces.org/ui">

    <ui:composition>

        <p:dialog id="${bo.javaName}ViewDlg" widgetVar="${bo.javaName}ViewDialog" modal="true" resizable="false" appendTo="@(body)" header="${r"#{"}bundle.View${bo.javaName}Title}">
            <h:form id="${bo.javaName}ViewForm">
                <h:panelGroup id="display">
                    <${bo.javaName?lower_case}:View header="${r"#{"}bundle.Header${bo.javaName}} entity="${r"#{"}${bo.javaName}Controller.selected}"/>
                    <p:commandButton value="${r"#{"}bundle.Close}" onclick="${bo.javaName}ViewDialog.hide()"/>
                </h:panelGroup>
            </h:form>
        </p:dialog>

    </ui:composition>
</html>
