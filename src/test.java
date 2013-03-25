
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class test {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("aaaaaaaaaaaaaaaa");
		ApplicationContext ct=new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("bbbbbbbbbbbbbbbb");
	}
	
	@Test
	public void todo(){
		
	}

}
