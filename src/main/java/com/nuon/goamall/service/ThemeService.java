package com.nuon.goamall.service;

import com.nuon.goamall.model.Theme;
import com.nuon.goamall.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    public List<Theme> findByNames(List<String> names){
        return themeRepository.findByNames(names);
    }

    public Optional<Theme> findByName(String name) {
        return  themeRepository.findByName(name);
    }
}
