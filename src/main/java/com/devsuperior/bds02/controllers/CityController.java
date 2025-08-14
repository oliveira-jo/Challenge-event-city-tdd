package com.devsuperior.bds02.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.servicies.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

  private final CityService cityService;

  public CityController(CityService cityService) {
    this.cityService = cityService;
  }

  @GetMapping
  public ResponseEntity<List<CityDTO>> findAll() {
    return ResponseEntity.ok().body(cityService.findAll());
  }

  @PostMapping
  public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto) {
    dto = cityService.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    cityService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
