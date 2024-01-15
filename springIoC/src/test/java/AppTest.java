import com.bytedance.website.Ad;
import com.bytedance.website.Comments;
import com.bytedance.website.Press;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class AppTest {

    @Test
    public void testBeanCreation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve beans from the container
        Press press1 = context.getBean("press1", Press.class);
        Ad ad1 = context.getBean("ad1", Ad.class);
        Comments comments1 = context.getBean("comments1", Comments.class);

        // Assert that the beans are not null
        assertNotNull("Press bean is null", press1);
        assertNotNull("Ad bean is null", ad1);
        assertNotNull("Comments bean is null", comments1);

        // Print the retrieved beans
        System.out.println("Press 1: " + press1);
        System.out.println("Ad 1: " + ad1);
        System.out.println("Comments 1: " + comments1);
    }
}
