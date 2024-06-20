package com.dictionaryapp.service.impl;

import com.dictionaryapp.config.CurrentUser;
import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {
    private final ModelMapper modelMapper;
    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    public WordServiceImpl(ModelMapper modelMapper, WordRepository wordRepository, LanguageRepository languageRepository, CurrentUser currentUser, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    @Override
    public void addWord(AddWordDto addWordDto) {
        Word word = modelMapper.map(addWordDto, Word.class);
        Language language = languageRepository.findByLanguageName(LanguageEnum.valueOf(addWordDto.getLanguage())).get();
        word.setLanguage(language);
        word.setAddedBy(userRepository.findByUsername(currentUser.username()).get());
        wordRepository.saveAndFlush(word);
    }
}
