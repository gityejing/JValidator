#JValidator
___
##Dependencies

+ Spring AOP
+ dom4j
___
##Usage

+ ***[1] Add code in spring config file***

spring.xml
	
	<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator" />
	
    <bean class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
		<property name="advice" ref="validateAdvice" />
		<property name="pattern" value="xxx" />
    </bean>

    <bean id="validateAdvice" class="com.lmmmowi.jvalidator.ValidateAdvice" />

+ ***[2] Write your validate rule in xml***

valid.xml

	<validate>
		<value constraint="int" min="0" max="99" />
	
		<object>
			<value name="name" constraint="string" maxLen="5" />
			<value name="title" constraint="string" minLen="2" />
		</object>
	</validate>


+ ***[3] Add annotation to your methods***

Method

	@Validate("classpath:valid.xml")
	public void hello(int number, A a) {
		System.out.println("invoke hello");
	}