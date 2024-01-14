package Spring_IoC;
import Spring_IoC.Press;
import Spring_IoC.Ad;
import Spring_IoC.Comments;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PressAdComments_IoC {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve beans from the container
        Press press1 = context.getBean("press1", Press.class);
        Ad ad1 = context.getBean("ad1", Ad.class);
        Comments comments1 = context.getBean("comments1", Comments.class);

        // Print the retrieved beans
        System.out.println("Press 1: " + press1);
        System.out.println("Ad 1: " + ad1);
        System.out.println("Comments 1: " + comments1);
    }
}
