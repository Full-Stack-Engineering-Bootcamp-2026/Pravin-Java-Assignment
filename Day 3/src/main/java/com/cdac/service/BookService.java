package com.cdac.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cdac.model.Book;

@Service
public class BookService {

	private Map<Integer, Book> books = new HashMap<Integer, Book>();

	public BookService() {
		books.put(1, new Book(1, "Water", "Pravin", 100));
		books.put(2, new Book(2, "Land", "Pravin", 1000));
	}

	public Map<Integer, Book> findAll() {

		return books;

	}

	public Book findById(int id) {
		Book res = new Book();
		try {
			res = books.entrySet().stream().filter(t -> t.getValue().getId() == id).findFirst()
					.orElseThrow(() -> new Exception("hell")).getValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

	}

	public String save(Book book) {

		System.out.println(book.getAuthor());
		books.put(3, book);

		return "Book Saved!";
	}

	public String update(Book book, int id) {

		try {
			Integer key = books.entrySet().stream().filter(t -> t.getValue().getId() == id).findFirst()
					.orElseThrow(() -> new Exception("hell")).getKey();
			books.put(key, book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "updated!";

	}

	public String delete(int id) {

		try {
			Integer key = books.entrySet().stream().filter(t -> t.getValue().getId() == id).findFirst()
					.orElseThrow(() -> new Exception("hell")).getKey();
			books.remove(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Deleted!";
	}

}
