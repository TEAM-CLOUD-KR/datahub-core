package kr.dataportal.datahubcore.implement.service.common;

import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.implement.repository.common.Category1stRepository;
import kr.dataportal.datahubcore.interfaces.common.Category1stInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Category1stService implements Category1stInterface {
    private final Category1stRepository category1stRepository;

    public Category1st findOne(String text) {
        return category1stRepository.findOne(text);
    }

    @Override
    public List<Category1st> findAll() {
        return category1stRepository.findAll();
    }
}
