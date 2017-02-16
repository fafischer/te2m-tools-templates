/*
* ${info.copyrightInfo}
*/

package ${bo.packageName}.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The Class Simple${bo.javaName}PersistenceTest
 * <p>
 * This class provides basic Arquillian based tests for the entity ${bo.javaName} 
 * </p>
 * @author ${info.author}
 * @since ${info.version}
 */
@RunWith(Arquillian.class)
public class Simple${bo.javaName}PersistenceTest {
    
    /**
     * Creates the deployment.
     *
     * @return the archive
     */
    @Deployment
    public static Archive<?> createDeployment() {

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addPackage(${bo.javaName}.class.getPackage())
            .addAsManifestResource("test-persistence.xml", "persistence.xml")
            .addAsManifestResource("jbossas-ds.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return jar;
    }
 
    /**
     * The Constant TEST_ENTITY_NAMES.
     */
    private static final String[] TEST_ENTITY_NAMES = {
        UUID.randomUUID.toString(),
        UUID.randomUUID.toString(),
        UUID.randomUUID.toString()
    };
    
    /**
     * The em.
     */
    @PersistenceContext
    EntityManager em;
    
    /**
     * The utx.
     */
    @Inject
    UserTransaction utx;
 
    /**
     * Prepares the persistence test.
     *
     * @throws Exception the exception
     */
    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        insertData();
        startTransaction();
    }

    /**
     * Clear data.
     *
     * @throws Exception the exception
     */
    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Dumping old records...");
        em.createQuery("delete from ${bo.javaName}").executeUpdate();
        utx.commit();
    }

    /**
     * Insert data.
     *
     * @throws Exception the exception
     */
    private void insertData() throws Exception {
        utx.begin();
        em.joinTransaction();
        System.out.println("Inserting records...");
        for (String testString : TEST_ENTITY_NAMES) {
            ${bo.javaName} entity = new ${bo.javaName}();
            // TODO add required initialization
            em.persist(entity);
        }
        utx.commit();
        // reset the persistence context (cache)
        em.clear();
    }

    /**
     * Start transaction.
     *
     * @throws Exception the exception
     */
    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }
    
    /**
     * Commit transaction.
     *
     * @throws Exception the exception
     */
    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }
    
    /**
     * Should find all entities using jpql query.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldFindAllEntitiesUsingJpqlQuery() throws Exception {
        // given
        String fetchingAllEntitiesInJpql = "select e from ${bo.javaName} e order by e.uuid";

        // when
        System.out.println("Selecting (using JPQL)...");
        List<${bo.javaName}> entities = em.createQuery(fetchingAllEntitiesInJpql, ${bo.javaName}.class).getResultList();

        // then
        System.out.println("Found " + entities.size() + " recorde (using JPQL):");
        assertContainsAllEntities(entities);
    }
    
    /**
     * Should find all entities using criteria api.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldFindAllEntitiesUsingCriteriaApi() throws Exception {
        // given
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<${bo.javaName}> criteria = builder.createQuery(${bo.javaName}.class);
                
        Root<${bo.javaName}> root = criteria.from(${bo.javaName}.class);
        criteria.select(root);
        // TIP: If you don't want to use the JPA 2 Metamodel,
        // you can change the get() method call to get("id")
        criteria.orderBy(builder.asc(root.get(${bo.javaName}_.uuid)));
        // No WHERE clause, which implies select all

        // when
        System.out.println("Selecting (using Criteria)...");
        List<${bo.javaName}> entities = em.createQuery(criteria).getResultList();

        // then
        System.out.println("Found " + entities.size() + " recorde (using Criteria):");
        assertContainsAllEntities(entities);
    }
    
    /**
     * Assert contains all entities.
     *
     * @param entities the entities
     */
    private static void assertContainsAllEntities(Collection<${bo.javaName}> entities) {
        Assert.assertEquals(TEST_ENTITY_NAMES.length, entities.size());
        
        /*
        TODO: Adjust test
        final Set<String> retrievedEntityNames = new HashSet<String>();
        for (${bo.javaName} entity : entities) {
            System.out.println("* " + entity);
            retrievedEntityNames.add(entity.getName());
        }
        Assert.assertTrue(retrievedEntityNames.containsAll(Arrays.asList(TEST_ENTITY_NAMES)));
        =/
    }
}
