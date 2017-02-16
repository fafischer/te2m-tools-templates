/*
* ${info.copyrightInfo}
*/


package ${bo.packageName}.controller;

import ${bo.packageName}.boundary.${bo.javaName}Facade;
import ${bo.packageName}.entity.${bo.javaName};
import de.te2m.web.common.controller.util.JsfUtil;
import de.te2m.web.common.controller.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * The Class ${bo.javaName}Controller
 * This class is the basic backing bean for all JSF pages related to the entity ${bo.javaName}.  
 *
 * @author ${info.author}
 * @since ${info.version}
 */
@Named("${bo.javaName}Controller")
@SessionScoped
public class ${bo.javaName}Controller implements Serializable {

    @EJB
    private ${bo.javaName}Facade ejbFacade;
    private List<${bo.javaName}> items = null;
    private ${bo.javaName} selected;

    public ${bo.javaName}Controller() {
    }

    /**
    * Returns the currently selected ${bo.javaName}
    */
    public ${bo.javaName} getSelected() {
        return selected;
    }
    
    /**
    * Seths the currently selected ${bo.javaName}
    */
    public void setSelected(${bo.javaName} selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ${bo.javaName}Facade getFacade() {
        return ejbFacade;
    }

    /**
    * Prepares the creation of a new ${bo.javaName}
    */
    public ${bo.javaName} prepareCreate() {
        selected = new ${bo.javaName}();
        initializeEmbeddableKey();
        return selected;
    }
    
    /**
    * Creates a new ${bo.javaName}
    */
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("${bo.javaName}Created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
    * Updates the database entry related to the currently selected ${bo.javaName}
    */
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("${bo.javaName}Updated"));
    }

    /**
    * Deletes the currently selected ${bo.javaName}
    */
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("${bo.javaName}Deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
    * Returns all instances of ${bo.javaName}
    */
    public List<${bo.javaName}> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
    
    /**
    * Finds the ${bo.javaName} for the provided ID
    */
    public ${bo.javaName} get${bo.javaName}(java.lang.String id) {
        return getFacade().find(id);
    }

    /**
    * Returns all instances of ${bo.javaName}
    */
    public List<${bo.javaName}> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    
    /**
    * Returns all instances of ${bo.javaName}
    */
    public List<${bo.javaName}> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
    * The Class ${bo.javaName}ControllerConverter
    * Faces converter for ${bo.javaName}
    */
    @FacesConverter(forClass = ${bo.javaName}.class)
    public static class ${bo.javaName}ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ${bo.javaName}Controller controller = (${bo.javaName}Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "${bo.javaName}Controller");
            return controller.get${bo.javaName}(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ${bo.javaName}) {
                ${bo.javaName} o = (${bo.javaName}) object;
                return getStringKey(o.getUuid());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ${bo.javaName}.class.getName()});
                return null;
            }
        }

    }

}
