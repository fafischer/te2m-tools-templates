/*
* ${info.copyrightInfo}
*/


package ${bo.packageName}.boundary;

import de.te2m.api.core.boundary.AbstractFacade;
import ${bo.packageName}.entity.${bo.javaName};
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
<#if bo.imports??>
<#list bo.imports as o>
import ${o};
</#list>
</#if>

/**
 * The Class ${bo.javaName}Facade
 * This class is responsible for managing the entity ${bo.javaName}
 *
 * @author ${info.author}
 * @since ${info.version}
 */
@Stateless
public class ${bo.javaName}Facade extends AbstractFacade<${bo.javaName}> {
    
    /**
     * The em.
     */
    @PersistenceContext
    private EntityManager em;

    /* (non-Javadoc)
     * @see de.te2m.api.core.boundary.AbstractFacade#getEntityManager()
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Instantiates a new ${bo.name} facade.
     */
    public ${bo.javaName}Facade() {
        super(${bo.javaName}.class);
    }
    
    /**
     * Creates the ${bo.name}.
     *
<#if bo.attributes??><#list bo.attributes as o>
     * @param new${o.javaName} the new ${o.name}</#list></#if>
     * @return the new ${bo.name}
     */
    public ${bo.javaName} create${bo.javaName}(<#if bo.attributes??><#list bo.attributes as o> ${o.javaTypeName} new${o.javaName}<#if o_has_next>,</#if></#list></#if>)
    {
        ${bo.javaName} new${bo.javaName} = new ${bo.javaName}();
<#if bo.attributes??><#list bo.attributes as o>
        new${bo.javaName}.set${o.javaName}(new${o.javaName});
</#list></#if>
        create(new${bo.javaName});
        return new${bo.javaName};        
    }

}
