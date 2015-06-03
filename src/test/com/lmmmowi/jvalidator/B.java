package test.com.lmmmowi.jvalidator;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lmmmowi.jvalidator.Validate;
import com.lmmmowi.jvalidator.core.Violation;
import com.lmmmowi.jvalidator.exception.ValidateException;

public class B {

	@Validate("classpath:vald.xml")
	public void hello(A a) {
		System.out.println("invoke hello");
	}

	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"spring.xml");

			B b = context.getBean(B.class);
			b.hello(new A());
		} catch (ValidateException e) {
			e.printStackTrace();
			
			List<Violation> violations = e.getViolations();
			if (violations != null) {
				for (Violation violation : violations) {
					System.out.println(violation.getMessage());
				}
			}
		}
	}
}
