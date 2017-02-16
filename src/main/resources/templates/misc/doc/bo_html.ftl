<html>
	<head>
		<title>${bo.name}</title>
	</head>
	<body>
		<h1>Service ${bo.name}</h1>
		<p>${bo.description}</p>
		<#if bo.operations??>
			This service provides the following operations:
			<ul>
				<#list bo.operations as o>
    			<li>${o.name}</li>
				</#list>
			</ul>
		</#if>

		<#if bo.operations??>
			<#list bo.operations as o>
			<h2>${o.name}</h2>
    		<p>${o.description}</p>
			
			<#if o.parameters??>
			<h3>Parameters</h3>
			<table border="1">
			<tr>
			<td>Name</td>
			<td>Description</td>
			<td>Type</td>
			</tr>
			<#list o.parameters as p>
    		<tr>
			<td>${p.javaName}</td>
			<td>${p.description}</td>
			<td>&nbsp;</td>
			</tr>
			</#list>
			</table>
			</#if>
			<#if o.returnValue??>
    		<h3>Return Value</h3>
    		
    		<table border="1">
			<tr>
			<td>Name</td>
			<td>Description</td>
			<td>Type</td>
			</tr>
    		<tr>
			<td>${o.returnValue.name}</td>
			<td>${o.returnValue.description}</td>
			<td>&nbsp;</td>
			</tr>
			</table> 
			</#if>
			</#list>
		</#if>		
	</body>
</head>