package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.CityMinDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<CityMinDTO> findAll(String name, Pageable pageable) {
        Page<City> list = cityRepository.searchByName(name, pageable);
        return list.map(x -> new CityMinDTO(x));
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City Entity = new City();
        Entity.setName(dto.getName());
        Entity = cityRepository.save(Entity);
        return new CityDTO(Entity);
    }

}
