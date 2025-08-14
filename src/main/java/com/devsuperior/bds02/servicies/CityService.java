package com.devsuperior.bds02.servicies;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {

  private final CityRepository cityRepository;

  public CityService(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @Transactional
  public CityDTO insert(CityDTO dto) {
    var entity = cityRepository.save(new City(null, dto.getName()));
    return new CityDTO(entity);
  }

}
