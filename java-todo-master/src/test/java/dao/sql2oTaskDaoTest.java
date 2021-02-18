package dao;

import models.Task;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class sql2oTaskDaoTest {
    private sql2oTaskDao taskDao;

    private Connection conn;

    @Before
    public void setUp() throws Exception {
       String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
       Sql2o sql2o = new Sql2o(connectionString, "", "");

       taskDao = new sql2oTaskDao(sql2o);

       conn = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Task task = new Task("mow the lawn");

        int originalTaskId = task.getId();

        taskDao.add(task);

        assertNotEquals(originalTaskId, task.getId());
    }

    @Test
    public void existingTasksCanBeFoundById() throws Exception {
        Task task = new Task("mow the lawn");
        taskDao.add(task);
        Task foundTask = taskDao.findById(task.getId());

        assertEquals(task, foundTask);
    }


}