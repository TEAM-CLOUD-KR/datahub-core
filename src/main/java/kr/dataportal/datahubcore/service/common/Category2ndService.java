package kr.dataportal.datahubcore.service.common;

import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.repository.common.Category2ndRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Category2ndService {
    private final Category2ndRepository category2ndRepository;

    public Category2nd findOne(String text) {
        return category2ndRepository.findOne(text);
    }
}
