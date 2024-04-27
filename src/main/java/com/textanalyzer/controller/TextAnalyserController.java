package com.textanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.textanalyzer.data.TextData;
import com.textanalyzer.service.TextAnalyseService;

@RestController
public class TextAnalyserController {

	@Autowired
	TextAnalyseService textAnalyseService;

	@PostMapping("/analyze")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String analyze(@RequestParam String text) {
		return textAnalyseService.analyzed(text);
	}

	@GetMapping("/longest-word")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String getLongetWord() {
		return TextData.longestWord;
	}
}
