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
			<h2>Service Operations</h2>
			<#list bo.operations as o>
			<h3>${o.name}</h3>
    		<p>${o.description}</p>
			
			<#if o.parameters??>
			<h4>Parameters</h4>
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
    		<h4>Return Value</h4>
    		
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
		<#if bo.allSubTasks??><#if bo.allSubTasks?size!=0>
                <h2>Tasks</h2>
		<p>
		In order to implement this service the following tasks are required:
		</p>	
		<table border="1" width="100%">
			<thead>
			<tr>
			<td>Name</td>
			<td>Description</td>
			<td>Estimation</td>
			</tr>
			</thead>
			<tbody>
                        <#list bo.allSubTasks as task>
			<tr><td>${task.name}</td><td>${task.description}</td><td>${task.estimation}&nbsp;${task.unit}</td></tr>
			</#list>
                        </tbody>
		</table>
                </#if></#if>

	</body>
</head>