#TODO Merge with main resource bundle
Save=Save
Cancel=Cancel
Create=Create
Edit=Edit
View= View
Delete=Delete

Create${bo.javaName}Title=Create new ${bo.name}
Edit${bo.javaName}Title=Edit existing ${bo.name}
List${bo.javaName}Title=List all ${bo.name}
View${bo.javaName}Title=View ${bo.name}
Header${bo.javaName}=${bo.name}
<#if bo.attributes??>
<#list bo.attributes as o>
Create${bo.javaName}Title_${o.javaName}=Create new ${o.name}
Create${bo.javaName}Label_${o.javaName}=${o.name}
Edit${bo.javaName}Title_${o.javaName}=Edit ${o.name}
Edit${bo.javaName}Label_${o.javaName}=${o.name}
List${bo.javaName}Title_${o.javaName}=${o.name}
View${bo.javaName}Title_${o.javaName}=View ${o.name}
View${bo.javaName}Label_${o.javaName}=${o.name}
</#list>
</#if>

#Messaages
msg_Count=There are currently {0} {1} available.
${bo.javaName}Created=${bo.name} created
${bo.javaName}Updated=${bo.name} updated
${bo.javaName}Deleted=${bo.name} deleted
PersistenceErrorOccured=A persistence error occurred.
#Buttons
button_ShowAll=Show all

