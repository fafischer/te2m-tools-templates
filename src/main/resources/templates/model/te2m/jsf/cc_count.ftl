<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:cc="http://java.sun.com/jsf/composite"
      xmlns:p="http://primefaces.org/ui"
      xmlns:h="http://java.sun.com/jsf/html">
    <!-- INTERFACE -->
    <cc:interface>
        <cc:attribute name="header"/>
    </cc:interface>

    <!-- IMPLEMENTATION -->
    <cc:implementation>                            
        
        <p:panel header="${r"#{"}cc.attrs.header}">
            <h:outputText value="${r"#{"}bundle.msg_Count}">
                <f:param value="${r"#{"}${bo.javaName}Controller.count}"/>
                <f:param value="${bo.name}"/> 
            </h:outputText>
            <p:commandButton action="" valie="/pages/${bo.javaName}/List.xhtml" value="${r"#{"}bundle.button_ShowAll}"/>
        </p:panel>

    </cc:implementation>
</html>