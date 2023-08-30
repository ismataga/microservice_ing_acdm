package com.example.ingressspringfirst.service;

import com.example.ingressspringfirst.model.DictionaryRequest;
import com.example.ingressspringfirst.model.DictionaryResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {
    public DictionaryResponse getDictionaryById(Long id){
        return new DictionaryResponse(id,"Mobile","Azecell");
    }

    public List<DictionaryResponse> getDictionaries(String category){
        return List.of(new DictionaryResponse(1L, "Mobile","Azercell"));
    }

    public void saveDictionary(DictionaryRequest dictionaryRequest){

    }
}
