## Exercise 1 - questions
<p align="center">
![monophy](https://user-images.githubusercontent.com/60923555/114907834-6621f200-9e13-11eb-913b-313a5a618459.gif)
</p>

# a) Identify a couple of examples on the use of AssertJ expressive methods chaining.

```
- in EmployeeService_UnitTest:
	-> assertThat(found.getName()).isEqualTo(name); (line 53)
	-> assertThat(fromDb).isNull(); (line 59)
	-> assertThat(doesEmployeeExist).isEqualTo(false); (line 75)
	-> assertThat(fromDb.getName()).isEqualTo("john"); (line 83)
	-> assertThat(fromDb).isNull(); (line 92)
	-> assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName()); (line 103)

- in EmployeeRestControllerTemplateIT:
	-> assertThat(found).extracting(Employee::getName).containsOnly("bob"); (line 51)
	-> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); (line 64)
	-> assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex"); (line 65)
	
- in EmployeeRestControllerIT:
	-> assertThat(found).extracting(Employee::getName).containsOnly("bob"); (line 51)

- in EmployeeRepositoryTest:
	-> assertThat( found ).isEqualTo(alex); (line 27)
	-> assertThat(fromDb).isNull(); (line 33)
	-> assertThat(fromDb).isNotNull(); (line 42)
	-> assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail()); (line 43)
	-> assertThat(fromDb).isNull(); (line 49)
	-> assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName()); (line 65)
```

# b) Identify an example in which you mock the behaviour of the repository (and avoid involving a database). 

```
- We mock the behaviour of the repository in the EmployeeService_UnitTest file
```

# c) What is the difference between standard @Mock and @MockBean?

```
- @Mock is an annotation from the Mockito library
- @MockBean is an annotation included in the spring-boot-test library that already includes Mockito
- @MockBean allows to add Mockito mocks to the Spring ApplicationContext
- @MockBean will replace any existing bean of the same type in the application context but if no bean of the same type is defined, a new one is added
- in our example, we mock the bean/service that is needed to make the test
```

# d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

```
- it's used to attach a set of application properties together in a single file so that you can run the integration test recurring to a real persistent database, running on a device.
```






