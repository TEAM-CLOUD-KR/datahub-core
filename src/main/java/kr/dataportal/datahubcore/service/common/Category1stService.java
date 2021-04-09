package kr.dataportal.datahubcore.service.common;

import kr.dataportal.datahubcore.domain.common.Category1st;
import kr.dataportal.datahubcore.repository.common.Category1stRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Category1stService {
    private final Category1stRepository category1stRepository;

    public Category1st findOne(String text) {
        return category1stRepository.findOne(text);
    }
}
