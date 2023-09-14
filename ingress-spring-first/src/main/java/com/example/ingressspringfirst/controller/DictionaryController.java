package com.example.ingressspringfirst.controller;

import com.example.ingressspringfirst.model.request.DictionaryRequest;
import com.example.ingressspringfirst.model.response.DictionaryResponse;
import com.example.ingressspringfirst.service.DictionaryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/dictionaries")
@RequiredArgsConstructor
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @GetMapping("/{id}")
    public DictionaryResponse getDictionaryById(
            @RequestHeader(name = "user-Id") Long userId,
            @PathVariable Long id) {
        return dictionaryService.getDictionaryById(id);
    }

    @GetMapping
    public List<DictionaryResponse> getDictionaries(@RequestParam String category) {
        return dictionaryService.getDictionaries(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDictionary(@RequestBody DictionaryRequest dictionaryRequest) {
        dictionaryService.saveDictionary(dictionaryRequest);
    }

}
