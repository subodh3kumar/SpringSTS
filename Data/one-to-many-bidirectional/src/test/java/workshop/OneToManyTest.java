package workshop;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import workshop.entity.Author;
import workshop.entity.Book;
import workshop.repository.AuthorRepository;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OneToManyTest {
	
	@Autowired
	private AuthorRepository authorRepository;

	@Test
	void insert() {
		log.info("insert() method start");
		
		Author subodh = new Author();
		subodh.setName("Subodh");
		subodh.setGenre("Fiction");
		subodh.setAge(32);
		
		Book techBook = new Book();
		techBook.setIsbn("ISBN-101");
		techBook.setTitle("Java Learning");
		
		subodh.addBook(techBook);
		
		techBook = new Book();
		techBook.setIsbn("ISBN-102");
		techBook.setTitle("Spring Learning");
		
		subodh.addBook(techBook);
		
		techBook = new Book();
		techBook.setIsbn("ISBN-103");
		techBook.setTitle("JPA Learning");
		
		subodh.addBook(techBook);
		
		authorRepository.save(subodh);
		
		Author juli = new Author();
		juli.setName("Juli");
		juli.setGenre("Cooking");
		juli.setAge(24);
		
		Book receipeBook = new Book();
		receipeBook.setIsbn("ISBN-201");
		receipeBook.setTitle("Vegetable Receipe");
		
		juli.addBook(receipeBook);
		
		receipeBook = new Book();
		receipeBook.setIsbn("ISBN-202");
		receipeBook.setTitle("Salad Receipe");
		
		juli.addBook(receipeBook);
		
		receipeBook = new Book();
		receipeBook.setIsbn("ISBN-203");
		receipeBook.setTitle("Chicken Receipe");
		
		juli.addBook(receipeBook);
		
		authorRepository.save(juli);
	}
	
	@Test
	@Transactional
	void read() {
		log.info("read() method start");
	}
}
