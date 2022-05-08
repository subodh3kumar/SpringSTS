package workshop.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
public class Author {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
	private String id;

	private String name;

	private String genre;

	private int age;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> books = new ArrayList<>();

	public void addBook(Book book) {
		log.info("adding book: {}", book);
		this.books.add(book);
		book.setAuthor(this);
	}

	public void removeBook(Book book) {
		log.info("remove book: {}", book);
		book.setAuthor(null);
		this.books.remove(book);
	}

	public void removeBooks() {
		log.info("removing books");

		Iterator<Book> iterator = this.books.iterator();

		while (iterator.hasNext()) {
			Book book = iterator.next();
			book.setAuthor(null);
			iterator.remove();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", genre=" + genre + ", age=" + age + "]";
	}
}
