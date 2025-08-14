package com.devsuperior.bds02.servicies;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.servicies.exceptions.ResourceNotFoundException;

@Service
public class EventService {

  private final EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Transactional
  public EventDTO update(Long id, EventDTO dto) {
    var entity = eventRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Resource not found."));

    entity.setName(dto.getName());
    entity.setDate(dto.getDate());
    entity.setUrl(dto.getUrl());
    entity.setCity(new City(dto.getCityId(), null));

    eventRepository.save(entity);

    return new EventDTO(entity);
  }

}
