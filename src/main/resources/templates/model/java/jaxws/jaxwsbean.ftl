/*
* ${info.copyrightInfo}
*/

package ${bo.packageName};

import javax.jws.WebMethod;
import javax.jws.WebService;
<#if bo.imports??><#list bo.imports as o>
import ${o};
</#list></#if>

/**
 * ${bo.description}
 * 
 * @author ${info.author}
 * @since ${info.version}
 */
@WebService()
public class ${bo.javaName} {

<#if bo.operations??><#list bo.operations as o>
    /**
    * ${o.description}
<#if o.parameters??><#list o.parameters as p>
    * @param a${p.javaName} ${p.description}
</#list></#if>
<#if o.returnValue??>
    * @return ${o.returnValue.javaName} ${o.returnValue.description} 
</#if>
    * @since ${info.version}
    */
	@WebMethod
    public <#if o.returnValue??>${o.returnValue.javaTypeName}<#else>void</#if> ${o.name}(<#if o.parameters??><#list o.parameters as p>${p.javaTypeName} a${p.javaName}<#if p_has_next>,</#if></#list></#if>)
    {
        // ToDo Implement method
        <#if o.returnValue??>
        ${o.returnValue.javaTypeName} a${o.returnValue.javaName} = null;
        return a${o.returnValue.javaName};
        </#if>
    }   

</#list>
</#if>
    
}