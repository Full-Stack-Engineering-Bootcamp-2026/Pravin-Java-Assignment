package com.cdac.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {

	public List<String> getMessage() {

		return List.of("Be like water", "learn before earn", "stay hungry stay foolish");
	}

}
