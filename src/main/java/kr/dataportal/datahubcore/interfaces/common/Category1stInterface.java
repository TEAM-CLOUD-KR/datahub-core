package kr.dataportal.datahubcore.interfaces.common;

import kr.dataportal.datahubcore.domain.common.Category1st;

import java.util.List;

public interface Category1stInterface {
    Category1st findOne(String text);
    List<Category1st> findAll();
}
