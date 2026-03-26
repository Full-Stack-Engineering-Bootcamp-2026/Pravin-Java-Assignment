package com.cdac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.model.Book;
import com.cdac.service.BookService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@GetMapping("/books")
	public ResponseEntity<?> getBooks() {

		return ResponseEntity.ok(bookService.findAll());

	}

	@PutMapping("/books/{id}")
	public ResponseEntity<?> update(@RequestBody Book book, @PathVariable int id) {

		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.update(book, id));

	}

	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {

		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.delete(id));

	}

	@PostMapping("/book")
	public ResponseEntity<?> save(@RequestBody Book book) {

		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));

	}

}
