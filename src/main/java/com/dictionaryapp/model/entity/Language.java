package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity{
    //•	Has an Id – "UUID-String" or Long
    //•	Has a Language name (unique, not null)
    //o	an option between (GERMAN, SPANISH, FRENCH, ITALIAN)
    //•	Has a Description  (not null)
    //o	For GERMAN - "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe."
    //o	For SPANISH  – "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure."
    //o	For FRENCH – "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature."
    //o	For ITALIAN - "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history."
    //•	Has collection of Words
    //o	One language may have many words, but one word has only one language.
    //Implement the entities with the correct data types and implement repositories for them.

    public Language(LanguageEnum languageName, String description) {
        this.languageName = languageName;
        this.description = description;
    }
    public Language(){

    }

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LanguageEnum languageName;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "language")
    private Set<Word> words;

}
