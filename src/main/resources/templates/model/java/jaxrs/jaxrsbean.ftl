/*
* ${info.copyrightInfo}
*/

package ${bo.packageName};

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
<#if bo.imports??><#list bo.imports as o>
import ${o};
</#list></#if>

/**
 * ${bo.description}
 * 
 * This class implements a RESTful web service.
 * <#if bo.properties['jaxrs.path']??><p>The service path is <code>${bo.properties['jaxrs.path']}</code></p></#if>	
 * 
 * @author ${info.author}
 * @since ${info.version}
 */
<#if bo.properties['jaxrs.consumes']??>@Consumes({"${bo.properties['jaxrs.consumes']}"})</#if>
<#if bo.properties['jaxrs.produces']??>@Produces({"${bo.properties['jaxrs.produces']}"})</#if>
<#if bo.properties['jaxrs.path']??>@Path("${bo.properties['jaxrs.path']}")</#if>
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
	
	<#if o.properties['jaxrs.method']??>${o.properties['jaxrs.method']}</#if>
	<#if o.properties['jaxrs.consumes']??>@Consumes({"${o.properties['jaxrs.consumes']}"})</#if>
	<#if o.properties['jaxrs.produces']??>@Produces({"${o.properties['jaxrs.produces']}"})</#if>
	<#if o.properties['jaxrs.path']??>@Path("${o.properties['jaxrs.path']}")</#if>
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