package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.service.LanguageService;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void registerLanguage(Language language) {
        languageRepository.saveAndFlush(language);
    }

    @Override
    public long getCount() {
        return languageRepository.count();
    }
}
