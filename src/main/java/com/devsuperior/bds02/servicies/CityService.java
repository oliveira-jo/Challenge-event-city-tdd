package com.devsuperior.bds02.servicies;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.servicies.exceptions.DatabaseException;
import com.devsuperior.bds02.servicies.exceptions.ResourceNotFoundException;

@Service
public class CityService {

  private final CityRepository cityRepository;

  public CityService(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @Transactional(readOnly = true)
  public List<CityDTO> findAll() {
    List<City> result = cityRepository.findAll(Sort.by("name"));
    return result.stream().map(CityDTO::new).toList();
  }

  @Transactional
  public CityDTO insert(CityDTO dto) {
    var entity = cityRepository.save(new City(null, dto.getName()));
    return new CityDTO(entity);
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {
    if (!cityRepository.existsById(id)) {
      throw new ResourceNotFoundException("Product not found.");
    }
    try {
      cityRepository.deleteById(id);

    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Fail in reference integrity");

    }
  }

}
