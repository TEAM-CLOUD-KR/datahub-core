package kr.dataportal.datahubcore.implement.service.common;

import kr.dataportal.datahubcore.domain.common.Category2nd;
import kr.dataportal.datahubcore.implement.repository.common.Category2ndRepository;
import kr.dataportal.datahubcore.interfaces.common.Category2ndInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Category2ndService implements Category2ndInterface {
    private final Category2ndRepository category2ndRepository;

    public Category2nd findOne(String text) {
        return category2ndRepository.findOne(text);
    }
}
