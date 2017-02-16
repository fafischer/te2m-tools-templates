/*
* ${info.copyrightInfo}
*/

package ${bo.packageName};

<#if bo.imports??>
<#list bo.imports as o>
import ${o};
</#list>
</#if>
<#if bo.properties['jaxb.generate']??>import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;</#if>

/**
 * ${bo.description}
 * 
 * @author ${info.author}
 * @since ${info.version}
 */
<#if bo.properties['jaxb.generate']??>@XmlRootElement(name="${bo.nameAsJavaConstantName}")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType (name="${bo.nameAsJavaConstantName}")</#if>
public class ${bo.javaTypeName} {

<#if bo.members??>
<#list bo.members as o>
    /** ${o.description} */<#if bo.properties['jaxb.generate']??>
    @XmlElement(name="${o.nameAsJavaConstantName}")</#if>
    private ${o.javaTypeName} my${o.javaName};
</#list>
</#if>
<#if bo.attributes??>
<#list bo.attributes as o>
    /** 
    * Returns ${o.name} 
    * @returns ${o.description}
    */
    public ${o.javaTypeName} get${o.javaName}()
    {
        return  my${o.javaName};
    }
</#list>
</#if>
<#if bo.attributes??>
<#list bo.attributes as o>
    /** 
    * Sets ${o.name} 
    * @param ${o.description}
    */
    public void set${o.javaName}(${o.javaTypeName} a${o.javaName})
    {
        my${o.javaName}=a${o.javaName};
    }
</#list>
</#if>
<#if bo.operations??>
<#list bo.operations as o>
    /**
    * ${o.description} 
    * <#if o.parameters??><#list o.parameters as p>
    * @param a${p.javaName} ${p.description}
</#list></#if><#if o.returnValue??>
    * @return ${o.returnValue.javaName} ${o.returnValue.description} </#if>
    */

    public <#if o.returnValue??>${o.returnValue.javaTypeName}<#else>void</#if> ${o.name}(<#if o.parameters??><#list o.parameters as p>${p.javaTypeName} a${p.javaName}<#if p_has_next>,</#if></#list></#if>)
    {
        // ToDo Implement method
        <#if o.returnValue??>return null;</#if>
    }   

</#list>
</#if>
    
}