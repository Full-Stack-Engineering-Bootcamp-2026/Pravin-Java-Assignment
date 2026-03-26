package com.cdac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdac.repository.MessageRepository;

@Service
public class GreetService {

	private MessageRepository messageRepository;

	public GreetService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public String greet(String name) {
		return "hello" + name;

	}

	public List<String> messages() {
		return messageRepository.getMessage();
	}

}
