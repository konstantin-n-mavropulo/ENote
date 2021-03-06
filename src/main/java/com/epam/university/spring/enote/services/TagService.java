package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Tag;

import java.util.Set;

/**
 * Interface for declaring special (not general CRUD's support operations of <code>Tag</code>
 * class); should be used for interactions with Spring.
 */
public interface TagService extends GenericService<Tag> {
    Set<Tag> getTagsByUserId(Integer userId);
}
