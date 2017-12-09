package com.epam.university.spring.enote.services.impls;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.services.DBService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotepadService implements DBService<Notepad> {

  private final GenericDao<Notepad> notepadRepository;

  @Autowired
  public NotepadService(
      GenericDao<Notepad> notepadRepository) {
    this.notepadRepository = notepadRepository;
  }

  @Override
  public Notepad getById(Integer id) {
    return null;
  }

  @Override
  public List<Notepad> getAll() {
    return null;
  }

  @Override
  public Notepad save(Notepad element) {
    return null;
  }

  @Override
  public List<Notepad> saveAll(List<Notepad> elements) {
    return null;
  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }

  @Override
  public boolean deleteAll() {
    return false;
  }
}
