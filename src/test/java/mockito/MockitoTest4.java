package mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

//捕获参数来进一步断言
//较复杂的参数匹配器会降低代码的可读性，有些地方使用参数捕获器更加合适。

public class MockitoTest4 {

	@Test
	public void capturing_args() {
		PersonDao personDao = mock(PersonDao.class);
		PersonService personService = new PersonService(personDao);

		ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
		personService.update(1, "jack");
		verify(personDao).update(argument.capture());
		assertEquals(1, argument.getValue().getId());
		assertEquals("jack", argument.getValue().getName());
	}

	class Person {
		private int id;
		private String name;

		Person(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

	interface PersonDao {
		public void update(Person person);
	}

	class PersonService {
		private PersonDao personDao;

		PersonService(PersonDao personDao) {
			this.personDao = personDao;
		}

		public void update(int id, String name) {
			personDao.update(new Person(id, name));
		}
	}

}
