# 首先添加maven依赖

	<dependency>
		<groupId>org.mockito</groupId>
    	<artifactId>mockito-all</artifactId>
    	<version>1.9.5</version>
    	<scope>test</scope>
	</dependency>

# 当然mockito需要junit配合使用

	<dependency>
		<groupId>junit</groupId>
    	<artifactId>junit</artifactId>
    	<version>4.11</version>
    	<scope>test</scope>
	</dependency>

# 然后为了使代码更简洁，最好在测试类中导入静态资源

	import static org.mockito.Mockito.*;
	import static org.junit.Assert.*;

# 注解
@Captor,@Spy,@InjectMocks,@Mock 


# 如果使用powermock, 需要注掉mockito 和 Junit
    
     <!--  
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-all</artifactId>
			<version>1.9.5</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.11</version>
			<scope>test</scope>
		</dependency>
	-->
	<dependency>
		<groupId>org.powermock</groupId>
		<artifactId>powermock-module-junit4</artifactId>
		<version>1.6.4</version>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>org.powermock</groupId>
		<artifactId>powermock-api-mockito</artifactId>
		<version>1.6.4</version>
		<scope>test</scope>
	</dependency>


