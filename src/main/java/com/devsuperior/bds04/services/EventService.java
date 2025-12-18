package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> list = eventRepository.findAll(pageable);
        return list.map(x -> new EventDTO(x));
    }

    @Transactional
    public EventDTO insert(EventDTO dto){
        Event Entity = new Event();
        Entity.setName(dto.getName());
        Entity.setDate(dto.getDate());
        Entity.setUrl(dto.getUrl());

        City city = cityRepository.getReferenceById(dto.getCityId());
        Entity.setCity(city);

        Entity = eventRepository.save(Entity);
        return new EventDTO(Entity);
    }
}
