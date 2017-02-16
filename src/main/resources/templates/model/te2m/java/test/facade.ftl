/*
* ${info.copyrightInfo}
*/

package ${bo.packageName}.boundary;

import ${bo.packageName}.entity.${bo.javaName};
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

/**
 * The Class ${bo.javaName}FacadeTest.
 *
 * <p>
 * This class provides basic Arquillian based tests for the ${bo.javaName}Facade 
 * </p>
 * @author ${info.author}
 * @since ${info.version}
 */
 */
@RunWith(Arquillian.class)
public class ${bo.javaName}FacadeTest {

    /**
     * Instantiates a new ${bo.javaName} facade test.
     */
    public ${bo.javaName}FacadeTest() {
    }

    /**
     * Creates the deployment.
     *
     * @return the java archive
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsManifestResource("jbossas-ds.xml")
                .addClasses(${bo.javaName}.class, ${bo.javaName}Facade.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /**
     * The ${bo.javaName} facade.
     */
    @EJB
    ${bo.javaName}Facade facade;

    /**
     * Test of create method, of class ${bo.javaName}Facade.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        ${bo.javaName} entity = new ${bo.javaName}();

        entity = facade.create(entity);

        assertNotNull(entity);

        assertNotNull("UUID missing", entity.getUuid());
    }
    

    /**
     * Test of create${bo.javaName} method, of class ${bo.javaName}Facade.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCreate${bo.javaName}() throws Exception {
        System.out.println("create");
        ${bo.javaName} entity = new ${bo.javaName}();

        <#if bo.attributes??><#list bo.attributes as o> 
        ${o.javaTypeName} new${o.javaName}= new ${o.javaTypeName}();
        </#list></#if>)
        entity = facade.create${bo.javaName}(<#if bo.attributes??><#list bo.attributes as o> new${o.javaName}<#if o_has_next>,</#if></#list></#if>);

        assertNotNull(entity);

        assertNotNull("UUID missing", entity.getUuid());
    }
    
    
}
