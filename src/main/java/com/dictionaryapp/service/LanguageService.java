package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Language;

public interface LanguageService {
    void registerLanguage(Language language);
    long getCount();
}
