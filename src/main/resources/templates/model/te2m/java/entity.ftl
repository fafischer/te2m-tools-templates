/*
* ${info.copyrightInfo}
*/

package ${bo.packageName}.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import de.te2m.api.core.entity.AbstractNamedEntity;
<#if bo.imports??>
<#list bo.imports as o>
import ${o};
</#list>
</#if>

/**
 * The Class ${bo.javaName}
 *
 * ${bo.description}
 * <p>
 * This class is an persistent entity class.
 * The table name is  ${bo.javaName} 
 * The inheritance strategy is InheritanceType.TABLE_PER_CLASS.
 * </p>
 * @author ${info.author}
 * @since ${info.version}
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlRootElement
public class ${bo.javaName} extends AbstractEntity implements Serializable {

<#if bo.attributes??>
<#list bo.attributes as o>
    /**
    * my${o.javaName}
    * ${o.description}
    */
    private ${o.type} my${o.javaName};
</#list>
</#if>

<#if bo.attributes??>
<#list bo.attributes as o>
    /**
    * Returns the attribute ${o.javaName}
    * @Return The ${o.name}
    */
    public ${o.type} get${o.javaName}()
    {
        return my${o.javaName};
    }

    /**
    * Set new ${o.javaName}
    * @Param The new ${o.name}
    */
    public void set${o.javaName}(${o.type} new${o.javaName})
    {
        my${o.javaName} = new${o.javaName};
    }
</#list>
</#if>
}