/*
* ${info.copyrightInfo}
*/

package ${bo.packageName};

<#if bo.imports??>
<#list bo.imports as o>
import ${o};
</#list>
</#if>

/**
 * ${bo.description}
 * 
 * @author ${info.author}
 * @since ${info.version}
 */
public interface ${bo.javaName} {

<#if bo.operations??>
<#list bo.operations as o>
    /**
    * ${o.description}
<#if o.parameters??>
<#list o.parameters as p>
    * @param a${p.javaName} ${p.description}
</#list>
</#if>
<#if o.returnValue??>
    * @return ${o.returnValue.name} ${o.returnValue.description} 
</#if>
    * @since ${info.version}
    */

    public <#if o.returnValue??>${o.returnValue.javaName}<#else>void</#if> ${o.name}(<#if o.parameters??><#list o.parameters as p>${p.javaTypeName} a${p.javaName}<#if p_has_next>,</#if></#list></#if>); 

</#list>
</#if>
    
}